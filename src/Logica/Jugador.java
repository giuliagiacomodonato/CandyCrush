package Logica;

import java.io.Serializable;

public class Jugador implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	
	public Jugador(String nombre) {
		this.nombre = nombre;
	}
	
	public String obtener_nombre() {
		return nombre;
	}

}
