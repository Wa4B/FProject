

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;


public class HomePage  extends CenterPanel  implements Serializable{
		
	public ImageIcon logoimg;
	protected JTable jt;
	protected String[] tname = {"코드","제목","저자","출판사"};
	protected JTextField sc = new JTextField();
	protected JButton scb = new JButton("검색");
	protected JComboBox scf ;
	protected String[][] data2;
	protected JButton rantalb = new JButton("대여");
	protected JButton returnb = new JButton("반납");
	protected JButton addb = new JButton("추가");
	protected JButton removeb = new JButton("제거");
	protected JButton fixb = new JButton("수정");
	
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
			data2 = data.clone();
			Container co = new Container();
			JPanel jlp = new JPanel(new BorderLayout());
			JPanel scp = new  JPanel(null);
			JPanel bp = new JPanel(new GridLayout(1,5));
			bp.add(returnb);
			bp.add(rantalb);
			bp.add(addb);
			bp.add(fixb);
			bp.add(removeb);
			
			jlp.add(bp,BorderLayout.SOUTH);
			
			jt = new JTable(); // 도서 데이터.
			jt.setModel(new DefaultTableModel(data2,tname));
			jlp.add(scp,BorderLayout.NORTH);
			jlp.add(new JScrollPane(jt),BorderLayout.CENTER);
			jp.add(jlp);
			
			String ft[] = {"전체","제목","저자","출판사"};
			scf = new JComboBox(ft);
			scp.setPreferredSize(new Dimension(600,20));
			scp.add(sc);
			sc.setSize(400, 20);
			sc.setLocation(0, 0);
			scp.add(scb);
			scb.setSize(100, 20);
			scb.setLocation(400, 0);
			scp.add(scf);
			scf.setSize(100, 20);
			scf.setLocation(500, 0);
			
			jt.setCellSelectionEnabled(false);
			jt.setEnabled(false);
			
			jlp.setSize(600, 345);
			jlp.setLocation(200, 100);
			jp.updateUI();
			
			
		}else{
			try {
				
				oos.writeObject("/home getdata 0");
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}//setpanel
	
	public void setAction(){
		sc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println(sc.getText());
            	System.out.println(scf.getSelectedItem().toString());
            	
            	String mail = "/home scdata/"+scf.getSelectedItem().toString()+"/"+sc.getText();
            	try {
					oos.writeObject(mail);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		});
		scb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String mail = "/home scdata/"+scf.getSelectedItem().toString()+"/"+sc.getText();
            	try {
					oos.writeObject(mail);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		});
		removeb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	JTextField jtf = new JTextField();
            	JButton okb = new JButton("확인");
            	JLabel texts = new JLabel("제거할 도서의 코드를 입력해 주세요.");
            	
            	
            }
		});
	}
}
