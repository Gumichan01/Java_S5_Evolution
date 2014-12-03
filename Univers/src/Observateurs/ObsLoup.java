package Observateurs;

import Modele.Mort;
import Modele.Loup;
import Vue.Vue;

public class ObsLoup implements Observateur{

	Loup loupObservable;
	Vue vue;
	
	public ObsLoup(Loup l, Vue v){
		
		this.loupObservable = l;
		this.vue = v;
		
	}
	
	@Override
	public void notifier() {
		
		char ligne = (char) ('A' + loupObservable.getRect().y);
		int colonne = loupObservable.getRect().x +1;
		
		if(loupObservable.getEtatMort()==Mort.Faim){
			
			vue.afficherMort("["+ligne+colonne+"] : Un loup meurt de faim");
		}
		else if(loupObservable.getEtatMort()==Mort.Mange){
			
			vue.afficherMort("["+ligne+colonne+"] : Un loup a servi de repas");
		}
		else{
			
			vue.afficherMort("["+ligne+colonne+"] : Un loup meurt de vieillesse");
		}
		
	}

}

