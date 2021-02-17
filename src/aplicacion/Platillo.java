
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package aplicacion;

import javax.swing.ImageIcon;

public class Platillo extends Objeto{
	
	private String imagenPlatillo= "src/imagenes/nave.png";
	private final int score;
	private int resiste;
	private DisparoNormal d;
	private String imgDisparo="src/imagenes/balaInvasora.png";
	
	public Platillo(int[] p) {
		d= new DisparoNormal(new int[] {0,0},false);
		creeDisparo(d);
		d.setVisible(false);
		name= "platillo";
		vivo= true;
		creePos();
		score= 200;
		resiste= 1;
		ImageIcon imagen = new ImageIcon(imagenPlatillo);
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
	
	public DisparoNormal getDisparo() {
		return d;
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
