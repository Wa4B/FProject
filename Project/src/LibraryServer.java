import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;


public class LibraryServer {
	
	
	
	public static void main(String[] args) {			
		
		
		
		
		try{		
			ServerSocket server = new ServerSocket(10001);	
			System.out.println("접속을 기다립니다.");	
			
			
			ArrayList<Book> book = new ArrayList<Book>();
			ArrayList<User> user = new ArrayList<User>();	
			ArrayList<Library> library = new ArrayList<Library>();	
			ArrayList<CenterPanel> cplist = new ArrayList<CenterPanel>();	
			
			DataManager dm = new DataManager(book,user,library,cplist);
			
			
			while(true){	
				Socket sock = server.accept();
				ChatThread chatthread = new ChatThread(sock, dm);
				chatthread.start();
				
			} // while	
		}catch(Exception e){	
			System.out.println(e);
		}	
	} // main
	
}


class ChatThread extends Thread{			

	private ObjectOutputStream oos;
	private Socket sock;		
	private String id;		
	private BufferedReader br;		

	private DataManager dm;
	private boolean initFlag = false;	
	
	private ArrayList<Book> book;
	private ArrayList<User> user;	
	private ArrayList<Library> library;	
	private ArrayList<CenterPanel> cplist;	
	
	private String libname ;
	
	
	public ChatThread(Socket sock, DataManager dm){	
		
		this.sock = sock;	
		this.dm = dm;
		this.book = dm.getbook();
		this.user = dm.getuser();
		this.library = dm.getlibrary();
		this.cplist = dm.getcplist();
		
		
		
		try{	
			long time = System.currentTimeMillis();
			SimpleDateFormat dtime = new SimpleDateFormat("[hh시 mm분 ss초]");
			String strtime= dtime.format(new Date(time));
			
			
			
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			
			id = (String)ois.readObject();
			
			libname = id;
			broadcast(strtime+" "+id + "님이 접속하였습니다.");	
			System.out.println("접속한 사용자의 아이디는 " + id + "입니다.");	
			
			oos.writeObject(cplist.get(0));
			oos.flush();
			
			initFlag = true;	
		}catch(Exception ex){		
			System.out.println(ex);	
		}		
	} // 생성자			
	public void run(){			
		try{		
			long time = System.currentTimeMillis();
			SimpleDateFormat dtime = new SimpleDateFormat("[hh시 mm분 ss초]");
			String strtime= dtime.format(new Date(time));
			String line = null;	
			while((line = br.readLine()) != null){		
				if(line.equals("/quit"))	
					break;
				if(line.indexOf("/to ") == 0){	
					sendmsg(line);
				}if(line.equals("/list")){
						
				}
			}		
		}catch(Exception ex){			
			System.out.println(ex);		
		}finally{			
			
			broadcast(id + " 님이 접속 종료하였습니다.");		
			try{		
				if(sock != null)	
					sock.close();
			}catch(Exception ex){}		
		}			
	} // run				
	public void sendmsg(String msg){				
		int start = msg.indexOf(" ") +1;			
		int end = msg.indexOf(" ", start);			
		if(end != -1){			
			String to = msg.substring(start, end);		
			String msg2 = msg.substring(end+1);		
			
			
		}		
	} // sendmsg			
	public void broadcast(String msg){			
		
	} // broadcast		
	
	public void sandPanel(String name){
		
	}
}				
