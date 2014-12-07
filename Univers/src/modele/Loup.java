package modele;

import java.awt.Rectangle;

import observateurs.ObsSelMineraux;

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
		
		if(proie.getAge() > 1){
			
			proie.meurt(Mort.Mange);
			this.compt_survie = 0;	// On remet le compteur à 0 car le loup est rassasié
		}
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

				/* Pas très propre le cast, mais la méthode getMatierDansCase() renvoie une matière au sens large
				 * Dans le cas présent on a bien vérifier que c'est un animal, donc l'exécution de la fonction ne fera pas planter le programme
				 */
				this.manger((Animal) env[r.x][r.y].getMatierDansCase());	// On mange la proie
				
				/* On va se mettre à la position de la proie désormais dévorée*/
				env[this.rect.x][this.rect.y].setNewMatiere(null);	// On n'occupe plus cette case
				this.rect.x = r.x;	// On met à jour les coordonnées du loup
				this.rect.y = r.y;
				env[this.rect.x][this.rect.y].setNewMatiere(this);	// On prend la place de la proie
			}
			else{
				
				if(!this.meurtVieillesse && !this.meurtFaim)
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
					/*else
						env[x][y].setNewMatiere(null); // La case devient inoccupée*/
					
				}catch(Exception e){
					// Il y a eu un problème, la case est inoccupé
					env[x][y].setNewMatiere(null);
				}
			}
			
		}

	}


}

















