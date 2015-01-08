package controleur;


import java.awt.Adjustable;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;



import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;

import exceptionUnivers.DimensionNonValideException;
import exceptionUnivers.ValeurNegativeException;

import univers.Univers;
import vue.Fenetre;
import vue.Sprite;
import vue.Telescripteur;
import vue.VueJeu;
import vue.VueTelescripteur;



public class CommandeGraphX {

	public final static String GRAPHX_QUITTER = "Q";
	public final static String GRAPHX_PAUSE = "P";
	
	private static String[][] grille;
	private static ArrayList<String> text_list;
	
	ControleurUnivers ctrl;
	private static Fenetre fenetre;
	private static Telescripteur telescripteur;
	VueJeu vueJeu;
	Thread threadJeu;
	
	JButton jouerBouton;
	
	
	// Les champs textes
	final JTextField texte_nbM = new JTextField(10);
	final JTextField texte_nbL = new JTextField(10);
	
	final JTextField texte_l = new JTextField(10);
	final JTextField texte_h = new JTextField(10);


	/**
	 *  Cronstruit le contrôleur de l'univers et la fenetre
	 */
	public CommandeGraphX(){

		ctrl = new ControleurUnivers();
		fenetre = new Fenetre(200, 200,800, 600);
		fenetre.setTitle("Evolution - Projet Java");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		text_list = new ArrayList<String>();

		fenetre.setVisible(true);
	}


	/**
	 *  Créer une version "bufferisée" de l'image à partir du fichier
	 *  dont le chemin est mis en paramètre
	 *  
	 * @param cheminFichier Le chemin (relatif ou absolu) du fichier
	 * @return l'image qui a été mise en mémoire tampon
	 * 
	 */

	
	
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
				
		// On ajoute les panels et les champs de texte
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
	 *  Jouer la partie
	 */
	public void jouerPartie(){
		
		chargement();
		
		//vueJeu = new VueJeu(fenetre);
		
		threadJeu = ctrl.new Jeu();
		threadJeu.start();


		
	}

	/**
	 * 
	 * Charge toutes les données nécessaires à l'éxecution de la partie
	 * 
	 */
	private void chargement(){


		telescripteur = new Telescripteur(600, 100, 400, 200);
		
		JMenuBar barre = new JMenuBar();
		JButton boutonQuit = new JButton("Quitter");
		JButton boutonPause = new JButton("Pause/Continuer");
		
		
		boutonQuit.setActionCommand(GRAPHX_QUITTER);
		boutonPause.setActionCommand("C");
		
		/* Ajouts des ActionListeners */
		boutonQuit.addActionListener(new ActionListener() {
			// L'écouteur pour quitter le programme 
			@Override
			public void actionPerformed(ActionEvent arg0) {

				CommandeGraphX.fenetre.dispose();
				telescripteur.dispose();
				System.exit(0);
			}
		});
		
		boutonPause.addActionListener(ctrl);	// ctrl pour la pause
		
		// On ajoute les boutons dans la barre
		barre.add(boutonQuit);
		barre.add(boutonPause);
		telescripteur.setJMenuBar(barre);

		// On met en place la fenêtre de telescripteur
		telescripteur.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telescripteur.setTitle("Evolution - Telescripteur");
		telescripteur.setResizable(true);
		telescripteur.setLayout(new FlowLayout());
		
		telescripteur.lancerTimer();
		
		telescripteur.revalidate();
		telescripteur.repaint();
		
		telescripteur.setVisible(true);

		// On purge la fenêtre principale
		fenetre.getContentPane().removeAll();

		
		// Creer les sprites
		
		fenetre.lancerTimer();
		
		fenetre.revalidate();
		fenetre.repaint();

	}
	
	
	public static synchronized void recupererGrille(String [][] g){
		
		grille = g;
	}
	
	public static synchronized String [][] getGrille(){
		
		return grille;
	}
	
	
	public static synchronized void recupererTexte(String t){
		
		if(t != null)
			text_list.add(t);
	}
	
	public static synchronized void clearList(){
		
		text_list.clear();
	}

	public static synchronized ArrayList<String> getListOfText(){
		
		return text_list;
	}
	
	
	
 	/**
	 * 
	 * Ajoute un écouteur du bouton mis en paramètre
	 * 
	 * @param bouton Le Jbutton à écouter
	 * @exception NullPointerException Si le paramètre est null (ne devrait pas arriver)
	 * 
	 */
 	private void ajoutEcouteurBoutonJouer(JButton bouton){
		
		bouton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				int nbM = 0,nbL = 0, largeur = 0, hauteur = 0;

				try{
					// On récupère les valeur saisies dans les champs textes
					nbM = Integer.parseInt(texte_nbM.getText());
					nbL = Integer.parseInt(texte_nbL.getText());
					largeur = Integer.parseInt(texte_l.getText());
					hauteur = Integer.parseInt(texte_h.getText());
					
					System.out.println(nbM+" "+nbL+" "+largeur+" "+hauteur);
					
					CommandeGraphX.this.ctrl.construireUnivers(new Univers(largeur, hauteur, nbM, nbL));
					
					
				}catch(NumberFormatException | ValeurNegativeException | DimensionNonValideException e1){
					// Problème avec le parseInt, ou bien avec le constructeur de Univers
					erreurChampsInvalide();
					e1.printStackTrace();

				}finally{
				
					// Si les valeurs définies sont correctes, OK
					if( nbM >= 0 && nbL >= 0 && 
							largeur >= Univers.UNIVERS_TAILLE_MIN && hauteur >= Univers.UNIVERS_TAILLE_MIN )
					{
						CommandeGraphX.fenetre.setSize(largeur*(Sprite.LARGEUR_SPRITE), hauteur*(Sprite.HAUTEUR_SPRITE));
						CommandeGraphX.fenetre.setLignes(hauteur);
						CommandeGraphX.fenetre.setColonnes(largeur);
						CommandeGraphX.this.jouerPartie();
					}	
					
				}
				
			}
		});
		
	}

	
	public static Fenetre getFrame(){
		
		return fenetre;
	}
	
	public static Telescripteur getTelescripteur(){
		
		return telescripteur;
	}
	
	/**
	 * 
	 * Créer une fenêtre d'erreur pout indiquer
	 * que la ou les saisie(s) est/sont invalide(s)
	 * 
	 */
	private void erreurChampsInvalide(){
		
		JFrame frame = new JFrame();
		JLabel label1 = new JLabel("Il y a des champs invalides !");
		JLabel label2 = new JLabel("Consignes : ");
		JLabel label3 = new JLabel("Valeurs > 0");
		JLabel label4 = new JLabel("Nombres d'animaux max : "+Integer.MAX_VALUE);
		JLabel label5 = new JLabel("Dimension univers : "
				+Univers.UNIVERS_TAILLE_MIN+"x"+Univers.UNIVERS_TAILLE_MIN+" -> "
					+Univers.UNIVERS_TAILLE_MAX+"x"+Univers.UNIVERS_TAILLE_MAX+"");


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














