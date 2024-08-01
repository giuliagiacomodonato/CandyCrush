package Animadores;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import Interfaces.Animador;
import Vista.Celda;
import ManejadorAnimaciones.CentralAnimaciones;

public class AnimadorDetonacion extends Thread implements Animador {

	protected CentralAnimaciones manager;
	protected Celda celda_animada;
	protected int delay;
	protected int prioridad;
	protected String path_imagen_estado;
	protected boolean animacion_en_progreso;
	public AnimadorDetonacion(CentralAnimaciones manager, Celda celda, int delay) {
		this.manager = manager;
		this.celda_animada = celda;
		this.delay = delay;
		this.prioridad = PrioridadAnimaciones.PRIORIDAD_DETONACION;
		path_imagen_estado = celda.get_entidad_logica().obtener_imagen();
	}
	
	public Celda get_celda_asociada() {
		return celda_animada;
	}
	
	public int obtener_prioridad() {
		return prioridad;
	}

	public void comenzar_animacion() {
		
          start();
   
	}
	
	public void run() {
		int size_label = celda_animada.obtener_size();
		ImageIcon imagen_icon = new ImageIcon(this.getClass().getResource(path_imagen_estado));
		Image imagen_escalada = imagen_icon.getImage().getScaledInstance(size_label, size_label, Image.SCALE_REPLICATE);
		Icon icono_escalado = new ImageIcon(imagen_escalada);
		celda_animada.setIcon(icono_escalado);
		
		try {
			sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		celda_animada.eliminar_de_ventana();

		manager.notificarse_finalizacion_animador(this);
	}

}
