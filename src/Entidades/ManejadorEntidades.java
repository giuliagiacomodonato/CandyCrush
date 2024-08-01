package Entidades;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import Logica.Juego;
import Logica.Tablero;

public class ManejadorEntidades extends Thread{

	
    private Timer timer;
    private int tiempo_transcurrido;
    private Tablero mi_tablero;
    private Juego mi_juego;
    
    public ManejadorEntidades(Juego juego) {
        timer = new Timer();
        tiempo_transcurrido = 0;
        mi_tablero = juego.obtener_manejador().obtener_tablero();
        mi_juego = juego;
    }
    
    public void iniciar() {
    	ManejadorEntidades manejador = this;
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                tiempo_transcurrido++;
               SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                      for(int i = 0; i<mi_tablero.cant_filas(); i++) {
                    	  for(int j = 0; j<mi_tablero.cant_columnas(); j++) {
                    		  mi_tablero.obtener(i, j).tick(manejador);
                    	  }
                      }
                    }
                });
                
            }
        };

        // Programar la tarea para ejecutarse cada segundo
        timer.scheduleAtFixedRate(tarea, 0, 1000);
    }

    public int obtener_tiempo_transcurrido() {
    	return tiempo_transcurrido;
    }
    
    public void detener() {
        timer.cancel();
        mi_juego.finalizar_nivel(false);
    }

    
}
