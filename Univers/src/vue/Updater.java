package vue;

import javax.swing.SwingUtilities;

import controleur.CommandeGraphX;



public class Updater extends Thread {

	public final int temps = 100;
	
	public Updater(){
		
		super("updater");
	}
	
	public void run(){
		
		try{
			
				while(Thread.currentThread() == this){
					
					Thread.sleep(10);
					
					SwingUtilities.invokeLater(new Runnable() {
						@Override public void run() {
							
							System.out.println("UPDATE");
							CommandeGraphX.getFrame().revalidate();
							CommandeGraphX.getFrame().repaint();
						}
					});
				}
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}
