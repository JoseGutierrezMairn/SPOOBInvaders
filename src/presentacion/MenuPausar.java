
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
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import presentacion.SPOOBInvadersGUI;
import presentacion.Tablero;

/**
 * Clase Menu pausar
 */
public class MenuPausar extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton reiniciar;
	private JButton guardar;
	private JButton abrir;
	private JButton salir;
	private SPOOBInvadersGUI gui;
	private Tablero menuTablero;
	private File archivo;
	ImageIcon imagen;
	ImageIcon reparacion;
	
	
	/**
	 * Constructor para Objetos de la Clase MenuJugar
	 * @param g SPOOBInvadersGUI
	 */
	public MenuPausar(SPOOBInvadersGUI g) {
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
		g.drawImage(reparacion.getImage(),300,50,d.width-630,d.height-630,null);
		this.setOpaque(false);
		super.paintComponent(g);
	}
	
	/**
	 * Prepara los elemtos del menu pausar
	 */
	private void prepareElementos() {
		setLayout(null);
		imagen = new ImageIcon(getClass().getResource("/imagenes/space.jpg"));
		setSize(imagen.getIconWidth(), imagen.getIconHeight());
		reparacion = new ImageIcon(getClass().getResource("/imagenes/naveEnReparacion.png"));
		setSize(reparacion.getIconWidth(), reparacion.getIconHeight());
		prepareBotones();
		prepareAcciones();
	}
	
	/**
	 * Prepara los Botones del menu Pausar
	 */ 
	private void prepareBotones() {
		reiniciar= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonReiniciar.png")));
		guardar= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonGuardar.png")));
		abrir= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonAbrir.png")));
		salir= new JButton(new ImageIcon(getClass().getResource("/imagenes/volver.png")));
		
		add(reiniciar);
		add(guardar);
		add(abrir);
		add(salir);
		
		reiniciar.setBounds(gui.getWidth()/2-80, gui.getHeight()/2-100, 130, 30); 
		guardar.setBounds(gui.getWidth()/2-75, gui.getHeight()/2-60, 120, 30);
		abrir.setBounds(gui.getWidth()/2-65, gui.getHeight()/2-20, 100, 30);
		salir.setBounds(gui.getWidth()/2-65, gui.getHeight()/2+20, 100, 30); 
	}
	
	/**
	 * Prepara las Acciones del menu Pausar
	 */
	private void prepareAcciones() {
		/**
		 * 
		 */
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gui.getNumJug() == 1) {
					gui.abraTablero();
				}else if(gui.getNumJug() == 2) {
					gui.jVsj();
				}else if(gui.getNumJug() == 3) {
					gui.jVsc();
				}
				
				
			}
		});
		
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.guardar();
				
			}
		});
		
		abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.abrir(archivo);
				
			}
		});
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gui.abraJugar();
				
			}
		});
	}

}
