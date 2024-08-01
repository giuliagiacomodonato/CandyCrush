package Entidades;

import java.util.HashSet;

import Auxiliares.Color;
import Auxiliares.Constantes;
import Logica.Tablero;

public class Gelatina extends Entidad{
	
	protected Caramelo caramelo_interno;
	
	public Gelatina(Tablero tablero,Color c, Caramelo caramelo) {
		super(tablero,c,"/Imagenes/gelatinas/", true);
		caramelo_interno = caramelo;
		
	}
	
	//Operaciones de match
	
	public boolean se_produce_match_con(Entidad e) {
		return e.aplica_match_con(this);
	}

	public boolean aplica_match_con(Caramelo c) {
		return c.aplica_match_con(this);
		
	}

	public boolean aplica_match_con(Potenciador potenciador) {
		return potenciador.aplica_match_con(this);
	}
	
	
	public boolean aplica_match_con(DobleRayado dobleRayado) {
		return dobleRayado.aplica_match_con(this);
	}

	public boolean aplica_match_con(Glaseado g) {
		return false;
	}
	
	public boolean aplica_match_con(Bomba b) {
		return false;
	}

	public boolean aplica_match_con(Gelatina g) {
		return g.obtener_caramelo_interno().aplica_match_con(this);
	}
	
	//Operaciones de detonacion
	
	public boolean puede_explotar_dos(Entidad e) {
		return e.explotan_dos(this);
		
	}

	public boolean explotan_dos(Caramelo caramelo) {
		return false;
	}
		
	public boolean explotan_dos(Potenciador potenciador) {
		return false;
	}
		
	public boolean explotan_dos(DobleRayado dobleRayado) {
		return caramelo_interno.obtener_color() == dobleRayado.obtener_color();
	}
		
	public boolean explotan_dos(Gelatina gelatina) {
		return false;
	}
		
	public boolean explotan_dos(Glaseado glaseado) {
		return false;
	}
		
	public boolean explotan_dos(Bomba bomba) {
		return false;
	}

	
	//Operaciones de intercambio

	@Override
	public boolean es_posible_intercambiar(Entidad entidad) {
		
		return entidad.puede_recibir(this);
	}

	@Override
	public boolean puede_recibir(Caramelo caramelo) {
		return caramelo_interno.puede_recibir(caramelo);
		
	}

	@Override
	public boolean puede_recibir(Glaseado glaseado) {
		return caramelo_interno.puede_recibir(glaseado);
		
	}
	
	public boolean reacciona_cuando_rompe_vecino() {
		return false;
	}

	@Override
	public boolean puede_recibir(Potenciador potenciador) {
		return caramelo_interno.puede_recibir(potenciador);
		
	}

	@Override
	public boolean puede_recibir(Gelatina gelatina) {
		return caramelo_interno.puede_recibir(gelatina.obtener_caramelo_interno());
	}

	@Override
	public boolean puede_recibir(Bomba bomba) {

		return caramelo_interno.puede_recibir(bomba);
	}

	

	@Override
	public boolean puede_recibir(DobleRayado dobleRayado) {

		return caramelo_interno.puede_recibir(dobleRayado);
	}

	@Override
	public void intercambiar(Entidad entidad) {
		entidad.intercambiar_con(this);
	}

	@Override
	public void intercambiar_con(Caramelo caramelo) {
		intercambiar_caramelo_y_gelatina(caramelo,this);
	}

	@Override
	public void intercambiar_con(Glaseado glaseado) {
	
		
	}

	
	
	@Override
	public void intercambiar_con(Potenciador potenciador) {
		
	}

	@Override
	public void intercambiar_con(Gelatina gelatina) {
		Caramelo caramelo_internoThis = caramelo_interno;
		Caramelo caramelo_internoGelatina = gelatina.obtener_caramelo_interno();
		this.caramelo_interno = caramelo_internoGelatina;
		gelatina.establecer_caramelo_interno(caramelo_internoThis);
		intercambiar_entidad_y_entidad(caramelo_internoThis, caramelo_internoGelatina);
	}

	@Override
	public void intercambiar_con(Bomba bomba) {
	
	}
	
	@Override
	public void intercambiar_con(DobleRayado dobleRayado) {
	
	}
	
	public Entidad agregarse_a_tracker() {
    	return caramelo_interno;
    }
	
	@Override
	public int obtener_puntaje() {
		return 10;
	}
	
	public void detonar() {
		detonada = true;
		entidad_grafica.notificarse_detonacion();
   	}

    public void enfocar() {
   		enfocada = true;
   		caramelo_interno.enfocar();
   	}
   	
   	public void desenfocar() {
   		enfocada = false;
   		caramelo_interno.desenfocar();
   	}
   		
	public HashSet<Entidad> obtener_entidades_afectadas_por_detonacion(){
		HashSet<Entidad> afectadas = new HashSet<Entidad>();
		afectadas.addAll(obtener_entidades_adyacentes_afectadas_por_detonacion());
		return afectadas;
	}
	
	public Caramelo obtener_caramelo_interno() {
		return caramelo_interno;
	}
	
	public void establecer_caramelo_interno(Caramelo caramelo) {
		caramelo_interno = caramelo;
	}

	public boolean esta_detonada() {
    	return detonada;
    }

	public void cambiar_posicion(int nueva_fila, int nueva_columna) {
		caramelo_interno.cambiar_posicion(nueva_fila, nueva_columna);
	}
	
	public String toString() {
		return Constantes.GELATINA;
		
	}
	
	public String obtener_imagen() {
		int indice = 0;
	    indice += (enfocada ? 1 : 0);
	    indice += (detonada ? 2 : 0);
	    return imagenes_representativas[indice];
	}
	
	protected void cargar_imagenes(String path_img) {
		imagenes_representativas = new String [4];
		imagenes_representativas[0] = path_img + "gelatina.png";
		imagenes_representativas[1] = path_img +"gelatina-resaltado.png";
		imagenes_representativas[2] = path_img + "explosion.gif";
		imagenes_representativas[3] = path_img + "explosion.gif";
	}
	
	public Color obtener_color() {
		return caramelo_interno.obtener_color();
	}
    
	protected HashSet<Entidad> obtener_entidades_adyacentes_afectadas_por_detonacion(){
		HashSet<Entidad> entidades_adyacentes_afectadas = new HashSet<Entidad>();
		
		if(tablero.en_rango(fila-1, columna) && tablero.obtener(fila-1, columna).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila-1, columna));
		
		if(tablero.en_rango(fila+1, columna) && tablero.obtener(fila+1, columna).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila+1, columna));
		
		if(tablero.en_rango(fila, columna-1) && tablero.obtener(fila, columna-1).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila, columna-1));
		
		if(tablero.en_rango(fila, columna+1) && tablero.obtener(fila, columna+1).reacciona_cuando_rompe_vecino())
			entidades_adyacentes_afectadas.add(tablero.obtener(fila, columna+1));
		
		entidades_adyacentes_afectadas.add(caramelo_interno);
		
		return entidades_adyacentes_afectadas;
 	}   
}
