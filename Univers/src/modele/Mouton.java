package modele;

import java.awt.Rectangle;

import observateurs.ObsMouton;
import observateurs.ObsSelMineraux;

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
	protected Animal seReproduire(Animal partenaire) {
		
		if(partenaire instanceof Mouton){

			int select_sexe = (int)(Math.random()*2 +1);
			Sexe s;
			
			// On selectionne le sexe;
			if(select_sexe == 1)
				s = Sexe.Male;
			else
				s = Sexe.Femelle;
			
			try{

				Mouton petit = new Mouton(new Rectangle(Entite.WIDTH, Entite.HEIGHT) , this.symbole(), duree_existence, s, duree_survie);
				petit.ajoutObservateur(new ObsMouton(petit));
				return petit;

			}catch (Exception e) {

				e.printStackTrace();
			}
		}

		return null;
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

					if( this.sexe != partenaire.sexe){

						// Il sont de sexe différent, ne sont-t-il pas déjà en reproduction ?
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
				
				if(!this.meurtVieillesse && !this.meurtFaim && !enReproduction)
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
					/*else
						env[x][y].setNewMatiere(null); // La case devient inoccupée*/
						
				}catch(Exception e){
					System.out.println("EXCEPTION");
					env[x][y].setNewMatiere(null);
				}
			}
			
		}


	}

}
