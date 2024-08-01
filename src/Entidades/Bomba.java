package Entidades;

import java.util.HashSet;
import Auxiliares.Color;
import Auxiliares.Constantes;
import Logica.Tablero;

public class Bomba extends Entidad {
    private final int puntaje = 150;
    private volatile int tiempo;

    public Bomba(Tablero tablero, int t, String v) {
        super(tablero,Color.NEGRO, "/Imagenes/"+v+"/", true);
        tiempo = t;
    }
	public HashSet<Entidad> obtener_entidades_afectadas_por_detonacion(){
		return obtener_entidades_adyacentes_afectadas_por_detonacion();
	}

    
    public boolean reacciona_cuando_rompe_vecino() {
		return true;
	}
	
	public int obtener_puntaje() {
		return puntaje;
	}
	
	public String toString() {
		return Constantes.BOMBA;
	}
    
    /**
     * 
     * @return Imagen de la entidad
     */
    
     
     public String obtener_imagen() {
     	int indice = 0;
     	
     	if(tiempo>=7 && tiempo<12) {
     		indice = 1;
     	}
     	if(tiempo<7) {
     		indice = 2;
     	}
     	if(tiempo == 0 || esta_detonada()) {
     		
     		indice = 6;
     	}
     	
     	if(enfocada) {
     		indice = indice + 3;
     	}
     	
     	return imagenes_representativas[indice];
     }
     
     /**
      * Cargar imagenes de la entidad
      * @param path_img ruta de la imagen
      */
     protected void cargar_imagenes(String path_img) {
 		imagenes_representativas = new String [7];
 	  	
 			imagenes_representativas[0] = path_img + color.toString()+1+".png";
 			imagenes_representativas[1] = path_img + color.toString()+2+".png";
 			imagenes_representativas[2] = path_img + color.toString()+3+".png";
 			imagenes_representativas[3] = path_img + color.toString()+1+"-resaltado.png";
 			imagenes_representativas[4] = path_img + color.toString()+2+"-resaltado.png";
 			imagenes_representativas[5] = path_img + color.toString()+3+"-resaltado.png";
 			imagenes_representativas[6] = path_img + "explosion.gif";
 		
 	}

    @Override
    public void tick(ManejadorEntidades m) {
            tiempo--;
            entidad_grafica.notificarse_cambio_foco();
            if(tiempo == 0) {
            	entidad_grafica.notificarse_detonacion();
            }
            if (tiempo == -2) {
                m.detener();
                
            }
      
    }
    
    public boolean se_produce_match_con(Entidad e) {
		return false;
	}
    
    public boolean aplica_match_con(Caramelo c) {
		return false;
	}
	
    public boolean aplica_match_con(Potenciador potenciador) {
		return false;
	}
	
	public boolean aplica_match_con(DobleRayado dobleRayado) {
		return false;
	}
	
	public boolean aplica_match_con(Glaseado g) {
		return false;
	}
	
	public boolean aplica_match_con(Bomba b) {
		return false;
	}
	
	public boolean aplica_match_con(Gelatina g) {
		return false;
	}
	
	//Operaciones de detonacion
	
	public boolean puede_explotar_dos(Entidad e) {
		return e.explotan_dos(this);
	}

	public boolean explotan_dos(Caramelo caramelo) {
		return false;
	}
	
	public boolean explotan_dos(Potenciador potenciador) {
		return false;
	}
	
	public boolean explotan_dos(DobleRayado dobleRayado) {
		return false;
	}
	
	public boolean explotan_dos(Gelatina gelatina) {
		return false;
	}
	
	public boolean explotan_dos(Glaseado glaseado) {
		return false;
	}
	
	public boolean explotan_dos(Bomba bomba) {
		return false;
	}
	
	//Operaciones de intercambio
	
	@Override
	public boolean es_posible_intercambiar(Entidad entidad) {
		return entidad.puede_recibir(this);
	}


	@Override
	public boolean puede_recibir(Caramelo caramelo) {
		return false;
	}


	@Override
	public boolean puede_recibir(Glaseado glaseado) {
		return false;
	}


	@Override
	public boolean puede_recibir(Potenciador potenciador) {
		return false;
	}

	
	@Override
	public boolean puede_recibir(Bomba bomba) {
		return false;
	}
	
	@Override
	public boolean puede_recibir(Gelatina gelatina) {
		return false;
	}


	@Override
	public boolean puede_recibir(DobleRayado dobleRayado) {
		return false;
	}


	@Override
	public void intercambiar(Entidad entidad) {
		
	}


	@Override
	public void intercambiar_con(Caramelo caramelo) {
		
	}


	@Override
	public void intercambiar_con(Glaseado glaseado) {
		
	}


	@Override
	public void intercambiar_con(Potenciador potenciador) {
		
	}
	
	
	@Override
	public void intercambiar_con(Bomba bomba) {
		
	}

	@Override
	public void intercambiar_con(Gelatina gelatina) {
		
	}


	@Override
	public void intercambiar_con(DobleRayado dobleRayado) {
		
	}
}