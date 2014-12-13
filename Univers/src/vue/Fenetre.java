package vue;

import java.awt.Component;
import java.awt.Container;



import javax.swing.JFrame;


public class Fenetre extends JFrame{
	
	public Fenetre(int x,int y, int w, int h){
		
		this.setBounds(x,y,w,h);				// On definit la taille
		
		this.getContentPane().setLayout(null);
		
		this.setResizable(false);
		
	}
	
	
	/**
	 *  Ajoute un composant dans la fenetre
	 * @param comp le composant
	 */
	public void ajoutComposant(Component comp){
		
		this.getContentPane().add(comp);
	}
	
	
}
