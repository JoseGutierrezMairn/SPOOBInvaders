
/**
 * @author JOSE GUTIERREZ Y CAMILO SAENZ
 * PROYECTO FINAL POOB (PROGRAMACION ORIENTADA A OBJETOS)
 * SPOOBInvaders
 * @version 5.0
 * @date 11/12/2018
 */

package aplicacion;

public class SPOOBInvadersException extends Exception{
	public static final String ARCHIVO_VACIO = "El Archivo se Encuentra Vacio";
	public static final String ARCHIVO_NO_ENCONTRADO = "El Archivo no ha Sido Encontrado";
	public static final String ERROR = "Error de Ejecucion";
	public static final String CLASE_NO_ENCONTRADA = "Error de Clase";
	
	
	public SPOOBInvadersException(String message) {
		super(message);
	}
}
