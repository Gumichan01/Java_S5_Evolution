package modele;

import java.awt.Rectangle;

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
	public void evoluerDans(Matiere [][] env, boolean [][] herbes){
		
		// S'il n'a pas déjà été mangé (toujours vivant ?) alors il grandit
		if(this.estVivant)
			grandir();
		
		// Si après avoir grandi, il est toujours vivant, OK
		if(this.estVivant){
					
			int x = this.rect.x;
			int y = this.rect.y;
			
			if(herbes[x][y] == true){
				
				herbes[x][y] = false;
				this.compt_survie = 0;
				System.out.println("\n ("+rect.x+","+rect.y+") Le mouton mange de l'herbe \n");
			}
			else{
				
				super.evoluerDans(env, herbes);
			}

			
		}
		else{
			
			// On est mort. Doit-on deposer les sels minéraux ?
			if(this.meurtFaim || this.meurtVieillesse){
				int x = this.rect.x;
				int y = this.rect.y;
				
				try {

					SelMineral sel = new SelMineral(this.getRect());
					
					env[x][y] = sel;
					env[x][y].ajoutObservateur( new ObsSelMineraux(sel) );
					
				}catch(Exception e){
					env[x][y] = null;
				}
			}
			
		}


	}


}
