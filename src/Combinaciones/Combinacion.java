package Combinaciones;

import java.util.List;
import java.util.LinkedList;

import Logica.Tablero;
import Entidades.Entidad;
import Interfaces.ManejadorOperaciones;
import Logica.EfectosDeTransicion;
import Logica.Fabrica;

public abstract class Combinacion {
	protected Tablero tablero;
	protected EfectosDeTransicion efectos_regla;
	protected Fabrica fabrica;
	
	public Combinacion(ManejadorOperaciones manejador_tablero) {
		tablero = manejador_tablero.obtener_tablero();
		efectos_regla = new EfectosDeTransicion();
		fabrica = manejador_tablero.obtener_fabrica();
	}
	
	protected abstract List<Entidad> forma_combinacion(Entidad e);
	
	public abstract int obtener_prioridad();
	
	public abstract EfectosDeTransicion calcular_efectos_regla_en(Entidad e);
	
	public abstract EfectosDeTransicion calcular_efectos_regla_en(Entidad entidad_origen, Entidad entidad_destino);
	
	protected List<Entidad> obtener_1_derecha(Entidad e){
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila(), e.obtener_columna() + 1)) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila(), e.obtener_columna() + 1);
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
			}
		}
		return lista_a_devolver;
	}
	
	protected List<Entidad> obtener_1_izquierda(Entidad e) {
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila(), e.obtener_columna() - 1)) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila(), e.obtener_columna() - 1);
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
			}
		}
		return lista_a_devolver;
	}
	
	protected List<Entidad> obtener_1_abajo(Entidad e) {
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila() + 1, e.obtener_columna())) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila() + 1, e.obtener_columna());
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
			}
		}
		return lista_a_devolver;
	}

	protected List<Entidad> obtener_1_arriba(Entidad e) {
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila() - 1, e.obtener_columna())) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila() - 1, e.obtener_columna());
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
			}
		}
		return lista_a_devolver;
	}
	
	protected List<Entidad> obtener_2_derecha(Entidad e){
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila(), e.obtener_columna() + 1)) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila(), e.obtener_columna() + 1);
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
				if(tablero.en_rango(e.obtener_fila(), e.obtener_columna() + 2)) {
					entidad_a_agregar = tablero.obtener(e.obtener_fila(), e.obtener_columna() + 2);
					if(e.se_produce_match_con(entidad_a_agregar)){
						lista_a_devolver.add(entidad_a_agregar);
					}
				}
			}
		}
		if(lista_a_devolver.size() != 2) {
			lista_a_devolver = new LinkedList<Entidad>();
		}
		return lista_a_devolver;
	}
	
	protected List<Entidad> obtener_2_izquierda(Entidad e){
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila(), e.obtener_columna() - 1)) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila(), e.obtener_columna() - 1);
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
				if(tablero.en_rango(e.obtener_fila(), e.obtener_columna() - 2)) {
					entidad_a_agregar = tablero.obtener(e.obtener_fila(), e.obtener_columna() - 2);
					if(e.se_produce_match_con(entidad_a_agregar)){
						lista_a_devolver.add(entidad_a_agregar);
					}
				}
			}
		}
		if(lista_a_devolver.size() != 2) {
			lista_a_devolver = new LinkedList<Entidad>();
		}
		return lista_a_devolver;
	}
	
	protected List<Entidad> obtener_2_abajo(Entidad e){
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila() + 1, e.obtener_columna())) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila() + 1, e.obtener_columna());
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
				if(tablero.en_rango(e.obtener_fila() + 2, e.obtener_columna())) {
					entidad_a_agregar = tablero.obtener(e.obtener_fila() + 2, e.obtener_columna());
					if(e.se_produce_match_con(entidad_a_agregar)){
						lista_a_devolver.add(entidad_a_agregar);
					}
				}
			}
		}
		if(lista_a_devolver.size() != 2) {
			lista_a_devolver = new LinkedList<Entidad>();
		}
		return lista_a_devolver;
	}
	
	protected List<Entidad> obtener_2_arriba(Entidad e){
		List<Entidad> lista_a_devolver= new LinkedList<Entidad>();
		if(tablero.en_rango(e.obtener_fila() - 1, e.obtener_columna())) {
			Entidad entidad_a_agregar = tablero.obtener(e.obtener_fila() - 1, e.obtener_columna());
			if(e.se_produce_match_con(entidad_a_agregar)){
				lista_a_devolver.add(entidad_a_agregar);
				if(tablero.en_rango(e.obtener_fila() - 2, e.obtener_columna())) {
					entidad_a_agregar = tablero.obtener(e.obtener_fila() - 2, e.obtener_columna());
					if(e.se_produce_match_con(entidad_a_agregar)){
						lista_a_devolver.add(entidad_a_agregar);
					}
				}
			}
		}
		if(lista_a_devolver.size() != 2) {
			lista_a_devolver = new LinkedList<Entidad>();
		}
		return lista_a_devolver;
	}
}