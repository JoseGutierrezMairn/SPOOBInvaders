
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
 * Clase Barrera Roja
 */
public class BarreraRoja extends Objeto{
	private String imgBarrera="src/imagenes/barreraRoja.png";
	
	/**
	 * Constructor para objetos de la clase barrera Roja
	 * @param pos
	 */
	public BarreraRoja(int[] pos) {
		creePos();
		setX(pos[0]);
		setY(pos[1]);
		ImageIcon imagen = new ImageIcon(imgBarrera);
		cambieImagen(imagen.getImage());
	}
	
	
	@Override
	public boolean muero(Objeto d) {
		boolean answ=false;
		if(d.esMalo()) {
			answ=true;
		}
		return answ;
	}
}
