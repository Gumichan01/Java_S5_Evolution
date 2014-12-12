package vue;

public class Vue_graphique implements Vue{

	protected static Fenetre fenetre;
	protected static Fenetre telescripteur;
	
	
	@Override
	public void afficherGrille(String[][] g) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO : afficher la grille");
	}

	@Override
	public void afficherConsole(String s) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("TODO : afficher le 'string'");
	}


	/**
	 * Affecte une nouvelle fenêtre à la vue graphique
	 * 
	 * Si la fenêtre mise en paramètre est null ou que la classe a déjà une instance de {@link Fenetre}
	 * alors il ne se passe rien
	 * 
	 * @param f La fenêtre
	 */
	public static void setFenetre(Fenetre f){
		
		if(f != null && fenetre == null)
			fenetre = f;
	}


	public static void setTelescripteur(Fenetre telescript){
		
		if(telescript != null && telescripteur == null)
			telescripteur = telescript;
	}
	
	
}
