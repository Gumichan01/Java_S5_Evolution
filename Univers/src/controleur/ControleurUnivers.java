package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import univers.Univers;

public class ControleurUnivers implements ActionListener{

	private Univers univers;
	private boolean enPause = false;


	public ControleurUnivers(){}
	
	/**
	 * 
	 * @param u
	 */
	public ControleurUnivers(Univers u) {
	
		if(u == null)
				throw new NullPointerException("Echec lors de la création du contrôleur de l'univers : Univers inexistant !!");
		
		univers = u;
		
	}

	/**
	 * 
	 * @param u
	 */
	public void construireUnivers(Univers u){
		
		if(u == null)
			throw new NullPointerException("Erreur construction de l'univers");
		
		if(univers == null)
			univers = u;
		
	}
	
	
	public void jouer(){
		
		while(univers.nbAnimaux() > 0){

				try{	// On laisse s'écouler 1 seconde entre chaque tour
					// On le baissera sans doute à 500 millisecondes pour un grand nombre d'éléments
				Thread.sleep(1000);
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
			}
			
			if(!enPause)
				univers.evoluer();
		
		}
		
	}
	
	
	/**
	 * Mets en pause l'univers ou poursuit le jeu
	 * 
	 */
	public void pauseUnivers(){
		
		if(!enPause){

			enPause = true;
		}
		else{

			enPause = false;
		}
		
	}

	/**
	 * 
	 * @author luxon
	 *
	 */
	public class Jeu extends Thread{
		
		@Override
		public void run(){
			
			ControleurUnivers.this.jouer();
		}
	}
	
	
	/**
	 * Récupère l'évenement et agit sur l'univers (Mise en pause, dans le cas présent)
	 * 
	 * @param arg0 L'évenement cible
	 * 
	 */
	@Override

	public void actionPerformed(ActionEvent arg0) {

			this.pauseUnivers();
	}
	
	
}
