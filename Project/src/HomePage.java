

import java.io.IOException;
import java.io.Serializable;

import javax.swing.*;

import java.awt.*;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


public class HomePage  extends CenterPanel  implements Serializable{
		
	public ImageIcon logoimg;
	protected JTable jt;
	protected String[] tname = {"�ڵ�","����","����","���ǻ�"};
	
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
		logo.setLocation(50,50);
		logo.setSize(logoimg.getIconWidth(),logoimg.getIconHeight());
		
		JLabel hellolb = new JLabel(userinfo.getname()+"�� �ȳ��ϼ���.");
		jp.add(hellolb);
		hellolb.setSize(300, 50);
		hellolb.setLocation(50,200);
		
		
		if(data.length > 0){
			
			Container co = new Container();
			JPanel jlp = new JPanel(new BorderLayout());
			jt = new JTable(); // ���� ������.
			jt.setModel(new DefaultTableModel(data,tname));
			
			jlp.add(new JScrollPane(jt),BorderLayout.CENTER);
			jp.add(jlp);
			
			jt.setCellSelectionEnabled(false);
			jt.setEnabled(false);
			
			jlp.setSize(600, 345);
			jlp.setLocation(200, 200);
			jp.updateUI();
			JFrame a = new JFrame();
			
		}else{
			try {
				
				oos.writeObject("/home getdata 0");
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}
}
