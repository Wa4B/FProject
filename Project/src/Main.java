import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JPanel;


public class Main {
	
	private ArrayList<Book> book;
	private ArrayList<User> user;
	private ArrayList<Library> library;
	
	
	public static void main(String[] args){
		
		
		String arg[] = {"��ϵ�����","PC01","192.168.123.100"};//������ �̸�,PC��ȣ,ip�ּ�
		if(arg.length != 3){	
			System.out.println("���� : java ChatClient id �����Ҽ���ip");
			System.exit(1);
		}	
		
		
		GUI gui = new GUI();
		
		Socket sock = null;	
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean endflag = false;	
		try{	
			sock = new Socket(arg[2], 10001);	
			// ������� id�� �����Ѵ�.		
				
			
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
			
			Object ob = arg[1];
			oos.writeObject(ob);
			oos.flush();
			
			InputThread it = new InputThread(sock, gui,oos,ois);		
			it.start();		
			//cg.inputst(pw);
			String line = null;		
			while(true){		
				
			}		
			//System.out.println("Ŭ���̾�Ʈ�� ������ �����մϴ�.");		
		}catch(Exception ex){			
			if(!endflag)		
				System.out.println(ex);	
		}finally{			
			try{		
				if(oos != null)	
					oos.close();
			}catch(Exception ex){}		
			try{		
				if(ois != null)	
					ois.close();
			}catch(Exception ex){}		
			try{		
				if(sock != null)	
					sock.close();
			}catch(Exception ex){}		
		} // finally			
		
	} // main				
} // class					
					
class InputThread extends Thread{					
	private Socket sock = null;				
	
	private ObjectOutputStream oos = null;
	private ObjectInputStream ois = null;
	private GUI gui;
	
	
	public InputThread(Socket sock, GUI gui,ObjectOutputStream oos,ObjectInputStream ois){				
		this.sock = sock;			
		this.ois = ois;
		this.oos = oos;
		this.gui = gui;
	}				
	public void run(){				
		try{			
			Object ob = null;		
			while((ob = ois.readObject()) != null){		
				if(ob instanceof CenterPanel){
					CenterPanel cp = (CenterPanel)ob;
					
					gui.setCenter(cp);
					System.out.println(cp.getName());
				}
				
			}		
		}catch(Exception ex){			
		}finally{			
			try{		
				if(ois != null)	
					ois.close();
			}catch(Exception ex){}		
			try{		
				if(sock != null)	
					sock.close();
			}catch(Exception ex){}		
		}			
	} // InputThread				
}					