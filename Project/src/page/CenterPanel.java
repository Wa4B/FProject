package page;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;


public class CenterPanel implements Serializable{
	protected String name;
	protected Socket sock;
	protected JPanel jp;
	protected ActionListener[] buttonaction;
	protected JButton[] abutton;
	
	
	
	public void setName(String name){
		this.name = name;
	}
	public void setPanel(JPanel jp){
		this.jp= jp;
	}
	public void setbuttonaction(ActionListener[] aclist){
		this.buttonaction = aclist;
	}
	public void setbutton(JButton[] bt){
		this.abutton = bt;
	}
	
	
	public JPanel getPanel(){
		return jp;
	}
	
	public void setSocket (Socket sock){
		this.sock =sock;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAction(){
		
	}
}