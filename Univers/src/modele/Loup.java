package modele;

import java.awt.Rectangle;

import univers.Case;
import univers.Nourriture;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;

import Observateurs.ObsSelMineraux;
import Observateurs.Observateur;

public class Loup extends Animal implements MangeurMouton{

	
	

	public Loup(Rectangle r, String s, int dureeDeVie,Sexe sexe, int duree_survie)
			throws SymboleInvalideException, RectangleNonValideException,
			ValeurNegativeException {
		super(r, s, dureeDeVie, duree_survie, sexe);
		
	}
	// TODO Auto-generated constructor stub
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
	public void evoluerDans(Case [][] env) {
		
		grandir();
		
		if(this.estVivant){
			// TODO faire test présence mouton sur une case adjacente, manger le mouton si possible, sinon se deplacer
			super.evoluerDans(env);
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

















