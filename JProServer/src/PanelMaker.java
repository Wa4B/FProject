import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;

import page.CenterPanel;
import page.MainPage;


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
		
		jplist.add(new MainPage());
				
	}
	
	public void SaveUser(){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		
		
		
		 
		try{
			fout = new FileOutputStream("jplist.dat");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(jplist);//���� �Է��� ��Ģ�� ���� �ι� �� ����.
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