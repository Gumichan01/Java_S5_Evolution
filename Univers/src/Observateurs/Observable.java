package Observateurs;
/**
 * Gère les élément observable par les {@link Obervateur}, 
 * La classe Observable est utilisé dans le patron de conception Observateur
 * 
 * @version 1.0
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 *	TODO Luxon & Kahina - faire les sous-classes de Observable (pas tout de suite, on verra ça après)
 */
public interface Observable {
	
	public void ajoutObservateur(Observateur o );
	 public void notifierObs();
}
