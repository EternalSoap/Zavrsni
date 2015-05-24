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
					switch(meniView.getIndex()){
						case 0: g2d.drawString(f, 550, 400); break;
						case 1: g2d.drawString(f, i+100, j+75);i+=400; if(i >= 125+300){i=0; j = 600;} break;
						case 2: break;
						
						
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
		case 0: appView.links.add(new Linker(new Point(4*100+25,3*100+25), new Point[]{new Point(3*100+25,4*100+25)}, new Point[]{new Point(3*100+25,2*100+25)}, 0, false)); break;
		case 1: appView.links.add(new Linker(new Point(1*100+25,1*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[]{new Point(2*100+25,3*100+25), new Point(4*100+25,3*100+25), new Point(6*100+25,3*100+25)},1,false));
				appView.links.add(new Linker(new Point(2*100+25,2*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[]{new Point(2*100+25,3*100+25)}, 0, false));
				appView.links.add(new Linker(new Point(4*100+25,2*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[]{new Point(4*100+25,3*100+25)}, 0, false));
				appView.links.add(new Linker(new Point(6*100+25,2*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[]{new Point(6*100+25,3*100+25)}, 0, false));
		break;
		case 2: appView.links.add(new Linker(new Point(7*100+25,3*100+25), new Point[]{new Point(0*100+25,3*100+25)}, new Point[]{new Point(2*100+25,1*100+25), new Point(5*100+25,1*100+25), new Point(2*100+25,5*100+25), new Point(5*100+25,5*100+25)},0, false));
				appView.links.add(new Linker(new Point(2*100+25,0*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[]{new Point(2*100+25,1*100+25)}, 0, true));
				appView.links.add(new Linker(new Point(5*100+25,0*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[]{new Point(2*100+25,1*100+25)}, 0, true));
				appView.links.add(new Linker(new Point(2*100+25,6*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[]{new Point(2*100+25,5*100+25)}, 0, true));
				appView.links.add(new Linker(new Point(5*100+25,6*100+25), new Point[]{new Point(7*100+25,3*100+25)}, new Point[]{new Point(5*100+25,5*100+25)}, 0, true));
			
			break;
		case 3: break;
		case 4: break;
		case 5: break;
		case 6: break;
		case 7: break;
		
		}
		String toPrint = "";
	for(Linker link : appView.links){
		for(Element out : appView.l){
			if(out.ima(link.metar)){
				if(out instanceof Elementi.Ampermetar){
					String[] struja = struja(link);
					
					out.updateValue(struja[0]);
					toPrint = "I ("+struja[0]+") = U (" +struja[1] + ") / R ("+struja[2] + ") ";
					formula.add(toPrint);
				}
				if(out instanceof Elementi.Voltmetar){
					String[] napon = napon(link);
					out.updateValue(napon[0]);
					toPrint = "I ("+napon[0]+") = U (" +napon[1] + ") / R ("+napon[2] + ") ";
					formula.add(toPrint);
				}
			}
		}
	}
		
	}


	private String[] napon(Linker link) {
		float uV = 0;
		float uR = 0;
		for(Point a : link.par1){
			for(Element e : appView.l){
				if(e.ima(a)){
					if(e.getValue()== "Greška"){uV = 0; break;}
					try{
						uV = Float.parseFloat(e.getValue());
					}catch(NumberFormatException err){err.printStackTrace();} 
				}
			}
		}
		for(Point b : link.par2){
			for(Element e : appView.l){
				if(e.ima(b)){
					try{
						uR = Float.parseFloat(e.getValue());
						if(uR == 0){ link.zero = true; break;}
					}catch(NumberFormatException err){err.printStackTrace();}
					
				}
				
			}
			
		}
		for(Linker l : appView.links){
			if(l.zero) return new String[]{"Greška",Float.toString(uV),Float.toString(uR)};
		}
		return new String[]{Float.toString(round(uV*uR,2)),Float.toString(uV), Float.toString(uR)};
		
	}


	private String[] struja(Linker link) {
		float uV = 0;
		float uR = 0;
		Boolean parallel = false;
		for(Point a : link.par1){
			for(Element e : appView.l){
				if(e.ima(a)){
					try{
					uV = uV + Float.parseFloat(e.getValue());
					}catch(NumberFormatException err){err.printStackTrace();}
				}
			}
		}
			for(Point b : link.par2){
				for(Element e : appView.l){
					if(e.ima(b)){
						float test = Float.parseFloat(e.getValue());
						if(test==0){ link.zero = true; break;}
						if(link.par == 0) try{ uR = uR + Float.parseFloat(e.getValue()); } catch(NumberFormatException err){err.printStackTrace();}
						else try{ float r = Float.parseFloat(e.getValue());uR = uR + 1/r; parallel = true;} catch(NumberFormatException err){err.printStackTrace();}
					}
				}
			}
			if(parallel)  uR = 1/uR;
			for(Linker l : appView.links){
				if(l.zero) return new String[]{"Greška",Float.toString(uV),Float.toString(uR)};
			}
			
			return new String[]{Float.toString(round(uV/uR,2)),Float.toString(uV),Float.toString(uR)};
		
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
