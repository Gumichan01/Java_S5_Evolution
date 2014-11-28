package Modele;

import java.awt.Rectangle;

import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;
import ExceptionUnivers.ValeurNegativeException;

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
		return null;
	}

	
	@Override
	public void evoluerDans(Matiere [][] env, boolean [][] herbes) {
		
		// TODO faire teste présence mouton sur une case adjacente
		//evoluerDans(env);
		super.evoluerDans(env,null);
	}
	
	/*public void evoluerDans(Matiere [][] env){
		
		super.evoluerDans(env,null);
		
		// TODO faire teste présence mouton sur une case adjacente
		
	}*/



}
