package Logica;

import java.io.Serializable;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Auxiliares.Constantes;
import Auxiliares.Pair;

public class RankingJugadores implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Pair<Integer, Jugador>> ranking;
	
	public RankingJugadores() {
		this.ranking = new LinkedList<Pair<Integer, Jugador>>();
	}
	
	public void agregar_jugador( Jugador j , int puntaje) {
		Pair<Integer, Jugador> par = new Pair<Integer, Jugador>(puntaje, j);
		if(!ranking.contains(par)) {
			this.ranking.add(par);
		}
		Collections.sort(this.ranking, Collections.reverseOrder());
		if(ranking.size()>Constantes.CANT_JUGADORES_RANKING) {
			ranking.remove(ranking.size()-1);
		}
	}
	
	public List<Pair<Integer, Jugador>> obtener_jugadores(){
		return ranking;
	}
}
