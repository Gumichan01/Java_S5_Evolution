package vue;

import java.awt.Component;
import java.awt.Container;



import javax.swing.JFrame;


public class Fenetre extends JFrame{

	private Container conteneur;
	
	public Fenetre(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	// fin programme après fermeture de la fenetre
		this.setTitle("Evolution - Projet Java");		// On met le titre
		this.setBounds(200, 200,800, 600);				// On definit la taille
		
		this.conteneur =  this.getContentPane();
		this.conteneur.setLayout(null);
		
		this.setResizable(false);
		
		this.setVisible(true);							// On le rend visible
	}
	
	
	
	public void afficherMenu(){
		

		
	}
	
	
	public void afficherJeu(){
		
		// TODO afficher plateau ...
		
	}
	
	public void ajoutComposant(Component comp){
		
		this.conteneur.add(comp);
	}
	
	
}
