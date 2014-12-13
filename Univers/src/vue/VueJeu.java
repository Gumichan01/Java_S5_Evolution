package vue;

public class VueJeu implements Vue{

	protected static Fenetre fenetreJeu;
	
	
	public VueJeu(){}
	
	/**
	 * On construit la Vue du jeu
	 * 
	 * @param fenetreJeu
	 */
	public VueJeu(Fenetre fenetreJeu){
		
		setFenetre(fenetreJeu);
	}
	
	/**
	 * Affecte une nouvelle fenêtre à la vue graphique
	 * 
	 * Si la fenêtre mise en paramètre est null ou que la classe a déjà une instance de {@link Fenetre}
	 * alors il ne se passe rien
	 * 
	 * @param f La fenêtre
	 */
	private void setFenetre(Fenetre f){
		
		if(f != null && fenetreJeu == null)
			fenetreJeu = f;
	}
	
	
	@Override
	public void afficherGrille(String[][] g) {
		// TODO Afficher la grille sur la fenêtre
		
	}

	@Override
	public void afficherConsole(String s) {
		// Ne jamais exécuter cette fonction
		throw new UnsupportedOperationException("Operation invalide !");
		
	}

	
	
}
