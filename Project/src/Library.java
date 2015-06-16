import java.io.Serializable;
import java.util.ArrayList;


public class Library implements Serializable{//전체 도서관 데이터.
	
	private ArrayList<LibraryBook>library = new ArrayList<LibraryBook>(); //소장 책 데이터
	
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
