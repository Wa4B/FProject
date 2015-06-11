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
	}
}
