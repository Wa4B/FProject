import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;


public class PanelMaker {
	
	public ArrayList<CenterPanel> jplist = new ArrayList<CenterPanel>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PanelMaker pm = new PanelMaker();
		pm.start();
	}
	public void start(){
		mainpage();
		SaveUser();
	}
	
	public void mainpage(){
		CenterPanel jp = new CenterPanel("mainpage");
		JFrame jf = new JFrame();
		
		jp.setBackground(Color.PINK);
		
		JButton bt = new JButton("버튼");
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JFrame jf2 = new JFrame();
				jf2.setVisible(true);
				
			}
		});
		jp.add(bt);
		jf.add(jp);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		
		jplist.add(jp);
		
	}
	
	public void SaveUser(){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		
		
		
		 
		try{
			fout = new FileOutputStream("jplist.dat");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(jplist);//파일 입력의 법칙에 따라 두번 한 것임.
			oos.reset();
			oos.writeObject(jplist);
			oos.reset();
			
			System.out.println("jplist.dat save.");
			
		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
		} // finally
	}
}
