

import java.io.Serializable;

import javax.swing.*;

import java.awt.*;

import javax.swing.event.*;


public class HomePage  extends CenterPanel  implements Serializable{
		
	public ImageIcon logoimg;
	
	
	
	public HomePage(String name){
		super(name);
	}
	
	public void setPanel(){
		this.name = "HomePage";
		jp = new JPanel();
		
		jp.setBackground(Color.white);
		
		
		JLabel logo = new JLabel(logoimg);
		jp.setLayout(null);
		jp.add(logo);
		logo.setLocation(50,0);
		logo.setSize(logoimg.getIconWidth(),logoimg.getIconHeight());
		
		JLabel hellolb = new JLabel(username+"¥‘ æ»≥Á«œººø‰.");
		jp.add(hellolb);
		hellolb.setSize(300, 50);
		hellolb.setLocation(50,200);
		
		
	}
}
