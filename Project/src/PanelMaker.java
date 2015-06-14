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
		MainPage mp = new MainPage("MainPage");
		mp.titlelogo = new ImageIcon("img/logo2.JPG");
		HomePage hp = new HomePage("HomePage");
		hp.logoimg = new ImageIcon("img/logo3.JPG");
		
		jplist.add(mp);
		jplist.add(hp);
		
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
			
			oos.flush();
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
