package Observateurs;

import vue.Vue;
import modele.Loup;
import modele.Mort;

public class ObsLoup implements Observateur{

	Loup loupObservable;
	Vue vue;
	
	public ObsLoup(Loup l){
		
		this.loupObservable = l;
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

