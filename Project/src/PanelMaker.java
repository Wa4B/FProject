import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JPanel;


public class PanelMaker {
	
	public ArrayList<CenterPanel> jplist = new ArrayList<CenterPanel>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PanelMaker pm = new PanelMaker();
		pm.start();
	}
	public void start(){
		pan1();
		SaveUser();
	}
	
	public void pan1(){
		JPanel jp = new JPanel();
		jp.setBackground(Color.PINK);
		jplist.add(new CenterPanel(jp,"pan1"));
		
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
