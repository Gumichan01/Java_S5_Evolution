package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import univers.Univers;

public class ControleurUnivers /*implements ActionListener*/{

	private Univers univers;
	//private transient boolean enPause = false;


	public ControleurUnivers(){}
	
	public ControleurUnivers(Univers u) {
	
		if(u == null)
				throw new NullPointerException("Echec lors de la création du contrôleur de l'univers : Univers inexistant !!");
		
		univers = u;
		
	}

	public void construireUnivers(Univers u){
		
		if(u == null)
			throw new NullPointerException("Erreur construction de l'univers");
		
		univers = u;
	}
	
	public void evoluerUnivers(){
		
		while(univers.nbAnimaux() > 0){

				try{	// On laisse s'écouler 1 seconde entre chaque tour
					// On le baissera sans doute à 500 millisecondes pour un grand nombre d'éléments
				Thread.sleep(1000);
				
			}catch(InterruptedException e){
				
				e.printStackTrace();
			}
			
			//if(enPause)
				univers.evoluer();
		
		}
		
	}
	
	/*public void pauseUnivers(boolean pause){
		
		if(pause){
			// TODO mettre en pause l'univers
			enPause = true;
		}
		else{
			// TODO remettre l'univers là pù il s'est arrêté
			enPause = false;
		}
		
	}*/

	/**
	 * Récupère l'evennement et agit sur l'univers (mettre en pause, le sauvegarder ...)
	 */
	/*@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO : Mettre l'univers en pause et continuer");
	}*/
	
	
}
