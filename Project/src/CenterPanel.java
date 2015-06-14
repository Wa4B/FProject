
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.*;


public class CenterPanel implements Serializable{
	protected String name;
	protected ObjectOutputStream oos;
	protected ObjectInputStream ois;
	protected Socket sock;
	protected JPanel jp = new JPanel();
	protected ActionListener[] buttonaction;
	protected JButton[] abutton;
	
	protected User userinfo;
	protected String data[][] = {};
	
	public CenterPanel(String name){
		this.name = name;
	}
	public CenterPanel(){
		
		
		
	}
	
	public void setdata(String[][] data){
		this.data = data.clone();
	}
	
	public void updateui(){
		jp.updateUI();
	}
	public void setUser(User userinfo){
		this.userinfo = userinfo;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setPanel(){
		
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
	
	public void setOos (ObjectOutputStream oos){
		this.oos= oos;
	}
	public void setOis (ObjectInputStream ois){
		this.ois= ois;
	}
	public void setSock(Socket sock){
		this.sock = sock;
	}
	public String getName(){
		return name;
	}
	
	public void setAction(){
		
	}
}