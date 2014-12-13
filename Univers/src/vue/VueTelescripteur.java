package vue;

public class VueTelescripteur implements Vue{

	protected static Fenetre telescripteur;
	
	/**
	 * 
	 * 
	 * @param telescript
	 */
	public static void setTelescripteur(Fenetre telescript){
	 	
		if(telescript != null && telescripteur == null)
			telescripteur = telescript;
	}
	
	
	@Override
	public void afficherGrille(String[][] g) {
		// Ne jamais exécuter cette fonction
		throw new UnsupportedOperationException("Operation invalide !");
	}

	@Override
	public void afficherConsole(String s) {
		// TODO Affiche les informations relatives à un evennement
		
	}

}
