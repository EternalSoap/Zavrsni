package Grafika;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Elementi.Linija;
import Elementi.Otpornik;


public class meniView extends JPanel implements ActionListener {
	JComboBox primjeri;
	public meniView(){
		setMaximumSize(new Dimension(300,768));
		setBorder(new EmptyBorder(25,25,450,25));
		setLayout(new GridLayout(10,1,10,10));
		Dimension maxSize = new Dimension(100,30);
		
		String [] listaPrimjera = {"Ohmov zakon - Primjer 1", "Ohmov zakon - Primjer 2", "Ohmov zakon - Primjer 3", "Prvi Kirchhoffov zakon - Primjer 1","Prvi Kirchhoffov zakon - Primjer 2","Prvi Kirchhoffov zakon - Primjer 3","Drugi Kirchhoffov zakon - Primjer 1","Drugi Kirchhoffov zakon - Primjer 2","Drugi Kirchhoffov zakon - Primjer 3"};  
		primjeri = new JComboBox(listaPrimjera);
		primjeri.setSelectedIndex(0);
		primjeri.addActionListener(this);
		JButton amp = new JButton("Ampermetar");
		JButton vol = new JButton("Voltmetar");
		JButton vod = new JButton("Vod");
		JButton save = new JButton("Spremi");
		JButton load = new JButton("Ucitaj");
		
		
		
		JButton temp = new JButton("Test");
		temp.setMaximumSize(maxSize);
		temp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addRes();
			}
		});
		add(temp);
		
		load.setMaximumSize(maxSize);
		save.setMaximumSize(maxSize);
		amp.setMaximumSize(maxSize);
		vol.setMaximumSize(maxSize);
		vod.setMaximumSize(maxSize);
		primjeri.setMaximumSize(new Dimension(200,30));
		
		load.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
					appView.loadList(primjeri.getSelectedIndex());
					repaint();
				
			}	
			
		});
		
		save.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
					appView.saveList(primjeri.getSelectedIndex());
				
			}	
			
		});
		
		amp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				
			}
		});
		vol.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				
			}
		});
		vod.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addLine();
			}
		});
		
		add(amp);
		add(vol);
		add(vod);
		add(save);
		add(load);
		add(primjeri);
	}
	


	protected void addRes() {
		
		appView.dodajEl(new Otpornik(new unos("0").returnOtpor()));
		
	}


	protected void addLine() {
		appView.dodajEl(new Linija(new Point(0,0), new Point(0,0)));
		
	}


	public Dimension getPreferredSize(){return new Dimension(300,768);}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(primjeri.getSelectedIndex());
	}
	
}

