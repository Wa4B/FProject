import java.io.Serializable;
import java.util.ArrayList;


public class LibraryBook implements Serializable{//개별 도서관 데이터.
	
	private String libname;
	private ArrayList<Book> book = new ArrayList<Book>();

	
	LibraryBook(String libname,ArrayList<Book> book){
		
		this.libname = libname;
		this.book = book;
		
	}
	public String getName(){
		return libname;
	}
	public ArrayList<Book> getBook(){
		return book;
	}
	
	public void setBook(ArrayList<Book> book){
		this.book = book;
	}
	
	
}
