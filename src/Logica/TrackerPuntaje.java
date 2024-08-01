package Logica;


import java.util.List;

import Entidades.Entidad;
import Interfaces.Tracker;

public class TrackerPuntaje implements Tracker{
	protected int puntaje;
	public TrackerPuntaje() {
		puntaje = 0;
	}
	
	public void track(List<Entidad> l) {
		for(Entidad e : l) {
			puntaje+= e.obtener_puntaje();
		}
	}
	
	public int obtener_puntaje() {
		return puntaje;
	}
}
