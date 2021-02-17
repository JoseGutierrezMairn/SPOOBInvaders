
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
 * Clase Disparo
 */
public class DisparoNormal extends Objeto{
	private String imagenDisparo= "src/imagenes/balin.png";
	private boolean bueno;
	
	/**
	 * Constructor para Objetos de la Clase Disparo
	 * @param pos boolean
	 * @param bueno int
	 */
	public DisparoNormal(int[] p, boolean bueno) {
		creePos();
		name = "normal";
		vivo= true;
		pos= new int[2];
		this.bueno= bueno;
		disparoInicial(p);	
	}
	
	@Override
	public boolean esMalo() {
		return bueno;
	}
	
	/**
	 * Se encarga de tener la posicion inicial del disparo
	 * @param pos
	 */
	private void disparoInicial(int[] pos) {
		if(!bueno) {
			imagenDisparo= "src/imagenes/balaInvasora.png";
		}
		ImageIcon imagen = new ImageIcon(imagenDisparo);
		cambieImagen(imagen.getImage());
		setX(pos[0]);	
		setY(pos[1]);
	}
}