package Vista;

import javax.swing.JFrame;

import Logica.Juego;
import Logica.RankingJugadores;

import javax.swing.JPanel;

import Auxiliares.Constantes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;


import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
/**
 * Menu de inicio con los botones para cargar los diferentes niveles
 */
public class MenuNiveles extends JFrame {
	 
	private static final long serialVersionUID = 1L;
	private Juego juego;
	private VentanaRanking ventana_ranking;
	private RankingJugadores ranking;
	private JPanel panel_menu;
	private static int[][] arreglo_posiciones_botones = {{10, 325, 63, 59}, {10, 223, 63, 68}, {22, 144, 63, 69},{125, 97, 69, 68},{213, 154, 76, 59}};

	public MenuNiveles(Juego j) {
		ranking = j.obtener_ranking();
		ventana_ranking = new VentanaRanking(this,ranking);
		setTitle("Candy Crush");
		setSize(new Dimension(700, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		juego = j;
		
		JPanel panel_sin_bot = new JPanel();

		panel_sin_bot.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel_sin_bot, BorderLayout.CENTER);
		panel_sin_bot.setLayout(null);

		panel_menu = new JPanel();
		
		panel_menu.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel_menu, BorderLayout.CENTER);
		panel_menu.setLayout(null);
		
		
		JLabel Imagen_no_bot = new JLabel("");
		Imagen_no_bot.setIcon(new ImageIcon(MenuInicio.class.getResource("/Imagenes/mundo.png")));
		Imagen_no_bot.setBounds(0, 0, 769, 671);
		panel_sin_bot.add(Imagen_no_bot);
		panel_sin_bot.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel_sin_bot, BorderLayout.CENTER);
		panel_sin_bot.setLayout(null);
		panel_menu.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel_menu, BorderLayout.CENTER);
		panel_menu.setLayout(null);

		
		JButton boton_ranking = new JButton("Mejores Jugadores");
		boton_ranking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mostrar_ranking();
			}
		});
		boton_ranking.setBounds(265, 595, 188, 23);
		panel_menu.add(boton_ranking);
		
		for (int i = 1; i <= Constantes.CANT_NIVELES; i++) {
			crear_boton_nivel(i);
		}
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuInicio.class.getResource("/Imagenes/mundo.png")));
		lblNewLabel.setBounds(0, 0, 769, 671);
		panel_menu.add(lblNewLabel);
		setVisible(true);
		  
	}

	 private void crear_boton_nivel(int nivel) {
	        JButton button = new JButton("");
	        button.setFocusPainted(false);
	        button.setBorderPainted(false);
	        button.setContentAreaFilled(false);

	        button.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                juego.iniciar_nivel(nivel);
	                juego.establecer_nivel_actual(nivel);
	                setVisible(false);
	            }
	        });
	        button.setBounds(arreglo_posiciones_botones[nivel-1][0],arreglo_posiciones_botones[nivel-1][1], arreglo_posiciones_botones[nivel-1][2], arreglo_posiciones_botones[nivel-1][3]);
	    	panel_menu.add(button);
	 }

	private void mostrar_ranking() {
		setVisible(false);
		ventana_ranking.mostrar();
	}
	
	public void cierre_de_juego() {
		juego.agregar_jugador_a_ranking();
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(Constantes.RANKING_ARCHIVO);
		    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		    objectOutputStream.writeObject(ranking);
		    objectOutputStream.flush();
		    objectOutputStream.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	new MenuInicio();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
}



