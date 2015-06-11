import java.io.Serializable;
import java.net.Socket;

import javax.swing.JPanel;


public class CenterPanel extends JPanel implements Serializable{
	
	private String name;
	private Socket sock;
	
	CenterPanel(String name){
		
		this.name = name;
		
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	void setSocket (Socket sock){
		this.sock =sock;
	}
	
	public String getName(){
		return name;
	}
}