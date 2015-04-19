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
	JComboBox<String> primjeri;
	JButton temp;
	JButton izv;
	JButton amp;
	JButton vol;
	JButton vod;
	JButton save;
	JButton load;
	JButton desno_gore ;
	JButton dolje_desno;
	JButton lijevo_dolje;
	JButton lijevo_gore;
	JButton Tdesno;
	JButton Tlijevo;
	JButton Tgore;
	JButton Tdolje;
	JButton delete;
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
		load = new JButton("Ucitaj");
		desno_gore = new JButton("Vod desno-gore");
		dolje_desno = new JButton("Vod desno-dolje");
		lijevo_dolje = new JButton("Vod lijevo-dolje");
		lijevo_gore = new JButton("Vod lijevo-gore");
		Tdesno = new JButton("Vod T-desno");
		Tlijevo = new JButton("Vod T-lijevo");
		Tgore = new JButton("Vod T-gore");
		Tdolje = new JButton("Vod T-dolje");
		delete = new JButton("Obrisi");
		
		
		
		
		temp = new JButton("Otpornik");
		temp.setMaximumSize(maxSize);
		temp.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				addRes();
			}
		});
		
		
		
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
		delete.setMaximumSize(maxSize);
		
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
		
		delete.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				delete();
				
			}
		});
		
		add(primjeri);
		
		
		
		
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
		
		if(index >2){
			appView.slide = false;
			add(temp);
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
			add(delete);
			repaint();
		}
		else{
			appView.slide = true;
			remove(temp);
			remove(save);
			remove(load);
			remove(izv);
			remove(vod);
			remove(vol);
			remove(amp);
			remove(desno_gore);
			remove(dolje_desno);
			remove(lijevo_gore);
			remove(lijevo_dolje);
			remove(Tgore);
			remove(Tdolje);
			remove(Tlijevo);
			remove(Tdesno);
			remove(delete);
			repaint();
			
		}
		appView.changeLayout();
		appView.loadList(index);
		repaint();
		
	}
	
}

