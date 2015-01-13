package vue;



import controleur.CommandeGraphX;

public class VueJeu implements Vue{

	
	public VueJeu(){}

	
	@Override
	public void afficherGrille(String[][] g) {

		CommandeGraphX.getFrame().setMAJ(true);
		CommandeGraphX.recupererGrille(g);

	}

	@Override
	public void afficherConsole(String s) {
		// Ne jamais exécuter cette fonction
		throw new UnsupportedOperationException("Operation invalide !");
		
	}

	
	
}
