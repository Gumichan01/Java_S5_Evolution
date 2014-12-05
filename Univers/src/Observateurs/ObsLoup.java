package Observateurs;


import vue.Vue_console;
import modele.Loup;
import modele.Mort;

public class ObsLoup extends Vue_console implements Observateur{

	Loup loupObservable;
	
	public ObsLoup(Loup l){
		
		this.loupObservable = l;
	}
	
	@Override
	public void notifier() {
		
		char ligne = (char) ('A' + loupObservable.getRect().y);
		int colonne = loupObservable.getRect().x +1;
		
		if(loupObservable.getEtatMort()==Mort.Faim){
			
			this.afficherMort("["+ligne+colonne+"] : Un loup meurt de faim");
		}
		else if(loupObservable.getEtatMort()==Mort.Mange){
			
			this.afficherMort("["+ligne+colonne+"] : Un loup a servi de repas");
		}
		else{
			
			this.afficherMort("["+ligne+colonne+"] : Un loup meurt de vieillesse");
		}
		
	}

}

