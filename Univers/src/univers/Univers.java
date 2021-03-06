package univers;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
//import java.util.LinkedList;

import observateurs.ObsLoup;
import observateurs.ObsMouton;
import observateurs.ObsUnivers;
import observateurs.Observable;
import observateurs.Observateur;

import modele.Animal;
import modele.Entite;
import modele.Loup;
import modele.Matiere;
import modele.Mouton;
import modele.SelMineral;
import modele.Sexe;

import exceptionUnivers.DimensionNonValideException;
import exceptionUnivers.ValeurNegativeException;


/**
 * 
 * @class Univers
 * 
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

	public final static int UNIVERS_TAILLE_MIN = 8;
	public final static int UNIVERS_TAILLE_MAX = 26;
	
	private int largeur; 	// largeur de l'univers, 999 max
	private int hauteur;	// hauteur de l'univers, 26 max

	// boolean ajoutAnimal = false;
	
	private int tour = 0;

	private Case [][] plateau;	// Le plateau sur lequel mettre les matières

	private ArrayList<Matiere> matieres;	// La liste de matières qui évolueront (SelMineral, Animuax,...)
	private ArrayList<Observateur> liste_observateurs;
	
	private int nbMoutonEnTrop;
	private int nbLoupEntrop;
	
	private int moutons;
	private int loups;

	private LinkedList<Animal> file;		// La liste des animaux en trop

	public Univers(int l, int h, int nbMouton, int nbLoup) 
		throws ValeurNegativeException, DimensionNonValideException{

		if(l < UNIVERS_TAILLE_MIN || l > UNIVERS_TAILLE_MAX)
			throw new DimensionNonValideException("Largeur invalide : "+l+" !");
		
		if(h < UNIVERS_TAILLE_MIN || h > UNIVERS_TAILLE_MAX)
			throw new DimensionNonValideException("Hauteur invalide : "+h+" !");

		if(nbMouton < 0 || nbLoup < 0)
			throw new ValeurNegativeException("Nombre d'animaux invalide !");

		
		if(nbMouton > (l*h)){
			
			moutons = (l*h) / 4;
			nbMoutonEnTrop = (l*h) - moutons;
		}
		else{
			
			moutons = nbMouton;
			nbMoutonEnTrop = 0;
		}
		
		if(nbLoup > (l*h)){
			
			loups = (l*h) / 4;
			nbLoupEntrop = (l*h) - loups;
		}
		else{
			
			loups = nbLoup;
			nbLoupEntrop = 0;
		}
		
		this.largeur = l;
		this.hauteur = h;
		
		plateau = new Case[largeur][hauteur];

		matieres = new ArrayList<Matiere>();
		matieres.ensureCapacity((this.largeur*this.hauteur)+1); // On définit une capacité min = (taille totale du plateau + 1)
		
		if(nbMoutonEnTrop + nbLoupEntrop > 0)
			file = new LinkedList<Animal>();
		
		
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
		int select_sexe;

		Sexe s;

		// On place les moutons
		for(int i = 1; i <= moutons;i++){

			do{

			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);

			}while(plateau[xAlea][yAlea].getMatierDansCase() != null);

			//Selection aléatoire du sexe
			select_sexe = (int)(Math.random()*2 +1);

			if(select_sexe == 1)
				s = Sexe.Male;
			else
				s = Sexe.Femelle;
			

			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {

				Mouton m = new Mouton(new Rectangle(xAlea, yAlea, Entite.WIDTH, Entite.HEIGHT), "M", 50, s, 5);
				m.ajoutObservateur(new ObsMouton(m));
		
				this.ajouter(m); 
				this.plateau[xAlea][yAlea].setNewMatiere(this.matieres.get(this.matieres.size() - 1));

			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}

		// On place les loups
		for(int i = 1; i <= loups;i++){
			
			do{
			
			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);

			}while(plateau[xAlea][yAlea].getMatierDansCase() != null);
			
			//Selection aléatoire du sexe
			select_sexe = (int)(Math.random()*2 +1);

			if(select_sexe == 1)
				s = Sexe.Male;
			else
				s = Sexe.Femelle;

			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {
				
				Loup loup = new Loup(new Rectangle(xAlea, yAlea, Entite.WIDTH, Entite.HEIGHT),"L", 60, s, 10); 
				loup.ajoutObservateur(new ObsLoup(loup));
				
				this.ajouter(loup);
				this.plateau[xAlea][yAlea].setNewMatiere(this.matieres.get(this.matieres.size() - 1));
				
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		
		// On met les animaux en trop
		for(int i = 0; i < nbMoutonEnTrop; i++){
			
			do{

			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);

			}while(plateau[xAlea][yAlea].getMatierDansCase() != null);

			//Selection aléatoire du sexe
			select_sexe = (int)(Math.random()*2 +1);

			if(select_sexe == 1)
				s = Sexe.Male;
			else
				s = Sexe.Femelle;
			

			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {

				Mouton m = new Mouton(new Rectangle(xAlea, yAlea, Entite.WIDTH, Entite.HEIGHT), "M", 50, s, 5);
				m.ajoutObservateur(new ObsMouton(m));
		
				file.addLast(m);

			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		
		for(int i = 0; i < nbLoupEntrop; i++){
			
			do{
				
			xAlea = (int)(Math.random()*this.largeur);
			yAlea = (int)(Math.random()*this.hauteur);

			}while(plateau[xAlea][yAlea].getMatierDansCase() != null);
			
			//Selection aléatoire du sexe
			select_sexe = (int)(Math.random()*2 +1);

			if(select_sexe == 1)
				s = Sexe.Male;
			else
				s = Sexe.Femelle;

			// Ajout de la nouvelle instance de l'animal dans l'ArrayList
			try {
				
				Loup loup = new Loup(new Rectangle(xAlea, yAlea, Entite.WIDTH, Entite.HEIGHT),"L", 60, s, 10); 
				loup.ajoutObservateur(new ObsLoup(loup));
				
				file.addLast(loup);
				
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

	/**
	 * Fait evoluer l'univers
	 */
	public void evoluer(){
	
		tour++;
	
		for(int i = 0;i < matieres.size();i++){
	
			matieres.get(i).evoluerDans(this.plateau);
	
		}
	
	
		this.recupererNouvMatieres();
		this.supprimerMorts();	// On supprime les morts
		
		if(this.nbAnimaux() < (this.largeur * this.hauteur)){
			
			if(file != null && !file.isEmpty()){
				
				this.ajouter(file.pollFirst());
			}
		}
	
		// Appel de la vue (l'observateur)
		this.notifierObs();
		
	}

	private void recupererNouvMatieres(){

		for(int c = 0; c < this.largeur ; c++){
			for(int l = 0; l < this.hauteur; l++){

				Matiere tmp = plateau[c][l].getMatierDansCase();

				if(!this.matieres.contains(tmp)){
					// On récupère les sels minéraux ou bien un nouvel animal qui vient de naitre
					if( tmp instanceof SelMineral || tmp instanceof Animal)
						ajouter(tmp);
				}

			}	
		}
	}
	
	
	private void supprimerMorts(){

		for(int i = 0; i < this.matieres.size();i++){
			if(!this.matieres.get(i).vivant()){
				
				// On met l'emplacement du mort à null sauf s'il a disparu de lui-même
				if(this.matieres.get(i).equals(plateau[this.matieres.get(i).getRect().x][this.matieres.get(i).getRect().y].getMatierDansCase())){
				
					plateau[this.matieres.get(i).getRect().x][this.matieres.get(i).getRect().y].setNewMatiere(null);
				}
				
				this.matieres.remove(i);	// On le supprime
				i--;	// On décrémente i à cause du décalage des éléments de la liste vers la gauche
			}
		}
	}

	public int nbAnimaux(){
		
		int compt = 0;
		
		for(Matiere m  : this.matieres){
			
			if(m instanceof Animal)
				compt++;
			
		}
		
		return compt;
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
		
		return this.largeur;
	}
	
	public int getHauteur(){
		
		return this.hauteur;
	}
	
	public ArrayList<Matiere> getMatieres(){
		
		return matieres;
	}

	
	public Case [][] getPlateau(){
		
		return this.plateau;
	}
	
	@Override
	public String toString(){
		
		return "\nTour : "+tour+" | Nombre d'animaux :  "+nbAnimaux();
	}
}



















