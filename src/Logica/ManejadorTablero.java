package Logica;


import java.util.HashMap;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import Combinaciones.ComparadorCombinacion;
import Combinaciones.Combinacion;
import Interfaces.ManejadorOperaciones;
import Vista.Celda;
import Entidades.Entidad;

public class ManejadorTablero implements ManejadorOperaciones{	
	protected Tablero tablero;
	protected int fila_jugador;
	protected int columna_jugador;
	protected Juego juego;
	protected Fabrica fabrica;

	protected List<Combinacion> lista_reglas;
	
	public ManejadorTablero(Juego j, Fabrica f) {
		juego = j;
		lista_reglas = new LinkedList<Combinacion>();
		
		fabrica = f;
	}

	public Tablero crear_tablero(int fila, int columna) {
		tablero = new Tablero(fila,columna);
		return tablero;
	}
	
	public Tablero obtener_tablero() {
		return tablero;
	}
	
	public Fabrica obtener_fabrica() {
		return fabrica;
	}
	
	public int cantidad_filas_tablero() {
		return tablero.cant_filas();
	}
	public int cantidad_columnas_tablero() {
		return tablero.cant_columnas();
	}
	
	public void establecer_reglas(List<Combinacion> l) {
		lista_reglas = l;
		lista_reglas.sort(new ComparadorCombinacion<Combinacion>());
	}
	
	
	public void fijar_jugador(int fila_destino, int columna_destino) {
		if (en_rango(fila_destino,columna_destino)) {
			tablero.obtener(fila_destino, columna_destino).enfocar();
			tablero.obtener(fila_jugador, columna_jugador).desenfocar();
			fila_jugador = fila_destino;
			columna_jugador = columna_destino;
		}
	}
	
	private void intercambiar_entidades_y_transicionar(int fila_destino, int columna_destino) {
		int fila_origen = fila_jugador;
		int columna_origen = columna_jugador;
		Entidad entidad_origen, entidad_destino;
		EfectosDeTransicion efecto_intercambio;
		if (en_rango(fila_destino, columna_destino)) {	
			entidad_origen = tablero.obtener(fila_origen, columna_origen);
			entidad_destino = tablero.obtener(fila_destino, columna_destino);
			if (entidad_origen.es_posible_intercambiar(entidad_destino)) {
				entidad_origen.intercambiar(entidad_destino);
				efecto_intercambio = calcular_efectos_por_intercambio(fila_origen, columna_origen, fila_destino, columna_destino);
				
				if (efecto_intercambio.existen_entidades_a_detonar()) {
					
					transicionar_proximo_estado(efecto_intercambio);
					juego.disminuir_movimientos();
					fijar_jugador(fila_destino, columna_destino);
					
				}else{
					entidad_origen = tablero.obtener(fila_origen, columna_origen);
					entidad_destino = tablero.obtener(fila_destino, columna_destino);
					
					entidad_origen.intercambiar(entidad_destino);
				}
			}
		}
		else {
			fijar_jugador(fila_origen, columna_origen);
		}
	}

 
	protected EfectosDeTransicion calcular_efectos_por_intercambio(int fila_origen, int columna_origen, int fila_destino, int columna_destino) {
		EfectosDeTransicion efecto_transicion = new EfectosDeTransicion();
		Entidad entidad_destino = tablero.obtener(fila_destino, columna_destino);
		Entidad entidad_origen = tablero.obtener(fila_origen, columna_origen);
		
		if(entidad_destino.puede_explotar_dos(entidad_origen)) {
			EfectosDeTransicion efectos_por_explotar_dos = calcular_efectos_por_explotar_dos(entidad_destino, entidad_origen);
			efecto_transicion = agregar_sin_repetidos(efecto_transicion,efectos_por_explotar_dos);
		}
		else {
			EfectosDeTransicion efectos_regla = new EfectosDeTransicion();
			EfectosDeTransicion efectos_regla2 = new EfectosDeTransicion();
			for(Combinacion regla: lista_reglas) {
				efectos_regla = regla.calcular_efectos_regla_en(entidad_origen, entidad_destino);
				if(efectos_regla.existen_entidades_a_detonar()) {
					break;
				}
			}
			for(Combinacion regla: lista_reglas) {
				efectos_regla2 = regla.calcular_efectos_regla_en(entidad_origen, entidad_destino);
				if(efectos_regla2.existen_entidades_a_detonar()) {
					break;
				}
			}
			efectos_regla = agregar_sin_repetidos(efectos_regla, efectos_regla2);
			efecto_transicion = agregar_sin_repetidos(efecto_transicion, efectos_regla);
			EfectosDeTransicion efectos_detonacion_por_entidad = new EfectosDeTransicion();
			for(Entidad entidad : efecto_transicion.entidades_a_detonar()) {
	            for(Entidad ent : entidad.obtener_entidades_afectadas_por_detonacion()) {
	                efectos_detonacion_por_entidad.agregar_entidad_a_detonar_y_reemplazar(ent);
	                for(Entidad e : ent.obtener_entidades_afectadas_por_detonacion()) {
	                    efectos_detonacion_por_entidad.agregar_entidad_a_detonar_y_reemplazar(e);
	                }
	            }
	        }
			efecto_transicion = agregar_sin_repetidos(efecto_transicion, efectos_detonacion_por_entidad);
		}
		return efecto_transicion;
	}
	
	protected EfectosDeTransicion calcular_efectos_por_explotar_dos(Entidad entidad_origen,Entidad entidad_destino) {
		EfectosDeTransicion efectos_por_explotar_dos = new EfectosDeTransicion();
		
		Set<Entidad> lista_entidades_afectadas_entidad_origen = entidad_origen.obtener_entidades_afectadas_por_detonacion();
		for(Entidad entidad : lista_entidades_afectadas_entidad_origen)
			efectos_por_explotar_dos.agregar_entidad_a_detonar_y_reemplazar(entidad);
		
		Set<Entidad> lista_entidades_afectadas_entidad_destino = entidad_destino.obtener_entidades_afectadas_por_detonacion();
		for(Entidad entidad : lista_entidades_afectadas_entidad_destino)
			efectos_por_explotar_dos.agregar_entidad_a_detonar_y_reemplazar(entidad);
		efectos_por_explotar_dos.agregar_entidad_a_detonar_y_reemplazar(entidad_origen);
        efectos_por_explotar_dos.agregar_entidad_a_detonar_y_reemplazar(entidad_destino);
		return efectos_por_explotar_dos;
	}
	
	protected EfectosDeTransicion agregar_sin_repetidos(EfectosDeTransicion efectos_transicion, EfectosDeTransicion efectos_con_repetidos) {
		EfectosDeTransicion efectos_sin_repetidos = efectos_transicion;
		Set<Entidad> entidades_sin_repeticion = new LinkedHashSet<Entidad>();
		
		for(Entidad entidad : efectos_con_repetidos.entidades_a_detonar())
			entidades_sin_repeticion.add(entidad);
		
		for(Entidad entidad: entidades_sin_repeticion)
			efectos_sin_repetidos.agregar_entidad_a_detonar_y_reemplazar(entidad);
			
		for(Entidad entidad : efectos_con_repetidos.entidades_a_incorporar())
			efectos_sin_repetidos.agregar_entidad_de_reemplazo(entidad);
			
		return efectos_sin_repetidos;
	}
	
	synchronized protected void transicionar_proximo_estado(EfectosDeTransicion efecto_transicion) {
		Map<Integer,List<Entidad>> entidades_nuevas;
		List<Entidad> entidades_a_detonar = efecto_transicion.entidades_a_detonar();
		List<Entidad> entidades_a_incorporar = efecto_transicion.entidades_a_incorporar();
		List<Entidad> entidades_a_reemplazar = efecto_transicion.entidades_a_reemplazar();
		List<Entidad> entidades_a_trackear = new LinkedList<Entidad>();
		
		
		detonar(entidades_a_detonar);
		agregar_entidades_nuevas(entidades_a_incorporar);
		
		entidades_nuevas = aplicar_caida_y_reubicar(entidades_a_reemplazar);
		
		for(Entidad e: entidades_a_detonar) {
			if(!entidades_a_trackear.contains(e))
				entidades_a_trackear.add(e.agregarse_a_tracker());
		}
		
		juego.trackear(entidades_a_trackear);
		juego.actualizar_puntaje(entidades_a_detonar);
		buscar_combinaciones_restantes(entidades_nuevas);
	
		fijar_jugador(fila_jugador, columna_jugador);
	}
	
	private void buscar_combinaciones_restantes(Map<Integer,List<Entidad>> entidades_nuevas) {
		EfectosDeTransicion efectos = new EfectosDeTransicion();
		EfectosDeTransicion efecto_afectadas =  new EfectosDeTransicion();
		List<Entidad> entidades_a_buscar = new LinkedList<Entidad>();
	
		for (int col = 0; col < tablero.cant_columnas(); col++) {
			List<Entidad> entidades_nuevas_columna = entidades_nuevas.get(col);
			if (entidades_nuevas_columna.size() > 0){
				for (int fila = 0; fila <tablero.cant_filas(); fila++) {
					Entidad nueva = tablero.obtener(fila, col);
					if (!nueva.esta_detonada()) {
						entidades_a_buscar.add(nueva);
					}
	                   
				}
			}
		}
		boolean encontre = false;
		for(Combinacion regla: lista_reglas) {
			efecto_afectadas =  new EfectosDeTransicion();
			efectos = new EfectosDeTransicion();
			for(Entidad entidad_a_buscar: entidades_a_buscar) {
				efectos = regla.calcular_efectos_regla_en(entidad_a_buscar);
				if(efectos.existen_entidades_a_detonar()) {
					for(Entidad e: efectos.entidades_a_detonar()) {
						efecto_afectadas.entidades_a_detonar().addAll(e.obtener_entidades_afectadas_por_detonacion());	
					}	
					if(efectos.existen_entidades_a_detonar()) {
						agregar_sin_repetidos(efectos, efecto_afectadas);
						transicionar_proximo_estado(efectos);
						encontre = true;
						efecto_afectadas =  new EfectosDeTransicion();
						efectos = new EfectosDeTransicion();
					}
				}
			}
			if(encontre) {
				efecto_afectadas =  new EfectosDeTransicion();
      			efectos = new EfectosDeTransicion();
      			break;
			}
		}
	}

	protected Map<Integer,List<Entidad>>  aplicar_caida_y_reubicar(List<Entidad> entidades_a_reemplazar) {
		int filaDestino =0;
		Map<Integer,List<Entidad>> entidadesNuevasPorColumna;
				    for (int i = 0; i < tablero.cant_columnas(); i++) {
				         filaDestino = tablero.cant_filas() - 1;  // Comenzar desde la parte inferior de la columna
				        for (int j = tablero.cant_filas() - 1; j >= 0; j--) {
				            if (!tablero.obtener(j, i).esta_detonada()) {
				                tablero.obtener(j, i).caer();
				                Entidad e = tablero.obtener(j, i);
				                e.cambiar_posicion(filaDestino, i);
				                tablero.insertar(filaDestino, i, e);
				                filaDestino--;
				            }
				        }
				    }
				    entidadesNuevasPorColumna =   caer(entidades_a_reemplazar, filaDestino);
				    return entidadesNuevasPorColumna;
				}

	private Map<Integer,List<Entidad>> caer(List<Entidad> entidades_a_reemplazar, int filaDestino) {
		 List<Entidad> entidades_nuevas;
		 int fila = -1;
		 int columna;
		 Map<Integer,List<Entidad>> entidades_nuevas_por_columna = new HashMap<Integer,List<Entidad>>();
		 for(int i = 0; i < tablero.cant_columnas();i++) {
			 entidades_nuevas_por_columna.put(i,new LinkedList<>());
		 }
		    
		 for(Entidad e : entidades_a_reemplazar) {
			 columna = e.obtener_columna();
			 Entidad entidad_nueva = fabrica.fabricar_caramelo_aleatorio(tablero);
			 entidades_nuevas = entidades_nuevas_por_columna.get(e.obtener_columna());
			 entidades_nuevas.add(entidad_nueva);
		    	
			 fila = entidades_nuevas.size()*(-1);
			 entidad_nueva.establecer_posicion(fila, columna);
			 juego.asociar_entidad_logica_y_grafica(entidad_nueva);
		 }
		    
		 for(int i = 0; i < tablero.cant_columnas();i++) {
			 entidades_nuevas = entidades_nuevas_por_columna.get(i);
			 if(!entidades_nuevas.isEmpty()) {
				 for(int j = 0; j < entidades_nuevas.size();j++) {
					 for(Entidad e: entidades_nuevas) {
						 fila = e.obtener_fila();
						 if(fila < tablero.cant_filas()-1)
							 e.cambiar_posicion(e.obtener_fila()+1, e.obtener_columna());
			    		}
			    	}
		    		for(Entidad e: entidades_nuevas) {
		    			tablero.insertar(e.obtener_fila(), e.obtener_columna(), e);
		    		}
			 }

		 }
		 return entidades_nuevas_por_columna;
	}


	protected void detonar(List<Entidad> entidades_a_detonar) {
		for(Entidad e: entidades_a_detonar) {
			e.detonar();
		}
	}
	
	protected void agregar_entidades_nuevas(List<Entidad> entidades_a_incorporar) {
		for(Entidad e: entidades_a_incorporar) {
			tablero.insertar(e.obtener_fila(), e.obtener_columna(), e);
			juego.asociar_entidad_logica_y_grafica(e);
			e.mostrar();
		
		}
	}
	
	public void asociar_entidades_logicas_y_graficas() {
		Entidad e;
		Celda cel;
		for (int f=0; f<tablero.cant_filas(); f++) {
			for (int c=0; c<tablero.cant_columnas(); c++) {
				e = tablero.obtener(f, c);
				cel = juego.obtener_ventana().agregar_entidad(e);
				e.asociar_entidad_grafica(cel);
				cel.setVisible(true);
			}
		}
		for(Entidad entidad_a: tablero.obtener_entidades_asociadas()) {
			juego.asociar_entidad_logica_y_grafica(entidad_a);
		}
	}

	private boolean en_rango(int fila, int columna) {
		boolean en_rango_fila = (0 <= fila) && (fila < tablero.cant_filas());
		boolean en_rango_columna = (0 <= columna) && (columna < tablero.cant_columnas());
		return (en_rango_fila && en_rango_columna);
	}
	
	public void mover_jugador(int direccion) {
		switch(direccion) {
			case Juego.ABAJO:{
				mover_jugador_aux(fila_jugador + 1, columna_jugador);
				break;
			}
			case Juego.ARRIBA:{
				mover_jugador_aux(fila_jugador - 1, columna_jugador);
				break;
			}
			case Juego.IZQUIERDA:{
				mover_jugador_aux(fila_jugador, columna_jugador - 1);
				break;
			}
			case Juego.DERECHA:{
				mover_jugador_aux(fila_jugador, columna_jugador + 1);
				break;
			}
		}
	}
	
	private void mover_jugador_aux(int fila_destino, int columna_destino) {
		if (en_rango(fila_destino,columna_destino)) {
			tablero.obtener(fila_destino, columna_destino).enfocar();
			tablero.obtener(fila_jugador, columna_jugador).desenfocar();
			fila_jugador = fila_destino;
			columna_jugador = columna_destino;
		
		}
	}

	public void intercambiar_entidades(int direccion) {
        switch(direccion) {
            case Juego.ABAJO:{
                if (en_rango(fila_jugador + 1, columna_jugador)) {
                    intercambiar_entidades_y_transicionar(fila_jugador + 1, columna_jugador);
                    break;
                }
            }
            case Juego.ARRIBA:{
                if (en_rango(fila_jugador - 1, columna_jugador)) {
                    intercambiar_entidades_y_transicionar(fila_jugador - 1, columna_jugador);
                    break;
                }
            }
            case Juego.IZQUIERDA:{
                if (en_rango(fila_jugador, columna_jugador - 1)) {
                    intercambiar_entidades_y_transicionar(fila_jugador, columna_jugador - 1);
                    break;
                }
            }
            case Juego.DERECHA:{
                if (en_rango(fila_jugador, columna_jugador + 1)) {
                    intercambiar_entidades_y_transicionar(fila_jugador, columna_jugador + 1);
                    break;
                }
            }
        }
    }
	
	
}
