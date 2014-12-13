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
	 * @param pause True pour mettre en pause, False pour continuer
	 * 
	 */
	public void pauseUnivers(boolean pause){
		
		if(pause && !enPause){
			// TODO mettre en pause l'univers
			enPause = true;
		}
		else if(enPause){
			// TODO remettre l'univers là où il s'est arrêté
			enPause = false;
		}
		
	}

	/**
	 * Récupère l'évenement et agit sur l'univers (mettre en pause, le sauvegarder ...)
	 * 
	 * @param arg0 L'évenement cible
	 * 
	 */
	@Override

	public void actionPerformed(ActionEvent arg0) {
		// TODO Gérer les action sur les différants boutons
		throw new UnsupportedOperationException("TODO : Mettre l'univers en pause et continuer");
	}
	
	
}
