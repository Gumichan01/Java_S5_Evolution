package Observateurs;

import Modele.Matiere;
import Modele.Univers;
import Vue.Vue;

public class ObsUnivers implements Observateur{

	Univers universObservable;
	Vue vue;
	
	public ObsUnivers(Univers u, Vue v){
		
		this.universObservable = u;
		this.vue = v;
		
	}

	@Override
	public void notifier() {
		// TODO Auto-generated method stub
		
		Matiere [][] plateau = universObservable.getPlateau();
		
		String [][] grille = new String[plateau.length][plateau[0].length];
		
		for(int c = 0;c < plateau.length; c++)
		{
			for(int l = 0; l < plateau[0].length; l++)
			{
				if(plateau[c][l] == null){
					
					grille[c][l] = " ";
				}
				else{
					
					grille[c][l] = plateau[c][l].symbole();
				}
				
			}
			
		}
		
		vue.afficherGrille(grille);
		
	}
	
	
}
