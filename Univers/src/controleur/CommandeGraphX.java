package controleur;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import exceptionUnivers.DimensionNonValideException;
import exceptionUnivers.ValeurNegativeException;

import univers.Univers;
import vue.Fenetre;

public class CommandeGraphX {

	ControleurUnivers ctrl;
	Fenetre fenetre;

	
	JButton jouerBouton;
	
	// Les champs textes
	final JTextField texte_nbM = new JTextField(10);
	final JTextField texte_nbL = new JTextField(10);
	
	final JTextField texte_l = new JTextField(10);
	final JTextField texte_h = new JTextField(10);
	
	
	/**
	 *  Cronstruit le controleur de l'univers et la fenetre
	 */
	public CommandeGraphX(){

		ctrl = new ControleurUnivers();
		fenetre = new Fenetre();
		
	}
	
	
	/**
	 * Affiche le menu principal du jeu
	 */
	public void afficherMenu(){
		
		JPanel moutonPan = new JPanel();
		JPanel loupPan = new JPanel();
		JPanel largeurPan = new JPanel();
		JPanel hauteurPan = new JPanel();
		
		jouerBouton = new JButton("Jouer");
	
		
		moutonPan.add(new JLabel("Nombre de moutons"));
		loupPan.add(new JLabel("Nombre de loups"));
		
		largeurPan.add(new JLabel("Largeur du plateau"));
		hauteurPan.add(new JLabel("Hauteur du plateau"));
		
		// Mettre le panel du mouton et le texte
		moutonPan.setBounds(0, 120, 150, 50);
		loupPan.setBounds(0,200, 130, 50);
		
		texte_nbM.setBounds(200, 120, 150, 20);
		texte_nbL.setBounds(200, 200, 150, 20);
		
		//Mettre la largeur et la hauteur de l'univers
		largeurPan.setBounds(0,300,150,50);
		hauteurPan.setBounds(0,400,150,50);
		
		texte_l.setBounds(200,300,150,20);
		texte_h.setBounds(200,400,150,20);
		
		jouerBouton.setBounds((fenetre.getWidth()-200)/2, fenetre.getHeight()-100, 200, 32);
		ajoutEcouteurBoutonJouer(jouerBouton);
				
		// On ajoute les panel et les champs de texte
		fenetre.ajoutComposant(moutonPan);
		fenetre.ajoutComposant(texte_nbM);
		
		fenetre.ajoutComposant(loupPan);
		fenetre.ajoutComposant(texte_nbL);
		
		fenetre.ajoutComposant(largeurPan);
		fenetre.ajoutComposant(hauteurPan);
		fenetre.ajoutComposant(texte_l);
		fenetre.ajoutComposant(texte_h);
		
		fenetre.ajoutComposant(jouerBouton);
		
		fenetre.revalidate();
		fenetre.repaint();	
	}
	
	
	/**
	 *  Joue la partie
	 */
	public void jouer(){
		
		ctrl.evoluerUnivers();
	}
	
	
	/**
	 * Ajoute met un écouteur du bouton mis en paramètre
	 * 
	 * @param bouton Le Jbutton à écouter
	 * @exception NullPointerException Si le paramètre est null
	 */
	private void ajoutEcouteurBoutonJouer(JButton bouton){
		
		bouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int nbM = 0,nbL = 0, largeur = 0, hauteur = 0;


				try{
					
					nbM = Integer.parseInt(texte_nbM.getText());
					nbL = Integer.parseInt(texte_nbL.getText());
					largeur = Integer.parseInt(texte_l.getText());
					hauteur = Integer.parseInt(texte_h.getText());
					
					System.out.println(nbM+" "+nbL+" "+largeur+" "+hauteur);
					
					CommandeGraphX.this.ctrl.construireUnivers(new Univers(largeur, hauteur, nbM, nbL));
					
					CommandeGraphX.this.jouer();
					
				}catch(NumberFormatException e1){
					
					erreurChampsInvalide();
					e1.printStackTrace();

				}catch(ValeurNegativeException e2){
					
					erreurChampsInvalide();
					e2.printStackTrace();
					
				}catch(DimensionNonValideException e3){
					
					erreurChampsInvalide();
					e3.printStackTrace();
				}
				
			}
		});
		
	}
	
	/**
	 * Créer une fenêtre d'erreur
	 */
	private void erreurChampsInvalide(){
		
		JFrame frame = new JFrame();
		JLabel label1 = new JLabel("Il y a des champs invalides !");
		JLabel label2 = new JLabel("Consignes : ");
		JLabel label3 = new JLabel("Valeurs > 0");
		JLabel label4 = new JLabel("Nombres d'animaux max : "+Integer.MAX_VALUE);
		JLabel label5 = new JLabel("Dimension max univers : 50x50 ");
		
		
		label1.setBounds(0,0,300,32);
		label2.setBounds(0, 32, 300, 32);
		label3.setBounds(0, 64, 300, 32);
		label4.setBounds(0, 96, 300, 32);
		label5.setBounds(0, 128, 300, 32);
		
		frame.setTitle("ERREUR SAISIE");
		frame.setBounds(300, 400, 300, 192);
		
		frame.getContentPane().setLayout(new GridLayout(5, 0));
		frame.getContentPane().add(label1);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(label3);
		frame.getContentPane().add(label4);
		frame.getContentPane().add(label5);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);

	}
	
}














