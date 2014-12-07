package modele;

import java.awt.Rectangle;
import java.util.ArrayList;

import observateurs.Observable;
import observateurs.Observateur;

import univers.Case;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;
//import java.util.ArrayList;

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
public abstract class Matiere extends Entite implements Observable {

	
	protected ArrayList<Observateur> observateurs;
	protected int duree_existence;
	

	/**
	 * 
	 * Construit la matière
	 * 
	 * @param r Le "bounds" de la matière (position + dimension)
	 * @param s Le symbole représentative de la matière
	 * @param duree_existence
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

		duree_existence = dureeDeVie;
		observateurs = new ArrayList<Observateur>();
	}


	public abstract void evoluerDans(Case [][] env); 
	
	public abstract boolean vivant();
	
	@Override
	public void ajoutObservateur(Observateur o) {
		if(o == null){
			throw new NullPointerException("Ajout d'un Observateur null");
		}
		else
		{
			observateurs.add(o);
		}
		
	}

	@Override
	public void notifierObs() {
		for(int i = 0; i<observateurs.size(); i++){
			observateurs.get(i).notifier();
			
		}
		
	}
	
	
	public String toString(){
		
		return"| Durée de vie : "+duree_existence+" "+super.toString();
	}
	
}



















