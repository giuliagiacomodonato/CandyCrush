package Logica;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import java.util.TimerTask;

import javax.swing.SwingUtilities;

import Interfaces.UsuarioReloj;

public class Reloj {


    private Timer timer;
    private int tiempo_transcurrido;
  
    private List<UsuarioReloj> usuarios;
    
    public Reloj(int limite, UsuarioReloj u) {
        this.timer = new Timer();
        usuarios = new LinkedList<UsuarioReloj>();
        this.tiempo_transcurrido =limite * 1000;  // Convertir milisegundos a segundos
        int tiempoLimite = limite * 1000;  // Convertir milisegundos a segundos
        usuarios.add(u);
        int minutos = (int) tiempoLimite / 60;
        int segundos = (int) tiempoLimite % 60;
        u.actualizar_tiempo(minutos, segundos);
    }
    
   
    public void iniciar() {
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                tiempo_transcurrido--;
                if (tiempo_transcurrido >= 0) {
                	 tiempo_transcurrido -= 1000;  // Restar un segundo en milisegundos
                     int minutos = (int) tiempo_transcurrido / 60000;  // Convertir milisegundos a minutos
                     int segundos = (int) (tiempo_transcurrido % 60000) / 1000;  // Convertir milisegundos a segundos
                     
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                        	for(UsuarioReloj u: usuarios) {
                        		u.actualizar_tiempo(minutos, segundos);
                        	}
                        }
                    });
                    if (tiempo_transcurrido <= 0) {
                        notificar_finalizacion();
                        detener();
                    }
                }
            }
        };

        // Se ejecuta cada segundo
        timer.scheduleAtFixedRate(tarea, 0, 1000);
    }
  
    public void detener() {
        timer.cancel();
    }
    
    public int get_tiempo_transcurrido() {
        return tiempo_transcurrido;
    }

    private void notificar_finalizacion() {
    	for(UsuarioReloj u: usuarios) {
    		u.tiempo_terminado();
    	}
    	
    }
    
    public void agregar_usuario(UsuarioReloj u) {
 	   usuarios.add(u);
    }
     
}
