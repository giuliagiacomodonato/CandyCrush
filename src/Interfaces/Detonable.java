package Interfaces;

import Entidades.Bomba;
import Entidades.Caramelo;
import Entidades.DobleRayado;
import Entidades.Entidad;
import Entidades.Gelatina;
import Entidades.Glaseado;
import Entidades.Potenciador;

public interface Detonable {

	public void detonar();
	
	public boolean puede_explotar_dos(Entidad e);

	public boolean explotan_dos(Caramelo caramelo);
	
	public boolean explotan_dos(Potenciador potenciador);
	
	public boolean explotan_dos(DobleRayado dobleRayado);
	
	public boolean explotan_dos(Gelatina gelatina);
	
	public boolean explotan_dos(Glaseado glaseado);
	
	public boolean explotan_dos(Bomba bomba);
}
