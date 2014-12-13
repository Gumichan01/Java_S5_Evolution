package mainUnivers;

import controleur.CommandeGraphX;
import modele.Debug;




/**
 * La classe où tout commence 
 * 
 * Pour l'instant on teste tout dans le main
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 */
public class Main {

	/**
	 * @param args Les paramètres positionnels ne servent à rien 
	 */
	public static void main(String[] args){
		// TODO Finir les autres @TODO avant de faire l'interface graphique
		
	if(Debug.DEBUG_UNIVERS)
		System.out.println("Création de l'Univers");
	
	// On crée une fenetre
	CommandeGraphX cmdX =  new CommandeGraphX();
	cmdX.afficherMenu();
	
	if(Debug.DEBUG_UNIVERS)
		System.out.println("Fin de l'Univers");			
			


		
	}

}
