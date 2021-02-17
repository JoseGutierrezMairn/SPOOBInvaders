
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package aplicacion;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * Clase Jugador
 */
public class Jugador extends Objeto{
	private static final long serialVersionUID = 1L;
	private int[] pos;
	private boolean meDieron;
	private int vidas;
	private int num;
	private String imagenJugador= "src/imagenes/jugador.png";
	private ArrayList<String> imagenes;
	private int score;
	private int[] scorePos;
	private Objeto obj;
	
	/**
	 * Constructor para Objetos de la Clase Jugador
	 * @param num int
	 * @param ancho int 
	 */
	public Jugador(int num, int ancho, int[]p) {
		score=0;
		vivo= true;
		creePos();
		this.num= num;
		setX(p[0]);	
		setY(600);
		scorePos= new int[] {0,0};
		if(num==0) {
			scorePos[0]=10;
			scorePos[1]=20;
		}else {
			scorePos[0]= ancho-120;
			scorePos[1]= 20;
		}
		vidas=3;
		meDieron=false;
		obj= new DisparoNormal(new int[] {getX()+22, getY()-15}, true);
		imagenes= new ArrayList<String>();
		guardeImagenes();
		obj.setVisible(false);
		ImageIcon imagen = new ImageIcon(imagenJugador);
		cambieImagen(imagen.getImage());
	}
	
	/**
	 * Retorna La Posicion del score
	 * @return scorePos int[]
	 */
	public int[] getScorePos() {
		return scorePos;
	}
	
	/**
	 * Agrega las imagenes de cada jugador
	 */
	public void guardeImagenes() {
		imagenes.add("src/imagenes/jugador.png");
		imagenes.add("src/imagenes/jugadorDos.png");	
	}

	/**
	 * este metodo realiza el disparo de parte de los jugadores
	 */
	public void dispare(String type) {
		if(type.equals("rojo")) {
			obj= new DisparoRojo(new int[] {getX()+22, getY()-15}, true);
		}else if(type.equals("normal")){
			obj= new DisparoNormal(new int[] {getX()+22, getY()-15}, true);
		}
		creeDisparo(obj);
		obj.setVisible(true);
	}
	
	
	/**
	 * retorna si el disparo esta visible
	 * @return d.esVisible boolean
	 */
	public boolean disparoVisible() {
		return obj.esVisible();
	}
	
	/**
	 * Obtiene el disparo
	 * @return d Disparo
	 */
	public Objeto getDisparo() {
		return obj;
	}
	
	/**
	 * Modifica el score
	 * @param x int
	 */
	public void setScore(int x) {
		score+=x;
	}
	
	/**
	 * devuelve el score
	 * @return score int
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * devuelve las vidas de los jugadores
	 * @return vidas int
	 */
	public int getLives() {
		return vidas;
	}
	
	public void setLives(int x) {
		vidas+=x;
	}
	
	/**
	 * Cambia las imaganes de los jugadores
	 * @param img
	 */
	public void cambieStringImagen(int img) {
		imagenJugador= imagenes.get(num);
		ImageIcon imagen = new ImageIcon(imagenJugador);
		cambieImagen(imagen.getImage());
	}
}
