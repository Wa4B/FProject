import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Main {
	
	private ArrayList<Book> book;
	private ArrayList<User> user;
	private ArrayList<Library> library;
	
	
	public static void main(String[] args){
		
		DataManager dm = new DataManager();
		
		String arg[] = {"상록도서관","192.168.0.159"};//도서관 이름, ip주소
		if(arg.length != 2){	
			System.out.println("사용법 : java ChatClient id 접속할서버ip");
			System.exit(1);
		}	
		
		
		GUI gui = new GUI();
		
		Socket sock = null;	
		BufferedReader br = null;	
		PrintWriter pw = null;	
		boolean endflag = false;	
		try{	
			sock = new Socket(arg[1], 10001);
			pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));		
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));		
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));		
			// 사용자의 id를 전송한다.		
			pw.println(arg[0]);		
			pw.flush();		
			InputThread it = new InputThread(sock, br, gui);		
			it.start();		
			//cg.inputst(pw);
			String line = null;		
			while(true){		
				
			}		
			//System.out.println("클라이언트의 접속을 종료합니다.");		
		}catch(Exception ex){			
			if(!endflag)		
				System.out.println(ex);	
		}finally{			
			try{		
				if(pw != null)	
					pw.close();
			}catch(Exception ex){}		
			try{		
				if(br != null)	
					br.close();
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
	private BufferedReader br = null;	
	public GUI gui;
	public InputThread(Socket sock, BufferedReader br,GUI gui){				
		this.sock = sock;			
		this.br = br;			
		this.gui = gui;
	}				
	public void run(){				
		try{			
			String line = null;		
			while((line = br.readLine()) != null){		
				if(line.equals("/listreset")){
					
				}else if(line.indexOf("/addlist")==0){
					int start = line.indexOf(' ');
					String msg = line.substring(start);
					
				}else if(line.equals("/slistreset")){
					
				}else if(line.indexOf("/addslist")==0){
					int start = line.indexOf(' ');
					
					String msg = line.substring(start);
					
				}else{
					
				}
				
				
				
			}		
		}catch(Exception ex){			
		}finally{			
			try{		
				if(br != null)	
					br.close();
			}catch(Exception ex){}		
			try{		
				if(sock != null)	
					sock.close();
			}catch(Exception ex){}		
		}			
	} // InputThread				
}					