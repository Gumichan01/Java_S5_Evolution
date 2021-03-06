package modele;

import java.awt.Rectangle;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;


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
	private String symbole;	/* Mouton : "M", Loup : "L", SelMineral : "S", ...  (ne doit être modifié par les classes filles) */

	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	
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

	public final String symbole(){

		return symbole;
	}
	
	/*
	 * Renvoie une copie de rect avec les attributs du rect de l'entité courante
	 * NB : Cela evite aux classes utilisatrices de modifier le Rectangle de la classe cible
	 */
	public final Rectangle getRect(){

		return new Rectangle(this.rect);
	}
	
	@Override
	public String toString(){

		return"| Coordonnées :  ("+rect.x+","+rect.y+"); symbole :  "+symbole+"\n";
	}

}

















