package Grafika;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class unos extends JFrame {

	JDialog jd;
	JComboBox<String> jedinice;
	JTextField vrijednost;
	public unos(String value,String type){
		
		JSlider slide = new JSlider();
		jd = new JDialog(this,"Unos vrijednosti",true);
		jd.setLocation(new Point(500,200));
		jd.setLayout(new FlowLayout());
		String [] j = {"","k"};
		String []  j2 = {"V"};
		JTextField ohm = new JTextField(1);
		ohm.setEditable(false);
		ohm.setBorder(null);
		
		if(type=="otpor"){
			slide.setMaximum(10000);
			slide.setValue(Integer.parseInt(value));
			ohm.setText("\u2126");
			jedinice = new JComboBox<String>(j);
			jedinice.setSelectedIndex(0);}
		else{
			slide.setMaximum(500);
			ohm.setText("V");
			jedinice = new JComboBox<String>(j2);
			jedinice.setSelectedIndex(0);
			
		}
		
		vrijednost = new JTextField(5);
		if(value!="0") vrijednost.setText(value);
		
		JButton save = new JButton("OK");
		save.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(type=="otpor")
					returnOtpor();
				else
					returnVoltage();
			}
			
		});
		
		slide.addChangeListener(new ChangeListener(){

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if(!source.getValueIsAdjusting()){
					vrijednost.setText(Integer.toString(source.getValue()));
				}
				
			}
			
			
		});
		jd.add(vrijednost);
		jd.add(jedinice);
		jd.add(ohm);
		jd.add(save);
		jd.add(slide);
		jd.setSize(400,400);
		jd.setVisible(true);
		
	}


	protected String returnVoltage() {
		setVisible(false);
		dispose();
		return vrijednost.getText();
		
	}

	protected String returnOtpor() {
		long otpor = Integer.parseInt(vrijednost.getText());
		int index = jedinice.getSelectedIndex();
		switch(index){
		case 0: break;
		case 1: otpor*=Math.pow(10.00,3.00);break;
		}
		setVisible(false);
		dispose();
		return Long.toString(otpor);
		
	}
	
	
}
