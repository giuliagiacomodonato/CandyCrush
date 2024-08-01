package Logica;


import java.util.Random;




import Auxiliares.Color;
import Auxiliares.Constantes;
import Entidades.Bomba;
import Entidades.Caramelo;
import Entidades.DobleRayado;
import Entidades.Entidad;
import Entidades.Envuelto;
import Entidades.Gelatina;
import Entidades.Glaseado;
import Entidades.Horizontal;
import Entidades.Vertical;


public class Fabrica {
	private static String version;
	public Fabrica(String v) {
		version = v;
	}
	
	public  Color fabricar_color(String color) {
		Color c = null;
		switch(color){
	 		case Constantes.AZUL:
	 			c = Color.AZUL;
	 			break;
	 		case Constantes.VERDE:
	 			c = Color.VERDE;
	 			break;
	 		case Constantes.AMARILLO:
	 			c = Color.AMARILLO;
	 			break;
	 		case Constantes.VIOLETA:
	 			c = Color.VIOLETA;;
	 			break;
	 		case Constantes.NARANJA:
	 			c = Color.NARANJA;
	 			break;
	 		case Constantes.ROJO:
	 			c = Color.ROJO;
	 			break;
	 		case Constantes.BLANCO:
	 			c = Color.BLANCO;
	 			break;
	 		case Constantes.NEGRO:
	 			c = Color.NEGRO;
	 			break;
	 		case Constantes.GRIS:
	 			c = Color.GRIS;
	 			break;
	 	}
		return c;
	}
	
	public Gelatina fabricar_gelatina(Tablero tablero,Color color) {
		Caramelo caramelo = new Caramelo(tablero,color,version);
		Gelatina gelatina = new Gelatina(tablero,fabricar_color(Constantes.GRIS),caramelo);
		return gelatina;
	}
	
	public  Entidad fabricar_entidad(Tablero t,String tipo, Color c) {
		Entidad entidad =null;
		switch(tipo){
			case Constantes.CARAMELO:
				entidad = new Caramelo(t,c,version);
				break;
			case Constantes.VERTICAL:
				entidad = new Vertical(t,c,version);
				break;
			case Constantes.ENVUELTO:
				entidad = new Envuelto(t,c,version);
				break;
			case Constantes.HORIZONTAL:
				entidad = new Horizontal(t,c,version);
				break;
			case Constantes.BOMBA:
				Random random = new Random();
				int numero_al_azar = random.nextInt(46) + 10;
				entidad = new Bomba(t, numero_al_azar,version);
				break;
			case Constantes.DOBLERAYADO:
				entidad = new DobleRayado(t,c,version);
				break;
			case Constantes.GLASEADO:
				entidad = new Glaseado(t,c,version);
				break;
		}
		return entidad;
	}
	

	
	public Caramelo fabricar_caramelo_aleatorio(Tablero t) {
		Random randomizador = new Random();
		Color[] colores = { Color.AMARILLO, Color.VERDE, Color.VIOLETA, Color.AZUL, Color.NARANJA, Color.ROJO};
		Color color_aleatorio = colores[randomizador.nextInt(0, colores.length)];
		Caramelo caramelo_nuevo = new Caramelo(t,color_aleatorio, version);
		return caramelo_nuevo;
	}


}
