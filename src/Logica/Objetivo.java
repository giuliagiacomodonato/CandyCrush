package Logica;
import Entidades.*;

import Auxiliares.Color;
import Interfaces.SeguidorObjetivo;
import Interfaces.TrackerObserver;


public abstract class Objetivo implements TrackerObserver {
	
	protected TrackerObjetivo toTrack;

	protected SeguidorObjetivo seguidor;
	protected Entidad entidad_objetivo;
	protected Color color_objetivo;
	protected int cantidad_total;
	protected int cantidad_obtenida;
	
	public Objetivo(SeguidorObjetivo n,TrackerObjetivo t,Entidad e,Color c, int cantidad) {
		toTrack = t;
		seguidor = n;
		toTrack.subscribirse_a_objetivo(this);
		entidad_objetivo = e;
		this.color_objetivo = c;
		cantidad_total = cantidad;
		cantidad_obtenida = 0;
	}
	
	public TrackerObjetivo obtener_tracker() {
		return toTrack;
	}
	
	
	public Color obtener_color() {
		return color_objetivo;
	}
	
	public Entidad obtener_entidad() {
		return entidad_objetivo;
	}
	
	public int obtener_cantidad() {
		return cantidad_total;
	}
	
	public void registrar_en_tracker(TrackerObjetivo tracker) {
        tracker.subscribirse_a_objetivo(this);
    }
	
	public abstract void actualizar();

}

	
	
	
