package Entidades;



import java.util.HashSet;



import Auxiliares.Color;
import Auxiliares.Constantes;
import Logica.Tablero;


public class Caramelo extends Entidad {

	   public Caramelo(Tablero tablero,Color c,  String v)  {
	       super(tablero,c,"/Imagenes/"+v+"/",true);
	    }

	//Operacion de match
	   
    public boolean se_produce_match_con(Entidad e) {
		return e.aplica_match_con(this);
	}
    
	public boolean aplica_match_con(Caramelo c) {
		return color == c.obtener_color();
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
		return color==g.obtener_color();
	}
	
	public boolean aplica_match_con(Bomba b) {
		return false;
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
		return gelatina.obtener_caramelo_interno().puede_recibir(this);
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

		intercambiar_caramelo_y_gelatina(this, gelatina);
		
	}


	@Override
	public void intercambiar_con(DobleRayado dobleRayado) {
		intercambiar_entidad_y_entidad(this, dobleRayado);
		
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
		return color == dobleRayado.obtener_color();
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
	
	
	
	public HashSet<Entidad> obtener_entidades_afectadas_por_detonacion(){
		return obtener_entidades_adyacentes_afectadas_por_detonacion();
	}

	public String toString() {
    	return Constantes.CARAMELO;
    }
	
	public boolean reacciona_cuando_rompe_vecino() {
		return false;
	}
	    
	public int obtener_puntaje() {
		return color.obtener_puntaje();
	}

}