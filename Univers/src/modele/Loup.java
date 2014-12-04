package modele;

import java.awt.Rectangle;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;

import Observateurs.Observateur;

public class Loup extends Animal implements MangeurMouton{

	
	

	public Loup(Rectangle r, String s, int dureeDeVie,Sexe sexe, int duree_survie)
			throws SymboleInvalideException, RectangleNonValideException,
			ValeurNegativeException {
		super(r, s, dureeDeVie, duree_survie, sexe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean manger(Animal nourriture) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mangerMouton(Mouton mouton) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Animal seReproduire(Animal partenaire) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO : faire la reproduction du loup");
		//return null;
	}

	
	@Override
	public void evoluerDans(Matiere [][] env, boolean [][] herbes) {
		
		if(this.estVivant){

			// TODO faire test présence mouton sur une case adjacente, manger le mouton si possible, sinon se deplacer
			super.evoluerDans(env,null);
			grandir();
		}

	}

}
