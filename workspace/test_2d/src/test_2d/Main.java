package test_2d;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class Surface extends JPanel {
	private int x,y;
	private List<Shape> grid;
	public Surface(){InitSurface();};
		
	private void InitSurface() {
		this.addMouseListener(new HitTest());
		grid = new ArrayList<>(5);
	}
	
	public void paintComponent(Graphics g){
		 
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for(Shape obj : grid){
			g2d.fill(obj);
			
		}
		
	}

	
	class HitTest implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			Rectangle r = new Rectangle(x,y,50,50);
			grid.add(r);
			repaint();
			

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e ) {
			// TODO Auto-generated method stub

		}

	}



}


public class Main extends JFrame {
	
public Main(){
		
	setTitle("test");
	add(new Surface());
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(400,300);
	setLocationRelativeTo(null);
	
};
	
	public static void main(String[] args){
		
		SwingUtilities.invokeLater(new Runnable(){
			
			public void run(){
				
				Main m = new Main();
				m.setVisible(true);
				
			}
			
		});
		
		
	};
}
