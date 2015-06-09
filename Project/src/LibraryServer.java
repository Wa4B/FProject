import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


public class LibraryServer {
	public static void main(String[] args) {			
		
		DataManager dm = new DataManager();
		
		
		try{		
			ServerSocket server = new ServerSocket(10001);	
			System.out.println("접속을 기다립니다.");	
			HashMap hm = new HashMap();	
			
			while(true){	
				Socket sock = server.accept();
				ChatThread chatthread = new ChatThread(sock, hm,dm);
				chatthread.start();
			} // while	
		}catch(Exception e){	
			System.out.println(e);
		}	
	} // main
}


class ChatThread extends Thread{			
	private Socket sock;		
	private String id;		
	private BufferedReader br;		
	private HashMap hm;		
	private DataManager dm;
	private boolean initFlag = false;	
	
	public ChatThread(Socket sock, HashMap hm,DataManager dm){	
		
		this.sock = sock;	
		this.hm = hm;	
		this.dm = dm;
		
		try{	
			long time = System.currentTimeMillis();
			SimpleDateFormat dtime = new SimpleDateFormat("[hh시 mm분 ss초]");
			String strtime= dtime.format(new Date(time));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));	
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));	
			id = br.readLine();	
			broadcast(strtime+" "+id + "님이 접속하였습니다.");	
			System.out.println("접속한 사용자의 아이디는 " + id + "입니다.");	
			synchronized(hm){	
				hm.put(this.id, pw);
			}	
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
					synchronized(hm){		
						Collection collection = hm.keySet();	
						Iterator iter = collection.iterator();	
						String list = "/list [서버] 현제 접속\n";
						while(iter.hasNext()){	
							list += iter.next()+"\n";
							
							
						}	
						Object obj = hm.get(id);		
						if(obj != null){		
							PrintWriter pw = (PrintWriter)obj;	
							pw.println(list);	
							pw.flush();	
						} // if	
						
					}		
				}
			}		
		}catch(Exception ex){			
			System.out.println(ex);		
		}finally{			
			synchronized(hm){		
				hm.remove(id);	
			}		
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
			Object obj = hm.get(to);		
			if(obj != null){		
				PrintWriter pw = (PrintWriter)obj;	
				pw.println(id + " 님이 다음의 귓속말을 보내셨습니다. :" + msg2);	
				pw.flush();	
			} // if	
		}		
	} // sendmsg			
	public void broadcast(String msg){			
		synchronized(hm){		
			Collection collection = hm.values();	
			Iterator iter = collection.iterator();	
			while(iter.hasNext()){	
				PrintWriter pw = (PrintWriter)iter.next();
				pw.println(msg);
				pw.flush();
				
			}	
		}		
	} // broadcast		
	
	
}				
