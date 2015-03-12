package Grafika;

import java.awt.BorderLayout;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Elementi.Element;
import Elementi.Otpornik;

public class appView extends JPanel implements Serializable{
	static ArrayList<Element> l = new ArrayList<Element>();
	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				
				Init();
				
			}
			
			
		});
		
	}

	protected static void Init() {
		JFrame j = new JFrame("Zavrsni rad");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//j.setExtendedState(j.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		JPanel container = new JPanel();
		meniView view1 = new meniView();
		radView view2 = new radView();
		container.setLayout(new BoxLayout(container,BoxLayout.X_AXIS));
		container.add(view1);
		container.add(view2);
		j.getContentPane().add(container, BorderLayout.NORTH);
		j.pack();
		j.setVisible(true);
		
	}
	
	public static void dodajEl(Element el){
		l.add(el);
	}

	public static void dodajXY(int x, int y) {
		for(Element i : l){
			if(i instanceof Elementi.Otpornik && (i.ready()) &&i.ima(new Point(x,y))) i.updateOtpor(new unos(i.getOtpor()).returnOtpor());
			if(!i.ready()) continue;
			if(i.ima(new Point(x,y))) return;
		}
		Element pom = l.get(l.size()-1);
		pom.updateXY(x,y);
		l.set(l.size()-1, pom);
		
		
	}

	public static void saveList(int index) {
		String ime ="data"+index+".obj";
		try{
		FileOutputStream file = new FileOutputStream(ime);
		ObjectOutputStream oos = new ObjectOutputStream(file);
		oos.writeObject(l);
		oos.close();
		file.close();
		}catch(IOException e){ e.printStackTrace();}
	}

	public static void loadList(int index) {
		String ime = "data"+index+".obj";
		System.out.println(index);
		try{
			FileInputStream file = new FileInputStream(ime);
			System.out.println(file.getChannel().size());
			long duljina = file.getChannel().size();
			if(duljina == 0) return;
			ObjectInputStream ois = new ObjectInputStream(file);
			
			l = (ArrayList<Element>) ois.readObject();
			refresh(l);
			ois.close();
			file.close();
			
		}catch(ClassNotFoundException e){e.printStackTrace();return;}
		catch(IOException i){i.printStackTrace();return;}
		
	}

	private static void refresh(ArrayList<Element> l2) {
		for(Element i : l2){
			i.refresh();
			
		}
		
	}
	
	
	
}
