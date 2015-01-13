package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.lang.model.type.NullType;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import modele.Matiere;

import univers.Univers;

public class ControleurUnivers implements ActionListener{

	Univers univers;
	private boolean enPause = false;


	public ControleurUnivers(){}



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
	 * Mets en pause l'univers ou poursuivre le jeu
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
