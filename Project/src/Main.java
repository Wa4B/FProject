import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class Main {
	
	
	
	public static void main(String[] args){
		
		
		String arg[] = {"상록도서관","192.168.0.159"};//도서관 이름,PC번호,ip주소
		if(arg.length != 2){	
			System.out.println("사용법 : java ChatClient id 접속할서버ip");
			System.exit(1);
		}	
		
		
		GUI gui = new GUI();
		
		Socket sock = null;	
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		boolean endflag = false;	
		try{	
			sock = new Socket(arg[1], 10001);	
			// 사용자의 id를 전송한다.		
				
			
			oos = new ObjectOutputStream(sock.getOutputStream());
			ois = new ObjectInputStream(sock.getInputStream());
			
			Object ob = arg[0];
			oos.writeObject(ob);
			oos.flush();
			
			InputThread it = new InputThread(sock, gui,oos,ois);		
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
					cp.setOos(oos);
					cp.setOis(ois);
					cp.setSock(sock);
					cp.setPanel();
					gui.setCenter(cp);
					System.out.println(cp.getName());
				}
				
				if(ob instanceof String){
					String str = (String)ob;
					if(str.indexOf("/sign")==0){
						sign(str);
					}
					if(str.indexOf("/pop")==0){
						popUp(str);
					}
				}
				if(ob instanceof String[][]){
					String[][] data = (String[][])ob;
					gui.setData(data);
					System.out.println("dd");
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
	public void sign(String line){
		String res = line.substring(6);
		
		if(res.equals("com")){
			JPanel wmp2 = new JPanel();
			wmp2.add(new JLabel("회원가입이 완료되었습니다."));
	
			JDialog wm2 = new JDialog();
			wm2.setTitle("가입완료");
			wm2.add(wmp2);
			wm2.setSize(200, 100);
			wm2.setLocation(500, 500);
			wm2.setModal(true);
			wm2.setVisible(true);
		
		
		}else{
			JPanel wmp2 = new JPanel();
			wmp2.add(new JLabel("ID가 중복됩니다.."));

			JDialog wm2 = new JDialog();
			wm2.setTitle("ID 중복");
			wm2.add(wmp2);
			wm2.setSize(200, 100);
			wm2.setLocation(500, 500);
			wm2.setModal(true);
			wm2.setVisible(true);
		}
	}
	public void popUp(String line){
		String pop = line.substring(7);
		int index = pop.indexOf("/");
		String title = pop.substring(0,index);
		String str = pop.substring(index+1);
		JDialog popup = new JDialog();
		
		popup.setTitle(title);
		popup.setResizable(false);
		
		popup.getContentPane().setLayout(null);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setText(str);
		textArea.setLineWrap(true);
		textArea.setBackground(SystemColor.menu);
		textArea.setBounds(31, 23, 222, 61);
		popup.getContentPane().add(textArea);
		
		JButton okb = new JButton("확인");
		okb.setBounds(94, 109, 97, 23);
		
		okb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				popup.dispose();
			}
		});
		popup.getContentPane().add(okb);
		popup.setSize(300, 180);
		popup.setLocation(400, 400);
		popup.setVisible(true);
		popup.setModal(true);
	}
}					