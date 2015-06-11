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

import javax.swing.JFrame;

import page.CenterPanel;


public class LibraryServer {
	
	
	
	public static void main(String[] args) {			
		
		JFrame serverf = new JFrame();
		serverf.setVisible(true);
		serverf.setDefaultCloseOperation(serverf.EXIT_ON_CLOSE);
		serverf.setSize(300, 400);
		serverf.setLocation(600, 400);
		
		
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
	private ObjectInputStream ois;
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
			this.oos = oos;
			this.ois = ois;
			id = (String)ois.readObject();
			
			libname = id;
			//broadcast(strtime+" "+id + "님이 접속하였습니다.");	
			System.out.println("접속한 사용자의 아이디는 " + id + "입니다.");	
			
			
			sandPanel(oos,"MainPage");
			
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
			Object ob = null;	
			while((ob = ois.readObject()) != null){	
				if(ob instanceof String){
					String line = (String)ob;
					
					if(line.indexOf("/login") == 0){
						login(oos,line);
					}
					if(line.indexOf("/sign") == 0){	
						sign(oos,line);
					}if(line.equals("/list")){
							
					}
				}
				
				
			}		
		}catch(Exception ex){			
			System.out.println(ex);		
		}finally{			
			
			//broadcast(id + " 님이 접속 종료하였습니다.");		
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
	
	
	public void sandPanel(ObjectOutputStream oos, String name){
		
		try {
			int indexnum = 0;
			for(int i = 0; i< cplist.size(); i+=1){
				if(cplist.get(i).getName().equals(name)){
					indexnum = i;
					break;
				}
			}
			oos.writeObject(cplist.get(indexnum));
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void login(ObjectOutputStream oos, String line){
		
	}
	public void sign(ObjectOutputStream oos, String line){
		String ID;
		String PW;
		String username;
		int userbirt;
		String power;
		String[] list = {};
		
		int[] set = new int[5];
		
		for(int i = 0 ; i< set.length ; i += 1){
			if(i == 0){
				set[0] = line.indexOf(" ")+ 1;
			}else{
				set[i] = line.indexOf(" ",set[i-1])+1;
			}
			
		}
		ID = line.substring(set[0], set[1]-1);
		PW = line.substring(set[1]+1, set[2]-1);
		username =line.substring(set[2]+1, set[3]-1);
		userbirt = Integer.parseInt(line.substring(set[3]+1, set[4]-1));
		power = line.substring(set[4]+1);
		
		boolean idcheck = false;
		for(int i = 0 ; i < user.size() ; i += 1){
			if(user.get(i).getID().equals(username)){
				idcheck = true;
				break;
			}
		}
		if(idcheck == false){
			user.add(new User(ID,PW,username,userbirt,list,power));
			System.out.println(user.get(0).getname()+"가입");
			try {
				oos.writeObject("/sign com"); //ID 중복되지 않음, 가입 승인
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
			}
		
			dm.SaveUser();
			dm.OpenUser();
		}else{
			try {
				oos.writeObject("/sign idw"); // ID 중복됨, 가입 불가.
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}				
