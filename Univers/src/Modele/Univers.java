package Modele;

import java.awt.Rectangle;
import java.util.ArrayList;
//import java.util.LinkedList;

import ExceptionUnivers.DimensionNonValideException;
import ExceptionUnivers.RectangleNonValideException;
import ExceptionUnivers.SymboleInvalideException;
import ExceptionUnivers.ValeurNegativeException;

/**
 * La classe Univers représente le modèle principal dans le jeu
 * Il contient les différentes entités
 * 
 * Cette univers va évoluer au fur et à mesure de l'état des entités cibles
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
		throws ValeurNegativeException, DimensionNonValideException{
		
		if(l < 0)
			throw new DimensionNonValideException("Largeur invalide : "+l+" !");
		
		if(h < 0)
			throw new DimensionNonValideException("Hauteur invalide : "+h+" !");

		if(nbMouton < 0 || nbLoup < 0)
			throw new ValeurNegativeException("Nombre d'animaux invalide !");
		
		// TODO Luxon - Traiter le cas où on a trop d'animaux par rapport à la taille du plateau
		if((nbMouton + nbLoup) > (l*h))
			throw new UnsupportedOperationException("TODO : Operation non supporté, traier le cas où on a trop d'animaux");
			
		
		this.largeur = l;
		this.hauteur = h;
		
		plateau = new Matiere[largeur][hauteur];
		herbes = new boolean[largeur][hauteur];

		matieres = new ArrayList<Matiere>();
		matieres.ensureCapacity((this.largeur*this.hauteur)+1); // On définit une capacité min = (taille totale du plateau + 1)
		
		// Position aléatoire
		int xAlea;
		int yAlea;
		
		for(int i = 0;i < this.largeur; i++ ){
			
			for(int j = 0; j< this.hauteur;j++){
				
				herbes[i][j] = true;
			}
		}
		
		
		// On place les moutons
		for(int i = 1; i <= nbMouton;i++){
			
			do{
			
			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);
				
			}while(plateau[xAlea][yAlea] != null);
			
			
			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {
				
				this.ajouter(new Mouton(new Rectangle(xAlea, yAlea, 1, 1), "M", 50, Sexe.Femelle, 5)); 
				this.plateau[xAlea][yAlea] = this.matieres.get(this.matieres.size() - 1);

			} catch (Exception e) {

				e.printStackTrace();
				}
			
		}

		// On place les loups
		for(int i = 1; i <= nbLoup;i++){
			
			do{
			
			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);

			}while(plateau[xAlea][yAlea] != null);

			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {
				
				this.ajouter(new Loup(new Rectangle(xAlea, yAlea, 1, 1),"L", 60, Sexe.Femelle, 10));
				this.plateau[xAlea][yAlea] = this.matieres.get(this.matieres.size() - 1);
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		
	}
	

	public void ajouter(Matiere m){
		
		if(m == null)
			throw new NullPointerException("Matiere null à ajouter non valide\n");
		
		matieres.add(m);
	}
	
	public Matiere enlever(Matiere m){
		
		int index = matieres.indexOf(m);

		return (index == -1) ? null: matieres.remove(index); 
		
	}
	
	
	public void evoluer(){
		
		while(matieres.size() != 0){
			
			try{	// On laisse s'écouler 1 seconde entre chaque tour
					// On le baissera sans doute à 500 millisecondes pour un grand nombre d'éléments
				Thread.sleep(1000);
			
			}catch(InterruptedException e){
				
			}

			tour++;	
			
			// TODO prévenir la vue du changement d'état pour la synchronisation 
			
			for(int i = 0;i < matieres.size();i++){
				
				matieres.get(i).evoluerDans(this.plateau, this.herbes);
			}
			
			if(Debug.DEBUG_UNIVERS)
				System.out.println(this.toString());
			
			this.supprimerMorts();	// On supprime les morts
			
			// TODO tester si une case se libère, pour mettre les animaux en trop
			
			// Appel de la vue (l'observateur)
		}
	}
	
	private void supprimerMorts(){
		
		for(int i = 0; i < this.matieres.size();i++){
			if(!this.matieres.get(i).vivant()){
				
				// On met lemplacement du mort à null
				plateau[this.matieres.get(i).getRect().x][this.matieres.get(i).getRect().y] = null;
				this.matieres.remove(i);	// On le supprime
				i--;	// On décrémente i à cause du décalage vers la gauche
			}
		}
	}
	
	
	public int getLargeur(){
		
		return largeur;
	}
	
	public int getHauteur(){
		
		return hauteur;
	}
	
	public ArrayList<Matiere> getMatieres(){
		
		return matieres;
	}
	
	public boolean [][] getHerbes(){
		
		return herbes;
	}
	
	@Override
	public String toString(){
		
		return "Univers - dimension(l,h) : ("+largeur+","+hauteur+")"
				+" Tour numéro : "+tour+"; Nombre d'éléments au total :  "+matieres.size();
	}
}



















