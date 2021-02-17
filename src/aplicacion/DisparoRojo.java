
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package aplicacion;

import javax.swing.ImageIcon;

/**
 * Clase DisparoRojo 
 */
public class DisparoRojo extends Objeto{
	private static final long serialVersionUID = 1L;
	private String imagenDisparoRojo = "src/imagenes/balinRoja.png";
	private boolean bueno;
	
	/**
	 * Construcotr para objetos de la clase DisparoRojo
	 * @param p
	 * @param bueno
	 */
	public DisparoRojo(int[] p, boolean bueno) {
		creePos();
		name = "rojo";
		vivo = true;
		pos = new int [2];
		this.bueno = bueno;
		disparoInicial(p);
	}
	
	/**
	 * 
	 */
	@Override
	public boolean esMalo() {
		return bueno;
	}
	
	/**
	 * Se encarga de tener la posicion inicial del disparo
	 * @param pos
	 */
	private void disparoInicial(int[] pos) {
		ImageIcon imagen = new ImageIcon(imagenDisparoRojo);
		cambieImagen(imagen.getImage());
		setX(pos[0]);	
		setY(pos[1]);
	}
}
