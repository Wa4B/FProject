import java.io.Serializable;


public class Book implements Serializable{
	private String isbn;//도서코드
	private String title;//도서명
	private String author;//저자
	private int price; //도서가격
	private int year; //출판연도
	private String com; //출판사
	Book(String isbn, String title, String author, int price ,int year, String com){
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.price = price;
		this.year = year;
		this.com = com;
	}
	
	public String[] getStringlist(){
		String[] str = new String[6];
		
		str[0] = isbn;
		str[1] = title;
		str[2] = author;
		str[3] = com;
		str[4] = Integer.toString(price);
		str[5] = Integer.toString(year);
		
		
		return str;
	}
	
	public String getisbn(){
		return isbn;
	}
	public String gettitle(){
		return title;
	}
	public String getauthor(){
		return author;
	}
	public int getprice(){
		return price;
	}
	public int getyear(){
		return year;
	}
	public String getcom(){
		return com;
	}
	
	
	
	public void setisbn(String isbn){
		this.isbn =isbn;
	}
	
	public void settitle(String title){
		this.title =title;
	}
	
	public void setauthor(String author){
		this.author =author;
	}
	
	public void setprice(int price){
		this.price =price;
	}
	
	public void setyear(int year){
		this.year =year;
	}
	
	public void setcom(String com){
		this.com =com;
	}
	
	
}
