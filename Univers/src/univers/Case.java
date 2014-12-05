package univers;

import exceptionUnivers.ValeurNegativeException;
import modele.Matiere;

/**
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 */
public class Case {

	int x,y;
	
	Nourriture nourriture;
	Matiere matiereSurCase;
	
	private Case(Matiere m, Nourriture n){
		
		nourriture = n;
		matiereSurCase = m;
		
	}

	
	public Case(int newX, int newY) throws ValeurNegativeException {
		
		this(null, Nourriture.Herbe);
		
		if(x < 0 || y < 0)
			throw new ValeurNegativeException("Coordonnées invalides");
		
		x = newX;
		y = newY;
	}
	
	
	public Matiere getMatierDansCase(){
		
		return matiereSurCase;
	}
	
	public Nourriture getNourriture(){
		
		return nourriture;
	}
	
	public void setNewMatiere(Matiere newMatiere){
		
		this.matiereSurCase = newMatiere;
	}
	
	public void setNourriture(Nourriture newNourriture){
		
		this.nourriture = newNourriture;
	}
	
	
}
