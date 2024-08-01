package Animadores;

import java.awt.Image;


import javax.swing.Icon;
import javax.swing.ImageIcon;

import Interfaces.Animador;
import ManejadorAnimaciones.CentralAnimaciones;
import Vista.*;

/**
 * Modela el comportamiento de un animador que permite visualizar el cambio de estado de una entidad.
 * Cuando el animador comienza su animación, modifica la imagen asociada a la celda animada.
 * La imagen que se considerará para efectivizar el cambio de estado, será la que se encontraba asociada a la celda lógica al momento
 * de crear el animador.
 * Una vez finalizada la animación, el animador notificará a su manager de esta situación.
 * @author FJoaquin (federico.joaquin@cs.uns.edu.ar)
 *
 */
public class AnimadorCambioEstado implements Animador, Comparable<Animador> {

	protected CentralAnimaciones mi_manager;
	protected Celda mi_celda_animada;
	private final int prioridad = 1;
	protected String path_img;
	
	/**
	 * Inicializa el estado interno del animador, considerando:
	 * @param m El manejador de animaciones al que le notificará el fin de la animación, cuando corresponda.
	 * @param c La celda animada.
	 */
	public AnimadorCambioEstado(CentralAnimaciones m, Celda c) {
		mi_manager = m;
		mi_celda_animada = c;
		path_img = c.get_entidad_logica().obtener_imagen();
	}


	    @Override
	    public int compareTo(Animador otro) {
	        // Define tu lógica de comparación aquí
	        return Integer.compare(this.prioridad, otro.obtener_prioridad());
	    }
	    
	    public int obtener_prioridad() {
	    	return prioridad;
	}
	
	@Override
	public Celda get_celda_asociada() {
		return mi_celda_animada;
	}

	@Override
	public void comenzar_animacion() {
		int size_label = mi_celda_animada.obtener_size();
		ImageIcon img_icon = new ImageIcon(this.getClass().getResource(path_img));
		Image img_escalada = img_icon.getImage().getScaledInstance(size_label, size_label, Image.SCALE_SMOOTH);
		Icon icono_escalado = new ImageIcon(img_escalada);
		mi_celda_animada.setIcon(icono_escalado);
		mi_celda_animada.repaint();
		mi_manager.notificarse_finalizacion_animador(this);
	}

}