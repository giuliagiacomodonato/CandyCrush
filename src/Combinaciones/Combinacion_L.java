package Combinaciones;

import java.util.LinkedList;


import java.util.List;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Entidades.Entidad;
import Interfaces.ManejadorOperaciones;
import Logica.EfectosDeTransicion;

public class Combinacion_L extends Combinacion{
	
	private static final int prioridad = 1;
	Entidad nuevo;
	public Combinacion_L(ManejadorOperaciones manejador_tablero) {
		super(manejador_tablero);
	}
	 
	public EfectosDeTransicion calcular_efectos_regla_en(Entidad e) {
		  efectos_regla.entidades_a_reemplazar().clear();
			efectos_regla.entidades_a_incorporar().clear();
			efectos_regla = new EfectosDeTransicion();
		List<Entidad> combinacion_entidad_destino = forma_combinacion(e);
		
		for(Entidad entidad : combinacion_entidad_destino)
			efectos_regla.agregar_entidad_a_detonar_y_reemplazar(entidad);
		if(!combinacion_entidad_destino.isEmpty() ) {	
			efectos_regla.agregar_entidad_de_reemplazo(nuevo);
		}
		return efectos_regla;
	}
	
	public EfectosDeTransicion calcular_efectos_regla_en(Entidad entidad_origen,Entidad entidad_destino) {
        efectos_regla.entidades_a_reemplazar().clear();
        efectos_regla.entidades_a_incorporar().clear();
        efectos_regla = new EfectosDeTransicion();
        List<Entidad> combinacion_entidad_destino = forma_combinacion(entidad_destino);

        List<Entidad> combinacion_entidad_origen = forma_combinacion(entidad_origen);

        for(Entidad entidad : combinacion_entidad_destino)
            efectos_regla.agregar_entidad_a_detonar_y_reemplazar(entidad);
        if(!combinacion_entidad_destino.isEmpty() ) {
            efectos_regla.agregar_entidad_de_reemplazo(nuevo);
        }

        for(Entidad entidad : combinacion_entidad_origen)
            efectos_regla.agregar_entidad_a_detonar_y_reemplazar(entidad);
        if(!combinacion_entidad_origen.isEmpty() ) {
            efectos_regla.agregar_entidad_de_reemplazo(nuevo);
        }
    return efectos_regla;
    }
	
	
	
	protected List<Entidad> forma_combinacion(Entidad e){
		List<Entidad> entidades_de_la_combinacion = new LinkedList<Entidad>();
		boolean seguir_buscando = true;
		List<Entidad> dos_a_izquierda = obtener_2_izquierda(e);
		List<Entidad> dos_a_derecha = obtener_2_derecha(e);
		List<Entidad> dos_arriba = obtener_2_arriba(e);
		List<Entidad> dos_abajo = obtener_2_abajo(e);
		
		if(!dos_a_izquierda.isEmpty() && !dos_abajo.isEmpty()) {
			seguir_buscando = false;
			entidades_de_la_combinacion.add(e);
			entidades_de_la_combinacion.addAll(dos_a_izquierda);
			entidades_de_la_combinacion.addAll(dos_abajo);
		}
		
		if(seguir_buscando && !dos_a_derecha.isEmpty() && !dos_abajo.isEmpty()) {
			seguir_buscando = false;
			entidades_de_la_combinacion.add(e);
			entidades_de_la_combinacion.addAll(dos_a_derecha);
			entidades_de_la_combinacion.addAll(dos_abajo);
		}
		
		if(seguir_buscando && !dos_a_derecha.isEmpty() && !dos_arriba.isEmpty()) {
			seguir_buscando = false;
			entidades_de_la_combinacion.add(e);
			entidades_de_la_combinacion.addAll(dos_a_derecha);
			entidades_de_la_combinacion.addAll(dos_arriba);
		}
		
		if(seguir_buscando && !dos_a_izquierda.isEmpty() && !dos_arriba.isEmpty()) {
			seguir_buscando = false;
			entidades_de_la_combinacion.add(e);
			entidades_de_la_combinacion.addAll(dos_a_izquierda);
			entidades_de_la_combinacion.addAll(dos_arriba);
		}
		
		if(seguir_buscando) {
			entidades_de_la_combinacion = new LinkedList<Entidad>();
		}
			else {
				Entidad nueva = null;
		        Color color = entidades_de_la_combinacion.get(0).obtener_color();
		        int fila = entidades_de_la_combinacion.get(0).obtener_fila();
		        int columna = entidades_de_la_combinacion.get(0).obtener_columna();
		        nueva = fabrica.fabricar_entidad(tablero, Constantes.ENVUELTO,color );
		        nueva.establecer_posicion(fila, columna);
		        nuevo = nueva;
			}
        return entidades_de_la_combinacion;
	}
	
	public int obtener_prioridad() {
		return prioridad;
	}
}