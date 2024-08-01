package Interfaces;

import Entidades.Entidad;

public interface TableroJuego{

	public int cant_filas();
	
	public int cant_columnas();
	
	public void insertar(int f, int c, Entidad e);
	
	public Entidad obtener(int f, int c);
	
	public void resetar_tablero(int f, int c);
	
	
}
