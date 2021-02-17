
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package presentacion;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import aplicacion.*;

/**
 * Clase Tablero
 */
public class Tablero extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private SPOOBInvadersGUI gui;
	ImageIcon imagen;
	private boolean jugando;
	private int ancho;
	private int alto;
	private long antes, tiempo, sleep;
	private JLabel scoreUno;
	private JLabel scoreDos;
	private SPOOBInvaders juego;
	private boolean pausa;
	private boolean stop;
	
	/**
	 * Constructor para Objetos de la Clase Tablero
	 * @param g SPOOBInvadersGUI
	 * @param alto int
	 * @param ancho int
	 */
	public Tablero(SPOOBInvadersGUI g, int alto, int ancho) {
		gui = g;
		jugando=false;
		this.ancho= ancho;
		this.alto= alto;	
	}
	
	/**
	 * Pinta cada objeto en el tablero de juego
	 * @param g Graphics
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		g.drawImage(imagen.getImage(),0,0,d.width,d.height,null);
		Font fuente=new Font("TimesRoman", Font.BOLD, 23);
		g.setFont(fuente);
		g.setColor(Color.WHITE);
		this.setOpaque(false);
		dibujeJugadores(g);
		dibujeDisparos(g);
		dibujeInvasores(g);
		dibujeBarreras(g);
		dibujePlatillo(g);
	}
	
	/**
	 * Dibuja las barreras em el tablero de juego
	 * @param g
	 */
	private void dibujeBarreras(Graphics g) {
		for(Objeto b: juego.getBarreras()) {
			if(b.esVisible()) {
				g.drawImage(b.getImagen(), b.getX(), b.getY(), null);
			}
		}
	}
	
	/**
	 * Dibuja cada invasor en el tablero de juego
	 * @param g Graphics
	 */
	private void dibujeInvasores(Graphics g) {
		Objeto[][] matriz= juego.getInvasores();
		for(Objeto[] o: matriz) {
			for(Objeto invasor: o) {
				if(invasor.esVisible()) {
					g.drawImage(invasor.getImagen(), invasor.getX(), invasor.getY(), null);
				}
			}
		}
	}
	
	/**
	 * Dibuja el platillo o nave en el tablero de juego
	 */
	private void dibujePlatillo(Graphics g) {
		Platillo p = juego.getPlatillo();
		if(p.esVisible()) {
			g.drawImage(p.getImagen(), p.getX(), p.getY(),null);
		}
	}
	
	
	/**
	 * Dibuja los diparos hechos
	 * @param g Graphics
	 */
	private void dibujeDisparos(Graphics g) {
		for(Jugador j: juego.getJugadores()) {
			if(j.disparoVisible()) {
				g.drawImage(j.getDisparo().getImagen(), j.getDisparo().getX(), j.getDisparo().getY(), null);
			}
		}
		for(Objeto[] l: juego.getInvasores()) {
			for(Objeto inv: l) {
				if(inv.getDisparo().esVisible()) {
					g.drawImage(inv.getDisparo().getImagen(), inv.getDisparo().getX(), inv.getDisparo().getY(), null);
				}
			}
		}
		Platillo p = juego.getPlatillo();
		if(p.getDisparo().esVisible()) {
			g.drawImage(p.getDisparo().getImagen(), p.getDisparo().getX(), p.getDisparo().getY(), null);
		}
	}
	
	/**
	 * Dibuja cada jugador en el tablero
	 * @param g Graphics
	 */
	private void dibujeJugadores(Graphics g) {
		for(Jugador j: juego.getJugadores()) {
			if(j.esVisible()) {
				g.drawImage(j.getImagen(), j.getX(), j.getY(), null);
			}
			g.drawString("Score: "+j.getScore(),j.getScorePos()[0], j.getScorePos()[1]);
			g.drawString("Lives: "+j.getLives(),j.getScorePos()[0], j.getScorePos()[1]+20);
			g.setColor(Color.RED);
		}
	}
	
	/**
	 * Inicia el juego 
	 * @param num int
	 */
	public void iniciaJuego(int num) {
		juego= new SPOOBInvaders(num, ancho);
		prepareElementos();
	}
	
	/**
	 * prepara los elementos para el tablero de juego
	 */
	public void prepareElementos() {
		setBackground(Color.BLACK);
		setLayout(null);
		imagen = new ImageIcon(getClass().getResource("/imagenes/space.jpg"));
		setSize(imagen.getIconWidth(), imagen.getIconHeight());
		scoreUno= new JLabel("Score: ");
		scoreUno.setLocation(15, 15);
		//score.setBackground(new java.awt.Color(0,0,0));
		//AudioClip audio= java.applet.Applet.newAudioClip(getClass().getResource("/sonidos/sound.wav"));
	}
	
	/**
	 * Se encarga de decidir si el juego continua
	 */
	public void continua() {
		pausa=false;
		stop=false;
		jugando= true;
		new Thread(this).start();
	}
	
	/**
	 * Hace que se mueva el jugador
	 * @param x int
	 * @param i int
	 */
	public void muevaJugador(int x, int i) {
		juego.muevaJugador(x,i, ancho);
	}
	
	/**
	 * Determina si el juego esta actualmente en juego
	 * @return
	 */
	public boolean enJuego() {
		return jugando;
	}	
	
	/**
	 * Realiza el disparo del jugador
	 * @param num int
	 */
	public void jugadorDispara(int num) {
		juego.jugadorDispara(num);
	}
	
	
	/**
	 * Realiza el disparo del jugador
	 * @param num int
	 */
	
	public int getNumJug() {
		return juego.numJug();
	}
	
	/**
	 * Metodo para pausar el juego
	 */
	public synchronized void pause() {
		pausa = true;
		gui.abraPausar();
	}
	
	/**
	 * metodo para despausar el juego
	 */
	public synchronized void resume() {
	pausa = false;
	gui.salirPausar();
	notify();
	}
	
	/**
	 * metodo para detener el juego
	 */
	public synchronized void stop() {
		stop = true;
		// If it was paused then resume and then stop
		notify();
		}
	
	/**
	 * Metodo run que se encarga de correr el juego
	 */
	public void run() {
		int cont=0;
		try {
			while(jugando && !stop) {
				
				synchronized(this) {
					if(pausa) {
						wait();
					}
				}
				juego.muevaInvasores(ancho);
				juego.disparaInvasor();
				juego.muevaPlatillo(ancho);
				juego.mireBarreras();
				if(juego.hanDisparado() && juego.numJug() <= 2) {
					for(Jugador j: juego.getJugadores()) {
						if(j.getDisparo().esVisible()) {
							juego.mireInvasores(j);
							juego.mirePlatillo(j);
						}
					}
					juego.muevaDisparo();			
				}if(juego.numJug() == 3) {
					for(Jugador j: juego.getJugadores()) {
						juego.simuleJugador(ancho);
						if(j.getDisparo().esVisible()) {
							juego.mireInvasores(j);
							juego.mirePlatillo(j);
						}
					}juego.muevaDisparo();	
				}
				if(juego.gana()) {
					JOptionPane.showMessageDialog(null, "Has ganado, pasas al siguiente nivel.");
					juego.hagaBarreras();
					juego.corrijaPosBarreras();
					juego.hagaMatriz();
					juego.hagaPlatillo();
				}else if(!juego.sigueJugando()) {
					jugando= false;
					JOptionPane.showMessageDialog(null, "Has perdido, vuelve a intentarlo");
				}
				Thread.sleep(5);
				repaint();
				cont++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
