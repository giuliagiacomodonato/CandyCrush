package Interfaces;

import Entidades.Bomba;

import Entidades.Caramelo;
import Entidades.DobleRayado;
import Entidades.Entidad;
import Entidades.Gelatina;
import Entidades.Glaseado;
import Entidades.Potenciador;

public interface Intercambiable {

	public boolean es_posible_intercambiar(Entidad entidad);
	
	public boolean puede_recibir(Caramelo caramelo);

	public boolean puede_recibir(Glaseado glaseado);

	public boolean puede_recibir(Potenciador potenciador);

	public boolean puede_recibir(Gelatina gelatina);
	
	public boolean puede_recibir(Bomba bomba);

	public boolean puede_recibir(DobleRayado dobleRayado);
	
	
	
	
	
	
	public void intercambiar(Entidad entidad);
	
	public void intercambiar_con(Caramelo caramelo);

	public void intercambiar_con(Glaseado glaseado);
	
	public void intercambiar_con(Potenciador potenciador);

	public void intercambiar_con(Gelatina gelatina);

	public void intercambiar_con(Bomba bomba);
	
	public void intercambiar_con(DobleRayado dobleRayado);
	
	

	

	
}
