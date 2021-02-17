
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
 * Clase Calamar
 */
public class Calamar extends Objeto{
	
	private String imagenCalamar= "src/imagenes/calamar.png";
	private final int score;
	private int resiste;
	private Objeto obj;
	private String imgDisparo="src/imagenes/balaInvasora.png";
	
	/**
	 * Constructor para Objetos de la Clase Calamar
	 * @param pos int[]
	 */
	public Calamar(int[] p) {
		obj= new DisparoNormal(new int[] {0,0},false);
		creeDisparo(obj);
		obj.setVisible(false);
		name= "calamar";
		vivo= true;
		creePos();
		score= 50;
		resiste= 3;
		ImageIcon imagen = new ImageIcon(imagenCalamar);
		cambieImagen(imagen.getImage());
		setX(p[0]);	
		setY(p[1]);
	}
	
	/**
	 * Cambia el valor de su resistencia si es impactado por un disparo
	 */
	public void setResiste() {
		resiste-=1;
	}
	
	public void dispare(String type) {
		//d= new DisparoNormal(new int[] {getX()+22,+25},false);
		ImageIcon imagen = new ImageIcon(imgDisparo);	
		obj= new DisparoNormal(new int[] {getX()+9,getY()+15},false);
		creeDisparo(obj);
		obj.cambieImagen(imagen.getImage());
		obj.setVisible(true);
	}
	
	/**
	 * retorna un disparo de tipo objeto
	 */
	public Objeto getDisparo() {
		return obj;
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
