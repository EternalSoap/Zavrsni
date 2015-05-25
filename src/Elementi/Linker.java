package Elementi;

import java.awt.Point;

public class Linker {
	public Point metar;
	public Point par1[]; //amp - voltage, volt - amp
	public Point par2[][]; //resistance
	public int nodeconnection[]; //0 serial , 1 parallel (marks the connection type of a node
	public Boolean zero = false;
	
	public Linker(Point m, Point p1[], Point p2[][], int n[]){
		metar = m;
		par1 = p1;
		par2 = p2;
		nodeconnection = n;
		
	}
	

}
