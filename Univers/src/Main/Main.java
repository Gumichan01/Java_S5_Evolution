package Main;

import Modele.Debug;
import Modele.Univers;



/**
 * La classe où tout commence 
 * 
 * Pour l'instant on teste tout dans le main
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 * TODO Kahina - Animal, Loup, Mouton, en gros tout la reste de la partie modèle 
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		if(Debug.DEBUG_UNIVERS)
			System.out.println("Création de l'Univers");
		
		new Univers(3, 3, 2, 2).evoluer();
		
		if(Debug.DEBUG_UNIVERS)
			System.out.println("FIn de l'Univers");
		
	}

}
