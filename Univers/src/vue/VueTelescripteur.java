package vue;


import controleur.CommandeGraphX;

public class VueTelescripteur implements Vue{
	
	
	@Override
	public void afficherGrille(String[][] g) {
		// Ne jamais exécuter cette fonction
		throw new UnsupportedOperationException("Operation invalide !");
	}

	@Override
	public void afficherConsole(String s) {
		
		CommandeGraphX.getTelescripteur().majTelescripteur(true);
		CommandeGraphX.recupererTexte(s);

	}

}
