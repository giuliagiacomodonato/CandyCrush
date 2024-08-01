package Interfaces;

import Entidades.Bomba;
import Entidades.Caramelo;
import Entidades.DobleRayado;
import Entidades.Entidad;
import Entidades.Gelatina;
import Entidades.Glaseado;
import Entidades.Potenciador;

public interface Matcheable {
	
	public boolean se_produce_match_con(Entidad e);
	
	public boolean aplica_match_con(Caramelo caramelo);
	
	public boolean aplica_match_con(Potenciador potenciado);
	
	public boolean aplica_match_con(DobleRayado doblerayado);
	
	public boolean aplica_match_con(Glaseado glaseado);
	
	public boolean aplica_match_con(Gelatina gelatina);
	
	public boolean aplica_match_con(Bomba bomba);
	
}
