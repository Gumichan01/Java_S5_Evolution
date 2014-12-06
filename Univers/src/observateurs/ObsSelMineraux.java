package observateurs;

import vue.Vue;
import vue.Vue_console;
import modele.SelMineral;

public class ObsSelMineraux extends Vue_console implements Observateur{

	SelMineral selMineralObservable;
	Vue vue;
	
	public ObsSelMineraux(SelMineral s){
		
		selMineralObservable = s;
	}
	
	
	@Override
	public void notifier() {
		// TODO Auto-generated method stub
		char ligne = (char) ('A' + selMineralObservable.getRect().y);
		int colonne = selMineralObservable.getRect().x +1;
		
		this.afficherMort("["+ligne+colonne+"] : De l'herbe repousse");
		
	}

}