package modele;

import java.awt.Rectangle;

import univers.Case;
import univers.Nourriture;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;

public class SelMineral extends Matiere{

	private int compteur;	// Compteur obligatoire pour evaluer le moment de la disparition
	
	public SelMineral(Rectangle r) throws 
		SymboleInvalideException,
			RectangleNonValideException,
				ValeurNegativeException {
		
		super(r, "S", 1);
		compteur = 0;
		
	}
	
	public SelMineral(Rectangle r, String s) throws 
	SymboleInvalideException,
		RectangleNonValideException,
			ValeurNegativeException {
	
		super(r, s, 1);
		compteur = 0;
	
	}
	
	public SelMineral(Rectangle r, String s, int duree_exist) throws 
	SymboleInvalideException,
		RectangleNonValideException,
			ValeurNegativeException {
	
		super(r, s, duree_exist);
		compteur = 0;
	
	}

	@Override
	public void evoluerDans(Case [][] env) {
		
		//if(env[this.rect.x][this.rect.y] == null)	// Si la case était vide, c'est pas normal, mais pas grave
			env[this.rect.x][this.rect.y].setNewMatiere(this);
		
		this.compteur += 1;
		
		if(this.compteur == this.duree_existence)	
			this.notifierObs();						// On previent les observateurs de la fin de l'existence
		else if(this.compteur > duree_existence){
			
			env[this.rect.x][this.rect.y].setNewMatiere(null);
			env[this.rect.x][this.rect.y].setNourriture(Nourriture.Herbe);	// L'herbe repousse
		}
	}

	@Override
	public boolean vivant() {
		return this.compteur <= this.duree_existence;
	}

	
	
}
