

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataManager {
	
	private ArrayList<Book> book;
	private ArrayList<User> user;	
	private ArrayList<Library> library;	
	private ArrayList<CenterPanel> cplist;	
	DataManager(ArrayList<Book> book,ArrayList<User> user,ArrayList<Library> library,ArrayList<CenterPanel> cplist){
		this.book = book;
		this.user = user;
		this.library = library;
		this.cplist = cplist;
	}
	DataManager(){
		Allopen();
	}
	
	ArrayList<Book> getbook(){
		return book;
	}
	ArrayList<User> getuser(){
		return user;
	}
	ArrayList<Library> getlibrary(){
		return library;
	}
	
	
	public void Allopen(){
		OpenUser();
		OpenBook();
		OpenLibrary();
		OpenPanel();
	}
	public void Allsave(){
		SaveUser();
		SaveBook();
		SaveLibrary();
	}
	
	public void OpenUser(){
		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try{
			
			fin = new FileInputStream("user.dat");
			ois = new ObjectInputStream(fin);
			
			user.clear();
			
			ArrayList list = (ArrayList)ois.readObject();
			
			
			for(int i = 0 ; i < list.size() ; i+=1){
				user.add((User)list.get(i));
			}
			
			System.out.println("user.dat open.");
			
		}catch(Exception ex){
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		} // finally
	}
	
	
	public void OpenBook(){
		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try{
			
			fin = new FileInputStream("bookinfo.dat");
			ois = new ObjectInputStream(fin);
			
			book.clear();
			
			ArrayList list = (ArrayList)ois.readObject();
			
			
			for(int i = 0 ; i < list.size() ; i+=1){
				book.add((Book)list.get(i));
			}
			
			System.out.println("bookinfo.dat open.");
			
		}catch(Exception ex){
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		} // finally
	}
	
	
	public void OpenLibrary(){
		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try{
			
			fin = new FileInputStream("library.dat");
			ois = new ObjectInputStream(fin);
			
			library.clear();
			
			ArrayList list = (ArrayList)ois.readObject();
			
			
			for(int i = 0 ; i < list.size() ; i+=1){
				library.add((Library)list.get(i));
			}
			
			System.out.println("library.dat open.");
			
		}catch(Exception ex){
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		} // finally
	}	
	
	
	public void SaveUser(){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		
		
		
		 
		try{
			fout = new FileOutputStream("user.dat");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(user);//파일 입력의 법칙에 따라 두번 한 것임.
			oos.reset();
			oos.writeObject(user);
			oos.reset();
			
			System.out.println("user.dat save.");
			
		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
		} // finally
	}
	
	
	public void SaveBook(){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		
		
		
		 
		try{
			fout = new FileOutputStream("bookinfo.dat");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(book);//파일 입력의 법칙에 따라 두번 한 것임.
			oos.reset();
			oos.writeObject(book);
			oos.reset();
			
			System.out.println("bookinfo.dat save.");
			
		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
		} // finally
	}
	
	
	public void SaveLibrary(){
		FileOutputStream fout = null;
		ObjectOutputStream oos = null;
		
		
		
		
		 
		try{
			fout = new FileOutputStream("library.dat");
			oos = new ObjectOutputStream(fout);
			
			oos.writeObject(library);//파일 입력의 법칙에 따라 두번 한 것임.
			oos.reset();
			oos.writeObject(library);
			oos.reset();
			
			System.out.println("library.dat save.");
			
		}catch(Exception ex){
		}finally{
			try{
				oos.close();
				fout.close();
			}catch(IOException ioe){}
		} // finally
	}
	public void OpenPanel(){
		FileInputStream fin = null;
		ObjectInputStream ois = null;

		try{
			
			fin = new FileInputStream("jplist.dat");
			ois = new ObjectInputStream(fin);
			
			cplist.clear();
			
			ArrayList list = (ArrayList)ois.readObject();
			
			
			for(int i = 0 ; i < list.size() ; i+=1){
				cplist.add((CenterPanel)list.get(i));
			}
			
			System.out.println("jplist.dat open.");
			
		}catch(Exception ex){
		}finally{
			try{
				ois.close();
				fin.close();
			}catch(IOException ioe){}
		} // finally
	}
}
