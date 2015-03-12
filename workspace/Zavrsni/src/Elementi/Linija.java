package Elementi;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;

public class Linija implements Element {
	transient BufferedImage slika;
	Boolean paint;
	Point a = null;
	Point b = null;
	Boolean inverted = false;
	String imageName="vod.png";
	
	public Linija(Point p1,Point p2){
		paint = false;
	}

	@Override
	public void updateXY(int x, int y) {
		if(x>1050) x-=50;
		if(y>625) y-=50;
		slika = null;
		if(a==null){a = new Point(x,y);return;}
		else if (b==null){b= new Point(x,y);}
		if(Math.abs(a.x-a.x)>50) inverted = false;
		else if(Math.abs(a.y-b.y)>50) inverted = true;
		try {
			slika =(!inverted? ImageIO.read(ClassLoader.getSystemResourceAsStream("vod.png")):ImageIO.read(ClassLoader.getSystemResourceAsStream("vod.png")));
		} catch (IOException e) {
			System.out.println("nema slike");
			e.printStackTrace();
		}
		System.out.println(inverted);
		paint = true;
		
	}

	@Override
	public Shape getShape() {
		return new Rectangle2D.Float(((a.x-75+100/2)/100)*100+50, ((a.y-50+50/2)/100)*100+25, 50, 25);
	}

	@Override
	public BufferedImage getImage() {
		return slika;
	}

	@Override
	public Boolean ready() {
		return paint;
	}

	@Override
	public boolean ima(Point point) {
		if(((this.a.x-25)/100 == (point.x-25)/100) && ((this.a.y-25)/100 == (point.y-25)/100)) return true;
		return false;
	}


	@Override
	public String getOtpor() {
		return null;
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

	@Override
	public boolean getRotation() {
		return this.inverted;
	}

	@Override
	public void updateOtpor(String otpor) {
		// TODO Auto-generated method stub
		
	}

	
}