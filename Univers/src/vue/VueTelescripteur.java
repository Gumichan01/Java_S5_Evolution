package vue;

import javax.swing.JLabel;

import controleur.CommandeGraphX;

public class VueTelescripteur implements Vue{
	
	
	@Override
	public void afficherGrille(String[][] g) {
		// Ne jamais exécuter cette fonction
		throw new UnsupportedOperationException("Operation invalide !");
	}

	@Override
	public void afficherConsole(String s) {
		// TODO Affiche les informations relatives à un evennement
		
		CommandeGraphX.getTelescripteur().majTelescripteur(true);
		CommandeGraphX.recupererTexte(s);
		
	}

}
