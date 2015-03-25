package Grafika;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class unos extends JFrame {

	JDialog jd;
	JComboBox jedinice;
	JTextField vrijednost;
	public unos(String value,String type){
		
		jd = new JDialog(this,"Unos vrijednosti",true);
		jd.setLocation(new Point(500,200));
		jd.setLayout(new FlowLayout());
		String [] j = {"p","n","\u00B5","m","","k","M","G","T"};
		String []  j2 = {"V"};
		JTextField ohm = new JTextField(1);
		ohm.setEditable(false);
		ohm.setBorder(null);
		if(type=="otpor"){
		ohm.setText("\u2126");
		jedinice = new JComboBox(j);
		jedinice.setSelectedIndex(4);}
		else{
			ohm.setText("V");
			jedinice = new JComboBox(j2);
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
		jd.add(vrijednost);
		jd.add(jedinice);
		jd.add(ohm);
		jd.add(save);
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
		case 0: otpor*=Math.pow(10.00,-12.00); break;
		case 1: otpor*=Math.pow(10.00,-9.00);break;
		case 2: otpor*=Math.pow(10.00,-6.00);break;
		case 3: otpor*=Math.pow(10.00,-3);break;
		case 4: break;
		case 5: otpor*=Math.pow(10.00,3.00);break;
		case 6: otpor*=Math.pow(10.00,6.00);break;
		case 7: otpor*=Math.pow(10.00,9.00);break;
		case 8: otpor*=Math.pow(10.00,12.00); break;
		}
		System.out.println(otpor);
		setVisible(false);
		dispose();
		return Long.toString(otpor);
		
	}

}
