package page;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainPage extends CenterPanel{
	
	private JButton[] loginbt = new JButton[2];
	private JTextField[] login = new JTextField[2];

	public MainPage(){
		this.name = "MainPage";
		
		jp = new JPanel();
		
		
		jp.setBackground(Color.WHITE);
		jp.setLayout(new GridLayout(4,1,0,0));
		
		JLabel title = new JLabel("도서관 종합 프로그램");
		

		jp.add(title);
		
		String[] logtx = {"ID","PW"};
		
		for(int i = 0; i < 2; i +=1){
			JPanel lbox = new JPanel();
			lbox.setLayout(new GridLayout(1,2));
			JLabel jl = new JLabel(logtx[i]);
			login[i] = new JTextField();
			lbox.add(jl);
			lbox.add(login[i]);
			jp.add(lbox);
		}
		loginbt[0] = new JButton("로그인");
		loginbt[1] = new JButton("회원가입");
		JPanel lbbox = new JPanel(new GridLayout(1,2));
		lbbox.add(loginbt[0]);
		lbbox.add(loginbt[1]);
		jp.add(lbbox);
		
		this.jp = jp;
	}
	
	public void setAction(){
		loginbt[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				System.out.println(2);
			}
		});
		loginbt[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				
				String[] texts = {"ID","PW","이름","생년월일","권한"};
				JButton signb = new JButton("회원가입");
				JTextField[] signt = new JTextField[6];
				JPanel signp = new JPanel(new GridLayout(6,1));
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
						
					}
				});
				
				JDialog sign = new JDialog();
				sign.setTitle("회원 가입");
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
