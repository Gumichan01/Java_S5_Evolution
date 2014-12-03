package Modele;

import java.awt.Rectangle;

import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;
import ExceptionUnivers.ValeurNegativeException;
import Observateurs.ObsMouton;
import Observateurs.Observable;
import Observateurs.Observateur;

public class Mouton extends Animal{

	public Mouton(Rectangle r, String s, int dureeDeVie,Sexe sexe ,int duree_survie)
			throws SymboleInvalideException, RectangleNonValideException,
			ValeurNegativeException {
		super(r, s, dureeDeVie, duree_survie, sexe);
	}

	@Override
	public Animal seReproduire(Animal partenaire) {
		// TODO Auto-generated method stub
			throw new UnsupportedOperationException("TODO : faire la reproduction du mouton");
		//return null;
	}

	@Override
	public void evoluerDans(Matiere [][] env, boolean [][] herbes){
		
		if(this.estVivant){
					
			// TODO faire teste si l'herbe est présent, si oui, manger. Sinon, se deplacer
			grandir();
			
			// Si après avoir grandi, il est toujours vivant, OK
			if(this.estVivant)
				super.evoluerDans(env, herbes);
		}
		

	}
	
	

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

}
