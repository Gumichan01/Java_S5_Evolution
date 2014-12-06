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
	public boolean manger(Animal proie) {
		
		// TODO à tester
		proie.meurt(Mort.Mange);
		this.compt_survie = 0;	// On remet le compteur à 0 car le loup est rassasié
		
		return false;
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
			
			Rectangle r = this.proieAProximiteDans(env);	//
			
			if( r != null){
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
	@Override
	public Rectangle proieAProximiteDans(Case[][] env) {
		// TODO à tester
		int xDroiteAdj, xGaucheAdj; 	// La position du voisin à droite ou bien à ggauche
		int yHautAdj, yBasAdj;			// La position du voisin d'en haut ou bien en bas
		
		int xProie, yProie;
		
		
		/*	Dans un premier temps, on va stocker les coordonnées des cases adjacentes
		 	d'abord en x */
		
		// On fixe la position X du voisin de droite 
		if((this.rect.x + 1) == env.length){
			
			// On est au bord droit
			xDroiteAdj = 0;
		}
		else{
			
			// Pas de problème
			xDroiteAdj = this.rect.x + 1; 
		}

		// On fait de même à gauche 
		if((this.rect.x - 1) < 0){

			// On est au bord gauche
			xGaucheAdj = env.length - 1;
		}
		else{
			
			// Pas de problème
			xGaucheAdj = this.rect.x - 1;
		}
		
		
		/* Puis en y */
		
		// On fixe la position Y du voisin du bas
		if((this.rect.y + 1) >= env[this.rect.x].length){
			
			// On est au bord du bas
			yBasAdj = 0;
		}
		else{
			
			// Pas de problème
			yBasAdj = this.rect.y + 1;
		}
		
		
		// On fait de même en haut 
		if((this.rect.y - 1) < 0){

			// On est au bord gauche
			yHautAdj = env[this.rect.x].length - 1;
		}
		else{
			
			// Pas de problème
			yHautAdj = this.rect.y - 1;
		}
		
		//System.out.println("x "+xDroiteAdj+" y"+yBasAdj );
		// Proie à gauche
		if(env[xGaucheAdj][this.rect.y].getMatierDansCase() instanceof Mouton){
			// Proie à gauche
			xProie = xGaucheAdj;
			yProie = this.rect.y;
			
			return new Rectangle(xProie,yProie,0,0);
		}
		else if(env[xGaucheAdj][yHautAdj].getMatierDansCase() instanceof Mouton){
			// Proie en haut à gauche
			xProie = xGaucheAdj;
			yProie = yHautAdj;
			
			return new Rectangle(xProie,yProie,0,0);
		}
		else if(env[this.rect.x][yHautAdj].getMatierDansCase() instanceof Mouton){
			// Proie en haut
			xProie = this.rect.x;
			yProie = yHautAdj;
			
			return new Rectangle(xProie,yProie,0,0);
		}
		else if(env[xDroiteAdj][yHautAdj].getMatierDansCase() instanceof Mouton){
			// Proie en haut à droite
			xProie = xDroiteAdj;
			yProie = yHautAdj;
			
			return new Rectangle(xProie,yProie,0,0);
		}
		else if(env[xDroiteAdj][this.rect.y].getMatierDansCase() instanceof Mouton){
			
			// Proie à droite
			xProie = xDroiteAdj;
			yProie = this.rect.y;
			
			return new Rectangle(xProie,yProie,0,0);
		}
		else if(env[xDroiteAdj][yBasAdj].getMatierDansCase() instanceof Mouton){
			
			// Proie en bas à droite
			xProie = xDroiteAdj;
			yProie = yBasAdj;
			
			return new Rectangle(xProie,yProie,0,0);
		}
		else if(env[this.rect.x][yBasAdj].getMatierDansCase() instanceof Mouton){
			
			// Proie en bas
			xProie = this.rect.x;
			yProie = yBasAdj;
			
			return new Rectangle(xProie,yProie,0,0);
		}
		else{ 
			
			// Proie en bas à gauche ? S'il n'y a pas de proie à cette endroit, alors aucune proie n'a été trouvée,-> null, sinon OK 
			return (env[this.rect.x][yBasAdj].getMatierDansCase() instanceof Mouton) ? new Rectangle(this.rect.x, yBasAdj,0,0): null;
		}

	}

}

















