package modele;

import java.awt.Rectangle;

import observateurs.ObsMouton;
import observateurs.ObsSelMineraux;
import observateurs.Observable;
import observateurs.Observateur;

import univers.Case;
import univers.Nourriture;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;


public class Mouton extends Animal{

	public Mouton(Rectangle r, String s, int dureeDeVie,Sexe sexe ,int duree_survie)
			throws SymboleInvalideException, RectangleNonValideException,
			ValeurNegativeException {
		super(r, s, dureeDeVie, duree_survie, sexe);
	}

	@Override
	public Animal seReproduire(Animal partenaire) {
		// TODO générer un nouvel animal
			throw new UnsupportedOperationException("TODO : faire la reproduction du mouton");
		//return null;
	}

	@Override
	protected boolean placerPetitDans(Animal petit, Case[][] env) {
		// TODO Placer le petit sur une case, si possible; 
		throw new UnsupportedOperationException("TODO : faire le placement du petit");
		//return false;
	}
	
	
	@Override
	public void evoluerDans(Case [][] env){
		
		// S'il n'a pas déjà été mangé (toujours vivant ?) alors il grandit
		if(this.estVivant)
			grandir();
		
		// Si après avoir grandi, il est toujours vivant, OK
		if(this.estVivant){

			Rectangle r = this.voisinAProximiteDans(env);
			
			if(r != null){
				// Il y a un voisin
				if( env[r.x][r.y].getMatierDansCase() instanceof Mouton ){

					// C'est un mouton, peut-il se reproduire avec ?
					Mouton partenaire = (Mouton) env[r.x][r.y].getMatierDansCase(); 

					if( this.sexe != partenaire.sexe){	// TODO attention : ce bloc ne doit pas être executé pour le moment

						// Il sont de sexe différent, ne sont-t-il pas déjà en pleine reproduction ?
						if( !this.enReproduction && !partenaire.enReproduction){

							// Ils peuvent se reproduire entre eux
							this.enReproduction = true;
							partenaire.enReproduction = true;
						}
						else{
							// Au moins un des deux se reproduit avec un autre mouton
							// On teste juste si les deux sont en reproduction, si ce n'est pas le cas on ne fait rien 
							if(this.enReproduction && partenaire.enReproduction){
								// Ils se reproduisent

								// Le mouton avertit les observateurs
								//this.notifierObs();

								// Qui va faire la gestation
								if(this.sexe == Sexe.Femelle){
									
									this.placerPetitDans(this.seReproduire(partenaire),env);
								}
								else{
									// C'est forcément le mouton partenaire car il n'est pas du même sexe que le mouton courant
									partenaire.placerPetitDans(partenaire.seReproduire(this),env);
								}

								this.enReproduction = false;
								partenaire.enReproduction = false;
	
							}

						}




					}
					else{
						
						
					}
					
				}
				
			}
			
			int x = this.rect.x;
			int y = this.rect.y;
			
			if(env[x][y].getNourriture() == Nourriture.Herbe){
				
				// Il y a de l'herbe, donc on le mange
				env[x][y].setNourriture(Nourriture.Rien);
				this.compt_survie = 0;
				//System.out.println("\n ("+rect.x+","+rect.y+") Le mouton mange de l'herbe \n");	// A enlever
			}
			else{
				
				super.evoluerDans(env);
			}
			
		}
		else{
			
			// Il est mort. Doit-on deposer les sels minéraux ?
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
