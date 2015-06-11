import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.*;


public class GUI {
	
	private MainFrame mf;
	GUI(){
		
		mf =new MainFrame();
	}
	public void setCenter(CenterPanel jp){
		mf.add(jp,BorderLayout.CENTER);
	}
	
}

class MainFrame extends JFrame{
	private JToolBar tool;
	MainFrame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		this.setLocation(500, 500);
		this.setLayout(new BorderLayout());
		
	}
	
}