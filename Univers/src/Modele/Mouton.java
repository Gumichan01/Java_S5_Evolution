package Modele;

import java.awt.Rectangle;

import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;
import ExceptionUnivers.ValeurNegativeException;

public class Mouton extends Animal{

	public Mouton(Rectangle r, String s, int dureeDeVie,Sexe sexe ,int duree_survie)
			throws SymboleInvalideException, RectangleNonValideException,
			ValeurNegativeException {
		super(r, s, dureeDeVie, duree_survie, sexe);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Animal seReproduire(Animal partenaire) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void evoluerDans(Matiere [][] env, boolean [][] herbes){
		
		super.evoluerDans(env, herbes);
		
		// TODO faire teste si l'herbe est présent puis le manger
		
	}

}
