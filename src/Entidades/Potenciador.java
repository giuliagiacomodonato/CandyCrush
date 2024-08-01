package Entidades;

import Auxiliares.Color;
import Logica.Tablero;
/**
 * 
 */
public abstract class Potenciador extends Entidad {

    
    public Potenciador(Tablero tablero,Color c, String v) {
    	super(tablero,c,"/Imagenes/"+v+"/",true);
    }
    
  //Operaciones de intercambio

  	@Override
  	public boolean es_posible_intercambiar(Entidad entidad) {
  		return entidad.puede_recibir(this);
  	}
  	
  	@Override
  	public boolean puede_recibir(Caramelo caramelo) {
  		return true;
  	}
  	
  	@Override
  	public boolean puede_recibir(Glaseado glaseado) {
  		return false;
  	}
  	
  	@Override
  	public boolean puede_recibir(Potenciador potenciador) {
  		return true;
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
  		return true;
  	}

  	@Override
  	public void intercambiar(Entidad entidad) {
  		entidad.intercambiar_con(this);
  	}
  	
  	@Override
  	public void intercambiar_con(Caramelo caramelo) {
  		intercambiar_entidad_y_entidad(this, caramelo);
  	}
  	
  	@Override
  	public void intercambiar_con(Potenciador potenciador) {
  		intercambiar_entidad_y_entidad(this, potenciador);
  	}
  	
  	@Override
  	public void intercambiar_con(Glaseado glaseado) {
  		
  	}
  	
  	@Override
  	public void intercambiar_con(Bomba bomba) {
  		
  	}

  	@Override
  	public void intercambiar_con(Gelatina gelatina) {
  		
  	}

  	@Override
  	public void intercambiar_con(DobleRayado dobleRayado) {
  		intercambiar_entidad_y_entidad(this, dobleRayado);
  		
  	}
    
    //Operaciones de match
    
    public boolean se_produce_match_con(Entidad e) {
		return e.aplica_match_con(this);
	}
	
	public boolean aplica_match_con(Caramelo caramelo) {
		return color == caramelo.obtener_color();
	}
	
	public boolean aplica_match_con(Potenciador potenciador) {
		return color == potenciador.obtener_color();
	}
	
	public boolean aplica_match_con(DobleRayado dobleRayado) {
		return color == dobleRayado.obtener_color();
	}
	
	public boolean aplica_match_con(Glaseado g) {
		return false;
	}
	
	public boolean aplica_match_con(Gelatina g) {
		return g.obtener_caramelo_interno().aplica_match_con(this);
	}
	
	public boolean aplica_match_con(Bomba b) {
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
		return true;
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
	
	public boolean reacciona_cuando_rompe_vecino() {
		return false;
	}

}