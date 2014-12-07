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
		// TODO générer un nouvel animal
			//throw new UnsupportedOperationException("TODO : faire la reproduction du mouton");
		
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
	protected boolean placerPetitDans(Animal petit, Case[][] env) {
		// TODO Placer le petit sur une case, si possible; 
		//throw new UnsupportedOperationException("TODO : faire le placement du petit");

		int xDroiteAdj, xGaucheAdj; 	// La position du voisin à droite ou bien à gauche
		int yHautAdj, yBasAdj;			// La position du voisin en haut ou bien en bas
		
		// Debut du code identique à voisinAproximité
		if((this.rect.x + 1) == env.length)
			xDroiteAdj = 0;
		else
			xDroiteAdj = this.rect.x + 1; 

		if((this.rect.x - 1) < 0)
			xGaucheAdj = env.length - 1;
		else
			xGaucheAdj = this.rect.x - 1;


		if((this.rect.y + 1) >= env[this.rect.x].length)
			yBasAdj = 0;
		else
			yBasAdj = this.rect.y + 1;


		if((this.rect.y - 1) < 0)
			yHautAdj = env[this.rect.x].length - 1;
		else
			yHautAdj = this.rect.y - 1;

		// On met le rect de petit à null;
		petit.rect = null;
		
		/*Si on trouve une case libre où mettre le petit 
		 * 1 -> on réinitialise le rectangle 
		 * 2 ->  on met le petit sur la case
		 * 3 -> On previent les observateurs */
		if(env[xGaucheAdj][this.rect.y].getMatierDansCase() == null){
			
			petit.rect = new Rectangle(xGaucheAdj,this.rect.y,Entite.WIDTH, Entite.HEIGHT);
			env[xGaucheAdj][this.rect.y].setNewMatiere(petit);
			petit.notifierObs();
			return true;
		}
		else if(env[xGaucheAdj][yHautAdj].getMatierDansCase() == null){
			
			petit.rect = new Rectangle(xGaucheAdj,yHautAdj,Entite.WIDTH, Entite.HEIGHT);
			env[xGaucheAdj][yHautAdj].setNewMatiere(petit);
			petit.notifierObs();
			return true;
		}
		else if(env[this.rect.x][yHautAdj].getMatierDansCase() == null){
			
			petit.rect = new Rectangle(this.rect.x,yHautAdj,Entite.WIDTH, Entite.HEIGHT);
			env[this.rect.x][yHautAdj].setNewMatiere(petit);
			petit.notifierObs();
			return true;
		}
		else if(env[xDroiteAdj][yHautAdj].getMatierDansCase() == null){
			
			petit.rect = new Rectangle(xDroiteAdj,yHautAdj,Entite.WIDTH, Entite.HEIGHT);
			env[xDroiteAdj][yHautAdj].setNewMatiere(petit);
			petit.notifierObs();
			return true;
		}
		else if(env[xDroiteAdj][this.rect.y].getMatierDansCase() == null){
			
			petit.rect = new Rectangle(xDroiteAdj,this.rect.y,Entite.WIDTH, Entite.HEIGHT);
			env[xDroiteAdj][this.rect.y].setNewMatiere(petit);
			petit.notifierObs();
			return true;
		}
		else if(env[xDroiteAdj][yBasAdj].getMatierDansCase() == null){
			
			petit.rect = new Rectangle(xDroiteAdj,yBasAdj,Entite.WIDTH, Entite.HEIGHT);
			env[xDroiteAdj][yBasAdj].setNewMatiere(petit);
			petit.notifierObs();
			return true;

		}
		else if(env[this.rect.x][yBasAdj].getMatierDansCase() == null){
			
			petit.rect = new Rectangle(this.rect.x,yBasAdj,Entite.WIDTH, Entite.HEIGHT);
			env[this.rect.x][yBasAdj].setNewMatiere(petit);
			petit.notifierObs();
			return true;
		}
		else if(env[xGaucheAdj][yBasAdj].getMatierDansCase() == null){ 
			
			petit.rect = new Rectangle(xGaucheAdj,yBasAdj,Entite.WIDTH, Entite.HEIGHT);
			env[xGaucheAdj][yBasAdj].setNewMatiere(petit);
			petit.notifierObs();
			return true;
		}
		
		// Il n'y a pas de case disponible -> Le petit ne naît pas
		return false;
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
				
				if(!this.meurtVieillesse && !this.meurtFaim)
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
