package observateurs;

import univers.Case;
import univers.Nourriture;
import univers.Univers;
import vue.VueJeu;



public class ObsUnivers extends VueJeu implements Observateur{

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
					
					if(plateau[c][l].getNourriture() == Nourriture.Rien)
						grille[c][l] = "V";
					else
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
