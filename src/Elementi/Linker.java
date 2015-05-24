package Elementi;

import java.awt.Point;

public class Linker {
	public Point metar;
	public Boolean type; //amp = 0, volt = 1
	public int par; 	 // 1 = parallel , 0 = serial
	public Point par1[]; //amp - voltage, volt - amp
	public Point par2[]; //resistance
	public Boolean zero = false;
	
	public Linker(Point m, Point p1[], Point p2[], int n, Boolean t){
		metar = m;
		par1 = p1;
		par2 = p2;
		par = n;
		type = t;
	}
	

}
