package Modele;

import java.awt.Rectangle;

import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;
import ExceptionUnivers.ValeurNegativeException;
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
	
	/*public void evoluerDans(Matiere [][] env){
		
		super.evoluerDans(env,null);
		
		// TODO faire teste présence mouton sur une case adjacente
		
	}*/



}
