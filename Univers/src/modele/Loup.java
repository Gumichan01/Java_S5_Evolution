package modele;

import java.awt.Rectangle;

import observateurs.ObsSelMineraux;
import observateurs.Observateur;

import univers.Case;
import univers.Nourriture;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;


public class Loup extends Animal implements Carnivore{


	public Loup(Rectangle r, String s, int dureeDeVie,Sexe sexe, int duree_survie)
			throws SymboleInvalideException, RectangleNonValideException,
			ValeurNegativeException {
		super(r, s, dureeDeVie, duree_survie, sexe);
		
	}
	
	@Override
	public void manger(Animal proie) {
		
		proie.meurt(Mort.Mange);
		this.compt_survie = 0;	// On remet le compteur à 0 car le loup est rassasié
		
	}


	@Override
	protected Animal seReproduire(Animal partenaire) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO : faire la reproduction du loup");
		//return null;
	}

	@Override
	protected boolean placerPetitDans(Animal petit, Case[][] env) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public void evoluerDans(Case [][] env) {
		
		grandir();
		
		if(this.estVivant){
			
			Rectangle r = this.voisinAProximiteDans(env);	//
			
			if( r != null && env[r.x][r.y].getMatierDansCase() instanceof Mouton){
				// On a une proie voisine à devorer
				/* Pas très propre le cast, mais la méthode proieAProximiteDans() renvoie les coordonnées de l'animal qui fait office de proie
				 * Içi c'est le mouton, mais cela est applicable à n'importe quelle autre animal qui peut être la proie d'un loup */
				this.manger((Animal) env[r.x][r.y].getMatierDansCase());
			}
			else
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
					// Il y a eu un problème, la case est inoccupé
					env[x][y].setNewMatiere(null);
				}
			}
			
		}

	}


}

















