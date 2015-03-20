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

import Elementi.Ampermetar;
import Elementi.Desno_gore;
import Elementi.Dolje_desno;
import Elementi.Izvor;
import Elementi.Lijevo_dolje;
import Elementi.Lijevo_gore;
import Elementi.Linija;
import Elementi.Otpornik;
import Elementi.Tdesno;
import Elementi.Tdolje;
import Elementi.Tgore;
import Elementi.Tlijevo;
import Elementi.Voltmetar;


public class meniView extends JPanel implements ActionListener {
	JComboBox primjeri;
	public meniView(){
		
		this.setMaximumSize(getPreferredSize());
		setBorder(new EmptyBorder(25,25,25,25));
		setLayout(new GridLayout(20,1,40,5));
		Dimension maxSize = new Dimension(100,40);
		
		String [] listaPrimjera = {"Ohmov zakon - Primjer 1", "Ohmov zakon - Primjer 2", "Ohmov zakon - Primjer 3", "Prvi Kirchhoffov zakon - Primjer 1","Prvi Kirchhoffov zakon - Primjer 2","Prvi Kirchhoffov zakon - Primjer 3","Drugi Kirchhoffov zakon - Primjer 1","Drugi Kirchhoffov zakon - Primjer 2","Drugi Kirchhoffov zakon - Primjer 3"};  
		primjeri = new JComboBox<String>(listaPrimjera);
		primjeri.setSelectedIndex(0);
		primjeri.addActionListener(this);
		JButton izv = new JButton("Izvor");
		JButton amp = new JButton("Ampermetar");
		JButton vol = new JButton("Voltmetar");
		JButton vod = new JButton("Vod");
		JButton save = new JButton("Spremi");
		JButton load = new JButton("Ucitaj");
		JButton desno_gore = new JButton("Vod desno-gore");
		JButton dolje_desno = new JButton("Vod desno-dolje");
		JButton lijevo_dolje = new JButton("Vod lijevo-dolje");
		JButton lijevo_gore = new JButton("Vod lijevo-gore");
		JButton Tdesno = new JButton("Vod T-desno");
		JButton Tlijevo = new JButton("Vod T-lijevo");
		JButton Tgore = new JButton("Vod T-gore");
		JButton Tdolje = new JButton("Vod T-dolje");
		
		
		
		
		JButton temp = new JButton("Test");
		temp.setMaximumSize(maxSize);
		temp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addRes();
			}
		});
		add(temp);
		
		
		izv.setMaximumSize(maxSize);
		load.setMaximumSize(maxSize);
		save.setMaximumSize(maxSize);
		amp.setMaximumSize(maxSize);
		vol.setMaximumSize(maxSize);
		vod.setMaximumSize(maxSize);
		desno_gore.setMaximumSize(maxSize);
		dolje_desno.setMaximumSize(maxSize);
		lijevo_dolje.setMaximumSize(maxSize);
		lijevo_gore.setMaximumSize(maxSize);
		Tdesno.setMaximumSize(maxSize);
		Tlijevo.setMaximumSize(maxSize);
		Tgore.setMaximumSize(maxSize);
		Tdolje.setMaximumSize(maxSize);
		primjeri.setMaximumSize(maxSize);
		
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
				addAmp();
			}
		});
		vol.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addVolt();
			}
		});
		vod.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addLine();
			}
		});
		
		izv.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addIzvor();
				
			}
			
		});
		
		desno_gore.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addDesno_gore();
			}
		});
		
		dolje_desno.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addDolje_desno();
				
			}
			
		});
		
		lijevo_dolje.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addLijevo_dolje();
			}
		});
		
		lijevo_gore.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addLijevo_gore();
			}
			
		});
		
		Tdesno.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addTdesno();
				
			}
		});
		
		Tlijevo.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addTlijevo();
				
			}
		});
		
		Tgore.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addTgore();
				
			}
		});
		
		Tdolje.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addTdolje();
				
			}
		});
		add(primjeri);
		add(save);
		add(load);
		add(izv);
		add(vod);
		add(vol);
		add(amp);
		add(desno_gore);
		add(dolje_desno);
		add(lijevo_dolje);
		add(lijevo_gore);
		add(Tgore);
		add(Tdolje);
		add(Tlijevo);
		add(Tdesno);
		
		
		
	}
	


	protected void addTdolje() {
		appView.dodajEl(new Tdolje());
		
	}



	protected void addTgore() {
		appView.dodajEl(new Tgore());
		
	}



	protected void addTlijevo() {
		appView.dodajEl(new Tlijevo());
		
	}



	protected void addTdesno() {
		appView.dodajEl(new Tdesno());
		
	}



	protected void addLijevo_gore() {
		appView.dodajEl(new Lijevo_gore());
		
	}



	protected void addLijevo_dolje() {
		appView.dodajEl(new Lijevo_dolje());
		
	}



	protected void addDolje_desno() {
		appView.dodajEl(new Dolje_desno());
		
	}



	protected void addDesno_gore() {
		appView.dodajEl(new Desno_gore());
		
	}



	protected void addVolt() {
		appView.dodajEl(new Voltmetar());
		
	}



	protected void addAmp() {
		appView.dodajEl(new Ampermetar());
		
	}



	protected void addIzvor() {
		appView.dodajEl(new Izvor(new unos("0","napon").returnVoltage()));
		
	}



	protected void addRes() {
		
		appView.dodajEl(new Otpornik(new unos("0","otpor").returnOtpor()));
		
	}


	protected void addLine() {
		appView.dodajEl(new Linija(new Point(0,0), new Point(0,0)));
		
	}


	public Dimension getPreferredSize(){return new Dimension(300,800);}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		appView.changeLayout();
		appView.loadList(primjeri.getSelectedIndex());
		repaint();
		
	}
	
}

