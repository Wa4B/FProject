

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.swing.*;

public class MainPage extends CenterPanel  implements Serializable{
	
	private JButton[] loginbt = new JButton[2];
	private JTextField[] login = new JTextField[2];
	public ImageIcon titlelogo;
	
	public MainPage(String name){
		super(name);
		
	}
	public static void main(String arg[]){
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setSize(900, 800);
		jf.setLocation(500, 200);
		jf.setLayout(new GridLayout(1,1));
		jf.setResizable(false);
		
		MainPage mp = new MainPage();
		mp.setPanel();
		mp.setAction();
		jf.add(mp.getPanel());
		
		
	}
	
	public MainPage(){
		
	}
	
	
	public void setPanel(){
		this.name = "MainPage";
		
		
		jp = new JPanel();

		jp.setBackground(Color.WHITE);
		jp.setLayout(null);
		
		
		JLabel title = new JLabel(titlelogo);
		jp.add(title);
		title.setLocation(450-titlelogo.getIconWidth()/2, 50);
		title.setSize(titlelogo.getIconWidth(),titlelogo.getIconHeight());
		
		JPanel logp = new JPanel();
		jp.add(logp);
		logp.setBackground(Color.white);
		logp.setSize(300, 100);
		logp.setLocation(450 - logp.getWidth()/2, 350);
		logp.setLayout(new GridLayout(3,1,0,0));
		
		loginbt[0] = new JButton("�α���");
		loginbt[1] = new JButton("ȸ������");
		String[] logtx = {"ID","PW"};

		for(int i = 0; i < 2; i +=1){
			JPanel lbox = new JPanel();
			lbox.setLayout(new GridLayout(1,2));
			JLabel jl = new JLabel(logtx[i]);
			jl.setHorizontalAlignment(jl.CENTER);
			lbox.add(jl);
			login[i] = new JTextField();
			lbox.add(login[i]);

			logp.add(lbox);
		}
		
		JPanel lbbox = new JPanel(new GridLayout(1,2));
		lbbox.add(loginbt[0]);
		lbbox.add(loginbt[1]);
		logp.add(lbbox);
		logp.updateUI();
		
		
	}
	
	public void setAction(){
		loginbt[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(login[0].getText().equals("")||login[1].getText().equals("")){
					JDialog wm = new JDialog();
					wm.setTitle("�Է� ����");
					JLabel text = new JLabel("�Է¿���, ����� �Է��� �ּ���.");
					wm.add(text);
					wm.setSize(200, 100);
					wm.setLocation(500, 500);
					wm.setResizable(false);
					wm.setModal(true);
					wm.setVisible(true);
				}else{
					String logtext ="/login " + login[0].getText()+" "+login[1].getText();
					System.out.println(logtext);
					try {
						oos.writeObject(logtext);
						oos.flush();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		loginbt[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				
				String[] texts = {"ID","PW","�̸�","�������","����"};
				JButton signb = new JButton("ȸ������");
				JTextField[] signt = new JTextField[6];
				JPanel signp = new JPanel(new GridLayout(6,1));
				signp.setBackground(Color.WHITE);
				JComboBox[] birtbox = new JComboBox[3];
				
				for(int i = 0 ; i < 6 ; i +=1){
					JPanel jp = new JPanel();
					if(i ==3 ){
						jp.setLayout(new GridLayout(1,4));
						
						String[] year = new String[100];
						String[] mon = new String[12];
						String[] day = new String[31];
						for(int j = 0; j < year.length ; j +=1){
							year[j] = Integer.toString(1916 + j);
						}
						for(int j = 0 ; j < 12 ; j += 1){
							mon[j] =  Integer.toString(1+ j);
						}
						for(int j = 0; j < day.length ; j+= 1){
							day[j] =  Integer.toString(1+j);
						}
						birtbox[0] = new JComboBox(year);
						birtbox[1]  = new JComboBox(mon);
						birtbox[2]  = new JComboBox(day);
						
						jp.add(new JLabel(texts[i]));
						jp.add(birtbox[0]);
						jp.add(birtbox[1]);
						jp.add(birtbox[2]);
						birtbox[1].addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e){
								if(Integer.parseInt((String) birtbox[1].getSelectedItem())==2){
									String[] day = new String[28];
									for(int j = 0; j < day.length ; j+= 1){
										day[j] =  Integer.toString(1+j);
									}
									
									birtbox[2] = new JComboBox(day);
									birtbox[2].updateUI();
									jp.updateUI();
								}
							}
						});
						signp.add(jp);
					}else if(i == 4){
						jp.setLayout(new GridLayout(1,2));
						jp.add(new JLabel(texts[4]));
						signt[i] = new JTextField();
						jp.add(signt[i]);
						signp.add(jp);
					}else if(i == 5){
						signp.add(signb);
					}else{
						jp.setLayout(new GridLayout(1,2));
						jp.add(new JLabel(texts[i]));
						signt[i] = new JTextField();
						jp.add(signt[i]);
						signp.add(jp);
					}
				}
				
				signb.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						System.out.println(10);
						String ID = signt[0].getText();
						String PW = signt[1].getText();
						String username = signt[2].getText();
						String userbirt = (String)birtbox[0].getSelectedItem()+birtbox[1].getSelectedItem()+birtbox[2].getSelectedItem();
						String power = signt[4].getText();
						String mail;
						if(power.equals(null)){
							power = "normal";
						}
						
						String allstr = ID+PW+username+userbirt+power;
						
						if(ID.equals("")||PW.equals("")||username.equals("")||userbirt.equals("")||allstr.indexOf(" ")!= -1){
							JPanel wmp = new JPanel();
							wmp.add(new JLabel("�߸��� �׸��� �ֽ��ϴ�."));
							System.out.println("wm");
							JDialog wm = new JDialog();
							wm.setTitle("�߸��� �Է�");
							wm.add(wmp);
							wm.setSize(200, 100);
							wm.setLocation(500, 500);
							wm.setModal(true);
							wm.setVisible(true);
						}else{
							
							mail = "/sign "+ID+" "+PW+" "+username+" "+userbirt+" "+power;
							try {
								System.out.println(mail);
								oos.writeObject(mail);
								oos.flush();
														
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				});
								
				JDialog sign = new JDialog();
				sign.setTitle("ȸ�� ����");
				sign.add(signp);
				sign.setSize(300, 200);
				sign.setLocation(500, 500);
				sign.setResizable(false);
				sign.setModal(true);
				sign.setVisible(true);
				
			}
		});
		
	}
}
