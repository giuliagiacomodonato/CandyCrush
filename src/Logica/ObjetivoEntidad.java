package Logica;

import Entidades.Entidad;

import Auxiliares.Color;

public class ObjetivoEntidad extends Objetivo{
	
	public ObjetivoEntidad(Nivel n,TrackerObjetivo t,Entidad e,Color c, int cantidad) {
		super(n,t,e,c,cantidad);
	}
	
	public void actualizar() {
		
		cantidad_obtenida = toTrack.obtener_cantidad(entidad_objetivo);
		
		if( cantidad_obtenida >= cantidad_total) {
			seguidor.objetivo_cumplido();
		}
	
		seguidor.actualizar_objetivos(((cantidad_total-cantidad_obtenida>0 ) ? cantidad_total-cantidad_obtenida: 0),this);
		
	}

}