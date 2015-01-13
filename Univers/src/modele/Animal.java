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

	
	protected boolean placerPetitDans(Animal petit, Case[][] env) {

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
		

		if(env[xGaucheAdj][this.rect.y].getMatierDansCase() instanceof Animal){
			
			// Voisin à gauche
			return new Rectangle(xGaucheAdj,this.rect.y,0,0);
		}
		else if(env[xGaucheAdj][yHautAdj].getMatierDansCase() instanceof Animal){
			
			// Voisin en haut à gauche
			return new Rectangle(xGaucheAdj,yHautAdj,0,0);
		}
		else if(env[this.rect.x][yHautAdj].getMatierDansCase() instanceof Animal){
			
			// Voisin en haut
			return new Rectangle(this.rect.x,yHautAdj,0,0);
		}
		else if(env[xDroiteAdj][yHautAdj].getMatierDansCase() instanceof Animal){
			
			// Voisin en haut à droite
			return new Rectangle(xDroiteAdj,yHautAdj,0,0);
		}
		else if(env[xDroiteAdj][this.rect.y].getMatierDansCase() instanceof Animal){
			
			// Voisin à droite
			return new Rectangle(xDroiteAdj,this.rect.y,0,0);
		}
		else if(env[xDroiteAdj][yBasAdj].getMatierDansCase() instanceof Animal){
			
			// Voisin en bas à droite
			return new Rectangle(xDroiteAdj,yBasAdj,0,0);

		}
		else if(env[this.rect.x][yBasAdj].getMatierDansCase() instanceof Animal){
			
			// Voisin en bas
			return new Rectangle(this.rect.x,yBasAdj,0,0);
		}
		else if(env[xGaucheAdj][yBasAdj].getMatierDansCase() instanceof Animal){ 
			
			// Voisin en bas à gauche
			return new Rectangle(xGaucheAdj,yBasAdj,0,0);
		}
		
		// Aucun voisin -> null
		return null;

	}

	protected void grandir(){

		this.age++;
		this.compt_survie++;
		
		//System.out.println("Age : "+age+ "compt_survie : "+compt_survie);
		
		// Si on depasse l'espérance de vie
		if(age > duree_existence){	
			// On meurt de vieillesse
			this.meurt(Mort.Naturel);
		}
		else if(compt_survie > duree_survie){
			// On meurt de faim
			this.meurt(Mort.Faim);
		}
		else if(age == duree_existence){
			meurtVieillesse = true;
			this.notifierObs();
		}
		else if(compt_survie == duree_survie){
			meurtFaim = true;
			this.notifierObs();
		}


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
						
			case Mange : meurtMange = true; meurtVieillesse = false; meurtFaim = false;
						 this.notifierObs();
						 break;
						
			case Naturel : 	meurtVieillesse = true;
							break;
		
			default : 	meurtVieillesse = true;
						break;
		}

			
		// Prévenir les observateurs
		//this.notifierObs();

	}
	
	/**
	 * Retourne l'etat de mort de l'animal qui est mort
	 * 
	 * @return L'etat de mort de l'animal
	 */
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
