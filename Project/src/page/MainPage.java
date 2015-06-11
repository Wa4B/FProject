package page;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPage extends CenterPanel{
	
	protected JButton test;
	
	public MainPage(){
		this.name = "MainPage";
		
		jp = new JPanel();
		test = new JButton("test");
		
		jp.setBackground(Color.WHITE);
		jp.add(test);
		this.jp = jp;
	}
	
	public void setAction(){
		test.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.out.println(2);
			}
		});
	}
}
