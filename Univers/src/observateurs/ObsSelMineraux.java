package observateurs;

import vue.Vue;
import vue.VueTelescripteur;
import modele.SelMineral;

public class ObsSelMineraux extends VueTelescripteur implements Observateur{

	SelMineral selMineralObservable;
	Vue vue;
	
	public ObsSelMineraux(SelMineral s){
		
		selMineralObservable = s;
	}
	
	
	@Override
	public void notifier() {

		char ligne = (char) ('A' + selMineralObservable.getRect().y);
		int colonne = selMineralObservable.getRect().x +1;
		
		this.afficherConsole("["+ligne+colonne+"] : De l'herbe repousse");
		
	}

}
