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
			System.out.println("������ ��ٸ��ϴ�.");	
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
			SimpleDateFormat dtime = new SimpleDateFormat("[hh�� mm�� ss��]");
			String strtime= dtime.format(new Date(time));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(sock.getOutputStream()));	
			br = new BufferedReader(new InputStreamReader(sock.getInputStream()));	
			id = br.readLine();	
			broadcast(strtime+" "+id + "���� �����Ͽ����ϴ�.");	
			System.out.println("������ ������� ���̵�� " + id + "�Դϴ�.");	
			synchronized(hm){	
				hm.put(this.id, pw);
			}	
			initFlag = true;	
		}catch(Exception ex){		
			System.out.println(ex);	
		}		
	} // ������			
	public void run(){			
		try{		
			long time = System.currentTimeMillis();
			SimpleDateFormat dtime = new SimpleDateFormat("[hh�� mm�� ss��]");
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
						String list = "/list [����] ���� ����\n";
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
			broadcast(id + " ���� ���� �����Ͽ����ϴ�.");		
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
				pw.println(id + " ���� ������ �ӼӸ��� �����̽��ϴ�. :" + msg2);	
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
