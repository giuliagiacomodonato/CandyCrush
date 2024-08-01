package Vista;

import java.awt.BorderLayout;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import Auxiliares.Pair;
import Logica.Jugador;
import Logica.RankingJugadores;
import javax.swing.JTable;
import javax.swing.JButton;

public class VentanaRanking extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuNiveles menu;
	private RankingJugadores ranking;
	private DefaultTableModel model;
	private JTable table;

	private JScrollPane scroll_pane;
	VentanaRanking(MenuNiveles m, RankingJugadores r) {
		menu = m;
		ranking = r;
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
		JPanel panel = new JPanel();

		panel.setBackground(new Color(206,93,159));
		panel.setPreferredSize(new Dimension(700, 500) );
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel label1 =new JLabel("MEJORES JUGADORES");
		label1.setBounds(111, 63, 500, 38);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setOpaque(true);
		label1.setBackground(new Color(218, 112, 214));
		label1.setForeground(new Color(255, 255, 255));
		label1.setFont( new Font("Cambria", Font.PLAIN, 20));
		label1.setForeground(new Color(255, 255, 255));
		getContentPane().add(label1);

		model=new DefaultTableModel();
		getContentPane().setLayout(null);
		String columnas[]= {"JUGADOR","PUNTAJE"};
		model.setColumnIdentifiers(columnas);
		table = new JTable(model);
		table.setBounds(111, 150, 500, 150);
		
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		
		
		
		scroll_pane= new JScrollPane();
		scroll_pane.setEnabled(false);
		scroll_pane.setBounds(111, 150, 500, 150);
		scroll_pane.setBackground(new Color(206,93,159));
		scroll_pane.setViewportView(table);
	
		
		table.getTableHeader().setReorderingAllowed(false) ;
		getContentPane().add(scroll_pane);
		getContentPane().setBackground(new Color(206,93,159));
		
		 JButton boton_menu = new JButton("Menu");
			boton_menu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					setVisible(false);
					menu.setVisible(true);
					menu.setFocusable(true);
					
				}
				
			});
			
			boton_menu.setBounds(300, 575, 100, 30);
			getContentPane().add(boton_menu, BorderLayout.SOUTH);

	}

	public void mostrar() {
	    setVisible(true);
	    if (model.getRowCount() > 0) {
	        for (int i = model.getRowCount() - 1; i >= 0; i--) {
	            model.removeRow(i);
	        }
	    }

	    List<Pair<Integer, Jugador>> mejores_jugadores = ranking.obtener_jugadores();
	   
	    for (Pair<Integer, Jugador>  par : mejores_jugadores) {
	        Jugador jugador = par.getSecond();
	        int puntaje = par.getFirst();
	        model.addRow(new Object[]{jugador.obtener_nombre(), Integer.toString(puntaje)});
	      
	    }
	}

	
}
