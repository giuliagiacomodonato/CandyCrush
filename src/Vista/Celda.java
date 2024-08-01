package Vista;


import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import Interfaces.EntidadGrafica;
import Interfaces.EntidadLogica;
import Interfaces.VentanaAnimable;

/**
 
*/
@SuppressWarnings("serial")
public class Celda extends JLabel implements EntidadGrafica {
    protected int size_label;
    protected EntidadLogica entidad_logica;
    protected VentanaAnimable mi_ventana;

    public Celda(VentanaAnimable v, EntidadLogica b, int s) {
        super();
        entidad_logica = b;
        size_label = s;
        mi_ventana = v;
        setBounds(b.obtener_columna() * size_label, b.obtener_fila() * size_label, size_label, size_label);
        cambiar_imagen(entidad_logica.obtener_imagen());
    }
    
    
    public EntidadLogica get_entidad_logica() { 
    	return entidad_logica; 
    }

    public int obtener_size() { 
    	return size_label; 
    }

    protected void cambiar_imagen(String i) {
        ImageIcon img_icon = new ImageIcon(this.getClass().getResource(i));
        Image img_escalada = img_icon.getImage().getScaledInstance(size_label, size_label, Image.SCALE_SMOOTH);
        Icon icono_escalado = new ImageIcon(img_escalada);
        setIcon(icono_escalado);
        setVisible(false);
    }

 // Operaciones para Entidad Grafica (Celda <-- Entidad Lógica)
	
 	public void notificarse_intercambio(){
 		mi_ventana.animar_intercambio(this);
 	}
 	
 	public void notificarse_cambio_foco() {
 		mi_ventana.animar_cambio_foco(this);
 	}
 	
 	public void notificarse_detonacion() {
 		mi_ventana.animar_detonacion(this);
 	}

 	public void notificarse_cambio_visibilidad() {
 		mi_ventana.animar_cambio_visibilidad(this);
 	}

 	public void eliminar_de_ventana() {
		mi_ventana.eliminar_celda(this);
	}
 
    protected void fijar_imagen_escalada_para_celda(String path_imagen) {
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource(path_imagen));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(size_label, size_label, Image.SCALE_SMOOTH);
		Icon icono_imagen_escalado = new ImageIcon(imagen_escalada);
		setIcon(icono_imagen_escalado);
	}
    
 
}
