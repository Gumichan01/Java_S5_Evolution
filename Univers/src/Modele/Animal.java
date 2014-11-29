package Modele;

import java.awt.Rectangle;

import javax.swing.AbstractAction;

import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;
import ExceptionUnivers.ValeurNegativeException;

public abstract class Animal extends Matiere{
	
	protected int duree_survie;
	protected boolean estVivant;
	protected int age;
	private int compt_survie;
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
	
	public abstract Animal seReproduire(Animal partenaire);
	
	
	protected void grandir(){
		
		// Si on depasse l'esperance de vie
		if(age > duree_vie){	
			// On meurt de vieillesse
			this.meurt(Mort.Naturel);
		}
		else if(age == duree_vie){
			// On va mourrir
			// TODO prevenir l'observateur
			age++;
		}
		else if(compt_survie > duree_survie){
			// On meurt de faim
			this.meurt(Mort.Faim);
		}
		else if(compt_survie == duree_survie){
			// On va mourrir de faim
			// TODO prevenir l'observateur
		}
		else{
			// Pas de problème
			age++;
		}
		
		compt_survie++;
		
	}
	
	
	@Override
	public void evoluerDans(Matiere [][] env, boolean [][] herbes) {
		
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
	 * @param dx 
	 * @param dy
	 * @param env
	 * 
	 * TODO gérer les deplacements en diagonal
	 */
	private void seDeplacer(int dx, int dy, Matiere [][] env){
		
		env[this.rect.x][this.rect.y] = null;	// On met l'ancienne position à null
		
		// Si on est au bord gauche
		if( (this.rect.x + dx) < 0 ){
			
			if(env[env.length -1][this.rect.y] == null)
				this.rect.x = env.length -1;
		}
		else if( (this.rect.x + dx) >= env.length ){
			// On est au bord droit
			if(env[0][this.rect.y] == null)
				this.rect.x = 0;
		}
		else{
			// On n'est pas au bord, tout va bien
			if(env[this.rect.x + dx][this.rect.y] == null)
				this.rect.x += dx;
		}
		
		// Si on est au bord du haut
		if( (this.rect.y + dy) < 0 ){
			if(env[this.rect.x][env[0].length -1] == null)
				this.rect.y = env[0].length -1;
		}
		else if( (this.rect.y + dy) >= env[0].length ){
			// On est au bord du bas
			if(env[this.rect.x][0] == null)
				this.rect.y = 0;
		}
		else{
			// On n'est pas au bord, tout va bien
			if(env[this.rect.x][this.rect.y + dy] == null)
				this.rect.y += dy;
		}
		
		env[this.rect.x][this.rect.y] = this;	// On met la nouvelle position à this
		
	}

	
	
	public void meurt(Mort type_mort){
		// TODO Auto-generated method stub
		
		if(Debug.DEBUG_UNIVERS)
			System.out.println("MORT de "+this.toString());
		
		this.estVivant = false;
		
		switch(type_mort){
		
			case Faim : meurtFaim = true;
						// TODO prevenir un observateur
						break;
						
			case Mange : meurtMange = true;
						// TODO prevenir un observateur
						break;
						
			case Naturel : 	meurtVieillesse = true;
							// TODO prevenir un observateur
							break;
		
			default : 	meurtVieillesse = true;
						break;
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
		return this.duree_vie;
	}
	
	public boolean enCoursDeReproduction(){
		return enReproduction;
		
	}





}
