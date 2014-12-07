package observateurs;

import univers.Case;
import univers.Univers;
import vue.Vue_console;



public class ObsUnivers extends Vue_console implements Observateur{

	Univers universObservable;
	
	public ObsUnivers(Univers u){
		
		this.universObservable = u;
	}

	@Override
	public void notifier() {
		
		Case [][] plateau = universObservable.getPlateau();
		
		String [][] grille = new String[plateau.length][plateau[0].length];
		
		for(int c = 0;c < plateau.length; c++)
		{
			for(int l = 0; l < plateau[0].length; l++)
			{
				if(plateau[c][l].getMatierDansCase() == null){
					
					grille[c][l] = " ";
				}
				else{
					
					grille[c][l] = plateau[c][l].getMatierDansCase().symbole();
				}
				
			}
			
		}
		
		this.afficherGrille(grille);
		System.out.println(universObservable.toString()+"\n");
		
	}
	
	
}
