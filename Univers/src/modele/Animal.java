package modele;

import java.awt.Rectangle;

import univers.Case;

import exceptionUnivers.RectangleNonValideException;
import exceptionUnivers.SymboleInvalideException;
import exceptionUnivers.ValeurNegativeException;


public abstract class Animal extends Matiere{
	
	protected int duree_survie;
	protected boolean estVivant;
	protected int age;
	protected int compt_survie;
	protected boolean meurtVieillesse; 
	protected boolean meurtMange;
	protected boolean meurtFaim;
	protected boolean enReproduction;
	protected Sexe sexe;

	public Animal(Rectangle r, String s, int dureeDeVie, int duree_survie, Sexe sexe)
			throws SymboleInvalideException, RectangleNonValideException,
			ValeurNegativeException {
		super(r, s, dureeDeVie);
		
		if(duree_survie <= 0 || duree_survie > dureeDeVie)
			throw new ValeurNegativeException("La durée de survie n'est pas valide !"
					+" Durée mise dans le constructeur : " + duree_survie+" !");
		
		this.duree_survie = duree_survie;
		this.compt_survie = 0;
		
		this.estVivant = true;
		this.age = 0;
		this.meurtVieillesse = false;
		this.meurtMange = false;
		this.meurtFaim = false;
		this.enReproduction = false;
		this.sexe = sexe;
		
	}
	
	protected abstract Animal seReproduire(Animal partenaire);
	protected abstract boolean placerPetitDans(Animal petit, Case [][] env);
	
	
	/**
	 * On vérifie si l'animal courant a un voisin
	 * 
	 * @param env le plateau où evolue l'animal
	 * @return Les coordonnées du voisin s'il y en a un, null sinon
	 *  
	 */
	public Rectangle voisinAProximiteDans(Case[][] env) {

		int xDroiteAdj, xGaucheAdj; 	// La position du voisin à droite ou bien à gauche
		int yHautAdj, yBasAdj;			// La position du voisin d'en haut ou bien en bas
		int xProie, yProie;
		
		// On met à -1 pour indiquer qu'on n'a pas de voisin
		xProie = -1;
		yProie = -1;
		
		
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
		
		// Proie à gauche
		if(env[xGaucheAdj][this.rect.y].getMatierDansCase() instanceof Animal){
			
			// Proie à gauche
			xProie = xGaucheAdj;
			yProie = this.rect.y;

		}
		else if(env[xGaucheAdj][yHautAdj].getMatierDansCase() instanceof Animal){
			
			// Proie en haut à gauche
			xProie = xGaucheAdj;
			yProie = yHautAdj;

		}
		else if(env[this.rect.x][yHautAdj].getMatierDansCase() instanceof Animal){
			
			// Proie en haut
			xProie = this.rect.x;
			yProie = yHautAdj;

		}
		else if(env[xDroiteAdj][yHautAdj].getMatierDansCase() instanceof Animal){
			
			// Proie en haut à droite
			xProie = xDroiteAdj;
			yProie = yHautAdj;

		}
		else if(env[xDroiteAdj][this.rect.y].getMatierDansCase() instanceof Animal){
			
			// Proie à droite
			xProie = xDroiteAdj;
			yProie = this.rect.y;

		}
		else if(env[xDroiteAdj][yBasAdj].getMatierDansCase() instanceof Animal){
			
			// Proie en bas à droite
			xProie = xDroiteAdj;
			yProie = yBasAdj;

		}
		else if(env[this.rect.x][yBasAdj].getMatierDansCase() instanceof Animal){
			
			// Proie en bas
			xProie = this.rect.x;
			yProie = yBasAdj;
			
		}
		else if(env[xGaucheAdj][yBasAdj].getMatierDansCase() instanceof Animal){ 
			
			xProie = xGaucheAdj;
			yProie = yBasAdj;
		}
		
		// Après avoir fait tous ces tests, a-t-on trouvé un voisin ?
		return ( (xProie == -1) && (yProie == -1) ) ? null: new Rectangle(xProie,yProie,0,0);

	}

	protected void grandir(){
		
		// Si on depasse l'espérance de vie
		if(age > duree_existence){	
			// On meurt de vieillesse
			this.meurt(Mort.Naturel);
		}
		else if(compt_survie > duree_survie){
			// On meurt de faim
			this.meurt(Mort.Faim);
		}
		else{
			// Pas de problème
			age++;
		}

		compt_survie++;
	}


	@Override
	public void evoluerDans(Case [][] env) {
		
		int n = (int)(Math.random()*8 + 1);
		
		switch(n){
		
			case 1 : 	seDeplacer(-1, -1,env);
						break;
		
			case 2 : 	seDeplacer(0, -1,env);
						break;				
					
			case 3 : 	seDeplacer(1, -1,env);
						break;
		
			case 4 :	seDeplacer(1, 0,env);
						break;

			case 5 : 	seDeplacer(1, 1,env);
						break;				
					
			case 6 : 	seDeplacer(0, 1,env);
						break;
						
			case 7 : 	seDeplacer(-1, 1,env);
						break;

			case 8 :	seDeplacer(-1, 0,env);
						break;
						
			default : break;
		}

		
	}
	
	/**
	 * Fait le deplacement proprement dit
	 * 
	 * @param dx Le déplacement en X
	 * @param dy Le déplacement en Y
	 * @param env L'environnement dans lequel on évolue
	 * 
	 * TODO gérer les deplacements en diagonal
	 */
	private void seDeplacer(int dx, int dy, Case [][] env){
		
		env[this.rect.x][this.rect.y].setNewMatiere(null);	// On rend la case inoccupée 
		
		// Si on est au bord gauche
		if( (this.rect.x + dx) < 0 ){
			
			if(env[env.length -1][this.rect.y].getMatierDansCase() == null)
				this.rect.x = env.length -1;
		}
		else if( (this.rect.x + dx) >= env.length ){
			// On est au bord droit
			if(env[0][this.rect.y].getMatierDansCase() == null)
				this.rect.x = 0;
		}
		else{
			// On n'est pas au bord, tout va bien
			if(env[this.rect.x + dx][this.rect.y].getMatierDansCase() == null)
				this.rect.x += dx;
		}
		
		// Si on est au bord du haut
		if( (this.rect.y + dy) < 0 ){
			if(env[this.rect.x][env[0].length -1].getMatierDansCase() == null)
				this.rect.y = env[0].length -1;
		}
		else if( (this.rect.y + dy) >= env[0].length ){
			// On est au bord du bas
			if(env[this.rect.x][0].getMatierDansCase() == null)
				this.rect.y = 0;
		}
		else{
			// On n'est pas au bord, tout va bien
			if(env[this.rect.x][this.rect.y + dy].getMatierDansCase() == null)
				this.rect.y += dy;
		}
		
		env[this.rect.x][this.rect.y].setNewMatiere(this);	// On met la nnouvelle case occupée
		
	}

	
	
	public void meurt(Mort type_mort){
		
		this.estVivant = false;
		
		switch(type_mort){
		
			case Faim : meurtFaim = true; 
						break;
						
			case Mange : meurtMange = true;
						break;
						
			case Naturel : 	meurtVieillesse = true;
							break;
		
			default : 	meurtVieillesse = true;
						break;
		}

			
		// Prévenir les observateurs
		this.notifierObs();

	}
	
	public Mort getEtatMort(){
		
		if(meurtFaim){
			return Mort.Faim;
		}
		else if(meurtMange){
			return Mort.Mange;
		}
		else{
			return Mort.Naturel;
		}
	}
	
	
	@Override
	public boolean vivant(){
		
		return estVivant;
	}
	
	
	public int getAge(){
		return this.age;
	}
	
	public int getDureeVie(){
		return this.duree_existence;
	}
	
	public boolean enCoursDeReproduction(){
		return enReproduction;
		
	}





}
