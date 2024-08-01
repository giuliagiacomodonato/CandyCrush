package Entidades;

import java.util.HashSet;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Logica.Tablero;

public class DobleRayado extends Potenciador {

	public DobleRayado(Tablero tablero,Color c,String v) {
		super(tablero,c,v);
	}

	protected void cargar_imagenes(String path_img) {
		imagenes_representativas = new String [4];
		imagenes_representativas[0] = path_img + color.toString() + "-dobleRayado.png";
		imagenes_representativas[1] = path_img + color.toString() + "-dobleRayado" + "-resaltado.png";
		imagenes_representativas[2] = path_img + "explosion.gif";
		imagenes_representativas[3] = path_img + "explosion.gif";
	 }
	
	 public String toString() {
	   	return Constantes.DOBLERAYADO;
	 }
	 
	 public int obtener_puntaje() {
	  	return puntaje;
	}
	 
	public void detonar() {
		detonada = true;
		entidad_grafica.notificarse_detonacion();		
	}
	    
	public HashSet<Entidad> obtener_entidades_afectadas_por_detonacion(){
		HashSet<Entidad> afectadas = new HashSet<Entidad>(); 	
	 	for(Entidad e : obtener_entidades_adyacentes_afectadas_por_detonacion()) {
	 		afectadas.add(e);
	 	}
	 	for(int i = 0; i < tablero.cant_filas();i++) {
	 		if(i != fila && !tablero.obtener(i, columna).esta_detonada()) {
	 			afectadas.add(tablero.obtener(i, columna));
	 		}
	 	}
	 	for(int j = 0; j < tablero.cant_columnas();j++) {
	 		if(j != columna && !tablero.obtener(fila, j).esta_detonada())
				afectadas.add(tablero.obtener(fila, j));
	 	}
	 	
	 	return afectadas;

	 }
		
	public boolean se_produce_match_con(Entidad e) {
		return e.aplica_match_con(this);
	}
	
	public boolean aplica_match_con(Caramelo caramelo) {
		return color == caramelo.obtener_color();
	}
		
	public boolean aplica_match_con(Rayado rayado) {
		return color == rayado.obtener_color();
	}
	
	public boolean aplica_match_con(Envuelto envuelto) {
		return color == envuelto.obtener_color();
	}
	
	public boolean aplica_match_con(DobleRayado dobleRayado) {
		return color == dobleRayado.obtener_color();
	}
		
	public boolean aplica_match_con(Glaseado glaseado) {
		return false;
	}
		
	public boolean aplica_match_con(Bomba b) {
		return false;
	}
		
	public boolean aplica_match_con(Gelatina gelatina) {
		return gelatina.obtener_caramelo_interno().aplica_match_con(this);
	}
	
	//Operaciones de detonacion
		
	public boolean puede_explotar_dos(Entidad e) {
		return e.explotan_dos(this);
	}

	public boolean explotan_dos(Caramelo caramelo) {
		return color == caramelo.obtener_color();
	}	
	
	public boolean explotan_dos(Envuelto envuelto) {
		return color == envuelto.obtener_color();
	}
		
	public boolean explotan_dos(Rayado rayado) {
		return color == rayado.obtener_color();
	}
	
	public boolean explotan_dos(DobleRayado dobleRayado) {
		return color == dobleRayado.obtener_color();
	}	
	
	public boolean explotan_dos(Gelatina gelatina) {
		return gelatina.obtener_caramelo_interno().explotan_dos(this);
	}
	
	public boolean explotan_dos(Glaseado glaseado) {
		return false;
	}
	
	public boolean explotan_dos(Bomba bomba) {
		return false;
	}

	    	
}
