package Entidades;

import java.util.HashSet;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Logica.Tablero;

/**
 * 
 */
public class Envuelto extends Potenciador {

	private final int puntaje = 50;
      /**
     * @param Color c 
     * @param Bloque b
     */
    public Envuelto(Tablero tablero,Color c,String v) {
    	
       super(tablero,c,v);
    }
    
    
    protected void cargar_imagenes(String path_img) {
    	
		imagenes_representativas = new String [4];
		imagenes_representativas[0] = path_img + color.toString() + "-envuelto.png";
		imagenes_representativas[1] = path_img + color.toString() + "-envuelto" + "-resaltado.png";
		imagenes_representativas[2] = path_img + "explosion.gif";
 		imagenes_representativas[3] = path_img + "explosion.gif";
	}
    
    public String toString() {
    	return Constantes.ENVUELTO;
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
 		int columna_izq = obtener_columna()-1;
    	int columna_der = obtener_columna()+1;
    	int fila_arriba = obtener_fila()-1;
    	int fila_abajo = obtener_fila()+1;
    	
 		for(Entidad e : obtener_entidades_adyacentes_afectadas_por_detonacion()) {
 			afectadas.add(e);
 		}
 	
    	for(int i = fila_arriba;i <= fila_abajo;i++) {
    		for(int j = columna_izq;j <= columna_der;j++) {
    			if(tablero.en_rango(i,j) && !tablero.obtener(i, j).esta_detonada()) {
    				afectadas.add(tablero.obtener(i,j));
    			}
    		}
    	}
    
 		afectadas.remove(this);
 		return afectadas;
 		
 		
 	}
   	
    	
   
}