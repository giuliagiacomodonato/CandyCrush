package ManejadorAnimaciones;

import java.util.HashMap;

import java.util.LinkedList;
import java.util.List;
import Animadores.AnimadorCambioEstado;
import Animadores.AnimadorCambioFoco;
import Animadores.AnimadorDetonacion;
import Animadores.AnimadorMovimiento;
import Animadores.AnimadorVisibilidad;
import Animadores.PrioridadAnimaciones;
import Interfaces.Animador;
import Interfaces.VentanaNotificable;
import Vista.Celda;
import Vista.Ventana;

public class CentralAnimaciones implements ManejadorAnimaciones{
	
	protected VentanaNotificable ventana; 
	protected List<Animador> animadores_pendientes;
	protected HashMap<Celda, List<Animador>> mapeo_celda_animadores;
	protected int prioridad_ultimo_animador_lanzado;
	protected int cantidad_animadores_ultima_prioridad_lanzados;
	
	
	public CentralAnimaciones(Ventana ventana) {
		this.ventana = ventana;
		animadores_pendientes = new LinkedList<Animador>();
		mapeo_celda_animadores = new HashMap<Celda, List<Animador>>();
		prioridad_ultimo_animador_lanzado = PrioridadAnimaciones.PRIORIDAD_SIN_PRIORIDAD;
		cantidad_animadores_ultima_prioridad_lanzados = 0;
	}
	
	synchronized public void animar_intercambio(Celda celda) {
		Animador animador = new AnimadorMovimiento(this, 10, 25, celda);
		agregar_animador_y_lanzar_pendientes(animador);
	}
	
	synchronized public void animar_cambio_foco(Celda celda) {
		Animador animador = new AnimadorCambioFoco(this, celda);
		agregar_animador_y_lanzar_pendientes(animador);
	}

	synchronized public void animar_detonacion(Celda celda) {
		Animador animador = new AnimadorDetonacion(this, celda, 400);
		agregar_animador_y_lanzar_pendientes(animador);
	}
	

	
	synchronized public void animar_cambio_visibilidad(Celda celda) {
		Animador animador = new AnimadorVisibilidad(this, celda, 500);
		agregar_animador_y_lanzar_pendientes(animador);
	}
	
	synchronized public void animar_cambio_estado(Celda celda) {
		Animador animador = new AnimadorCambioEstado(this, celda);
		agregar_animador_y_lanzar_pendientes(animador);
	}
	
	synchronized protected void agregar_animador_y_lanzar_pendientes(Animador animador) {
		ventana.notificarse_animacion_en_progreso();
		animadores_pendientes.add(animador);
		lanzar_pendientes();
	}
	
	synchronized protected void lanzar_pendientes() {
		Animador animador;
		int prioridad_animador;
		
		while(existen_pendientes() && existe_proxima_animacion_preparada()) {
			animador = animadores_pendientes.remove(0);
			prioridad_animador = animador.obtener_prioridad();
			agregar_animador_en_progreso(animador);
			incrementar_animadores_activos_y_actualizar_ultima_prioridad_lanzado(prioridad_animador);
			animador.comenzar_animacion();
		}
	}
	
	synchronized protected void agregar_animador_en_progreso(Animador animador) {
		Celda celda_animador = animador.get_celda_asociada();
		List<Animador> animadores_celda = mapeo_celda_animadores.get(celda_animador);
		if (animadores_celda != null) {
			animadores_celda.add(animador);
		}else {
			animadores_celda = new LinkedList<Animador>();
			animadores_celda.add(animador);
			mapeo_celda_animadores.put(celda_animador, animadores_celda);
		}
	}
	
	synchronized protected void incrementar_animadores_activos_y_actualizar_ultima_prioridad_lanzado(int prioridad_ultimo) {
		prioridad_ultimo_animador_lanzado = prioridad_ultimo;
		cantidad_animadores_ultima_prioridad_lanzados ++;
	}
	
	synchronized protected boolean existen_pendientes() {
		return ! animadores_pendientes.isEmpty();
	}
	
	synchronized protected boolean existe_proxima_animacion_preparada() {
		boolean retorno = false;
		
		if (existen_animadores_activos()) {
			retorno = existe_preparada_con_animadores_activos();
		}else {
			retorno = existe_preparada_sin_animadores_activos();
		}
		return retorno;
	}
	
	synchronized protected boolean existen_animadores_activos() {
		return cantidad_animadores_ultima_prioridad_lanzados != 0;
	}
	
	synchronized protected boolean existe_preparada_con_animadores_activos() {
		Animador animador_en_tope_espera;
		Celda celda_animador;
		int prioridad_animador;
		boolean retorno = false;
		
		if (existen_pendientes()) {
			animador_en_tope_espera = animadores_pendientes.get(0);
			celda_animador = animador_en_tope_espera.get_celda_asociada();
			retorno = ! tiene_animadores_en_progreso(celda_animador);
			prioridad_animador = animador_en_tope_espera.obtener_prioridad();
			retorno = retorno && tiene_igual_prioridad_que_ultimo_animador_lanzado(prioridad_animador);
		}
		return retorno;
	}
	
	synchronized protected boolean existe_preparada_sin_animadores_activos() {
		return existen_pendientes();
	}

	synchronized protected boolean tiene_animadores_en_progreso(Celda celda) {
		boolean retorno = false;
		List<Animador> animadores_celda_pendientes = mapeo_celda_animadores.get(celda);
		if (animadores_celda_pendientes != null) {
			retorno = ! animadores_celda_pendientes.isEmpty();
		}
		return retorno;
	}
	
	synchronized protected boolean tiene_igual_prioridad_que_ultimo_animador_lanzado(int prioridad) {
		return prioridad == prioridad_ultimo_animador_lanzado;
	}
	
	synchronized public void notificarse_finalizacion_animador(Animador animador) {
		ventana.notificarse_animacion_finalizada();
		quitar_animador_en_progreso(animador);
		decrementar_animadores_activos_y_actualizar_ultima_prioridad_lanzado();
		lanzar_pendientes();
	}
	
	synchronized protected void quitar_animador_en_progreso(Animador animador) {
		Celda celda_animador = animador.get_celda_asociada();
		List<Animador> animadores_celda = mapeo_celda_animadores.get(celda_animador);
		animadores_celda.remove(animador);
	}
	
	synchronized protected void decrementar_animadores_activos_y_actualizar_ultima_prioridad_lanzado() {
		cantidad_animadores_ultima_prioridad_lanzados --;
		if (cantidad_animadores_ultima_prioridad_lanzados == 0)
			prioridad_ultimo_animador_lanzado = PrioridadAnimaciones.PRIORIDAD_SIN_PRIORIDAD;
	}
}
