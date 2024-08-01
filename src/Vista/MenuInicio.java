package Vista;

import javax.swing.JFrame;




import Logica.Juego;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

import Auxiliares.Constantes;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Menu de inicio con los botones para cargar los diferentes niveles
 */
public class MenuInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JTextField nombre_jugador;


	public MenuInicio() {
	
		setTitle("Candy Crush");
		setSize(new Dimension(700, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panel_sin_botones = new JPanel();

		panel_sin_botones.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel_sin_botones, BorderLayout.CENTER);
		panel_sin_botones.setLayout(null);

		JPanel panel = new JPanel();
		
		panel.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_con_botones = new JPanel();
		panel_con_botones.setBounds(197, 195, 269, 39);
		panel.add(panel_con_botones);

	
		JPanel panel_seleccionar = new JPanel();
		panel_seleccionar.setBounds(197, 251, 269, 64);
		panel.add(panel_seleccionar);
		JLabel label_seleccion = new JLabel("Seleccione el modo que desee jugar");
		panel_seleccionar.add(label_seleccion);
		
		JButton boton_caramelos = new JButton(Constantes.VERSION_CARAMELOS);
		boton_caramelos.setEnabled(false);
		panel_seleccionar.add(boton_caramelos);
		boton_caramelos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(boton_caramelos.isEnabled()) {
					boton_caramelos.setVisible(false);
					panel_seleccionar.setVisible(false);
					panel_sin_botones.setVisible(false);
					dispose();
					Juego juego = new Juego(nombre_jugador.getText(), Constantes.VERSION_CARAMELOS);
					juego.obtener_ventana().establecer_menu(new MenuNiveles(juego));
				}
			}
			});
		
		JButton boton_gemas = new JButton(Constantes.VERSION_GEMAS);
		boton_gemas.setEnabled(false);
		panel_seleccionar.add(boton_gemas);
		boton_gemas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(boton_gemas.isEnabled()) {
					boton_gemas.setVisible(false);
					panel_seleccionar.setVisible(false);
					panel_sin_botones.setVisible(false);
					dispose();
					Juego juego = new Juego(nombre_jugador.getText(), Constantes.VERSION_GEMAS);
					juego.obtener_ventana().establecer_menu(new MenuNiveles(juego));
				}
			}
		});
		JLabel cartel_nombre = new JLabel("Ingrese su nombre");
		panel_con_botones.add(cartel_nombre);
		
		nombre_jugador = new JTextField();
		panel_con_botones.add(nombre_jugador);
		nombre_jugador.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    boton_gemas.setEnabled(true);
                    boton_caramelos.setEnabled(true);
                }
			}
		});
		nombre_jugador.setToolTipText("Ingrese su nombre y presione enter");
		nombre_jugador.setColumns(10);

		JLabel Imagen_no_bot = new JLabel("");
		Imagen_no_bot.setIcon(new ImageIcon(MenuInicio.class.getResource("/Imagenes/mundoDesenfocado.png")));
		Imagen_no_bot.setBounds(0, 0, 769, 671);
		panel_sin_botones.add(Imagen_no_bot);
		panel_sin_botones.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel_sin_botones, BorderLayout.CENTER);
		panel_sin_botones.setLayout(null);
		panel.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MenuInicio.class.getResource("/Imagenes/mundoDesenfocado.png")));
		lblNewLabel.setBounds(0, 0, 769, 671);
		panel.add(lblNewLabel);
		setVisible(true);
		  
	}
}



