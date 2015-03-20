package Grafika;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class splashScreen extends JPanel{

	String ime = "Hrvoje Goldner";
	String text = "Zavrsni rad";
	Font tf = new Font("Arial", Font.BOLD, 40);
	Font ti = new Font("Arial", Font.BOLD,30);
	public splashScreen(){
		this.setSize(new Dimension(875,800));
		setBorder(BorderFactory.createMatteBorder(0,1, 0, 0, Color.black));
		setVisible(true);
		System.out.println(this.getWidth());
	}
	
	public Dimension getPrefferedSize(){
		return new Dimension(875,800);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setFont(tf);
		g.drawString(text, this.getWidth()/2-100, this.getHeight()/2-100);
		g.setFont(ti);
		g.drawString(ime, 650, 600);
	}

}
