package Elementi;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Dolje_desno implements Element {
	
	Point a,b;
	Boolean paint,inverted;
	transient BufferedImage slika;
	private String imageName = "dolje_desno.png";
	
	public Dolje_desno(){
		a=b=null;
		paint = inverted = false;
	}

	@Override
	public void updateXY(int x, int y) {
		if(x>800) x-=50;
		if(y>=725) y-=100;
		slika = null;
		if(a==null){a = new Point(x,y);return;}
		else if (b==null){b= new Point(x,y);}
		if(Math.abs(a.x-a.x)>50) inverted = false;
		else if(Math.abs(a.y-b.y)>50) inverted = true;
		try {
			slika = ImageIO.read(ClassLoader.getSystemResourceAsStream(imageName));
		} catch (IOException e) {
			System.out.println("nema slike");
			e.printStackTrace();
		}
		System.out.println(inverted);
		paint = true;
		
	}

	@Override
	public BufferedImage getImage() {
		return this.slika;
	}

	@Override
	public Shape getShape() {
		return new Rectangle2D.Float(((a.x-75+100/2)/100)*100+50, ((a.y-75+100/2)/100)*100+50, 100,100);
	}

	@Override
	public Boolean ready() {
		return this.paint;
	}

	@Override
	public boolean ima(Point point) {
		if(((this.a.x-25)/100 == (point.x-25)/100) && ((this.a.y-25)/100 == (point.y-25)/100)) return true;
		return false;
	}

	@Override
	public String getValue() {
		return null;
	}

	@Override
	public void updateValue(String value) {
		//nope
		
	}

	@Override
	public boolean getRotation() {
		return false;
	}

	@Override
	public void refresh() {
		try {
			slika = ImageIO.read(ClassLoader.getSystemResourceAsStream(imageName));
			System.out.println(slika);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
