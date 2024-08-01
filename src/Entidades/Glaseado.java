package Entidades;

import java.util.HashSet;

import java.util.List;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Logica.Tablero;
/**
 * 
 */
public class Glaseado extends Entidad {
    private final int puntaje = PUNTAJE_GLASEADO;

    public Glaseado(Tablero tablero,Color c,String version ) {
    	super(tablero,c,"/Imagenes/glaseados/"+version+"/", true);
    }
    
    public HashSet<Entidad> obtener_entidades_afectadas_por_detonacion(){
		return new HashSet<Entidad>();
	}

    
    public  boolean combina(Entidad e, List<Entidad> l) {
    	return false;
    }
    
    public boolean reacciona_cuando_rompe_vecino() {
		return true;
	}
    
    //Operaciones de match
    
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
		return false;
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
	
	@Override
    public String toString() {
    	return Constantes.GLASEADO;
    }
    
    public int obtener_puntaje() {
    	return puntaje;
    }

}