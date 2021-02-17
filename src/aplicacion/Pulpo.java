
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
 * Clase Pulpo
 */
public class Pulpo extends Objeto{
	private String imagenPulpo= "src/imagenes/pulpo.png";
	private int score;
	private int resiste;
	private Objeto obj;
	private String imgDisparo="src/imagenes/balaInvasora.png";
	
	/**
	 * Constructor para Objetos de la Clase Pulpo
	 * @param pos int[]
	 */
	public Pulpo(int[] pos) {
		obj= new DisparoNormal(new int[] {0,0},false);
		creeDisparo(obj);
		obj.setVisible(false);
		name="pulpo";
		creePos();
		vivo= true;
		score= 10;
		resiste=1;
		ImageIcon imagen = new ImageIcon(imagenPulpo);
		cambieImagen(imagen.getImage());
		setX(pos[0]);	
		setY(pos[1]);
	}
	
	/**
	 * Cambia el valor de su resistencia si es impactado por un disparo
	 */
	public void setResiste() {
		resiste-=1;
	}
	
	public Objeto getDisparo() {
		return obj;
	}
	
	
	public void dispare(String type) {
		ImageIcon imagen = new ImageIcon(imgDisparo);	
		obj= new DisparoNormal(new int[] {getX()+9,getY()+15},false);
		creeDisparo(obj);
		getDisparo().cambieImagen(imagen.getImage());
		getDisparo().setVisible(true);
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
