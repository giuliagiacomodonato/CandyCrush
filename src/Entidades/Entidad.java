package Entidades;


import java.util.HashSet;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Interfaces.Detonable;
import Interfaces.Enfocable;
import Interfaces.EntidadGrafica;
import Interfaces.EntidadLogica;
import Interfaces.Intercambiable;
import Interfaces.Matcheable;
import Interfaces.Ocultable;
import Logica.Tablero;

public abstract class Entidad implements Matcheable,Enfocable,Intercambiable,EntidadLogica,Detonable,Ocultable {
    protected Color color;
    protected boolean enfocada;
    protected boolean visible;
    protected String [] imagenes_representativas;
    protected String path_img;
    protected int puntaje;
    protected EntidadGrafica entidad_grafica;
    protected int fila;
    protected int columna;
    protected Tablero tablero;
    protected boolean detonada;
    
	protected static final int PUNTAJE_BOMBA = 150;
	protected static final int PUNTAJE_RAYADOH = 45;
	protected static final int PUNTAJE_RAYADOV = 35;
	protected static final int PUNTAJE_RAYADOD = 100;
	protected static final int PUNTAJE_ENVUELTO = 50;
	protected static final int PUNTAJE_GELATINA = 10;
	protected static final int PUNTAJE_GLASEADO = 25;
    
    public Entidad(Tablero t, Color c, String path_img, boolean visible) {
        tablero = t;
    	color = c;
        this.visible = visible;
        cargar_imagenes(path_img);
        this.path_img = path_img;
        detonada = false;
    }   
    
	public abstract HashSet<Entidad> obtener_entidades_afectadas_por_detonacion();

    protected String obtener_path() {
    	return path_img;
    }
    
    public Color obtener_color(){
        return color;
    }
    
    public int obtener_columna() {
    	return columna;
    }
    
    public int obtener_fila() {
    	return fila;
    }
    
    public boolean get_enfocada() {
    	return enfocada;
    }
    
    public boolean esta_detonada() {
    	return detonada;
    }
    
	public void cambiar_posicion(int nueva_fila, int nueva_columna) {
		fila = nueva_fila;
		columna = nueva_columna;
		entidad_grafica.notificarse_intercambio();
	}
    
    protected void intercambiar_entidad_y_entidad(Entidad origen, Entidad destino) {
		int nueva_fila_origen = destino.obtener_fila();
		int nueva_columna_origen = destino.obtener_columna();
		destino.cambiar_posicion(origen.obtener_fila(), origen.obtener_columna());
		origen.cambiar_posicion(nueva_fila_origen, nueva_columna_origen);
		tablero.reubicar(origen);
		tablero.reubicar(destino);
	}
    
    protected void intercambiar_caramelo_y_gelatina(Caramelo caramelo, Gelatina gelatina) {
		Caramelo entidad_interno_gelatina = gelatina.obtener_caramelo_interno();
		int nueva_fila_caramelo = gelatina.obtener_caramelo_interno().obtener_fila();
		int nueva_columna_caramelo = gelatina.obtener_caramelo_interno().obtener_columna();
		int nueva_fila_caramelo_interno = caramelo.obtener_fila();
		int nueva_columna_caramelo_interno = caramelo.obtener_columna();
		
		entidad_interno_gelatina.cambiar_posicion(nueva_fila_caramelo_interno, nueva_columna_caramelo_interno);
		caramelo.cambiar_posicion(nueva_fila_caramelo, nueva_columna_caramelo);
		gelatina.establecer_caramelo_interno(caramelo);
		tablero.reubicar(entidad_interno_gelatina);
    }
    
    public void establecer_entidad_grafica(EntidadGrafica e) {
    	entidad_grafica = e;
    }
    
    public boolean obtener_visibilidad() {
    	return visible;
    }
 
  
    public boolean caer() {
    	boolean cai = false;
        if (detonada && tablero.en_rango(fila + 1, columna) && tablero.obtener(fila + 1, columna).esta_detonada()) {
        	detonada = false;
            cambiar_posicion(fila + 1, columna);
            cai = true;
        }
        if (!detonada && fila == -1) {
        	detonada = false;
        	while(tablero.en_rango(fila+1, columna) && tablero.obtener(fila+1, columna).esta_detonada()) {
                cambiar_posicion(fila + 1, columna);
        	}
            cai = true;
        }
       
        return cai;
    }

    public void enfocar() {
   		enfocada = true;
   		entidad_grafica.notificarse_cambio_foco();
   	}
   	
   	public void desenfocar() {
   		enfocada = false;
   		entidad_grafica.notificarse_cambio_foco();
   	
   	}
   	
   	public void establecer_detonacion(boolean d) {
   		detonada = d;
   	}
   	
   	public void detonar() {
   		detonada = true;
   		entidad_grafica.notificarse_detonacion();
   	}
   	
   	public void mostrar() {
		visible = true;
		entidad_grafica.notificarse_cambio_visibilidad();
	}  	
  
	public void ocultar() {
		visible = false;
		entidad_grafica.notificarse_cambio_visibilidad();
	}
    
    public String obtener_imagen() {
    	int indice = 0;
    	indice += (enfocada ? 1 : 0);
    	indice += (detonada ? 2 : 0);
    	return imagenes_representativas[indice];
    }
    
    protected void cargar_imagenes(String path_img) {
		imagenes_representativas = new String [4];
		imagenes_representativas[0] = path_img + color.toString() + ".png";
		imagenes_representativas[1] = path_img + color.toString() +"-resaltado.png";
		imagenes_representativas[2] = path_img + "explosion.gif";
		imagenes_representativas[3] = path_img + "explosion.gif";
    }
   
    public Entidad agregarse_a_tracker() {
    	return this;
    }
 
    public String toString() {
    	return Constantes.ENTIDAD;
    }
    
    public void tick(ManejadorEntidades m) {
    	
    }
    
    public abstract int obtener_puntaje();
    
    public void asociar_entidad_grafica(EntidadGrafica e) {
    	entidad_grafica = e;
    }
    
 	public abstract boolean reacciona_cuando_rompe_vecino();
	
 	protected HashSet<Entidad> obtener_entidades_adyacentes_afectadas_por_detonacion(){
		HashSet<Entidad> entidades_adyacentes_afectadas = new HashSet<Entidad>();
		
		if(tablero.en_rango(fila-1, columna) && tablero.obtener(fila-1, columna).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila-1, columna));
		
		if(tablero.en_rango(fila+1, columna) && tablero.obtener(fila+1, columna).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila+1, columna));
		
		if(tablero.en_rango(fila, columna-1) && tablero.obtener(fila, columna-1).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila, columna-1));
		
		if(tablero.en_rango(fila, columna+1) && tablero.obtener(fila, columna+1).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila, columna+1));
		return entidades_adyacentes_afectadas;
 	}   

	public void establecer_posicion(int f, int c) {
		fila = f;
		columna = c;
	}
	
}