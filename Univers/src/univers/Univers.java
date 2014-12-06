package univers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

import observateurs.ObsLoup;
import observateurs.ObsMouton;
import observateurs.ObsUnivers;
import observateurs.Observable;
import observateurs.Observateur;

import vue.Vue;

import modele.Animal;
import modele.Debug;
import modele.Loup;
import modele.Matiere;
import modele.Mouton;
import modele.SelMineral;
import modele.Sexe;

import exceptionUnivers.DimensionNonValideException;
import exceptionUnivers.ValeurNegativeException;


/**
 * La classe Univers représente le modèle principal dans le jeu
 * Il contient les différentes entités, mais aussi le plateau
 * 
 * Dans le cadre de ce projet, nous avaons fait le choix de créer un univers en tore bidimensionnel
 * http://fr.wikipedia.org/wiki/Univers_en_tore_bidimensionnel
 * 
 * Cette univers va évoluer au fur et à mesure de l'état des entités cibles
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 * 
 */
public class Univers implements Observable {

	private int largeur; 	// largeur de l'univers, 999 max
	private int hauteur;	// hauteur de l'univers, 26 max

	private int tour = 0;

	private Case [][] plateau;	// Le plateau sur lequelle mettre les matières, une case vaut null si aucune matière n'est présente

	private ArrayList<Matiere> matieres;	// La liste de matières qui évolueront (SelMineral, Animuax,...)
	private ArrayList<Observateur> liste_observateurs;
	//private LinkedList<Animal> file;

	public Univers(int l, int h, int nbMouton, int nbLoup) 
		throws ValeurNegativeException, DimensionNonValideException{

		if(l < 0 || l > 999)
			throw new DimensionNonValideException("Largeur invalide : "+l+" !");
		
		if(h < 0 || h > 26)
			throw new DimensionNonValideException("Hauteur invalide : "+h+" !");

		if(nbMouton < 0 || nbLoup < 0)
			throw new ValeurNegativeException("Nombre d'animaux invalide !");
		
		// TODO Luxon - Traiter le cas où on a trop d'animaux par rapport à la taille du plateau
		if((nbMouton + nbLoup) > (l*h)){
			// TODO Gérer le cas où j'ai plus de moutons que de loups et vice-versa
			// TODO Combien de mouton dois-je mettre par rapport aux loups pour assurer l'equilibre et la perennité de l'univers ?
			// TODO faire les tests lorsque l'implementation de nourriture et reproduction sera fait
			throw new UnsupportedOperationException("TODO : Operation non supporté, traier le cas où on a trop d'animaux");
		}
		
		this.largeur = l;
		this.hauteur = h;
		
		plateau = new Case[largeur][hauteur];	/** @deprecated Supprimer ça*/

		matieres = new ArrayList<Matiere>();
		matieres.ensureCapacity((this.largeur*this.hauteur)+1); // On définit une capacité min = (taille totale du plateau + 1)
		
		
		liste_observateurs = new ArrayList<Observateur>();
		liste_observateurs.add(new ObsUnivers(this));

		
		try{
		
			for(int i = 0; i < largeur; i++){
				
				for(int j = 0; j < hauteur; j++){
					
					plateau[i][j] = new Case(i,j);
				}
				
			}
		
		}catch(ValeurNegativeException ve ){
			
			System.out.println("FATAL ERROR : Un erreur grave s'est produite lors de l'initialisation du plateau :"
								+ve.getMessage());
			throw ve;
		}
		
		
		// Position aléatoire
		int xAlea;
		int yAlea;

		// On place les moutons
		for(int i = 1; i <= nbMouton;i++){

			do{

			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);

			}while(plateau[xAlea][yAlea].getMatierDansCase() != null);


			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {

				Mouton m = new Mouton(new Rectangle(xAlea, yAlea, 1, 1), "M", 50, Sexe.Femelle, 5);
				m.ajoutObservateur(new ObsMouton(m));
		
				this.ajouter(m); 
				this.plateau[xAlea][yAlea].setNewMatiere(this.matieres.get(this.matieres.size() - 1));

			} catch (Exception e) {

				e.printStackTrace();
				}
			
		}

		// On place les loups
		for(int i = 1; i <= nbLoup;i++){
			
			do{
			
			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);

			}while(plateau[xAlea][yAlea].getMatierDansCase() != null);

			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {
				
				Loup loup = new Loup(new Rectangle(xAlea, yAlea, 1, 1),"L", 60, Sexe.Femelle, 10); 
				loup.ajoutObservateur(new ObsLoup(loup));
				
				this.ajouter(loup);
				this.plateau[xAlea][yAlea].setNewMatiere(this.matieres.get(this.matieres.size() - 1));
				
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
				
				e.printStackTrace();
			}

			tour++;

			for(int i = 0;i < matieres.size();i++){

				matieres.get(i).evoluerDans(this.plateau);
			}

			if(Debug.DEBUG_UNIVERS)
				System.out.println(this.toString());

			this.recupererNouvMatieres();
			this.supprimerMorts();	// On supprime les morts
			
			// TODO tester si une case se libère, pour mettre les animaux en trop

			// Appel de la vue (l'observateur)
			this.notifierObs();

		}
	}

	private void recupererNouvMatieres(){

		for(int c = 0; c < this.largeur ; c++){
			for(int l = 0; l < this.hauteur; l++){

				Matiere tmp = plateau[c][l].getMatierDansCase();
				
				// On récupère les sels minéraux
				if(tmp instanceof SelMineral)
					ajouter(tmp);
				else if(tmp instanceof Animal && !this.matieres.contains(tmp)){ 
					// On a un nouvel animal qui vient de naitre;
					System.out.println("EVENT : Un animal vient de naitre ");
					this.matieres.add(tmp);
				}
					
				
			}	
		}
	}
	
	
	private void supprimerMorts(){

		for(int i = 0; i < this.matieres.size();i++){
			if(!this.matieres.get(i).vivant()){
				
				// On met l'emplacement du mort à null
				plateau[this.matieres.get(i).getRect().x][this.matieres.get(i).getRect().y].setNewMatiere(null);
				
				this.matieres.remove(i);	// On le supprime
				i--;	// On décrémente i à cause du décalage vers la gauche
			}
		}
	}


	@Override
	public void ajoutObservateur(Observateur o) {
	
		if(o != null){
			
			liste_observateurs.add(o);
		}
	}


	@Override
	public void notifierObs() {

		for(Observateur obs : liste_observateurs){
			
			obs.notifier();
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

	
	public Case [][] getPlateau(){
		
		return this.plateau;
	}
	
	@Override
	public String toString(){
		
		return "\nTour : "+tour+" | Nombre d'éléments au total :  "+matieres.size();
	}
}



















