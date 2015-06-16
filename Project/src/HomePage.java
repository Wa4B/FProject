import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class HomePage  extends CenterPanel  implements Serializable{
		
	public ImageIcon logoimg;
	protected JTable jt;
	protected String[] tname = {"�ڵ�","����","����","���ǻ�"};
	protected JTextField sc = new JTextField();
	protected JButton scb = new JButton("�˻�");
	protected JComboBox scf ;
	protected String[][] data2;
	protected JButton rantalb = new JButton("�뿩");
	protected JButton returnb = new JButton("�ݳ�");
	protected JButton addb = new JButton("�߰�");
	protected JButton removeb = new JButton("����");
	protected JButton fixb = new JButton("����");
	protected JButton logoutb= new JButton("�α׾ƿ�");
	
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
		
		jp.add(logoutb);
		logoutb.setSize(100, 50);
		logoutb.setLocation(50,300);
		
		
		if(data.length > 0){
			
			JCheckBox jcb = new JCheckBox();
			
			data2 = data.clone();
			Container co = new Container();
			JPanel jlp = new JPanel(new BorderLayout());
			JPanel scp = new  JPanel(null);
			JPanel bp = new JPanel(new GridLayout(1,5));
			//bp.add(returnb);
			//bp.add(rantalb);
			bp.add(fixb);
			bp.add(removeb);
			bp.add(addb);
			
			if(userinfo.getpow().equals("admin")){
				jlp.add(bp,BorderLayout.SOUTH);
			}
			
			
			
			
			DefaultTableModel jm = new DefaultTableModel(data2,tname){
	            
	            public boolean isCellEditable(int i, int c) {
	                return false;
	            	}
	            
			};
			
			jt = new JTable(jm); // ���� ������.
			jt.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			jlp.add(scp,BorderLayout.NORTH);
			jlp.add(new JScrollPane(jt),BorderLayout.CENTER);
			jp.add(jlp);
			
			String ft[] = {"��ü","����","����","���ǻ�","�ڵ�"};
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
			
			
			jt.setEnabled(true);
			
			
			
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
					oos.flush();
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
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		});
		removeb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int tindex = jt.getSelectedRow();
            	if(tindex != -1){
            		String isbns = data[tindex][0];
            	
            		String mail = "/remove "+isbns;
            		System.out.println(isbns);
            	
            		try {
						oos.writeObject(mail);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
            	}else{
            		popup("����","������ ���̺��� �����Ͽ� �ּ���.");
            	}
            }
		});
		addb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AddBook ab = new AddBook();
            	ab.setUser(userinfo);
            	ab.setOos(oos);
            	ab.setAction();
            }
		});
		fixb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(jt.getSelectedRow() != -1){
            		FixBook fb = new FixBook(data[jt.getSelectedRow()]);
            		fb.setOos(oos);
            		fb.setAction();
            	}else{
            		popup("����", "������ ���̺��� ������.");
            	}
            }
		});
		logoutb.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					oos.writeObject("/logout");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
		});
	}
}