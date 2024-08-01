package Logica;
import java.util.Map;


import Entidades.Entidad;
import Auxiliares.Color;
import Interfaces.Tracker;
import Interfaces.TrackerObserver;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;

public class TrackerObjetivo implements Tracker {
    private Map<String, Integer> entidades;
    private Map<String, Integer> colores;
    private List<TrackerObserver> observadores;

    public TrackerObjetivo() {
        entidades = new HashMap<String, Integer>();
        colores = new HashMap<String, Integer>();
        observadores = new LinkedList<TrackerObserver>();
    }
    
    public void subscribirse_a_objetivo(TrackerObserver observador) {
        observadores.add(observador);
    }
    
    public void track(List<Entidad> l) {
    	for (Entidad e : l) {
        	entidades.put(e.toString(), entidades.getOrDefault(e.toString(),0) + 1);
        	colores.put(e.obtener_color().toString(), colores.getOrDefault(e.obtener_color().toString(),0) + 1);   
        	
        }
        notificar_entidad_rota();
    }

    private void notificar_entidad_rota() {
    	for (TrackerObserver observador : observadores) {   	
    		observador.actualizar();
    	}
    }

    public int obtener_cantidad(Color c) {
        return colores.getOrDefault(c.toString(),0);
    }
    
    public int obtener_cantidad(Entidad e) {
    	return entidades.getOrDefault(e.toString(),0);
    }

}