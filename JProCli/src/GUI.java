import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import page.CenterPanel;


public class GUI {
	
	private MainFrame mf;
	GUI(){
		
		mf =new MainFrame();
	}
	public void setCenter(CenterPanel jp){
		jp.setAction();
		mf.change(jp.getPanel());
		mf.setTitle(jp.getName());
	}
	
}

class MainFrame extends JFrame{
	private JToolBar tool;
	private JScrollPane scp = new JScrollPane();
	private JPanel outbox = new JPanel(); // 胶农费 观
	private JPanel inbox = new JPanel(); // 胶农费 救

	private JPanel center = new JPanel();
	MainFrame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocation(500, 500);
		this.setLayout(new GridLayout(1,1));
		
		this.add(this.inbox);
		this.inbox.setLayout(new BorderLayout());
		this.inbox.add(center,BorderLayout.CENTER);
		
	}
	
	public void change(JPanel center){
		this.inbox.remove(this.center);
		this.center =center;
		this.inbox.add(this.center, BorderLayout.CENTER);
		this.inbox.updateUI();
		
	}
}