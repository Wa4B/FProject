

import java.io.IOException;
import java.io.Serializable;

import javax.swing.*;

import java.awt.*;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


public class HomePage  extends CenterPanel  implements Serializable{
		
	public ImageIcon logoimg;
	protected JTable jt;
	protected String[] tname = {"코드","제목","저자","출판사"};
	
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
		
		JLabel hellolb = new JLabel(userinfo.getname()+"님 안녕하세요.");
		jp.add(hellolb);
		hellolb.setSize(300, 50);
		hellolb.setLocation(50,200);
		
		
		if(data.length > 0){
			
			Container co = new Container();
			JPanel jlp = new JPanel(new BorderLayout());
			jt = new JTable(); // 도서 데이터.
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
