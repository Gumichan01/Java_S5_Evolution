package observateurs;


import vue.Vue_console;
import modele.Mort;
import modele.Mouton;

public class ObsMouton extends Vue_console implements Observateur{

	
	Mouton moutonObservable;
	
	public ObsMouton(Mouton m){
		
		this.moutonObservable = m;
		
	}
	
	
	@Override
	public void notifier() {
		
		char ligne = (char) ('A' + moutonObservable.getRect().y);
		int colonne = moutonObservable.getRect().x +1;
		
		if(moutonObservable.getEtatMort()==Mort.Faim){
			
			this.afficherMort("["+ligne+colonne+"] : Un mouton meurt de faim");
		}
		else if(moutonObservable.getEtatMort()==Mort.Mange){
			
			this.afficherMort("["+ligne+colonne+"] : Un mouton a servi de repas");
		}
		else{
			
			this.afficherMort("["+ligne+colonne+"] : Un mouton meurt de vieillesse");
		}
		
	}

}
