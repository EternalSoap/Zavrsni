package Grafika;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Elementi.Element;
import Elementi.Linker;

public class radView extends JPanel{
	Font tf = new Font("Arial", Font.BOLD, 15);
	Font tf2 = new Font("Arial",Font.BOLD,20);
	ArrayList<String> formula;
	ArrayList<String> resVals = new ArrayList<String>();
	public radView(){
		setBorder(new EmptyBorder(25,25,25,25));
		setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.black));
		addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(!appView.l.isEmpty())
				addCoord(e.getPoint());
			}
		});
	
		Thread repainter = new Thread(new Runnable() {
		    @Override
		    public void run() {
		        while (true) {
		            repaint();
		            try {
		                Thread.sleep(30);
		            } catch (InterruptedException ignored) {
		            }
		        }
		        
		    }
		});
		repainter.setName("Panel repaint");
		repainter.setPriority(Thread.MIN_PRIORITY);
		repainter.start();
		

	
	}
	
	


	private void addCoord(Point point) {
		//Coordinates
		  
		 /* System.out.println((point.x-25)/100+" "+(point.y-25)/100);
		  System.out.println(point.x + " " + point.y);
		*/
		if(appView.del){appView.deleteEl(point); return;} 
		appView.dodajXY(point.x, point.y);
		
	}


	public Dimension getPreferredSize(){
		return new Dimension(875,800);
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Dimension velicina = this.getSize();
		int redovi, stupci;
		redovi = (int)(velicina.getHeight()-50)/100;
		stupci = (int)(velicina.getWidth()-50)/100;
		for(int i=0;i<=redovi;i++){
			g.drawLine(25, i*100+25, stupci*100+25,i*100+25);
		}
		for(int i=0;i<=stupci;i++){
			g.drawLine(i*100+25,25,i*100+25,redovi*100+25);
		}
		
		calculate();
		
		Graphics2D g2d = (Graphics2D)g;
		for(Element p : appView.l){
			if(p.getImage()!=null){
				if(p.ready()){ 
					if(p.getRotation()) {
						
						AffineTransform r = AffineTransform.getRotateInstance(Math.toRadians(90), p.getImage().getWidth()/2, p.getImage().getHeight()/2);
						AffineTransformOp atop = new AffineTransformOp(r,AffineTransformOp.TYPE_BILINEAR);
						g2d.drawImage(atop.filter(p.getImage(), null),(int)p.getShape().getBounds2D().getX()-25,(int)p.getShape().getBounds2D().getY()-25,null);
					}
					else{
						g2d.drawImage(p.getImage(),(int)p.getShape().getBounds2D().getX()-25,(int)p.getShape().getBounds2D().getY()-25,null);
						
					}
		}
			if(p.getValue() != null ){
				g2d.setFont(tf);
				if(p instanceof Elementi.Otpornik){
					g2d.setColor(Color.blue);
					if(p.getRotation()){
						g2d.setTransform(rotate90(p));
						g2d.drawString(p.getValue(), (int)p.getShape().getBounds2D().getX()+18, (int)p.getShape().getBounds2D().getY()-50);
						g2d.setTransform(rotate0(p));
						g2d.setColor(Color.black);
						
					}else{
					g2d.drawString(p.getValue(), (int)p.getShape().getBounds2D().getX()+18, (int)p.getShape().getBounds2D().getY());
					g2d.setColor(Color.black);
					}
				}
				if(p instanceof Elementi.Izvor){
					g2d.setColor(Color.blue);
					if(!p.getRotation()){
						g2d.setTransform(rotate90(p));
						g2d.drawString(p.getValue(), (int)p.getShape().getBounds2D().getX()+18, (int)p.getShape().getBounds2D().getY()-60);
						g2d.setTransform(rotate0(p));
						g2d.setColor(Color.black);
						
					}else{
					g2d.drawString(p.getValue(), (int)p.getShape().getBounds2D().getX()+20, (int)p.getShape().getBounds2D().getY()-10);
					g2d.setColor(Color.black);
					}
				}
				
				if(p instanceof Elementi.Ampermetar || p instanceof Elementi.Voltmetar){
					g2d.setColor(Color.red);
					if(p.getRotation()){
						g2d.setTransform(rotate90(p));
						g2d.drawString(p.getValue(), (int)p.getShape().getBounds2D().getX()+18, (int)p.getShape().getBounds2D().getY()-50);
						g2d.setTransform(rotate0(p));
						g2d.setColor(Color.black);
					}else{
						g2d.drawString(p.getValue(), (int)p.getShape().getBounds2D().getX()+20, (int)p.getShape().getBounds2D().getY()-10);
						g2d.setColor(Color.black);
					}
				}
				
				int i=0;
				int j=0;
				for(String f : formula ){
					g2d.setFont(tf2);
					Color dank = new Color(8,117,35);
					g2d.setColor(dank);
					switch(meniView.getIndex()){
						case 0: g2d.drawString(f, 550, 400); break;
						case 1: g2d.drawString(f, i+200, j+75); j+= (j>500)? 50: 550;break;
						case 2: g2d.drawString(f, i+150, j+275); j+=100;break;
						
						
					}
				}
			
			}
		}
	}
		
		 
	}


	private void calculate() {
		formula = new ArrayList<String>();
		appView.links.clear();
		switch(meniView.getIndex()){
		case 0: appView.links.add(new Linker(new Point(4*100+25,3*100+25), new Point[]{new Point(3*100+25,4*100+25)}, new Point[][]{{new Point(3*100+25,2*100+25)}}, new int[]{0})); break;
		case 1: appView.links.add(new Linker(new Point(1*100+25,1*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[][]{{new Point(2*100+25,3*100+25), new Point(4*100+25,3*100+25), new Point(6*100+25,3*100+25)}}, new int[]{1}));
				appView.links.add(new Linker(new Point(2*100+25,2*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[][]{{new Point(2*100+25,3*100+25)}}, new int[]{0}));
				appView.links.add(new Linker(new Point(4*100+25,2*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[][]{{new Point(4*100+25,3*100+25)}}, new int[]{0}));
				appView.links.add(new Linker(new Point(6*100+25,2*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[][]{{new Point(6*100+25,3*100+25)}}, new int[]{0}));
		break;
		case 2: appView.links.add(new Linker(new Point(7*100+25,3*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[][]{{new Point(2*100+25,1*100+25), new Point(5*100+25,1*100+25), new Point(2*100+25,5*100+25), new Point(5*100+25,5*100+25)}}, new int[]{0}));
				appView.links.add(new Linker(new Point(2*100+25,0*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[][]{{new Point(2*100+25,1*100+25)}} , new int[]{0}));
				appView.links.add(new Linker(new Point(5*100+25,0*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[][]{{new Point(5*100+25,1*100+25)}}, new int[]{0}));
				appView.links.add(new Linker(new Point(2*100+25,6*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[][]{{new Point(2*100+25,5*100+25)}}, new int[]{0}));
				appView.links.add(new Linker(new Point(5*100+25,6*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[][]{{new Point(5*100+25,5*100+25)}}, new int[]{0}));
			
			break;
		case 3: appView.links.add(new Linker(new Point(0*100+25,2*100+25),new Point[]{new Point(0*100+25,3*100+25)}, new Point [][]{{new Point(1*100+25,1*100+25)},{new Point(5*100+25,4*100+25), new Point(3*100+25,4*100+25)}}, new int []{0,1}));
				appView.links.add(new Linker(new Point(1*100+25,0*100+25),new Point[]{new Point(0*100+25,2*100+25)}, new Point [][]{{new Point(1*100+25,1*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(3*100+25,2*100+25),new Point[]{new Point(1*100+25,0*100+25)}, new Point [][]{{new Point(3*100+25,4*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(5*100+25,2*100+25),new Point[]{new Point(1*100+25,0*100+25)}, new Point [][]{{new Point(5*100+25,4*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(4*100+25,4*100+25),new Point[]{new Point(3*100+25,2*100+25)}, new Point [][]{{new Point(3*100+25,4*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(6*100+25,4*100+25),new Point[]{new Point(5*100+25,2*100+25)}, new Point [][]{{new Point(5*100+25,4*100+25)}}, new int []{0}));
				break;
		case 4: appView.links.add(new Linker(new Point(1*100+25,1*100+25),new Point[]{new Point(0*100+25,3*100+25)}, new Point [][]{{new Point(6*100+25,3*100+25), new Point(7*100+25,3*100+25)}, {new Point(2*100+25,1*100+25), new Point(3*100+25,3*100+25)}, {new Point(5*100+25,1*100+25), new Point(2*100+25,4*100+25)}}, new int []{1,1,0}));
				appView.links.add(new Linker(new Point(1*100+25,4*100+25),new Point[]{new Point(0*100+25,3*100+25)}, new Point [][]{{new Point(6*100+25,3*100+25), new Point(7*100+25,3*100+25)}, {new Point(2*100+25,1*100+25), new Point(3*100+25,3*100+25)}, {new Point(5*100+25,1*100+25), new Point(2*100+25,4*100+25)} }, new int []{1,1,0}));
				appView.links.add(new Linker(new Point(3*100+25,2*100+25),new Point[]{new Point(0*100+25,3*100+25)}, new Point [][]{{new Point(3*100+25,3*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(6*100+25,2*100+25),new Point[]{new Point(0*100+25,3*100+25)}, new Point [][]{{new Point(6*100+25,3*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(7*100+25,2*100+25),new Point[]{new Point(0*100+25,3*100+25)}, new Point [][]{{new Point(7*100+25,3*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(4*100+25,1*100+25),new Point[]{new Point(0*100+25,3*100+25)}, new Point [][]{{new Point(5*100+25,1*100+25)}, {new Point(6*100+25,3*100+25)}, {new Point(7*100+25,3*100+25)} }, new int []{0,1,1}));
		
			
			break;
		case 5: appView.links.add(new Linker(new Point(2*100+25,5*100+25),new Point[]{new Point(4*100+25,5*100+25)}, new Point [][]{{new Point(2*100+25,1*100+25), new Point(2*100+25,2*100+25), new Point(2*100+25,3*100+25)}, {new Point(6*100+25,1*100+25), new Point(6*100+25,3*100+25)} }, new int []{1,1}));
				appView.links.add(new Linker(new Point(1*100+25,1*100+25),new Point[]{new Point(1*100+25,4*100+25)}, new Point [][]{{new Point(2*100+25,1*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(1*100+25,2*100+25),new Point[]{new Point(1*100+25,4*100+25)}, new Point [][]{{new Point(2*100+25,2*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(1*100+25,3*100+25),new Point[]{new Point(1*100+25,4*100+25)}, new Point [][]{{new Point(2*100+25,3*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(1*100+25,4*100+25),new Point[]{new Point(2*100+25,5*100+25)}, new Point [][]{{new Point(2*100+25,1*100+25), new Point(2*100+25,2*100+25), new Point(2*100+25,3*100+25)}}, new int []{1}));
				appView.links.add(new Linker(new Point(5*100+25,1*100+25),new Point[]{new Point(5*100+25,4*100+25)}, new Point [][]{{new Point(6*100+25,1*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(5*100+25,3*100+25),new Point[]{new Point(5*100+25,4*100+25)}, new Point [][]{{new Point(6*100+25,3*100+25)}}, new int []{0}));
				appView.links.add(new Linker(new Point(5*100+25,4*100+25),new Point[]{new Point(2*100+25,5*100+25)}, new Point [][]{{new Point(6*100+25,1*100+25), new Point(6*100+25,3*100+25)}}, new int []{1}));
				
		
		break;
		case 6: break;
		case 7: break;
		}
		ArrayList<String[]> container = new ArrayList<String[]>();
		for(Linker link : appView.links){
		for(Element out : appView.l){
			if(out.ima(link.metar)){
				if(out instanceof Elementi.Ampermetar){
					String[] struja = struja(link);
					container.add(struja);
					out.updateValue(struja[0]);
				}
				if(out instanceof Elementi.Voltmetar){
					String[] napon = napon(link);
					container.add(napon);
					out.updateValue(napon[0]);
				}
			}
		}
	}
	String toPrint;
	switch(meniView.getIndex()){
	case 0: toPrint = "I (" + container.get(0)[0] + ") = U (" +container.get(0)[1] + ") /R (" +container.get(0)[2] + ")"; formula.add(toPrint); break;
	case 1: toPrint = "I (" + container.get(0)[0] + ") = I1 (" + container.get(1)[0] + ") + I2 (" + container.get(2)[0] + ") + I3 ("+ container.get(3)[0] + ") "; formula.add(toPrint); toPrint = "In = U / Rn , npr I1 ("+ container.get(1)[0] + ")  = U (" + container.get(1)[1] + ")/R1 (" + container.get(1)[2] +")"; formula.add(toPrint);
		toPrint = "1/Ruk (" + container.get(0)[2] + ") = ";
		resVals.clear();
		int i=0;
		for(Element el : appView.l){
			if(el instanceof Elementi.Otpornik){
				resVals.add(el.getValue());
			}
			
		}
		for(i=0;i<resVals.size();i++){
			toPrint = toPrint + "1/R" + (i+1) + "("+ resVals.get(i) + ")";
			if(i+1!=resVals.size()) toPrint += " + ";
		}
		formula.add(toPrint);
	break;
	case 2: 
		toPrint = "U (" + container.get(0)[1] + ") = U1 (" + container.get(1)[0] + ") + U2 (" +container.get(2)[0] +") " + "+ U3 (" +container.get(3)[0] +") " + "+ U4 (" +container.get(4)[0] + ")";
		formula.add(toPrint);
		toPrint = "Un = I * Rn , npr. U1 (" + container.get(1)[0] + ") = I (" + container.get(1)[1] + ") * R1 (" + container.get(1)[2] + ") ";
		formula.add(toPrint);
		toPrint = "Ruk (" + container.get(0)[2] + ") = ";
		resVals.clear();
		i=0;
		for(Element el : appView.l){
			if(el instanceof Elementi.Otpornik){
				resVals.add(el.getValue());
			}
			
		}
		for(i=0;i<resVals.size();i++){
			toPrint = toPrint + "R" + (i+1) + " ("+ resVals.get(i) + ")";
			if(i+1!=resVals.size()) toPrint += " + ";
		}
		formula.add(toPrint);
		
		break;
	
	}
		
	}


	private String[] napon(Linker link) {
		float uV = 0;
		float uR = 0;
		float uS = 0;
		float uP = 0;
		int i=0;
		float ph = 0;
		for(Point a : link.par1){
			for(Element e : appView.l){
				if(e.ima(a) && e.ready()){
					if(e.getValue()== "Greška"){uV = 0; break;}
					try{
						uV = Float.parseFloat(e.getValue());
					}catch(NumberFormatException err){err.printStackTrace();} 
				}
			}
		}
		for(Point[] b : link.par2){
			ph = 0;
			if(link.nodeconnection[i] == 0){
				i++;
				for(Point c : b){
					for(Element e : appView.l){
						if(e.ima(c) && e.ready()){
							try{uS =uS + Float.parseFloat(e.getValue());}catch(NumberFormatException err){err.printStackTrace();}
						}
					}
				}
			}
			else{
				i++;
				for(Point c : b){
					for(Element e : appView.l){
						if(e.ima(c) && e.ready()){
							
							try{float p= Float.parseFloat(e.getValue());  ph= ph+1/p;}catch(NumberFormatException err){err.printStackTrace();}
						}
					}
				}
				uP +=1/ph;
			}
			uR = uS + uP;
		}
		if(uR==0) return new String[]{"Greska", Float.toString(round(uV,2)), Float.toString(round(uR,2))};
		return new String[]{Float.toString(round(uV*uR,2)),Float.toString(uV), Float.toString(round(uR,2))};
		
	}


	private String[] struja(Linker link) {
		float uV = 0;
		float uR = 0;
		for(Point a : link.par1){
			for(Element e : appView.l){
				if(e.ima(a)){
					try{
					uV = uV + Float.parseFloat(e.getValue());
					}catch(NumberFormatException err){err.printStackTrace();}
				}
			}
		}
		int i=0;
		float uS=0;
		float uP=0;
		float ph=0;
		Boolean par = null;
		for(Point[] b : link.par2){
			ph = 0;
			par = false;
			if(link.nodeconnection[i] == 0){
				i++;
				for(Point c : b){
					for(Element e : appView.l){
						if(e.ima(c) && e.ready()){
							try{uS =uS + Float.parseFloat(e.getValue());}catch(NumberFormatException err){err.printStackTrace();}
						}
					}
				}
			}
			else{
				i++;
				for(Point c : b){
					for(Element e : appView.l){
						if(e.ima(c) && e.ready()){
							par = true;
							try{float p= Float.parseFloat(e.getValue());  ph= ph+1/p;}catch(NumberFormatException err){err.printStackTrace();}
						}
					}
				}
				uP += 1/ph;
			}
			
		}
		
		uR = par == true? uS+uP: uS+uP;
		if(uR==0) return new String[]{"Greska", Float.toString(uV), Float.toString(uR)};
		return new String[]{Float.toString(round(uV/uR,2)),Float.toString(uV), Float.toString(round(uR,2))};
	}


	public static float round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.floatValue();
	}


	private AffineTransform rotate0(Element p) {
		AffineTransform spin = AffineTransform.getRotateInstance(Math.toRadians(360),(int)p.getShape().getBounds2D().getX(), (int)p.getShape().getBounds2D().getY());
		return spin;
	}


	private AffineTransform rotate90(Element p) {
		AffineTransform spin = AffineTransform.getRotateInstance(Math.toRadians(90),(int)p.getShape().getBounds2D().getX(), (int)p.getShape().getBounds2D().getY());
		return spin;
	}

	
	
}
