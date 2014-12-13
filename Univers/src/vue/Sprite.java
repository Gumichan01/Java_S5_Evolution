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
	
	public Sprite(BufferedImage img){
		
		if(img == null)
				throw new RuntimeException("Image non valide");
		
		image = img;
		
		
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		
		// Le code ci-dessous affiche l'image dans le JPanel.
        if (image != null) {
            g.drawImage(image, 0, 0, this.image.getWidth(), this.image.getHeight(), this);	
            // TODO |^| changer les |0,0| et mettre |x, y| � la place
        }
	}
	
	
}
