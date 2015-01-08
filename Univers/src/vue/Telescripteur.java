package vue;


import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.Timer;

import controleur.CommandeGraphX;

public class Telescripteur extends Fenetre{

	private boolean majT;
	JLayeredPane lpane;
	JPanel panel;
	JScrollBar scrollBar;
	int i = 1;

	public Telescripteur(int x, int y, int w, int h) {

		super(x, y, w, h);

		majT = false;
		
		//lpane = new JLayeredPane();
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
		
		//lpane.add(panel);
		
		//scrollBar = new JScrollBar();
		//scrollBar.setPreferredSize(new Dimension(24, this.getHeight() -64));
		//scrollPane.setViewportView(panel);
		//scrollPane.setWheelScrollingEnabled(true);
		//scrollBar.setBlockIncrement(8);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		//panel.add(scrollBar);
		//this.add(new JScrollPane(panel));
		this.add(panel);
	}

	public void majTelescripteur(boolean update){
		
		majT = update;
	}


	public void lancerTimer(){
		
		//super.lancerTimer();
		timer = new Timer(500, this);
		timer.start();
	}


	@Override
	public void actionPerformed(ActionEvent e){

		if(majT){

			//System.out.println("Telescripteur : Mise à jour en cours");

			i++;

			//this.panel.removeAll();
			//this.panel.setLayout(new GridLayout(10*i,1));
			
			ArrayList<String> textes = CommandeGraphX.getListOfText();

			for(int i = 0; i < textes.size(); i++){

				this.panel.add(new JLabel(textes.get(i)));
			}

			//this.scrollBar.revalidate();
			this.revalidate();
			this.repaint();

			majT = false;
		}

	}


}
