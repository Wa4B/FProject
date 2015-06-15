
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
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
	
	public void popup(String title, String line){
		JDialog popup = new JDialog();
		popup.setTitle(title);
		popup.setResizable(false);
		
		popup.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(line);
		textArea.setLineWrap(true);
		
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(31, 23, 222, 61);
		popup.getContentPane().add(textArea);
		
		JButton okb = new JButton("»Æ¿Œ");
		okb.setBounds(94, 109, 97, 23);
		
		okb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				popup.dispose();
			}
		});
		popup.getContentPane().add(okb);
		popup.setSize(300, 180);
		popup.setLocation(400, 400);
		popup.setVisible(true);
		popup.setModal(true);
	}
}