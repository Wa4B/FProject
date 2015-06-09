import java.io.Serializable;


public class LibraryBook implements Serializable{
	
	private String libname;
	private String isbn; //도서관 도서코드
	private String infoisbn; //도서 정보코드
	private int hold;//소장수
	private int tak;//현재 대여중인 책 수
	private String[] userlist;//현재 대여중인 유저 목록
	private int taknum;//누적 대여 횟수
	
	LibraryBook(String libname,String isbn,String infoisbn, int hold, int tak, String[] userlist, int taknum){
		
		this.libname = libname;
		this.isbn = isbn;
		this.infoisbn = infoisbn;
		this.hold = hold;
		this.tak = tak;
		this.userlist = userlist;
		this.taknum = taknum;
		
	}
	String getisbn(){
		return isbn;
	}
	String getinfoisbn(){
		return infoisbn;
	}
	int gethold(){
		return hold;
	}
	int gettak(){
		return tak;
	}
	String[] getlist(){
		
		return userlist;
	}
	int gettaknum(){
		return taknum;
	}
	
	void setisbn(String isbn){
		this.isbn = isbn;
	}
	void setinfoisbn(String infoisbn){
		this.infoisbn = infoisbn;
	}
	void sethold(int hold){
		this.hold = hold;
	}
	void settak(int tak){
		this.tak = tak;
	}
	void setlist(String[] list){
		this.userlist = list;
	}
	void settaknum(int taknum){
		this.taknum = taknum;
	}

}
