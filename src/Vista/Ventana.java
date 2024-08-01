package Vista;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ManejadorAnimaciones.CentralAnimaciones;
import Entidades.Entidad;
import Interfaces.VentanaAnimable;
import Interfaces.VentanaNotificable;
import Logica.Juego;
import Logica.Objetivo;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Ventana principal del Candy Crush
 */
@SuppressWarnings("serial")
public class Ventana extends JFrame implements VentanaAnimable, VentanaNotificable{ 

	protected Juego mi_juego;
	
	protected int filas;
	protected int columnas;
	protected int animaciones_pendientes;
	protected boolean bloquear_intercambios;
	protected CentralAnimaciones central_animaciones;
	protected JLabel reloj;
	protected JLabel label_objetivo; 
	protected JLabel labelVidas; 
	protected JLabel movimientos;
	protected JLabel texto_superior;
	protected JLabel label_img_objetivo;
	protected JPanel panel_principal;
	private int size_label = 70;
	private MenuNiveles menu;
	private VentanaFinNivel ventana_fin_nivel;
	private JButton boton_menu;
	private String version;
	
	public Ventana(Juego j, int f, int c) {
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
		mi_juego = j;
		filas = f;
		columnas = c;
		central_animaciones = new CentralAnimaciones(this);
		version = "";
		animaciones_pendientes = 0;
		bloquear_intercambios = false;
		inicializar();
		
		}

	public void establecer_menu(MenuNiveles m) {
		menu = m;
		ventana_fin_nivel = new VentanaFinNivel(mi_juego, this,menu);
	}

	public void establecer_version(String v) {
		version = v;
	}

	protected void inicializar() {
			setTitle("Candy Crush");
			setSize(new Dimension(700, 700));
			setResizable(false);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			getContentPane().setLayout(new BorderLayout());
			
			panel_principal = new JPanel();
			panel_principal.setLayout(null);

			getContentPane().add(panel_principal);
		
			panel_principal.setBackground(new Color(206,93,159));
		
			panel_principal.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {	
					switch(e.getKeyCode()) {
						case KeyEvent.VK_LEFT: 	{ if (!bloquear_intercambios) mi_juego.mover_cursor(Juego.IZQUIERDA); break; }
						case KeyEvent.VK_RIGHT: { if (!bloquear_intercambios) mi_juego.mover_cursor(Juego.DERECHA); break; }
						case KeyEvent.VK_UP: 	{ if (!bloquear_intercambios) mi_juego.mover_cursor(Juego.ARRIBA);break; }
						case KeyEvent.VK_DOWN: 	{ if (!bloquear_intercambios) mi_juego.mover_cursor(Juego.ABAJO); break; }
						case KeyEvent.VK_W:		{ if (!bloquear_intercambios) mi_juego.mover_entidad(Juego.ARRIBA); break; }
						case KeyEvent.VK_S:		{ if (!bloquear_intercambios) mi_juego.mover_entidad(Juego.ABAJO); break; }
						case KeyEvent.VK_A:		{ if (!bloquear_intercambios) mi_juego.mover_entidad(Juego.IZQUIERDA); break; }
						case KeyEvent.VK_D:		{ if (!bloquear_intercambios) mi_juego.mover_entidad(Juego.DERECHA); break; } 
					}
				}
			});
		
				cargar_labels();
			
		}

	public void cargar_labels() {
			JLabel label_movimientos =new JLabel("MOVIMIENTOS DISPONIBLES");
			label_movimientos.setHorizontalAlignment(SwingConstants.CENTER);
			label_movimientos.setOpaque(true);
			label_movimientos.setBackground(new Color(218, 112, 214));
			label_movimientos.setForeground(new Color(255, 255, 255));
			label_movimientos.setBounds(438, 131, 200, 38);
			label_movimientos.setFont( new Font("Cambria", Font.PLAIN, 15));
			panel_principal.add(label_movimientos);
			
			movimientos =new JLabel("");
			movimientos.setForeground(new Color(255, 255, 255));
			movimientos.setHorizontalAlignment(SwingConstants.CENTER);
			
			movimientos.setBounds(628, 120, 60, 60);
			movimientos.setFont(new Font("Cambria", Font.PLAIN, 20));
			movimientos.setVisible(true);
			panel_principal.add(movimientos);
			
			
			JLabel label_tiempo =new JLabel("TIEMPO DISPONIBLE");
			label_tiempo.setHorizontalAlignment(SwingConstants.CENTER);
			label_tiempo.setOpaque(true);
			label_tiempo.setBackground(new Color(218, 112, 214));
			label_tiempo.setForeground(new Color(255, 255, 255));
			label_tiempo.setBounds(438, 71, 200, 38);
			label_tiempo.setFont( new Font("Cambria", Font.PLAIN, 20));
			panel_principal.add(label_tiempo);
			
			reloj =new JLabel("");
			reloj.setForeground(new Color(255, 255, 255));
			reloj.setHorizontalAlignment(SwingConstants.CENTER);
			label_objetivo =new JLabel("");
			reloj.setBounds(634, 71, 54, 38);
			reloj.setFont( new Font("Serif", Font.PLAIN, 20));
			panel_principal.add(reloj);
			
			JLabel label_vidas =new JLabel("VIDAS DISPONIBLES");
			label_vidas.setBounds(438, 11, 200, 38);
			label_vidas.setHorizontalAlignment(SwingConstants.CENTER);
			label_vidas.setOpaque(true);
			label_vidas.setBackground(new Color(218, 112, 214));
			label_vidas.setForeground(new Color(255, 255, 255));
			label_vidas.setFont( new Font("Cambria", Font.PLAIN, 20));
			label_vidas.setForeground(new Color(255, 255, 255));
			panel_principal.add(label_vidas);
			
			labelVidas = new JLabel("");
			labelVidas.setBounds(646, 11, 60, 38);
			labelVidas.setFont( new Font("Serif", Font.PLAIN, 20));
			labelVidas.setForeground(new Color(255, 255, 255));
			panel_principal.add(labelVidas);
			
			label_img_objetivo =new JLabel("");
		
			
			getContentPane().add(panel_principal, BorderLayout.CENTER);
			
			panel_principal.setFocusable(true);
			
			boton_menu = new JButton("Abandonar");
			boton_menu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			boton_menu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				
					limpiar_vista();
					setVisible(false);
					menu.setVisible(true);
					menu.setFocusable(true);
					mi_juego.abandonar();
					ventana_fin_nivel = new VentanaFinNivel(null,null,null);
				}
				
			});
			
			boton_menu.setBounds(270, 627, 130, 23);
			panel_principal.add(boton_menu);

		}
		
	@Override
	public void notificarse_animacion_en_progreso() {
		synchronized(this){
			animaciones_pendientes ++;
			bloquear_intercambios = true;
		}
	}

	@Override
	public void notificarse_animacion_finalizada() {
		synchronized(this){
			animaciones_pendientes --;
			bloquear_intercambios = animaciones_pendientes > 0;
		}
	}

	public void animar_intercambio(Celda celda) {
		central_animaciones.animar_intercambio(celda);
	}
		
	public void animar_cambio_foco(Celda celda) {
		central_animaciones.animar_cambio_foco(celda);
	}
	
	public void animar_detonacion(Celda celda) {
		central_animaciones.animar_detonacion(celda);
	}

	public void animar_cambio_visibilidad(Celda celda) {
		central_animaciones.animar_cambio_visibilidad(celda);
	}

	public void eliminar_celda(Celda celda) {
		panel_principal.remove(celda);
		panel_principal.repaint();
	}

	public Celda agregar_entidad(Entidad e) {
		Celda celda = new Celda(this, e, size_label);
		panel_principal.add(celda);
		return celda;
	}
	
	public void mostar_menu() {
	  	ventana_fin_nivel.ocultar();
	  	menu.setVisible(true);
	 }
	 
	public void ocultar_ventana_fin_nivel() {
	   	ventana_fin_nivel.ocultar();
	}
	
	public void limpiar_vista() {
			panel_principal.removeAll();
			cargar_labels();
			panel_principal.revalidate();
			panel_principal.repaint();
		    panel_principal.requestFocus();
		    reloj.setText("");
		    labelVidas.setText("");
			label_objetivo.setText("");
			movimientos.setText("");
			label_img_objetivo.repaint();
			setTitle("Candy Crush - Nivel "+ mi_juego.obtener_nro_nivel_actual());
		}

	public void actualizar_movimientos(int mov) {
		movimientos.setText(""+mov);
	}

	public void actualizar_vidas(int vidas) {
		labelVidas.setText(""+vidas);
	}
	
	public void agote_vidas() {
			setVisible(false);
			menu.setVisible(false);
			ventana_fin_nivel.setVisible(false);
			new VentanaPerdiste(menu);
	}
	
	public void actualizar_tiempo(int minutos, int segundos) {
		String tiempoFormateado = String.format("%02d:%02d", minutos, segundos);
		reloj.setText(tiempoFormateado);
	}
	
	public void finalizar_nivel(boolean superado) {
		setVisible(false);
	    ventana_fin_nivel.mostrar(superado);
	}
	
	public void actualizar_objetivos(int cantidad, Objetivo o) {
	   	label_objetivo.setText(cantidad+"");
	}
	
	public void niveles_superados() {
	    	setVisible(false);
			menu.setVisible(false);
			ventana_fin_nivel.setVisible(false);
	    	new VentanaFinJuego(this, menu);
	}
	
	public void cargar_objetivo(Objetivo o) {
			JLabel label1 =new JLabel("DEBES ROMPER");
			label1.setBounds(438, 189, 200, 38);
			label1.setHorizontalAlignment(SwingConstants.CENTER);
			label1.setOpaque(true);
			label1.setBackground(new Color(218, 112, 214));
			label1.setForeground(new Color(255, 255, 255));
			label1.setFont( new Font("Cambria", Font.PLAIN, 20));
			label1.setForeground(new Color(255, 255, 255));
			panel_principal.add(label1);
	    	label_objetivo.setText(o.obtener_cantidad()+" ");
	    	ImageIcon imgIcon = new ImageIcon(this.getClass().getResource("/Imagenes/objetivos/"+ version+"/"+o.obtener_entidad().toString()+"-"+o.obtener_entidad().obtener_color().toString()+".png"));
    		Image imgEscalada = imgIcon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    		ImageIcon iconoEscalado = new ImageIcon(imgEscalada);
    		label_img_objetivo.setIcon(iconoEscalado);
    		label_img_objetivo.setBounds(500, 250, 200, 50);
	    	panel_principal.add(label_img_objetivo);
	    	
			label_objetivo.setBounds(480, 250, 200, 50);
			label_objetivo.setFont( new Font("Serif", Font.PLAIN, 20));
			label_objetivo.setForeground(new Color(255, 255, 255));
			panel_principal.add(label_objetivo);
			
	    }

	}
