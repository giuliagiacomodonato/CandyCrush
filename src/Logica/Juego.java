package Logica;


import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.*;

import Auxiliares.Constantes;
import Entidades.Entidad;
import Entidades.ManejadorEntidades;
import Interfaces.EntidadGrafica;
import Interfaces.ManejadorOperaciones;
import Interfaces.UsuarioReloj;
import Vista.Ventana;

public class Juego implements UsuarioReloj {
	public static final int ARRIBA = 15000;
	public static final int ABAJO = 15001;
	public static final int IZQUIERDA = 15002;
	public static final int DERECHA = 15003;

    private ManejadorOperaciones manejador_tablero;
    private int vidas;
    private Nivel nivel;
    private int nNivel;
    private Ventana mi_ventana;
    private int movimientos;
    private Jugador jugador; 
    private TrackerObjetivo tracker_objetivo;
    private TrackerPuntaje tracker_puntaje;
    private RankingJugadores ranking_jugadores;
    private ManejadorEntidades manejador_entidades;
    private GeneradorNivel generador;
    
    public Juego(String nombreJugador, String version) {
    	ranking_jugadores = new RankingJugadores();
    	Fabrica fabrica = new Fabrica(version);
    	generador = new GeneradorNivel(fabrica);
		try {
			FileInputStream fileInputStream = new FileInputStream(Constantes.RANKING_ARCHIVO);
		    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		    ranking_jugadores = (RankingJugadores) objectInputStream.readObject();
		    objectInputStream.close();
		}
		catch(FileNotFoundException e) {
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		manejador_tablero = new ManejadorTablero(this, fabrica);
    	mi_ventana = new Ventana(this, 6, 6);
    	
    	mi_ventana.establecer_version(version);
    	jugador = new Jugador(nombreJugador);
    	tracker_puntaje = new TrackerPuntaje();
    	nNivel = 1;
    	vidas = 3;
    	
    }

   public Ventana obtener_ventana() {
	   return mi_ventana;
   }
   

   public RankingJugadores obtener_ranking() {
	   return ranking_jugadores;
   }
   
	public void trackear(List<Entidad> l) {
		tracker_objetivo.track(l);
	}
   
	public void actualizar_puntaje(List<Entidad> l) {
		tracker_puntaje.track(l);
	}
	
   public ManejadorOperaciones obtener_manejador() {
	   return manejador_tablero;
   }
   
    private void asociar_entidades_logicas_y_graficas() {
		manejador_tablero.asociar_entidades_logicas_y_graficas();
		mi_ventana.setVisible(true);
	}
    
    public void asociar_entidad_logica_y_grafica(Entidad entidad_logica) {
		EntidadGrafica entidad_grafica = mi_ventana.agregar_entidad(entidad_logica);
		entidad_logica.asociar_entidad_grafica(entidad_grafica);
		entidad_grafica.notificarse_cambio_visibilidad();
	}
	
	/**
	 * Mueve una entidad
	 * @param d Direccion en la que se mueve
	 * @return Verdadero si puedo mover la entidad falso caso contrario
	 */
    public void mover_entidad(int d) {
    	manejador_tablero.intercambiar_entidades(d);;
	}

	/**
	 * Notifica a la ventana de finalizar el nivel
	 */
	public void tiempo_terminado() {
		finalizar_nivel(false);
	}
	
	/**
	 * Notifica a la ventana de la actualizacion del tiempo
	 * @param minutos nuevos minutos 
	 * @param segundos nuevos segundos
	 */
	public void actualizar_tiempo(int minutos, int segundos) {
		mi_ventana.actualizar_tiempo(minutos, segundos);
	}
	
	/**
	 * Actualizar las vidas del jugador
	 * Si el jugador queda con 0 vidas pierde el nivel y notifica a la ventana 
	 */
	public void actualizar_vidas() {
		vidas--;
		if(vidas == 0) {
			mi_ventana.agote_vidas();
		}
		mi_ventana.actualizar_vidas(vidas);
	}

	/**
	 * Finalizar el nivel notificando a la ventana
	 * @param superado Verdadero si supero el nivel falso caso contrario
	 */

	public void finalizar_nivel(boolean superado) {
		mi_ventana.finalizar_nivel(superado);
		nivel.terminar();
		if(!superado) {
			actualizar_vidas();
		}
		if(superado && nNivel == Constantes.CANT_NIVELES) {
			agregar_jugador_a_ranking();
			mi_ventana.niveles_superados();
		}
	
	}
	
	public void agregar_jugador_a_ranking() {
		ranking_jugadores.agregar_jugador(jugador, tracker_puntaje.obtener_puntaje());
	}

	public void abandonar() {

		mi_ventana.mostar_menu();
		manejador_entidades.detener();
		mi_ventana.ocultar_ventana_fin_nivel();
		nivel.terminar();
		
	}
	

    
    public void mover_cursor(int d) {
    	manejador_tablero.mover_jugador(d);
    	
	}
    
    public void iniciar_nivel(int n) {
    	nNivel = n;
    	mi_ventana.limpiar_vista();
    	tracker_objetivo = new TrackerObjetivo();
    	generar_nivel(n);
    	
    	manejador_entidades = new ManejadorEntidades(this);
    	asociar_entidades_logicas_y_graficas();
    	
    	manejador_tablero.fijar_jugador(nivel.get_fila_inicial_jugador(), nivel.get_columna_inicial_jugador());
    	movimientos = nivel.obtener_movimientos();
    	
    	mi_ventana.actualizar_movimientos(movimientos);
    	mi_ventana.actualizar_vidas(vidas);
		
    	nivel.iniciar();
    	manejador_entidades.iniciar();
    	
    }
    
    private void generar_nivel(int n) {
    	generador.generarNivel(n);
    	generador.crear_tablero(this,manejador_tablero);
    	nivel = generador.cargarEntidades(manejador_tablero.obtener_tablero(),this);
    	generador.cargar_reglas(manejador_tablero);
    	generador.cargar_movimientos(nivel);
    	generador.cargar_objetivos(nivel, tracker_objetivo);
    	generador.cargar_tiempo(nivel);
    	
    }
    
    public void disminuir_movimientos() {
    	movimientos--;
    	mi_ventana.actualizar_movimientos(movimientos);
    	if(movimientos == 0) {
    		finalizar_nivel(false);
    	}
    }
    
    public void actualizar_objetivos(int cantidad, Objetivo o) {
    	mi_ventana.actualizar_objetivos(cantidad, o);
    }
    
    public void establecer_objetivos(List<Objetivo> obj) {
    	for(Objetivo o: obj) {
    		mi_ventana.cargar_objetivo(o);
    	}
    }
    
    public Nivel obtener_nivel_actual() {
    	return nivel;
    }
   
    public int obtener_nro_nivel_actual() {
    	return nNivel;
    }
   
    public void establecer_nivel_actual(int n) {
    	nNivel = n;
    }
}
