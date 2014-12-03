package Observateurs;

import Modele.Mort;
import Modele.Mouton;
import Vue.Vue;

public class ObsMouton implements Observateur{

	
	Mouton moutonObservable;
	Vue vue;
	
	public ObsMouton(Mouton m, Vue v){
		
		this.moutonObservable = m;
		this.vue = v;
		
	}
	
	
	@Override
	public void notifier() {
		
		char ligne = (char) ('A' + moutonObservable.getRect().y);
		int colonne = moutonObservable.getRect().x +1;
		
		if(moutonObservable.getEtatMort()==Mort.Faim){
			
			vue.afficherMort("["+ligne+colonne+"] : Un mouton meurt de faim");
		}
		else if(moutonObservable.getEtatMort()==Mort.Mange){
			
			vue.afficherMort("["+ligne+colonne+"] : Un mouton a servi de repas");
		}
		else{
			
			vue.afficherMort("["+ligne+colonne+"] : Un mouton meurt de vieillesse");
		}
		
	}

}
