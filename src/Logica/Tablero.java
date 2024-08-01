package Logica;

import java.util.*;

import Entidades.Caramelo;
import Entidades.Entidad;
import Entidades.Gelatina;
import Interfaces.TableroJuego;

public class Tablero implements TableroJuego {
	
	private Entidad [][] mis_entidades;
    private int cant_filas;
    private int cant_columnas;
	protected List<Entidad> entidades_asociadas;

    public Tablero(int f, int c) {
       mis_entidades = new Entidad[f][c];
       cant_filas = f;
       cant_columnas = c;
       entidades_asociadas = new LinkedList<Entidad>();
    }

    public void resetar_tablero(int f, int c) {
		cant_filas = f;
		cant_columnas = c;
		mis_entidades = new Entidad[f][c];
		entidades_asociadas = new LinkedList<Entidad>();
	}

    public List<Entidad> obtener_entidades_asociadas(){
    	return entidades_asociadas;
    }
    
    public Entidad obtener(int f, int c) {
        return mis_entidades[f][c];
    }

    public void insertar(int f, int c, Entidad e) {
        mis_entidades[f][c] = e;
    }
    
    public void insertar_entidad_y_asociada(int f, int c, Gelatina g) {
		mis_entidades[f][c] = g;
		entidades_asociadas.add(g.obtener_caramelo_interno());
	}
    
    public void reubicar(Entidad e) {
		int nueva_fila = e.obtener_fila();
		int nueva_columna = e.obtener_columna();
		insertar(nueva_fila, nueva_columna,e);
	}

    public int cant_filas() {
        return cant_filas;
    }

    public int cant_columnas() {
        return cant_columnas;
    }
    
	public boolean en_rango(int fila, int columna) {
		boolean en_rango_fila = (0 <= fila) && (fila < cant_filas());
		boolean en_rango_columna = (0 <= columna) && (columna < cant_columnas());
		return (en_rango_fila && en_rango_columna);
	}
	
	public void intercambiar_entidades_asociadas(Caramelo a_eliminar, Caramelo a_agregar) {
		entidades_asociadas.remove(a_eliminar);
		entidades_asociadas.add(a_agregar);
	}
}