package Interfaces;

/**
 * Define los mensaje posibles de solicitar por sobre las entidades lógicas de la aplicación.
 * @author FJoaquin (federico.joaquin@cs.uns.edu.ar)
 *
 */
public interface EntidadLogica {
	/**
	 * Obtiene la fila en la que se ubica la entidad lógica.
	 * @return el valor de la fila.
	 */
	public int obtener_fila();
	/**
	 * Obtiene la columna en la que se ubica la entidad lógica.
	 * @return el valor de la columna.
	 */
	public int obtener_columna();
	/**
	 * Obtiene la ruta donde se encuentra la imagen representativa de la entidad, en relación a su estado.
	 * @return la ruta hacia la imagen.
	 */
	public String obtener_imagen();
	
	public boolean obtener_visibilidad();
}
