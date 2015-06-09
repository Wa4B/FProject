import java.io.Serializable;
import java.util.ArrayList;


public class Library implements Serializable{
	private String libname; //������ ��
	private ArrayList<LibraryBook>book = new ArrayList<LibraryBook>(); //���� å ������
	
	Library(String libname){
		this.libname = libname;
	}
	
	ArrayList<LibraryBook> getbook(){
		return book;
	}
	
	void setbook(ArrayList<LibraryBook> book){
		this.book = book;
	}
	
	void addBook(String isbn,String infoisbn, int hold, int tak, String[] userlist, int taknum){
		book.add(new LibraryBook(libname,isbn,infoisbn,hold,tak,userlist,taknum));
	}
	
	String[] getStriglist(){
		String[] list = new String[7];
		list[0] = libname;
		
		
		return list;
	}
}
