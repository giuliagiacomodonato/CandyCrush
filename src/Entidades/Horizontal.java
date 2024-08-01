package Entidades;

import java.util.HashSet;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Logica.Tablero;
/**
 * 
 */
public class Horizontal extends Rayado {
		
	private final int puntaje = PUNTAJE_RAYADOH;
        /**
     * @param Color c 
     * @param Bloque b
     */
    public Horizontal(Tablero tablero,Color c ,String v) {
        super(tablero,c,v);
    }
  
	protected void cargar_imagenes(String path_img) {
    	
		imagenes_representativas = new String [4];
		imagenes_representativas[0] = path_img + color.toString() + "-horizontal.png";
		imagenes_representativas[1] = path_img + color.toString() + "-horizontal" + "-resaltado.png";
		imagenes_representativas[2] = path_img + "explosion.gif";
 		imagenes_representativas[3] = path_img + "explosion.gif";
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
 		for(int i = 0; i < tablero.cant_columnas();i++) {
 			if(i!=columna &&  !tablero.obtener(fila, i).esta_detonada())
 				afectadas.add(tablero.obtener(fila, i));
 		}
 		afectadas.remove(this);
 		return afectadas;
 		
 	}
 	public String toString() {
 		return Constantes.HORIZONTAL;
 	}
 

}