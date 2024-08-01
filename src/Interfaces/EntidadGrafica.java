package Interfaces;

/**
 * Define los mensajes posibles de solicitar por sobre las entidades gráficas de la aplicación.
 * @author FJoaquin (federico.joaquin@cs.uns.edu.ar)
 *
 */
public interface EntidadGrafica {

	public void notificarse_intercambio();

	public void notificarse_cambio_foco();
	
	public void notificarse_detonacion();
	
	public void notificarse_cambio_visibilidad();
}
