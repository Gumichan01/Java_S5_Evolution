package Modele;

import java.awt.Rectangle;
//import java.util.ArrayList;

import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;
import ExceptionUnivers.ValeurNegativeException;
//import Observateurs.Observable_;
//import Observateurs.Observateur;

/**
 * 
 * Gère la matière
 * 
 * @version 1.0
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 * NOTE : Ne pas retirer les commentaires relatifs à l'interface Observable et à l'Arraylist (pour l'instant)
 * 
 */
public abstract class Matiere extends Entite /*implements Observable_*/ {

	// TODO implemants Observable
	
	//protected ArrayList<Observateur> observateurs;
	protected int duree_vie;
	

	/**
	 * 
	 * Construit la matière
	 * 
	 * @param r Le "bounds" de la matière (position + dimension)
	 * @param s Le symbole représentative de la matière
	 * @param duree_vie
	 * 
	 * @throws SymboleInvalideException voir {@link SymboleInvalideException}
	 * @throws RectangleNonValideException voir {@link RectangleNonValideException}
	 * @throws ValeurNegativeException voir {@link ValeurNegativeException}
	 */
	public Matiere(Rectangle r, String s, int dureeDeVie) 
			throws SymboleInvalideException,RectangleNonValideException, ValeurNegativeException {
		
		super(r, s);
		
		if(dureeDeVie < 0)	// Ce n'est pas valide, Exception !!
			throw new ValeurNegativeException("La durée de vie n'est pas valide !"
					+" Durée mise dans le constructeur : " + dureeDeVie+" !");
		
		duree_vie = dureeDeVie;
	}


	public abstract void evoluer();	// Kahina -  C'est dans cette méthode que sera appelé seDeplacer() dans Animal 
	

	public String toString(){
		
		return"| Durée de vie : "+duree_vie+" "+super.toString();
	}
	
}



















