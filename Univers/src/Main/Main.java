package Main;

import ExceptionUnivers.ValeurNegativeException;
import Modele.Debug;
import Modele.Univers;
import Observateurs.ObsUnivers;
import Vue.Vue;
import Vue.Vue_console;



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
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Finir les autres TODO avant de faire l'interface graphique
		
		if(Debug.DEBUG_UNIVERS)
			System.out.println("Création de l'Univers");
		
		Univers u;
		Vue v = new Vue_console();
		
		try {
			
			u = new Univers(8, 4, 0, 1, v);
			u.evoluer();
			
		}catch(Exception e){
			
			e.printStackTrace();
		
		}finally{
			
			if(Debug.DEBUG_UNIVERS)
				System.out.println("Fin de l'Univers");			
			
		}

		
	}

}
