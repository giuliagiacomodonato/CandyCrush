package Auxiliares;

public class Color {

	private static final int PUNTAJE_ROJO = 5;
	private static final int PUNTAJE_NARANJA =  15;
	private static final int PUNTAJE_AMARILLO = 20;
	private static final int PUNTAJE_VERDE = 10;
	private static final int PUNTAJE_AZUL = 20;
	private static final int PUNTAJE_VIOLETA = 25;
	private static final int PUNTAJE_BLANCO = 25;
	private static final int PUNTAJE_NEGRO = 0;
	private static final int PUNTAJE_GRIS = 0;
	
	public static Color ROJO = new Color(Constantes.ROJO, PUNTAJE_ROJO);
	public static Color AZUL = new Color(Constantes.AZUL, PUNTAJE_AZUL);
	public static Color VERDE = new Color(Constantes.VERDE, PUNTAJE_VERDE);
	public static Color VIOLETA = new Color(Constantes.VIOLETA, PUNTAJE_VIOLETA);
	public static Color NARANJA = new Color(Constantes.NARANJA, PUNTAJE_NARANJA);
	public static Color AMARILLO = new Color(Constantes.AMARILLO, PUNTAJE_AMARILLO);
	public static Color NEGRO = new Color(Constantes.NEGRO, PUNTAJE_NEGRO);
	public static Color BLANCO = new Color(Constantes.BLANCO, PUNTAJE_BLANCO);
	public static Color GRIS = new Color(Constantes.GRIS, PUNTAJE_GRIS);
		
		
	private int puntaje;
	private String nombre;
		
	public Color(String n, int p) {
		puntaje = p;
		nombre = n;
	}
		
	public String toString() {
		return nombre;
	}
		
	public int obtener_puntaje() {
		return puntaje;
	}

}
