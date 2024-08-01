package Interfaces;

import Vista.Celda;

public interface Animador{
	
	public Celda get_celda_asociada();
	
	public int obtener_prioridad();
	
	public void comenzar_animacion();
}
