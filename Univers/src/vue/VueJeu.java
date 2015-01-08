package vue;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controleur.CommandeGraphX;

public class VueJeu implements Vue{

	
	public VueJeu(){
		//imageLoup = chargerImage("image/AAA.jpeg");
	}

	
	@Override
	public void afficherGrille(String[][] g) {
		// TODO Afficher la grille sur la fenêtre

		CommandeGraphX.getFrame().setMAJ(true);
		CommandeGraphX.recupererGrille(g);

	}

	@Override
	public void afficherConsole(String s) {
		// Ne jamais exécuter cette fonction
		throw new UnsupportedOperationException("Operation invalide !");
		
	}

	
	
}
