package mainUnivers;

import javax.swing.JFrame;

import modele.Debug;
import univers.Univers;
import vue.CommandeGraphX;
import vue.Vue_graphique;



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
	public static void main(String[] args) throws Exception{
		// TODO Finir les autres TODO avant de faire l'interface graphique
		
		if(Debug.DEBUG_UNIVERS)
			System.out.println("Création de l'Univers");
		
		Univers u;
		
		try {
				// On crée une fenetre
				CommandeGraphX cmdX =  new CommandeGraphX();
				cmdX.afficherMenu();
				
			//u = new Univers(32,16,256,32);
			//u.evoluer();
			
			
		}catch(Exception e){
			
			//System.out.println("Une erreur s'est produite : "+ e.getMessage());
			e.printStackTrace();
			
		}finally{
			
			if(Debug.DEBUG_UNIVERS)
				System.out.println("Fin de l'Univers");			
			
		}

		
	}

}
