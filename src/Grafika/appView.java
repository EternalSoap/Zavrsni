package Grafika;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Elementi.Element;
import Elementi.Linker;

public class appView extends JPanel implements Serializable{
	static CopyOnWriteArrayList<Element> l = new CopyOnWriteArrayList<Element>();
	static CopyOnWriteArrayList<Linker> links = new CopyOnWriteArrayList<Linker>();
	static JFrame j = new JFrame("Simulacija Strujnog Kruga");
	static JPanel container = new JPanel();
	static Boolean del;
	static Boolean slide = false;
	static meniView view1;
	static splashScreen view2;
	static Boolean layout = false;
	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable(){

			@Override
			public void run() {
				
				Init();
				
			}
			
			
		});
		
	}

	protected static void Init() {
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setResizable(false);
		//j.setExtendedState(j.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		view1 = new meniView();
		//radView view2 = new radView();
		view2 = new splashScreen();
		container.setLayout(new BoxLayout(container,BoxLayout.X_AXIS));
		container.add(view1);
		container.add(view2);
		j.getContentPane().add(container, BorderLayout.NORTH);
		j.pack();
		j.setSize(1200, 768);
		j.setVisible(true);
		del = false;
		
	}
	
	public static void dodajEl(Element el){
		l.add(el);
	}

	public static void dodajXY(int x, int y) {
		for(Element i : l){
			if(i instanceof Elementi.Otpornik && (i.ready()) &&i.ima(new Point(x,y))) i.updateValue(new unos(i.getValue(),"otpor").returnOtpor());
			else if(i instanceof Elementi.Izvor && (i.ready()) &&i.ima(new Point(x,y))) i.updateValue(new unos(i.getValue(),"struja").returnVoltage());
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

	@SuppressWarnings("unchecked")
	public static void loadList(int index) {
		String ime = "data"+index+".obj";
		System.out.println(index);
		try{
			FileInputStream file = new FileInputStream(ime);
			System.out.println(file.getChannel().size());
			long duljina = file.getChannel().size();
			if(duljina == 0){file.close(); return;}
			ObjectInputStream ois = new ObjectInputStream(file);
			
			l =(CopyOnWriteArrayList<Element>) ois.readObject();
			refresh(l);
			ois.close();
			file.close();
			
		}catch(ClassNotFoundException e){e.printStackTrace();return;}
		catch(IOException i){i.printStackTrace();return;}
		
	}

	private static void refresh(CopyOnWriteArrayList<Element> l2) {
		for(Element i : l2){
			i.refresh();
			
		}
		
	}
	
	public static void changeLayout(){
		if(layout) return;
		layout = true;
		container.remove(view2);
		radView v2 = new radView();
		container.add(v2);
		j.getContentPane().add(container, BorderLayout.NORTH);
		j.revalidate();
	}

	public static void deleteEl(Point point) {
		for(Element e : l){
			if(e.ima(point)) {l.remove(e);}
		}
		del = false;
		
	}
	
}

