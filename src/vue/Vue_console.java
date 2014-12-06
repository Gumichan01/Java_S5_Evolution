package vue;

public class Vue_console implements Vue{

	@Override
	public void afficherGrille(String[][] g) {

		if(g == null){
			System.err.println("Impossible de mettre à jour la grille ");
			return;
		}
		
		char ch = 'A';
		
		System.out.println("");
		System.out.print("    ");
		
		for(int i= 1; i < g.length+1; i++){

			if( i < 9)
				System.out.print(i+"   ");
			else if(i == 9)
				System.out.print(i+"  ");
			else if(i < 100)
				System.out.print(" "+i+" ");
			else if(i < 1000)
				System.out.print(i);
		}
		
		System.out.println("");
		
		for(int l = 0; l < g[0].length; l++){
			
			// Séparateur de ligne
			System.out.print("  +");
			for(int j = 0; j < g.length; j++){System.out.print("---+");}
			
			System.out.print("\n"+ch+" | ");	//	Debut de la ligne
			ch++;
			
			for(int c = 0; c < g.length; c++){
				
				System.out.print(g[c][l]+" | ");	// String + separateur de colonne
				
			}
			
			System.out.println("");
		}
		
		System.out.print("  +");
		for(int j = 0; j < g.length; j++){System.out.print("---+");}
		
		System.out.println();
		
	}

	/**
	 * Affiche une phrase relative la mort de l'animal
	 * 
	 * @param s La chaine à afficher
	 */
	@Override
	public void afficherMort(String s) {
		
		System.out.println(s);
		
	}

	
	
	
}
