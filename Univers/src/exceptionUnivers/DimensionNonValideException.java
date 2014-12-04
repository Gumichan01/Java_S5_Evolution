package exceptionUnivers;

/**
 * Classe qui gère l'exception liée à la dimension invalide
 * 
 * @author luxon
 *
 */
public class DimensionNonValideException extends Exception{

	public DimensionNonValideException(String str){
		
		super(str);
	}
}
