package vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;



import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import controleur.CommandeGraphX;


public class Fenetre extends JFrame implements ActionListener{
	
	Timer timer;
	boolean mettreAJourJeu;
	private int lignes = 8;
	private int colonnes = 8;
	
	public static BufferedImage imageLoup;
	private static BufferedImage imageMouton;
	private static BufferedImage imageHerbe;
	private static BufferedImage imageSel;
	
	public Fenetre(int x,int y, int w, int h){
		
		this.setBounds(x,y,w,h);				// On definit la taille
		
		this.getContentPane().setLayout(null);
		
		this.setResizable(false);
		
		mettreAJourJeu = true;
		
		imageLoup = chargerImage("image/wolf.png");
	}
	
	private BufferedImage chargerImage(String cheminFichier){
		
		BufferedImage buf = null;
		
		try{

			if(cheminFichier == null)	// Le chemin est invalide
				throw new NullPointerException("Fichier non renseigné");
			
			buf = ImageIO.read(new File(cheminFichier));	// On essaie de lire le fichier
			
		}catch(NullPointerException ne){
			
			// Le paramètre est null
			ne.printStackTrace();
			throw ne;
			
		}
		catch(IOException io){
			
			// On arrive pas à lire le fichier
			io.printStackTrace();
			throw new RuntimeException(io);
			
		}
	
		return buf;	// Il y a eu un problème si on est arrivé là
		
		
	}
	
	/**
	 *  Ajoute un composant dans la fenetre
	 * @param comp le composant
	 */
	public void ajoutComposant(Component comp){
		
		this.getContentPane().add(comp);
	}


	public void lancerTimer(){
		
		timer = new Timer(500, this);
		timer.start();
	}
	
	public void setMAJ(boolean maj){
		
		mettreAJourJeu = maj;
	}
	
	
	public void setLignes(int l){
		
		if(l > 0)
			lignes = l;
	}
	
	public void setColonnes(int c){
		
		if(c > 0)
			colonnes = c;
	}
	
	public int getLignes(){
		
		return lignes;
	}
	
	public int getColones(){
		
		return colonnes;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String [][] g = CommandeGraphX.getGrille();
		
		if(g == null)
			return;
			
		//System.out.println("ACTION");
		if(mettreAJourJeu){

			this.getContentPane().removeAll();
			this.getContentPane().setLayout(new GridLayout(lignes, colonnes));
			
			
			//System.out.println("nombre de composants : "+this.getContentPane().getComponentCount());
			//System.out.println("Dim fenetre" + this.getWidth()+" * "+this.getHeight());
			//System.out.println("Dim imageLoup "+imageLoup.getWidth()+" * "+imageLoup.getHeight());
			
			for(int x = 0;x < g.length; x++){
				
				for(int y = 0; y < g[x].length; y++){
					
					if(g[x][y].equalsIgnoreCase("L")){
						
						this.getContentPane().add(new Sprite(imageLoup, 0, 0));
						
					}
					else{
						JPanel p = new JPanel();
						p.setBounds(0,0,32,33);
						p.setVisible(true);
						p.setBackground(new Color(255, 0, 0));
						this.getContentPane().add(p);
					}
				}
			}

			this.revalidate();
			this.repaint();
			mettreAJourJeu = false;
		}
	}
	

}
