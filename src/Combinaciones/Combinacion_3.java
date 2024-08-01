package Combinaciones;

import java.util.LinkedList;


import java.util.List;

import Entidades.Entidad;
import Interfaces.ManejadorOperaciones;
import Logica.EfectosDeTransicion;

public class Combinacion_3 extends Combinacion {
    private static final int prioridad = 5;
    
    public Combinacion_3(ManejadorOperaciones manejador_tablero) {
        super(manejador_tablero);
    }
	public EfectosDeTransicion calcular_efectos_regla_en(Entidad e) {
		efectos_regla.entidades_a_reemplazar().clear();
		efectos_regla.entidades_a_incorporar().clear();
		efectos_regla = new EfectosDeTransicion();
		List<Entidad> combinacion_entidad_destino = forma_combinacion(e);
		
		for(Entidad entidad : combinacion_entidad_destino)
			efectos_regla.agregar_entidad_a_detonar_y_reemplazar(entidad);
		
		return efectos_regla;
	}
	
	public EfectosDeTransicion calcular_efectos_regla_en(Entidad entidad_origen, Entidad entidad_destino) {
        efectos_regla.entidades_a_reemplazar().clear();
        efectos_regla.entidades_a_incorporar().clear();
        efectos_regla = new EfectosDeTransicion();
        List<Entidad> combinacion_entidad_destino = forma_combinacion(entidad_destino);
        List<Entidad> combinacion_entidad_origen = forma_combinacion(entidad_origen);
        
        for(Entidad entidad : combinacion_entidad_destino)
            efectos_regla.agregar_entidad_a_detonar_y_reemplazar(entidad);
        for(Entidad entidad : combinacion_entidad_origen)
            efectos_regla.agregar_entidad_a_detonar_y_reemplazar(entidad);
        return efectos_regla;
    }
	
	protected List<Entidad> forma_combinacion(Entidad e) {
		
		List<Entidad> entidades_de_la_combinacion = new LinkedList<Entidad>();
		List<Entidad> 	dos_a_izquierda = obtener_2_izquierda(e);
		List<Entidad> 	dos_a_derecha = obtener_2_derecha(e);
		List<Entidad> 	dos_arriba = obtener_2_arriba(e);
		List<Entidad> 	dos_abajo  = obtener_2_abajo(e);
		List<Entidad> 	uno_a_izquierda = obtener_1_izquierda(e);
		List<Entidad> 	uno_a_derecha = obtener_1_derecha(e);
		List<Entidad> 	uno_arriba = obtener_1_arriba(e);
		List<Entidad> 	uno_abajo = obtener_1_abajo(e);
		
		boolean seguir_buscando = true;
				
		entidades_de_la_combinacion.add(e);
		
		if (!dos_abajo.isEmpty()) {
			entidades_de_la_combinacion.addAll(dos_abajo);
			seguir_buscando = false;
		}
		
		if (seguir_buscando && !dos_arriba.isEmpty()) {
			entidades_de_la_combinacion.addAll(dos_arriba);
			seguir_buscando = false;
		}
		
		if (seguir_buscando && !dos_a_izquierda.isEmpty()) {
			entidades_de_la_combinacion.addAll(dos_a_izquierda);
			seguir_buscando = false;
		}
		
		if (seguir_buscando && !dos_a_derecha.isEmpty()) {
			entidades_de_la_combinacion.addAll(dos_a_derecha);
			seguir_buscando = false;
		}
		
		if (seguir_buscando && !uno_abajo.isEmpty() && !uno_arriba.isEmpty()) {
			entidades_de_la_combinacion.addAll(uno_abajo);
			entidades_de_la_combinacion.addAll(uno_arriba);
			seguir_buscando = false;
		}
		
		if (seguir_buscando && !uno_a_izquierda.isEmpty() && !uno_a_derecha.isEmpty()) {
			entidades_de_la_combinacion.addAll(uno_a_izquierda);
			entidades_de_la_combinacion.addAll(uno_a_derecha);
			seguir_buscando = false;
		}
		
		if (seguir_buscando) {
			entidades_de_la_combinacion = new LinkedList<Entidad>();
		}
		
		return entidades_de_la_combinacion;
	}

    public int obtener_prioridad() {
    	return prioridad;
    }
}