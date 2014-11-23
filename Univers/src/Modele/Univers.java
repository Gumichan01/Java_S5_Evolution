package Modele;

import java.util.ArrayList;
import java.util.LinkedList;

import ExceptionUnivers.ValeurNegativeException;

/**
 * La classe Univers représente le modéle principal dans le jeu
 * Il contient les différents entités
 * 
 * Cette univers va écvoluer au fur et à mesure de l'éta des entités cibles
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 * 
 */
public class Univers {

	private int largeur; 	// largeur de l'univers
	private int hauteur;	// hauteur de l'univers
	
	private int tour = 0;
	
	private Matiere [][] plateau;	// Le plateau sur lequelle mettre les matières, une case vaut null si aucune matière n'est présente
	//private String [][] grille;	// La grille où mettre les symboles (pour l'affichage) [! à mettre plutôt dans l'interface console ]
	private boolean [][] herbes;	/* Ce tableau à double dimension indique si une case à de l'herbe (d'où la suppression Herbe) */
	
	ArrayList<Matiere> matieres;	// La liste de matières qui évolueront (SelMineral, Animuax,...)
	
	public Univers(int l, int h, int nbMouton, int nbLoup) 
		throws ValeurNegativeException{
		
		if(l < 0)
			throw new ValeurNegativeException("Largeur invalide : "+l+" !");
		
		if(h < 0)
			throw new ValeurNegativeException("Hauteur invalide : "+h+" !");

		if(nbMouton < 0 || nbLoup < 0)
			throw new ValeurNegativeException("Nombre d'animaux invalide !");
		
		if((nbMouton + nbLoup) > (l*h))
			throw new UnsupportedOperationException("TODO : Operation non supporté, traier le cas où on a trop d'animaux");
			
		// TODO Luxon - Traiter le cas où on a trop d'animaux par rapport à la taille du plateau
		
		this.largeur = l;
		this.hauteur = h;
		
		plateau = new Matiere[largeur][hauteur];
		herbes = new boolean[largeur][hauteur];

		matieres = new ArrayList<Matiere>((this.largeur*this.hauteur)+1);	// La liste aura de base une capacité  = taille totale du plateau + 1
		
		// Position aléatoire
		int xAlea;
		int yAlea;
		
		
		for(int i = 0;i < this.largeur; i++ ){
			
			for(int j = 0; j< this.hauteur;j++){
				
				herbes[i][j] = true;
			}
		}
		
		
		// On place les moutons
		for(int i = 1; i< nbMouton;i++){
			
			do{
			
			xAlea = (int)(Math.random()%this.largeur);
			yAlea = (int)(Math.random()%this.hauteur);
				
			}while(plateau[xAlea][yAlea] != null);
			
			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			
		}

		// On place les loups
		for(int i = 1; i< nbLoup;i++){
			
			do{
			
			xAlea = (int)(Math.random()%this.largeur);
			yAlea = (int)(Math.random()%this.hauteur);
				
			}while(plateau[xAlea][yAlea] != null);
			
			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			
		}
		
		
	}
	

	private void ajouter(Matiere m){
		
		if(m == null)
			throw new NullPointerException("Matiere null à ajouter non valide\n");
		
		matieres.add(m);
	}
	
	private Matiere enlever(Matiere m){
		
		int index = matieres.indexOf(m);

		return (index == -1) ? null: matieres.remove(index); 
		
	}
	
	
	public void evoluer(){
		
		while(matieres.size() != 0){
			
			try{
			
				Thread.sleep(1000);
			
			}catch(InterruptedException e){
				
			}
			
			
			
		}
	}
	
}



















