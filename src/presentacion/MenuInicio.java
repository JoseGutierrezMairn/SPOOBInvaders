
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

/**
 * Clase MenuInicial
 */
public class MenuInicio extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton jugar;
	private JButton cargarJuego;
	private JButton cerrar;
	private JButton instrucciones;
	private SPOOBInvadersGUI gui;
    private File archivo;
    private JFileChooser seleccionado= new JFileChooser();
	ImageIcon imagen;
	Container contentpane = getRootPane();
	
	/**
	 * Constructor para Objetos de Clase MenuInicial
	 * @param g SPOOBInvadersGUI
	 */
	public MenuInicio(SPOOBInvadersGUI g) {
		gui= g;
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
	 * Prepara los elementos del menu Inicial
	 */
	private void prepareElementos() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		imagen = new ImageIcon(getClass().getResource("/imagenes/fondo.jpg"));
		setSize(imagen.getIconWidth(), imagen.getIconHeight());
		
		prepareBotones();
		prepareAcciones();  
	}
	
	/**
	 * Prepara los Botones del menu Inicial
	 */
	private void prepareBotones() {
		
		jugar= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonJugar.jpg")));
		cargarJuego= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonCargarJuego.jpg")));
		cerrar= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonSalir.jpg")));
		instrucciones= new JButton(new ImageIcon(getClass().getResource("/imagenes/buttonInstrucciones.png")));
		
		add(jugar);
		add(cargarJuego);
		add(cerrar);
		add(instrucciones);
		
		jugar.setBounds(gui.getWidth()/2 -50, gui.getHeight()/2+200, 120, 35);
		cargarJuego.setBounds(gui.getWidth()/2-270, gui.getHeight()/2+200, 200, 35);
		cerrar.setBounds(gui.getWidth()/2 +90, gui.getHeight()/2+200, 100, 35);
		instrucciones.setBounds(gui.getWidth()/2 -100, gui.getHeight()/2+250, 200, 35);
	}
	
	/**
	 * Prepara las Acciones del menu Inicial
	 */
	private void prepareAcciones() {
		/**
		 * Abre el modo Jugar del menu Jugar
		 */
		jugar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.abraJugar();
			}
		});
		
		/**
		 * Cierra el juego
		 */
		cerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.cierre();
			}
		});
		
		/**
		 * Carga el juego 
		 */
		cargarJuego.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.abrir(archivo);
			}
		});
		

		/**
		 * Carga las instrucciones
		 */
		instrucciones.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				gui.abraInstrucciones();
			}
		});
	}
}
