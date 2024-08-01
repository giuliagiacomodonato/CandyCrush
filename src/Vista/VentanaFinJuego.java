package Vista;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaFinJuego extends JFrame{

	
	private static final long serialVersionUID = 1L;

	private MenuNiveles menu;
	private Ventana mi_ventana;
	public VentanaFinJuego(Ventana ventana, MenuNiveles m) {
		mi_ventana = ventana;
		menu = m;
		menu.setVisible(false);
	
		setTitle("Candy Crush");
		setSize(new Dimension(700, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel();

		panel.setBackground(new Color(206,93,159));
		panel.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(null);
		
		JLabel label_ganaste =new JLabel("FELICITACIONES, GANASTE TODOS LOS NIVELES");
		label_ganaste.setBounds(100, 550, 500, 38);
		label_ganaste.setHorizontalAlignment(SwingConstants.CENTER);
		label_ganaste.setOpaque(true);
		label_ganaste.setBackground(new Color(218, 112, 214));
		label_ganaste.setForeground(new Color(255, 255, 255));
		label_ganaste.setFont( new Font("Cambria", Font.PLAIN, 20));
		label_ganaste.setForeground(new Color(255, 255, 255));
		panel.add(label_ganaste);
		panel.requestFocus();
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img_icon = new ImageIcon(VentanaFinNivel.class.getResource("/Imagenes/ganasteElJuego.png"));
		Image img_escalada = img_icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		Icon icono_escalado = new ImageIcon(img_escalada);
		lblNewLabel.setIcon(icono_escalado);
		lblNewLabel.setBounds(100, 30, 500, 500);
		panel.add(lblNewLabel);
		
		JButton boton_menu = new JButton("Menu");
		boton_menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				mi_ventana.limpiar_vista();
				setVisible(false);
				menu.setVisible(true);
				menu.setFocusable(true);
				
			}
		});
		boton_menu.setBounds(325, 601, 91, 23);
		panel.add(boton_menu);
		setVisible(true);
		menu.setVisible(false);

	}
}
