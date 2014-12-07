package observateurs;

/**
 * Gère les éléments observables par les {@link Obervateur}, 
 * La classe Observable est utilisé dans le patron de conception Observateur
 * 
 * @version 1.0
 * 
 * @author Luxon JEAN-PIERRE & Kahina RAHANI
 *
 */
public interface Observable {
	
	public void ajoutObservateur(Observateur o );
	 public void notifierObs();
}
