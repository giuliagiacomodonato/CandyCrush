package Combinaciones;



public class ComparadorCombinacion<E extends Combinacion> implements java.util.Comparator<E> {
	
	public int compare(E a, E b) {
		return (int) (a.obtener_prioridad() - b.obtener_prioridad());   //SI DA NEGATIVO, EL PRIMERO ES MENOR AL SEGUNDO
	}
	
}
