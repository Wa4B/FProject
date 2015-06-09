import java.io.Serializable;

import javax.swing.JPanel;


public class CenterPanel implements Serializable{
	private JPanel jp;
	private String name;
	
	CenterPanel(JPanel jp, String name){
		this.jp = jp;
		this.name = name;
	}
	
	JPanel getPanel(){
		return jp;
	}
	
	String getString(){
		return name;
	}
}