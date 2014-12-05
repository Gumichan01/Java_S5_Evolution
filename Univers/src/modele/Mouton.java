package modele;

import java.awt.Rectangle;

import univers.Case;
import univers.Nourriture;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;

import Observateurs.ObsMouton;
import Observateurs.ObsSelMineraux;
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
	public void evoluerDans(Case [][] env){
		
		// S'il n'a pas déjà été mangé (toujours vivant ?) alors il grandit
		if(this.estVivant)
			grandir();
		
		// Si après avoir grandi, il est toujours vivant, OK
		if(this.estVivant){
					
			int x = this.rect.x;
			int y = this.rect.y;
			
			if(env[x][y].getNourriture() == Nourriture.Herbe){
				
				// Il y avait de l'herbe, donc on le mange
				env[x][y].setNourriture(Nourriture.Rien);
				this.compt_survie = 0;
				//System.out.println("\n ("+rect.x+","+rect.y+") Le mouton mange de l'herbe \n");	// A enlever
			}
			else{
				
				super.evoluerDans(env);
			}
			
		}
		else{
			
			// On est mort. Doit-on deposer les sels minéraux ?
			if(this.meurtFaim || this.meurtVieillesse){
				int x = this.rect.x;
				int y = this.rect.y;
				
				try {
					// La case est-elle depourvue de quoi que ce soit ?
					if(env[x][y].getNourriture() == Nourriture.Rien ){
						
						SelMineral sel = new SelMineral(this.getRect());
						sel.ajoutObservateur(new ObsSelMineraux(sel));
						
						env[x][y].setNewMatiere(sel);
					}
				}catch(Exception e){
					env[x][y].setNewMatiere(null);
				}
			}
			
		}


	}


}
