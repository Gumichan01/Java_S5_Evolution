package Modele;

import java.awt.Rectangle;

import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;

/**
 * 
 * Représente un élement de l'univers
 * 
 * @version 1.0
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 */
public class Entite {

	protected Rectangle rect;	// Stocke les positions (x,y) et la taille (utile pour la partie graphique)
	protected String symbole;	/* Mouton : "M", Loup : "L", SelMineral : "S", ...  */
	
	/**
	 * Construit l'entité
	 * @param newX la nouvelle position x
	 * @param newY la nouvelle position y
	 * @param newW la largeur
	 * @param newH la hauteur
	 * @param s le symbole représentative de l'entité (pour l'affichage console)
	 * @throws SymboleInvalideException Si le symbole vaut null
	 */
	public Entite(int newX, int newY, int newW, int newH, String s) throws SymboleInvalideException{

		if(s == null)
			throw new SymboleInvalideException("Erreur, symbole invalide (null)");
		
		rect = new Rectangle(newX, newY, newW, newH);
		symbole = s;
		
	}
	
	/**
	 * Construit aussi l'entité
	 * @param r Le rectangle voir {@link Rectangle}
	 * @param s le symbole représentative de l'entité (pour l'affichage console)
	 * @throws SymboleInvalideException Si le symbole vaut null
	 * @throws RectangleNonValideException Si lerectangle vaut null
	 */
	public Entite(Rectangle r, String s) throws SymboleInvalideException, RectangleNonValideException{

		if(s == null)
			throw new SymboleInvalideException("Erreur, symbole invalide (null)");
		else if( r == null || r.x < 0 || r.y < 0 || r.width <= 0 || r.height <= 0 )
			throw new RectangleNonValideException("Je ne peux pas recevoir un rectangle null \n"
					+"ou bien un rectangle dont les informations sont invalides");
		
		rect = r;
		symbole = s;
	}
	
	
	public Rectangle getRect(){
		
		return new Rectangle(this.rect);
	}
	
	@Override
	public String toString(){
		
		return"| Coordonnées :  ("+rect.x+","+rect.y+"); symbole :  "+symbole+"\n";
	}
	
}

















