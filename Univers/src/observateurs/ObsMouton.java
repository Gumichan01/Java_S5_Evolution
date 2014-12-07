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
		
		if(moutonObservable.getAge() == 0){
			// Il vient de naître
			this.afficherConsole("["+ligne+colonne+"] :Un jeune mouton vient de naître");
		}
		else{
		
			// Le mouton est mort, comment ?
			if(moutonObservable.getEtatMort()==Mort.Faim){
				
				this.afficherConsole("["+ligne+colonne+"] : Un mouton meurt de faim");
			}
			else if(moutonObservable.getEtatMort()==Mort.Mange){
				
				this.afficherConsole("["+ligne+colonne+"] : Un mouton a servi de repas");
			}
			else{
				
				this.afficherConsole("["+ligne+colonne+"] : Un mouton meurt de vieillesse");
			}
			
		}
	}

}
