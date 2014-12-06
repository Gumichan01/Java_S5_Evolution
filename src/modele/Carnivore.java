package modele;

import java.awt.Rectangle;

import univers.Case;

public interface Carnivore {
	
	public Rectangle proieAProximiteDans(Case[][] env);
	public boolean manger(Animal proie);

}
