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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Elementi.Ampermetar;
import Elementi.Desno_gore;
import Elementi.Dolje_desno;
import Elementi.Izvor;
import Elementi.Krizni;
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
	JComboBox<String> primjeri;
	JButton temp;
	JButton izv;
	JButton amp;
	JButton vol;
	JButton vod;
	JButton save;
	JButton desno_gore ;
	JButton dolje_desno;
	JButton lijevo_dolje;
	JButton lijevo_gore;
	JButton Tdesno;
	JButton Tlijevo;
	JButton Tgore;
	JButton Tdolje;
	JButton delete;
	JButton krizni;
	ArrayList<JSlider> sliders;
	public meniView(){
		
		this.setMaximumSize(getPreferredSize());
		setBorder(new EmptyBorder(25,25,25,25));
		setLayout(new GridLayout(20,1,40,5));
		Dimension maxSize = new Dimension(100,40);
		
		String [] listaPrimjera = {"Ohmov zakon", "Prvi Kirchhoffov zakon","Drugi Kirchhoffov zakon","Mješani spoj otpornika - Primjer 1","Mješani spoj otpornika - Primjer 2","Mješani spoj otpornika - Primjer 3","Mješani spoj otpornika - Primjer 4"};  
		primjeri = new JComboBox<String>(listaPrimjera);
		primjeri.setSelectedIndex(0);
		primjeri.addActionListener(this);
		izv = new JButton("Izvor");
		amp = new JButton("Ampermetar");
		vol = new JButton("Voltmetar");
		vod = new JButton("Vod");
		save = new JButton("Spremi");
		desno_gore = new JButton("Vod desno-gore");
		dolje_desno = new JButton("Vod desno-dolje");
		lijevo_dolje = new JButton("Vod lijevo-dolje");
		lijevo_gore = new JButton("Vod lijevo-gore");
		Tdesno = new JButton("Vod T-desno");
		Tlijevo = new JButton("Vod T-lijevo");
		Tgore = new JButton("Vod T-gore");
		Tdolje = new JButton("Vod T-dolje");
		delete = new JButton("Obriši");
		krizni = new JButton("Vod križni");
		sliders = new ArrayList<JSlider>();
		for(int i=0;i<6;i++)sliders.add(new JSlider());
		

			
		
		
		
		
		temp = new JButton("Otpornik");
		temp.setMaximumSize(maxSize);
		temp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addRes();
			}
		});
		
		
		
		izv.setMaximumSize(maxSize);
		
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
		delete.setMaximumSize(maxSize);
		krizni.setMaximumSize(maxSize);
		
	
		
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
		
		delete.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				delete();
				
			}
		});
		
		krizni.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addKrizni();
			}
		});
		
		add(primjeri);
		
		
		
		
	}
	



	protected void addKrizni() {
		appView.dodajEl(new Krizni());
		
	}




	protected void delete() {
		if(!appView.l.isEmpty())
		appView.del = true;
		
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
		int index = primjeri.getSelectedIndex();
		switch(index){
		case 0: addSliders(2); repaint(); break;
		case 1: addSliders(5); repaint(); break;
		case 2: addSliders(5); repaint();break;
		case 3: 
		case 4: 
		case 5: 
		case 6: addButtons(); repaint();break;
		}
		appView.changeLayout();
		appView.loadList(index);
		revalidate();
		
	}



	private void addButtons() {
		for(JSlider s : sliders) remove(s);
		add(izv);
		add(amp);
		add(vol);
		add(vod);
		add(desno_gore);
		add(dolje_desno);
		add(lijevo_gore);
		add(Tdesno);
		add(Tlijevo);
		add(Tgore);
		add(Tdolje);
		add(krizni);
		add(delete);
		add(save);
		
	}



	private void addSliders(int i) {
		for(JSlider rs : sliders) remove(rs);
		
		remove(izv);
		remove(amp);
		remove(vol);
		remove(vod);
		remove(desno_gore);
		remove(dolje_desno);
		remove(lijevo_gore);
		remove(Tdesno);
		remove(Tlijevo);
		remove(Tgore);
		remove(Tdolje);
		remove(delete);
		remove(save);
		remove(krizni);
		for(JSlider s : sliders)
			if(i-->0)
			add(s);
		
	}
	
	
}

