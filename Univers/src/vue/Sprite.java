package vue;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;


@SuppressWarnings({ "unused", "serial" })
public class Sprite extends JPanel{


	public final static int LARGEUR_SPRITE = 32;
	public final static int HAUTEUR_SPRITE = 32;
	
	private int x,y;
	private BufferedImage image;
	
	public Sprite(BufferedImage img,int nx,int ny){
		
		if(img == null)
				throw new RuntimeException("Image non valide");
		
		image = img;
		x = nx;
		y = ny;
		
		setBounds(x, y, image.getWidth(), image.getHeight());
		setDoubleBuffered(true);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		// Le code ci-dessous affiche l'image dans le JPanel.
        if (image != null) {
        	super.paintComponent(g);
        	//System.out.println("VOILA "+x+" "+y+"  "+image.getWidth()+" "+image.getHeight());
            g.drawImage(image, x, y, this.image.getWidth(), this.image.getHeight(), this);	
        }
        
	}
	
	
}
