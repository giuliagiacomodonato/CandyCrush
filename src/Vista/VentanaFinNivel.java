package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Logica.Juego;

public class VentanaFinNivel extends JFrame{
	
	JButton boton_siguiente_nivel;
	JButton boton_reiniciar_nivel;
	private Juego mi_juego;
	private Ventana mi_ventana;
	private MenuNiveles mi_menu;
	private JPanel panel;
	private static final long serialVersionUID = 1L;
	private ImageIcon imgIcon_fin_nivel;
	private JLabel imagen_fin_nivel;
	
	public VentanaFinNivel(Juego j, Ventana ventana, MenuNiveles m) {
		setTitle("Candy Crush");
		setSize(new Dimension(700, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		mi_ventana = ventana;
		setVisible(false);
		mi_menu = m;
		mi_juego = j;
		panel = new JPanel();

		panel.setBackground(new Color(206,93,159));
		panel.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		JButton boton_menu = new JButton("Menu");
		boton_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				mi_juego.obtener_nivel_actual().terminar();
				mi_ventana.limpiar_vista();
				setVisible(false);
				mi_menu.setVisible(true);
				mi_menu.setFocusable(true);
				imagen_fin_nivel.setIcon(null);
				
			}
			
		});
		
		boton_menu.setBounds(194, 575, 134, 23);
		panel.add(boton_menu);
		boton_siguiente_nivel = new JButton("Siguiente nivel");
		 boton_siguiente_nivel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				mi_juego.obtener_nivel_actual().terminar();
				int proximo_nivel =mi_juego.obtener_nro_nivel_actual()+1;
				mi_juego.iniciar_nivel(proximo_nivel);
				mi_juego.establecer_nivel_actual(proximo_nivel);
				setVisible(false);
				imagen_fin_nivel.setIcon(null);
			}
			
		});
		
		 boton_siguiente_nivel.setBounds(338, 575, 134, 23);
		 boton_siguiente_nivel.setVisible(false);
			panel.add(boton_siguiente_nivel);
		
		boton_reiniciar_nivel = new JButton("Volver a intentar");
		 boton_reiniciar_nivel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int  proximo_nivel =mi_juego.obtener_nro_nivel_actual();
				mi_juego.iniciar_nivel(proximo_nivel);
				mi_juego.establecer_nivel_actual(proximo_nivel);
				setVisible(false);
				imagen_fin_nivel.setIcon(null);
			}
			
		});
		
		 boton_reiniciar_nivel.setBounds(338, 575, 134, 23);

			panel.add( boton_reiniciar_nivel);
		 boton_reiniciar_nivel.setVisible(false);
		 
	}
	public void mostrar(boolean superado) {
		imagen_fin_nivel = new JLabel("");
		if(superado) {
			 boton_siguiente_nivel.setVisible(true);
			 boton_reiniciar_nivel.setVisible(false);

	    		imgIcon_fin_nivel = new ImageIcon(VentanaFinNivel.class.getResource("/Imagenes/ganaste.png"));
	    		Image img_escalada = imgIcon_fin_nivel.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
	    		Icon icono_escalado = new ImageIcon(img_escalada);
				imagen_fin_nivel.setIcon(icono_escalado);
				imagen_fin_nivel.setBounds(150, 30, 500, 500);
				panel.add(imagen_fin_nivel);
		}
		else {
			boton_siguiente_nivel.setVisible(false);
			boton_reiniciar_nivel.setVisible(true);
    		imgIcon_fin_nivel = new ImageIcon(VentanaFinNivel.class.getResource("/Imagenes/gameover.png"));
    		Image img_escalada = imgIcon_fin_nivel.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
    		Icon icono_escalado = new ImageIcon(img_escalada);
			imagen_fin_nivel.setIcon(icono_escalado);
			imagen_fin_nivel.setBounds(100, 30, 500, 500);
			panel.add(imagen_fin_nivel);
		}
		panel.requestFocus();
		  
		 setVisible(true);
	}
	public void ocultar() {
		setVisible(false);
	}

}

