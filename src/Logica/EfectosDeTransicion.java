package Logica;

import java.util.LinkedList;
import java.util.List;

import Entidades.Entidad;

public class EfectosDeTransicion {
	
	protected List<Entidad> entidades_a_detonar;
	protected List<Entidad> entidades_a_incorporar;
	protected List<Entidad> entidades_a_reemplazar;
	
	public EfectosDeTransicion() {
		entidades_a_detonar = new LinkedList<Entidad>();
		entidades_a_incorporar = new LinkedList<Entidad>();
		entidades_a_reemplazar = new LinkedList<Entidad>();
	}
	
	public void agregar_entidad_a_detonar_y_reemplazar(Entidad entidad) {
		if(!entidades_a_detonar.contains(entidad) && !entidad.esta_detonada() && !entidades_a_reemplazar.contains(entidad)) {
			entidades_a_detonar.add(entidad);
			entidades_a_reemplazar.add(entidad);
			for(Entidad e: entidades_a_detonar) {
				if(e!=entidad && e.obtener_columna() == entidad.obtener_columna() && e.obtener_fila() == entidad.obtener_fila()) {
					entidades_a_reemplazar.remove(e);
					break;
				}
			}
		}
	}
	
	public void agregar_entidad_a_incorporar(Entidad entidad) {
		entidades_a_incorporar.add(entidad);
	}
	
	public void agregar_entidad_de_reemplazo(Entidad entidad) {
		for(Entidad e: entidades_a_reemplazar) {
			if(e.obtener_columna() == entidad.obtener_columna()) {
				entidades_a_reemplazar.remove(e);
				break;
			}
		}
		if(!entidades_a_incorporar.contains(entidad) && !entidad.esta_detonada())
			entidades_a_incorporar.add(entidad);
	}
	
	public boolean existen_entidades_a_detonar() {
		return !entidades_a_detonar.isEmpty();
	}
	
	public boolean existen_entidades_a_incorporar() {
		return !entidades_a_incorporar.isEmpty();
	}
	
	public boolean existen_entidades_a_reemplazar() {
		return !entidades_a_reemplazar.isEmpty();
	}
	
	public List<Entidad> entidades_a_detonar(){
		return entidades_a_detonar;
	}

	public List<Entidad> entidades_a_incorporar(){
		return entidades_a_incorporar;
	}
	
	public List<Entidad> entidades_a_reemplazar(){
		return entidades_a_reemplazar;
	}
}
