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
		
	private BufferedReader br;		

	private DataManager dm;
	private boolean initFlag = false;	
	
	private ArrayList<Book> book;
	private ArrayList<User> user;	
	private ArrayList<Library> library;	
	private ArrayList<CenterPanel> cplist;	
	
	private String libname ;
	private User userinfo;
	
	
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
			libname = (String)ois.readObject();

			//broadcast(strtime+" "+id + "님이 접속하였습니다.");	
			System.out.println(libname + "에서 접속");	
			
			
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
					}if(line.indexOf("/home")==0){
						home(oos,line);
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
	
	public void home(ObjectOutputStream oos, String line){
		String str = line.substring(6);
		System.out.println("dzs");
		if(str.indexOf("getdata")==0){
			int num = Integer.parseInt(str.substring(8));
			
			
			String data[][] = new String[20][4];
			for(int i = 0; i < 20; i+=1){
				if(book.size() < i){
					break;
				}else{
					
					data[i][0] = book.get(num*20 + i).getisbn();
					data[i][1] = book.get(num*20 + i).gettitle();
					data[i][2] = book.get(num*20 + i).getauthor();
					data[i][3] = book.get(num*20 + i).getcom();
					
				}
			}
			try {
				oos.writeObject(data);
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void sandPanel(ObjectOutputStream oos, String name){
		
		try {
			int indexnum = -1;
			System.out.println(cplist.size());
			for(int i = 0; i< cplist.size(); i+=1){
				if(cplist.get(i).getName().equals(name)){
					indexnum = i;
					break;
				}
			}
			
			if(!name.equals("MainPage")){
				cplist.get(indexnum).setUser(userinfo);
			}
			if(indexnum != -1&&name.equals("HomPage")){
				String[] bookdat = new String[20];
				for(int i = 0; i < book.size();i+=1){
					bookdat[i] = book.get(i).gettitle();
				}
				
			}
			
			//oos.writeObject(cplist.get(indexnum));
			oos.writeObject(cplist.get(indexnum));
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void login(ObjectOutputStream oos, String line){
		String id;
		String pw;
		int[] set = new int[2];
		int userindex = -1;
		
		for(int i = 0 ; i< set.length ; i += 1){
			if(i == 0){
				set[0] = line.indexOf(" ");
			}else{
				set[i] = line.indexOf(" ",set[i-1]+1);
			}
		}
		id = line.substring(set[0]+1, set[1]);
		pw = line.substring(set[1]+1);
		
		for(int i = 0 ; i < user.size() ; i+= 1){
			if(user.get(i).getID().equals(id)){
				userindex = i;
				break;
			}
		}
		if(userindex == -1||!user.get(userindex).getPW().equals(pw)){
			try {
				oos.writeObject("/popup 로그인오류/ID 또는 비밀번호가 틀렸습니다.");
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// catch
		}else{
			
			this.userinfo = user.get(userindex);
			sandPanel(oos,"HomePage");
		}
		
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
				set[0] = line.indexOf(" ");
			}else{
				set[i] = line.indexOf(" ",set[i-1]+1);
			}
		}
		
		ID = line.substring(set[0]+1, set[1]);
		PW = line.substring(set[1]+1, set[2]);
		username =line.substring(set[2]+1, set[3]);
		userbirt = Integer.parseInt(line.substring(set[3]+1, set[4]));
		if(set[4] != line.length()-1){
			power = line.substring(set[4]+1);
		}else{
			power = "null";
		}
		boolean idcheck = false;
		if(user.size() !=0){
			for(int i = 0 ; i < user.size() ; i += 1){
				if(user.get(i).getID().equals(ID)){
					System.out.println(user.get(i).getID());
					idcheck = true;
					break;
				}
			}
		}
		System.out.println(idcheck);
		if(idcheck == false){
			user.add(new User(ID,PW,username,userbirt,list,power));
			System.out.println(user.get(0).getID()+"가입");
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
