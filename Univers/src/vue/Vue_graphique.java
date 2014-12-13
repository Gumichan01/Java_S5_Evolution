package vue;

public interface Vue_graphique extends Vue{



	/**
	 * Affecte une nouvelle fenêtre à la vue graphique
	 * 
	 * Si la fenêtre mise en paramètre est null ou que la classe a déjà une instance de {@link Fenetre}
	 * alors il ne se passe rien
	 * 
	 * @param f La fenêtre
	 */
	public void setFenetre(Fenetre f);
	
	
}
