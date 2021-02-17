
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
 * Clase Barrera Verde
 */
public class BarreraVerde extends Objeto{
	private int vida;
	private String imgBarrera="src/imagenes/barreraVerde.png";
	
	/**
	 * Constructor para Objetos de la clase BarreraVerde
	 * @param pos
	 */
	public BarreraVerde(int[] pos) {
		vida=3;
		creePos();
		setX(pos[0]);
		setY(pos[1]);
		ImageIcon imagen = new ImageIcon(imgBarrera);
		cambieImagen(imagen.getImage());
		name = "verde";
	}
	

	
	@Override
	public boolean muero(Objeto d) {
		boolean answ=false;
		if(!d.esMalo()) {
			vida-=1;
		}
		if(vida == 2) {
			this.setStringImagen("src/imagenes/barreraVerdeDisparo.png");
		}
		if(vida == 1) {
			this.setStringImagen("src/imagenes/barreraVerdeMuere.png");
		}
		if(vida==0) {
			answ=true;
		}
		return answ;
	}
	

}
