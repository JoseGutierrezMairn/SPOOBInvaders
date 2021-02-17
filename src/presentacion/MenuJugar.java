
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package presentacion;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Clase MenuJugar
 */
public class MenuJugar extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton unJugador;
	private JButton jVsj;
	private JButton jVsc;
	private JButton volver;
	private SPOOBInvadersGUI gui;
	ImageIcon imagen;
	
	/**
	 * Constructor para Objetos de la Clase MenuJugar
	 * @param g SPOOBInvadersGUI
	 */
	public MenuJugar(SPOOBInvadersGUI g) {
		gui=g;
		prepareElementos();
	}
		
	/**
	 * Pinta cada objeto en el tablero de juego
	 * @param g Graphics
	 */
	protected void paintComponent(Graphics g) {
		Dimension d = getSize();
		g.drawImage(imagen.getImage(),0,0,d.width,d.height,null);
		this.setOpaque(false);
		super.paintComponent(g);
	}
		
	/**
	 * prepara los elementos del menu Jugar
	 */
	private void prepareElementos() {
		setLayout(null);
		
		imagen = new ImageIcon(getClass().getResource("/imagenes/space.jpg"));
		setSize(imagen.getIconWidth(), imagen.getIconHeight());

		prepareBotones();
		prepareAcciones();
	}
	
	/**
	 * prepara los botones del menu Jugar
	 */
	private void prepareBotones() {
		unJugador= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonUnJugador.jpg")));
		jVsj= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonJugadorVsJugador.jpg")));
		jVsc= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonJugadorVsComputador.jpg")));
		volver= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonVolver.jpg")));
		
		add(unJugador);
		add(jVsj);
		add(jVsc);
		add(volver);
		
		unJugador.setBounds(gui.getWidth()/2-50, gui.getHeight()/2-100, 150, 30); 
		jVsj.setBounds(gui.getWidth()/2-100, gui.getHeight()/2-60, 250, 30);
		jVsc.setBounds(gui.getWidth()/2-120, gui.getHeight()/2-20, 300, 30);
		volver.setBounds(gui.getWidth()/2-25, gui.getHeight()/2+20, 100, 30); 
	}
	
	/**
	 * prepara las acciones de lmenu Jugar
	 */
	private void prepareAcciones() {
		/**
		 * abre el modo de un jugador
		 */
		unJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.abraTablero();
			}
		});
		
		/**
		 * Abre el modo jugador vs jugador
		 */
		jVsj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.jVsj();
			}
		});
		
		/**
		 * Abre el modo de jugador vs computadora
		 */
		jVsc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.jVsc();
			}
		});
		
		/**
		 * Vuelve al menu inicial del juego
		 */
		volver.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.muestreMenuInicial();
			}
		});
	}
}
