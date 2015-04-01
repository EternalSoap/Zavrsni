package Grafika;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.AffineTransformOp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Elementi.Element;

public class radView extends JPanel{
	
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
		            spojeno();
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
		System.out.println((point.x-25)/100+" "+(point.y-25)/100);
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
		
		Graphics2D g2d = (Graphics2D)g;
		for(Element p : appView.l){
			if(p.getImage()!=null){
				if(p.ready()) 
					if(p.getRotation()) {
						
						AffineTransform r = AffineTransform.getRotateInstance(Math.toRadians(90), p.getImage().getWidth()/2, p.getImage().getHeight()/2);
						AffineTransformOp atop = new AffineTransformOp(r,AffineTransformOp.TYPE_BILINEAR);
						g2d.drawImage(atop.filter(p.getImage(), null),(int)p.getShape().getBounds2D().getX()-25,(int)p.getShape().getBounds2D().getY()-25,null);
					}
					else{
						g2d.drawImage(p.getImage(),(int)p.getShape().getBounds2D().getX()-25,(int)p.getShape().getBounds2D().getY()-25,null);
						
					}
		}
			
		}
		
		 
	}
	public Boolean spojeno(){
		Boolean s = true;
		int i,j,izvorx,izvory;
		i=j=izvorx=izvory=0;
		Element[][] polje = new Element[8][7];
		
		for(Element e : appView.l){
			if(e.ready()){
			i = (int) ((e.getShape().getBounds2D().getX()-25)/100);
			j = (int) ((e.getShape().getBounds2D().getY()-25)/100);
			if(e instanceof Elementi.Izvor ){izvorx = i; izvory = j; }
			polje[i][j] = e;
			}
			}
		int counter =0;
		int broj = appView.l.size();
		for(int prvi =1; prvi<broj; prvi++){
			for(int drugi = 0; drugi<broj; drugi++){
				Element e1 = appView.l.get(prvi);
				Element e2 = appView.l.get(drugi);
				if(e1.ready() && e2.ready()) {
					
					int w = e2.getRotation()?0:110;
					int h = e2.getRotation()?110:0;
					if(e1.getShape().getBounds2D().contains(new Point((int) e2.getShape().getBounds2D().getX()+w,(int)e2.getShape().getBounds2D().getY()+h))){
						System.out.println(prvi+" "+ drugi); counter++;}
					//else System.out.println("Ne");
				}	
			}
			if(counter!=broj/2) System.out.println("Krivo spojeno");
			
		}
		
			
			
		
				
				
		return s;
		
	}
	
	
}
