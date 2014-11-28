package Main;

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

		Univers u = new Univers(10, 10, 1, 1);
		
		//System.out.println(u.toString());
		
		u.evoluer();
		
		
		
	}

}
