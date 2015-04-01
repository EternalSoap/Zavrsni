package Elementi;
//test

import java.awt.Point;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
