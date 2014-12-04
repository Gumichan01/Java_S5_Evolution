package modele;

import java.awt.Rectangle;

/**
 * 
 * @deprecated Cette classe gère l'herbe, il est possible que cette classe ne soit pas utile
 * 
 * @version 1.0
 *
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 */
public class Herbe extends Entite{

	/**
	 * Créer l'herbe
	 * @param x position x
	 * @param y position y
	 * @param w largeur
	 * @param h longueur
	 * @throws Exception voir {@link Entite}
	 */
	public Herbe(int x, int y, int w, int h) throws Exception{
	
	super(new Rectangle(x, y, w, h), " ");
	}
}
