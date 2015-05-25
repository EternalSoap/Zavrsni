package Elementi;


import java.awt.Point;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public interface Element extends Serializable{

	void updateXY(int x, int y);

	BufferedImage getImage();

	Shape getShape();
	
	Boolean ready();
	
	boolean ima(Point point);

	String getValue();
	
	void updateValue(String value);
	
	boolean getRotation();

	void refresh();

	
}
