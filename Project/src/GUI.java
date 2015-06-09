import java.util.ArrayList;

import javax.swing.*;


public class GUI {
	
	
	GUI(){
		
		MainFrame mf =new MainFrame();
	}
	
}

class MainFrame extends JFrame{
	private JToolBar tool;
	MainFrame(){
		this.setVisible(true);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(500, 400);
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.dispose();
	}
	
}