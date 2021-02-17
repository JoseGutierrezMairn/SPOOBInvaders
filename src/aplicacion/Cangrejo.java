
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package aplicacion;

import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * 
 * @author CAMILO
 *
 */
public class Cangrejo extends Objeto{
	private String imagenCangrejo= "src/imagenes/cangrejo.png";
	private final int score;
	private int resiste;
	private Objeto obj;
	private String imgDisparo="src/imagenes/balaInvasora.png";
	
	/**
	 * 
	 * @param pos
	 */
	public Cangrejo(int[] pos) {
		obj = new DisparoNormal(new int[] {0,0},false);
		creeDisparo(obj);
		obj.setVisible(false);
		name= "cangrejo";
		creePos();
		vivo= true;
		score= 30;
		resiste= 2;
		ImageIcon imagen = new ImageIcon(imagenCangrejo);
		cambieImagen(imagen.getImage());
		
		setX(pos[0]);
		setY(pos[1]);
	}
	
	public Objeto getDisparo() {
		return obj;
	}
	
	public void dispare(String type) {
		ImageIcon imagen = new ImageIcon(imgDisparo);	
		obj= new DisparoNormal(new int[] {getX()+9,getY()+15},false);
		creeDisparo(obj);
		obj.cambieImagen(imagen.getImage());
		obj.setVisible(true);
	}
	
	
	/**
	 * Cambia el valor de su resistencia si es impactado por un disparo
	 */
	public void setResiste() {
		resiste-=1;
	}
	
	/**
	 * Retorna la resistencia correspondiente a este invasor
	 * @return resiste int
	 */
	public int getResiste() {
		return resiste;
	}
	
	/**
	 * Retorna el puntaje correspondiente a este invasor
	 * @return score int
	 */
	public int getScore() {
		return score;
	}
	
}
