package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class VentanaPerdiste extends JFrame{

	
	private static final long serialVersionUID = 1L;

	private MenuNiveles menu;
	public VentanaPerdiste(MenuNiveles m) {
		menu = m;
		menu.setVisible(false);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				menu.cierre_de_juego();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				menu.cierre_de_juego();
			}
		});
		setTitle("Candy Crush");
		setSize(new Dimension(700, 700));
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		JPanel panel_perdiste = new JPanel();

		panel_perdiste.setBackground(new Color(206,93,159));
		panel_perdiste.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel_perdiste, BorderLayout.CENTER);
		panel_perdiste.setLayout(null);
		
		JLabel label_usaste_vidas =new JLabel("USASTE TODAS TUS VIDAS. INTENTALO MÁS TARDE");
		label_usaste_vidas.setBounds(100, 550, 500, 38);
		label_usaste_vidas.setHorizontalAlignment(SwingConstants.CENTER);
		label_usaste_vidas.setOpaque(true);
		label_usaste_vidas.setBackground(new Color(218, 112, 214));
		label_usaste_vidas.setForeground(new Color(255, 255, 255));
		label_usaste_vidas.setFont( new Font("Cambria", Font.PLAIN, 20));
		label_usaste_vidas.setForeground(new Color(255, 255, 255));
		panel_perdiste.add(label_usaste_vidas);
		panel_perdiste.requestFocus();
		JLabel label_imagen_usaste_vidas = new JLabel("");
		ImageIcon img_icon = new ImageIcon(VentanaFinNivel.class.getResource("/Imagenes/caratriste.png"));
		Image img_escalada = img_icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH);
		Icon icono_escalado = new ImageIcon(img_escalada);
		label_imagen_usaste_vidas.setIcon(icono_escalado);
		label_imagen_usaste_vidas.setBounds(100, 30, 500, 500);
		panel_perdiste.add(label_imagen_usaste_vidas);
		setVisible(true);
		menu.setVisible(false);

	}

}
