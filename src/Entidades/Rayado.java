package Entidades;

import Auxiliares.Constantes;

import Auxiliares.Color;
import Logica.Tablero;

public abstract class Rayado extends Potenciador {

    public Rayado(Tablero tablero,Color c, String v) {
        super(tablero,c,v);
    }

    public String toString() {
    	return Constantes.RAYADO;
    }
    
    
}