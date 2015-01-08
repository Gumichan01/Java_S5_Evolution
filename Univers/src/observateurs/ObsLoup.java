package observateurs;


import vue.VueTelescripteur;
import vue.Vue_console;
import modele.Loup;
import modele.Mort;

public class ObsLoup extends VueTelescripteur implements Observateur{

	Loup loupObservable;
	
	public ObsLoup(Loup l){
		
		this.loupObservable = l;
	}
	
	@Override
	public void notifier() {
		
		char ligne = (char) ('A' + loupObservable.getRect().y);
		int colonne = loupObservable.getRect().x +1;
		
		
		if(loupObservable.getAge() == 0){
			// Il vient de naître
			this.afficherConsole("["+ligne+colonne+"] : Un louveteau vient de naître");
		}
		else if(loupObservable.getEtatMort()==Mort.Faim){
			
			this.afficherConsole("["+ligne+colonne+"] : Un loup meurt de faim");
		}
		else if(loupObservable.getEtatMort()==Mort.Mange){
			
			this.afficherConsole("["+ligne+colonne+"] : Un loup a servi de repas");
		}
		else{
			
			this.afficherConsole("["+ligne+colonne+"] : Un loup meurt de vieillesse");
		}
		
	}

}

