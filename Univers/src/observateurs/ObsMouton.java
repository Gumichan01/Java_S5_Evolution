package observateurs;


import vue.VueTelescripteur;
import vue.Vue_console;
import modele.Mort;
import modele.Mouton;

public class ObsMouton extends VueTelescripteur implements Observateur{

	
	Mouton moutonObservable;
	
	public ObsMouton(Mouton m){
		
		this.moutonObservable = m;
	}
	
	
	@Override
	public void notifier() {
		
		char ligne = (char) ('A' + moutonObservable.getRect().y);
		int colonne = moutonObservable.getRect().x +1;
		
		if(moutonObservable.getAge() == 0){

			this.afficherConsole("["+ligne+colonne+"] :Un jeune mouton vient de naître");
		}
		else{

			if(moutonObservable.getEtatMort() == Mort.Faim){

				this.afficherConsole("["+ligne+colonne+"] : Un mouton meurt de faim");
			}
			else if(moutonObservable.getEtatMort() == Mort.Mange){

				this.afficherConsole("["+ligne+colonne+"] : Un mouton a servi de repas à un loup");
			}
			else{

				this.afficherConsole("["+ligne+colonne+"] : Un mouton meurt de vieillesse");
			}
			
		}
	}

}
