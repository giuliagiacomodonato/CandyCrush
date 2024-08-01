package Interfaces;

import java.util.List;
import Combinaciones.Combinacion;

import Logica.Fabrica;
import Logica.Tablero;

public interface ManejadorOperaciones {
	
	public Tablero obtener_tablero();

	public void fijar_jugador(int fila_destino, int columna_destino);
	
	public void mover_jugador(int direccion);
	
	public void intercambiar_entidades(int direccion);
	
	public void asociar_entidades_logicas_y_graficas();
	
	public Tablero crear_tablero(int fila, int columna);
	
	public void establecer_reglas(List<Combinacion> l);
	
	public Fabrica obtener_fabrica();
}
