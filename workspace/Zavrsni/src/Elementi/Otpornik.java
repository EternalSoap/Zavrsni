package Elementi;


import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Otpornik implements Element{
	
	String otpor;
	private String imageName = "Otpornik.png";
	boolean paint = false;
	transient BufferedImage slika;
	Point a,b;
	boolean inverted = false;
	
	public Otpornik(String o){
		paint = false;
		otpor = o;
	}

	@Override
	public void updateXY(int x, int y) {
		if(x>1050) x-=50;
		if(y>625) y-=50;
		if(a==null){a = new Point(x,y); return;}
		else if(b==null){b = new Point(x,y);}
		if(Math.abs(a.x-b.x)>50) inverted = false;
		else if(Math.abs(a.y-b.y)>50) inverted = true;
		try{
		slika = ImageIO.read(ClassLoader.getSystemResourceAsStream(imageName));
		} catch(IOException e){e.printStackTrace();}
		paint = true;
		
	}

	@Override
	public BufferedImage getImage() {
		return slika;
	}

	@Override
	public Shape getShape() {
		return new Rectangle2D.Float(((a.x-75+100/2)/100)*100+50, ((a.y-50+100/2)/100)*100+25, 100,100);
	}

	@Override
	public Boolean ready() {
		return paint;
	}

	@Override
	public boolean ima(Point point) {
		//System.out.println("a"+(this.a.x-25)/100+" "+(this.a.y-25)/100);
		if(((this.a.x-25)/100 == (point.x-25)/100) && ((this.a.y-25)/100 == (point.y-25)/100)) return true;
		return false;
	}


	public void refresh() {
		try {
			slika = ImageIO.read(ClassLoader.getSystemResourceAsStream(imageName));
			System.out.println(slika);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public String getOtpor(){
		return this.otpor;
		
	}
	
	public void updateOtpor(String otpor){
		this.otpor = otpor;
	}

	@Override
	public boolean getRotation() {
		return this.inverted;
	};

	
}
