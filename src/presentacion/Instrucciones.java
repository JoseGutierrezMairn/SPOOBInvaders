
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package presentacion;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Clase Instrucciones
 */
public class Instrucciones extends JPanel{
	private static final long serialVersionUID = 1L;
	private JButton volver;
	private SPOOBInvadersGUI gui;
	ImageIcon imagen;
	ImageIcon titulo;
	ImageIcon pulpo;
	ImageIcon diez;
	ImageIcon cangrejo;
	ImageIcon treinta;
	ImageIcon calamar;
	ImageIcon cincuenta;
	ImageIcon platillo;
	ImageIcon doscientos;
	ImageIcon moverse;
	ImageIcon left;
	ImageIcon right;
	ImageIcon a;
	ImageIcon di;
	ImageIcon disparar;
	ImageIcon up;
	ImageIcon w;
	Container contentpane = getRootPane();
	
	/**
	 * Constructor para Objetos de Clase MenuInicial
	 * @param g SPOOBInvadersGUI
	 */
	public Instrucciones(SPOOBInvadersGUI g) {
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
		g.drawImage(titulo.getImage(),300,50,d.width-630,d.height-550,null);
		g.drawImage(pulpo.getImage(),15,230,d.width-900,d.height-640,null);
		g.drawImage(diez.getImage(),90,230,d.width-800,d.height-640,null);
		g.drawImage(cangrejo.getImage(),15,330,d.width-900,d.height-640,null);
		g.drawImage(treinta.getImage(),90,330,d.width-800,d.height-640,null);
		g.drawImage(calamar.getImage(),15,430,d.width-900,d.height-640,null);
		g.drawImage(cincuenta.getImage(),90,430,d.width-800,d.height-640,null);
		g.drawImage(platillo.getImage(),15,530,d.width-900,d.height-640,null);
		g.drawImage(doscientos.getImage(),90,530,d.width-800,d.height-640,null);
		g.drawImage(moverse.getImage(),560,230,d.width-800,d.height-640,null);
		g.drawImage(left.getImage(),520,310,d.width-850,d.height-640,null);
		g.drawImage(right.getImage(),700,310,d.width-850,d.height-640,null);
		g.drawImage(a.getImage(),520,370,d.width-900,d.height-640,null);
		g.drawImage(di.getImage(),700,370,d.width-900,d.height-640,null);
		g.drawImage(disparar.getImage(),560,450,d.width-800,d.height-640,null);
		g.drawImage(up.getImage(),520,520,d.width-900,d.height-640,null);
		g.drawImage(w.getImage(),700,520,d.width-900,d.height-640,null);
		this.setOpaque(false);
		super.paintComponent(g);
	}
	
	/**
	 * Prepara los elementos del menu Inicial
	 */
	private void prepareElementos() {
		setBackground(Color.BLACK);
		setLayout(null);
		
		imagen = new ImageIcon(getClass().getResource("/imagenes/space.jpg"));
		setSize(imagen.getIconWidth(), imagen.getIconHeight());
		titulo = new ImageIcon(getClass().getResource("/imagenes/titulo.png"));
		setSize(titulo.getIconWidth(), titulo.getIconHeight());
		pulpo = new ImageIcon(getClass().getResource("/imagenes/pulpo.png"));
		setSize(pulpo.getIconWidth(), pulpo.getIconHeight());
		diez = new ImageIcon(getClass().getResource("/imagenes/10puntos.png"));
		setSize(diez.getIconWidth(), diez.getIconHeight());
		cangrejo = new ImageIcon(getClass().getResource("/imagenes/cangrejo.png"));
		setSize(cangrejo.getIconWidth(), cangrejo.getIconHeight());
		treinta = new ImageIcon(getClass().getResource("/imagenes/30puntos.png"));
		setSize(treinta.getIconWidth(), treinta.getIconHeight());
		calamar = new ImageIcon(getClass().getResource("/imagenes/calamar.png"));
		setSize(calamar.getIconWidth(), calamar.getIconHeight());
		cincuenta = new ImageIcon(getClass().getResource("/imagenes/50puntos.png"));
		setSize(cincuenta.getIconWidth(), cincuenta.getIconHeight());
		platillo = new ImageIcon(getClass().getResource("/imagenes/nave.png"));
		setSize(platillo.getIconWidth(), platillo.getIconHeight());
		doscientos = new ImageIcon(getClass().getResource("/imagenes/200puntos.png"));
		setSize(doscientos.getIconWidth(), doscientos.getIconHeight());
		moverse = new ImageIcon(getClass().getResource("/imagenes/moverse.png"));
		setSize(moverse.getIconWidth(), moverse.getIconHeight());
		left = new ImageIcon(getClass().getResource("/imagenes/left.png"));
		setSize(left.getIconWidth(), left.getIconHeight());
		right = new ImageIcon(getClass().getResource("/imagenes/right.png"));
		setSize(right.getIconWidth(), right.getIconHeight());
		a = new ImageIcon(getClass().getResource("/imagenes/a.png"));
		setSize(a.getIconWidth(), a.getIconHeight());
		di = new ImageIcon(getClass().getResource("/imagenes/d.png"));
		setSize(di.getIconWidth(), di.getIconHeight());
		disparar = new ImageIcon(getClass().getResource("/imagenes/disparar.png"));
		setSize(disparar.getIconWidth(), disparar.getIconHeight());
		up = new ImageIcon(getClass().getResource("/imagenes/up.png"));
		setSize(up.getIconWidth(), up.getIconHeight());
		w = new ImageIcon(getClass().getResource("/imagenes/w.png"));
		setSize(w.getIconWidth(), w.getIconHeight());
		
		prepareBotones();
		prepareAcciones();  
	}
	
	/**
	 * Prepara los Botones del menu Inicial
	 */
	private void prepareBotones() {
		
		volver= new JButton(new ImageIcon(getClass().getResource("/imagenes/instruccionVolver.png")));
		add(volver);
		volver.setBounds(gui.getWidth()/2-50, gui.getHeight()/2+250, 100, 35);
	}
	
	/**
	 * Prepara las Acciones de juego del menu instrucciones
	 */
	private void prepareAcciones() {
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
