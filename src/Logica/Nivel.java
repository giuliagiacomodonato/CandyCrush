package Logica;
import java.util.*;

import Interfaces.SeguidorObjetivo;

public class Nivel implements SeguidorObjetivo {

    private List<Objetivo> objetivos;
    private Juego mi_juego;
    private Reloj reloj;
    private int objetivos_totales;
    private int objetivos_cumplidos;
    private int movimientos;
    private int tiempo;
	protected int fila_inicial_jugador;
	protected int columna_inicial_jugador;

	public Nivel(int f, int c ) {
		fila_inicial_jugador = f;
		columna_inicial_jugador = c;
	}
	
	public void establecer_juego(Juego j) {
		mi_juego = j;
		
	}

	public void iniciar() {
		reloj =  new Reloj(tiempo,mi_juego);
		reloj.iniciar();
	}

	public void establecer_objetivos(List<Objetivo> o) {
		objetivos = o;
		objetivos_totales = objetivos.size();
	    objetivos_cumplidos = 0;
	    mi_juego.establecer_objetivos(o);
	}
	
	public List<Objetivo> obtener_objetivos(){
		return objetivos;
	}
	
	public void actualizar_objetivos(int cantidad, Objetivo o) {
		
		mi_juego.actualizar_objetivos(cantidad,o);
	}
	
	public void objetivo_cumplido() {
		objetivos_cumplidos++;
		if(objetivos_cumplidos == objetivos_totales) {
			mi_juego.finalizar_nivel(true);
		}
		reloj.detener();
	}
	
	public Reloj obtener_reloj() {
		return reloj;
	}
	
	
	public void terminar() {
		reloj.detener();
	}

	public int get_fila_inicial_jugador() {
		return fila_inicial_jugador;
	}
	
	public int get_columna_inicial_jugador() {
		return columna_inicial_jugador;
	}
	
	public void establecer_movimientos(int mov) {
		movimientos = mov;
	}

	public void establecer_tiempo(int t) {
		tiempo = t;
		
	}

	public int obtener_movimientos() {
		return movimientos;
	}
	
}