import java.io.Serializable;
import java.util.ArrayList;


public class Library implements Serializable{//��ü ������ ������.
	
	private ArrayList<LibraryBook>library = new ArrayList<LibraryBook>(); //���� å ������
	
	Library(){
		
	}
	
	ArrayList<LibraryBook> getbook(){
		return library;
	}
	
	void setbook(ArrayList<LibraryBook> library){
		this.library = library;
	}
	
	void addBook(String libname,ArrayList<Book> booklist){
		library.add(new LibraryBook(libname,booklist));
	}
	
	
	
}
