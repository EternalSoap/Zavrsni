package Elementi;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Invisible implements Element {
	
	String [] values;
	boolean paint = false;
	Point a,b;
	
	public Invisible(String o, String n, String s){
		a = b = null;
		values = new String[3];
		values[0] = o;
		values[1] = n;
		values[2] = s;
		
		
	}

	@Override
	public void updateXY(int x, int y) {
		if(x>800) x-=50;
		if(y>=725) y-=100;
		if(this.a==null){this.a = new Point(x,y); return;}
		else if(this.b==null){this.b = new Point(x,y);}
		paint = true;
	}

	@Override
	public BufferedImage getImage() {
		return null;
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

	public String getValue(int i) {
		
		return values[i];
	}
	

	public void updateValue(String value, int i) {
		values[i] = value;
		
	}
	

	@Override
	public boolean getRotation() {
		return false;
	}

	@Override
	public void refresh() {
		//does nothing
		
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateValue(String value) {
		// TODO Auto-generated method stub
		
	}


}
