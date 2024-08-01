package Logica;

import org.json.simple.JSONArray; 



import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Combinaciones.Combinacion_3;
import Combinaciones.Combinacion_4;
import Combinaciones.Combinacion_5;
import Combinaciones.Combinacion_L;
import Combinaciones.Combinacion_T;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import Entidades.*;
import Combinaciones.Combinacion;
import Interfaces.ManejadorOperaciones;

public class GeneradorNivel {
	private Object jsonParser;
	private JSONObject jsonObject;
	private Fabrica fabrica;
	
	public GeneradorNivel(Fabrica fab) {
		fabrica = fab;
	}
	
	public void  generarNivel(int nivel) {
		try {
			jsonParser = new JSONParser().parse(new FileReader("src/niveles/nivel"+nivel+".json"));
			jsonObject = (JSONObject) jsonParser;
		} catch (IOException | ParseException e) {
		
		}
	}
	
	public void cargar_reglas(ManejadorOperaciones m) {
		List<Combinacion> lista_reglas = new LinkedList<Combinacion>();
		JSONArray regla_array = (JSONArray) jsonObject.get("reglas");
	    for(int i = 0; i<regla_array.size();i++) {
	    	String tipo_regla = (String) regla_array.get(i);
	    	Combinacion regla = crear_regla(tipo_regla,m);
	    	lista_reglas.add(regla);
	    }
	   m.establecer_reglas(lista_reglas);
	     
	}
	
	private Combinacion crear_regla(String tipo, ManejadorOperaciones m) {
		Combinacion regla = null;
		switch(tipo) {
			case "3":
				regla = new Combinacion_3(m);
				break;
			case "4":
				regla = new Combinacion_4(m);
				break;
			case "5":
				regla = new Combinacion_5(m);
				break;
			case "L":
				regla = new Combinacion_L(m);
				break;
			case "T":
				regla = new Combinacion_T(m);
				break;
		}
		return regla;
	}
	
	public Tablero crear_tablero(Juego j, ManejadorOperaciones m) {
		JSONArray dimensions = (JSONArray) jsonObject.get("dimensiones");
	    Long cant_filas = (Long) dimensions.get(0);
	    Long cant_columnas = (Long) dimensions.get(1);
	    
	    return m.crear_tablero(cant_filas.intValue(),cant_columnas.intValue());
	}


	public Nivel cargarEntidades(Tablero t, Juego juego) {
		Nivel nivel_a_cargar = new Nivel(2,2);
		JSONArray matriz = (JSONArray) jsonObject.get("entidades");
		Entidad entidad_a_insertar;
        int filas = matriz.size();
        int columnas = ((JSONArray) matriz.get(0)).size();
        for (int i = 0; i < filas; i++) {
            JSONArray fila = (JSONArray) matriz.get(i);
            for (int j = 0; j < columnas; j++) {
            	JSONArray entidad = (JSONArray) fila.get(j);
            	String gelatina = ((String) entidad.get(0));
            	if(gelatina.compareTo(Constantes.GELATINA)==0) {
            		Gelatina gelatina_a_insertar = crear_gelatina(t,entidad);
            		t.insertar_entidad_y_asociada(i,j,gelatina_a_insertar);
            		gelatina_a_insertar.establecer_posicion(i,j);
            		gelatina_a_insertar.obtener_caramelo_interno().establecer_posicion(i, j);
            	}else {
	            	entidad_a_insertar = crear_entidad(t,entidad);
	            	t.insertar(i, j,entidad_a_insertar);
	            	entidad_a_insertar.establecer_posicion(i,j);
            	}
                
               
            }
        }
        
        nivel_a_cargar.establecer_juego(juego);
        return nivel_a_cargar;   
	}

	
	public void cargar_tiempo(Nivel nivel) {
		  Long tiempo = (Long) jsonObject.get("tiempo");
	      nivel.establecer_tiempo(tiempo.intValue());
	}
	
	public void cargar_movimientos(Nivel nivel) {
		 Long movimientos = (Long) jsonObject.get("movimientos");
	     nivel.establecer_movimientos(movimientos.intValue());     
	}
	
	public void cargar_objetivos(Nivel nivel, TrackerObjetivo toTrack) {
        JSONArray objetivos_array = (JSONArray) jsonObject.get("objetivos");
        Long cantidad_objetivo = (Long) objetivos_array.get(0);
        JSONArray entidad_objetivo = (JSONArray) objetivos_array.get(1);
        String tipo = entidad_objetivo.get(1).toString();
        Entidad e1 = crear_entidad(null, entidad_objetivo);
        List<Objetivo> objetivos = new LinkedList<Objetivo>();
        if(tipo.compareTo(Constantes.CARAMELO)==0 ||tipo.compareTo(Constantes.GLASEADO)==0) {
        	objetivos.add(new ObjetivoColor(nivel, toTrack,e1 ,e1.obtener_color(), cantidad_objetivo.intValue()));
        }
        else {
        	objetivos.add(new ObjetivoEntidad(nivel, toTrack,e1 ,e1.obtener_color(), cantidad_objetivo.intValue()));
        }
        nivel.establecer_objetivos(objetivos);
	}
	
	private Entidad crear_entidad(Tablero t,JSONArray arreglo_entidad) {
	 	String tipo = ((String) arreglo_entidad.get(1));
   	 	String color = ((String) arreglo_entidad.get(2));
   	 	Color c = fabrica.fabricar_color(color);
   	 	Entidad entidad = fabrica.fabricar_entidad(t,tipo, c);
		return entidad;
	}
	
	private Gelatina crear_gelatina(Tablero t,JSONArray arregloEntidad) {
   	 	String color = ((String) arregloEntidad.get(2));
   	 	Color c = fabrica.fabricar_color(color);
   	 	Gelatina gelatina = fabrica.fabricar_gelatina(t, c);
		return gelatina;
		
	}	
	
}