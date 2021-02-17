
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package presentacion;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import aplicacion.SPOOBInvaders;
import aplicacion.SPOOBInvadersException;

/**
 * Clase SPOOBInvadersGUI
 */
public class SPOOBInvadersGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private MenuInicio menuInicio;
	private MenuJugar menuJugar;
	private Tablero menuTablero;
	private MenuPausar menuPausa;
	private Instrucciones instrucciones;
	private final Set<Integer> pressed = new HashSet<Integer>();
	private Dimension tamanoPantalla=Toolkit.getDefaultToolkit().getScreenSize();
	private final int width = (int) tamanoPantalla.getWidth()/2;
	private final int height = 700;
	private final Dimension tamanoFinal  = new Dimension(width,height);
	private static CardLayout card = new CardLayout();
	private static Container contenedor; 
	private JFileChooser seleccionado = new JFileChooser();
	private File archivo;

	

	/**
	 * Constructor para Objetos de Clase SPOOBInvadersGUI
	 */
	public SPOOBInvadersGUI() {
		prepareElementos();
		prepareAcciones();

	}
	
	/**
	 * Crea las capas del juego y luego las agrega al contenedor 
	 */
	public void prepareElementos() {
		setTitle("Space POOB Invaders");
		setSize(tamanoFinal);
		setResizable(false);
		setLocationRelativeTo(null);

		contenedor= getContentPane();
		menuInicio= new MenuInicio(this);
		menuJugar= new MenuJugar(this);
		menuTablero= new Tablero(this, height, width);
		menuPausa= new MenuPausar(this);
		instrucciones= new Instrucciones(this);
		
		card.addLayoutComponent(menuInicio, "menuInicio");
		card.addLayoutComponent(menuJugar, "menuJugar");
		card.addLayoutComponent(menuTablero,"menuTablero");
		card.addLayoutComponent(menuPausa, "menuPausa");
		card.addLayoutComponent(instrucciones, "instrucciones");
		
		contenedor.add(menuInicio);
		contenedor.add(menuJugar);
		contenedor.add(menuTablero);
		contenedor.add(menuPausa);
		contenedor.add(instrucciones);
		contenedor.setLayout(card);
		card.show(contenedor, "menuInicio");
	}
	
	/**
	 * Retorna el ancho del tablero
	 * @return width int 
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Retorna el largo del tablero
	 * @return height int
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Prepara las acciones elementales del juego
	 */
	public void prepareAcciones() {	
		setFocusable(true);
		addWindowListener(new WindowAdapter(){
			/**
			 * Accion para salir del juego
			 */
			public void windowClosing(WindowEvent e){
				salga();
			}
		});
		addKeyListener(
				new KeyAdapter() {
					public void keyPressed(KeyEvent e) {
						int key=e.getExtendedKeyCode();
						pressed.add(key);
						if(pressed.size()>0) {
							/**
							 * Accion para mover el jugador 1 a la izquierda
							 */
							if(pressed.contains(e.VK_LEFT)){		
								if(menuTablero.enJuego()) {
									menuTablero.muevaJugador(-8,0);
								}
	                        }
							/**
							 * Accion para mover el jugador 1 a la derecha
							 */
	                        if(pressed.contains(e.VK_RIGHT)){
	                        	if(menuTablero.enJuego()) {
	                        		menuTablero.muevaJugador(8,0);
	                        	}
	                        }
	                        /**
	                         * Accion para que el jugador 2 dispare
	                         */
	                        if(pressed.contains(e.VK_W)){
	                        	if(menuTablero.enJuego()) {
	                        		menuTablero.jugadorDispara(1);
	                        	}                    
	                        }
	                        /**
							 * Accion para que el jugador 1 dispare
							 */
	                        if(pressed.contains(e.VK_UP)){
	                        	if(menuTablero.enJuego()) {
	                        		menuTablero.jugadorDispara(0);
	                        	}
	                        }
	                        /**
	                         * Accion para mover el jugador 2 a la derecha
	                         */
	                        if(pressed.contains(e.VK_D)){
	                        	if(menuTablero.enJuego()) {
	                        		menuTablero.muevaJugador(8,1);
	                        	}
	                        }
	                        /**
	                         * Accion para mover el jugador 2 a la izquierda
	                         */
	                        if(pressed.contains(e.VK_A)){
	                        	if(menuTablero.enJuego()) {
	                        		menuTablero.muevaJugador(-8,1);
	                        	}
	                        }
	                        /**
	                         * Accion para guardar una partida
	                         */
	                        if (e.VK_G == key){
                                //System.out.println("guardar");
                                menuTablero.pause();
                                guardar();
                                menuTablero.resume();
                                
                            }
	                        /**
	                         * Accion para pausar una partida
	                         */
	                        if (e.VK_P == key){
                                menuTablero.pause();
                                //menuTablero.abraPausar();
                            }
	                        /**
	                         * Accion para despausar una partida
	                         */
	                        if (e.VK_R == key){
                                menuTablero.resume();
                            }
						}
                       
                    }
					 public void keyReleased(KeyEvent e){
                         pressed.remove(e.getExtendedKeyCode());
                     }
                     public void keyTiped(KeyEvent ke){}
				});
	}
	
	/**
	 * Abre el menuJugar del juego
	 */
	public void abraJugar() {
		card.show(contenedor, "menuJugar");
	}
	
	/**
	 * Abre el menuTablero del juego
	 */
	public void abraTablero() {
			menuTablero.iniciaJuego(1);
			card.show(contenedor, "menuTablero");
			menuTablero.continua();
	}
	
	/**
	 * Abre el menu Pausar
	 */
	public void abraPausar() {
		card.show(contenedor, "menuPausa");
		//System.out.println("Entro");
	}
	
	/**
	 * Abre el menu de Instrucciones
	 */
	public void abraInstrucciones() {
		card.show(contenedor, "instrucciones");
	}
	
	/**
	 * Sale del menu pausar
	 */
	public void salirPausar() {
		card.show(contenedor, "menuTablero");
	}
	
	
	/**
	 * Inicia el juego para 1 jugador
	 * @param num
	 */
	public void inicieJuego(int num) {
		menuTablero.iniciaJuego(num);
	}
	
	/**
	 * Inicia el juego para 2 jugadores
	 */
	public void jVsj() {
		menuTablero.iniciaJuego(2);
		card.show(contenedor, "menuTablero");
		menuTablero.continua();
	}
	
	/**
	 * Inicia el juego para jugadoir vs computadora
	 */
	public void jVsc() {
		menuTablero.iniciaJuego(3);
		card.show(contenedor, "menuTablero");
		menuTablero.continua();
	}
	
	/**
	 * Abre la opcion de cargar el juego
	 */
	public void cargueJuego() {
		JOptionPane.showMessageDialog(null,"Colocar JFIleChooser");
	}
	
	/**
	 * Cierra el juego
	 */
	public void cierre() {
		System.exit(0);
	}
	
	/**
	 * Abre el menuInicial del juego
	 */
	public void muestreMenuInicial() {
		card.show(contenedor, "menuInicio");
	}
	
	public int getNumJug() {
		return menuTablero.getNumJug();
	}
	
	/**
	 * Cierra el juego si se confirma la opcion
	 */
	private void salga(){
		int answ = JOptionPane.showConfirmDialog(this, "Seguro quiere salir?", "aviso", JOptionPane.YES_NO_OPTION);
		if (answ == JOptionPane.YES_OPTION){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}else{
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}
	
	/**
	 * Metodo main, inicia el juego
	 * @param args
	 */
	public static void main(String[] args){
		SPOOBInvadersGUI gui= new SPOOBInvadersGUI();
		gui.setVisible(true);
	}
	
	/**
	 * Este metodo abre una partida ya guardada del juego
	 * @param seleccionado File
	 */
	public void abrir(File archivo) {
		try {
			seleccionado.addChoosableFileFilter(new FileNameExtensionFilter("*.dat","dat"));
			int number = seleccionado.showOpenDialog(this);
			if(number == JFileChooser.APPROVE_OPTION) {
				archivo = seleccionado.getSelectedFile();
				if(archivo != null) {
					SPOOBInvaders.abrir(archivo);
					JOptionPane.showMessageDialog(null, "El archivo ha sido abierto correctamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			
		}catch(SPOOBInvadersException e) {
			if (e.getMessage().equals(SPOOBInvadersException.ARCHIVO_VACIO)){
				JOptionPane.showMessageDialog(null,SPOOBInvadersException.ARCHIVO_VACIO,"Advertencia",JOptionPane.WARNING_MESSAGE);
			}else if (e.getMessage().equals(SPOOBInvadersException.ARCHIVO_NO_ENCONTRADO)){
				JOptionPane.showMessageDialog(null,SPOOBInvadersException.ARCHIVO_NO_ENCONTRADO,"Advertencia",JOptionPane.WARNING_MESSAGE);
			}else{JOptionPane.showMessageDialog(null,SPOOBInvadersException.ERROR,"Advertencia",JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	/**
	 * Este metodo guarda una partida en el directorio seleccionado
	 */
	public void guardar() {
		try {
			seleccionado.addChoosableFileFilter(new FileNameExtensionFilter("*.dat","dat"));
			int seleccion = seleccionado.showSaveDialog(this);
			if (seleccion == JFileChooser.APPROVE_OPTION) {
	            archivo = seleccionado.getSelectedFile();
				 if(archivo !=null){
					SPOOBInvaders.guardar(archivo);
					JOptionPane.showMessageDialog(null,"El archivo se ha guardado exitosamente","Informacion",JOptionPane.INFORMATION_MESSAGE);} 
					}
			
		}catch(SPOOBInvadersException ex){
			if (ex.getMessage().equals(SPOOBInvadersException.ARCHIVO_VACIO)){
				JOptionPane.showMessageDialog(null,SPOOBInvadersException.ARCHIVO_VACIO,"Advertencia",JOptionPane.WARNING_MESSAGE);
			}else if (ex.getMessage().equals(SPOOBInvadersException.ARCHIVO_NO_ENCONTRADO)){
				JOptionPane.showMessageDialog(null,SPOOBInvadersException.ARCHIVO_NO_ENCONTRADO,"Advertencia",JOptionPane.WARNING_MESSAGE);
			}else{JOptionPane.showMessageDialog(null,SPOOBInvadersException.ERROR,"Advertencia",JOptionPane.WARNING_MESSAGE);}
		}
	}

}
