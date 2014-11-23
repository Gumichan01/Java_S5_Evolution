package ExceptionUnivers;

/**
 * Gère la le cas où le constructeur d'entite reçoit une rectangle non initialisé
 * 
 * 
 * @version 1.0
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 */
public class RectangleNonValideException extends Exception {

	public RectangleNonValideException(String str){
		
		super(str);
	}
}
