
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package aplicacion;
import java.awt.Image;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Clase Objeto
 */
public abstract class Objeto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected boolean vivo;
	protected String imagenObj;
	private Image imagen;
	protected int[] pos;
	protected int dx;
	protected int score;
	protected int resiste;
	protected int vida;
	protected String name;
	protected Objeto obj;
	/**
	 * Constructor para Objetos de la Clase Objeto
	 */
	//public Objeto() {
		
	//}

	/**
	 * 
	public void mover() {
		Platillo p = platillo;
		if(p.getX()>=-1000 && p.esVisible()) {
			p.setX(-2);
			number++;
		}if(p.getX() <= -1000 && p.esVisible()) {
			this.hagaPlatillo();
			number++;
		}
	}
	
	 * Realiza el movimiento del platillo de Izquierda a Derecha
	 * @param ancho int
	public void muevaPlatillo(int ancho) {
		mover();
		if(platillo.vivo && number>=3000) {
			number = 0;
		}
	}
	*/
	
	/**
	 * 
	 * @return
	 
	public void disparaPlatillo() {
		
		if(disparos<1) {
			platillo.dispare();
			
		}else if(disparos >= 100) {
			disparos =- 1;
		}
		disparos++;
		muevaDisparo();
		if(platillo.getDisparo().esVisible()) {
			reviseJugadores(platillo.getDisparo());
		}
	}
	*/
	
	/**
	 * Modifica la resistencia del objeto
	 */
	public void setResiste() {
		resiste-=1;
	}
	
	public void setStringImagen(String img) {
		
		imagenObj= img;
		ImageIcon imagen = new ImageIcon(img);
		cambieImagen(imagen.getImage());
	}
	
	
	
	public String getName() {
		return name;
	}
	
	/**
	 * retorna el valor de la resistencia del objeto
	 * @return resiste int
	 */
	public int getResiste() {
		return resiste;
	}
	
	/**
	 * retorna el valor del score
	 * @return score int
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * retorna la posicion en x
	 * @return pos[0] int
	 */
	public int getX() {
		return pos[0];
	}
	
	/**
	 * retorna la posicion en y
	 * @return pos[1] int
	 */
	public int getY() {
		return pos[1];
	}
	
	/**
	 * retorna si el objeto esta vivo
	 * @return vivo boolean
	 */
	public boolean esVisible() {
		return vivo;
	}
	
	/**
	 * cambia la imagen 
	 * @param imagen Image
	 */
	public void cambieImagen(Image imagen) {
		this.imagen= imagen;
	}
	
	public void creeDisparo(Objeto obj) {
		this.obj = obj;
	}
	
	public Objeto getDisparo() {
		return obj;
	}
	
	public void dispare(String type) {
		System.out.println(true);
	}
	
	public int getLives(){
		return 0;
	}
	
	/**
	 * retorna la imagen
	 * @return imagen Image
	 */
	public Image getImagen() {
		return imagen;
	}
	
	public void creePos() {
		pos= new int[2];
	}
	
	
	/**
	 * cambia las posicion en x
	 * @param x int
	 */
	public  void setX(int x) {
		pos[0]+= x;
	}
	
	public boolean muero(Objeto obj) {
		return true;
	}
	
	public boolean esMalo() {
		return true;
	}
	
	/**
	 * cambia la posicion en y
	 * @param y int
	 */
	public void setY(int y) { 
		pos[1]+= y;
	}
	
	/**
	 * cambia la visibilidad del objeto
	 * @param v boolean
	 */
	public void setVisible(boolean v) {
		vivo=v;
	}

}
