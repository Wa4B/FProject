import java.io.Serializable;


public class Book implements Serializable{
	private String isbn;//�����ڵ�
	private String title;//������
	private String author;//����
	private String com; //���ǻ�
	private int hold;//�����
	private int tak;//���� �뿩���� å ��
	private String[] isbn2;
	private String[] userlist;//���� �뿩���� ���� ���
	private int taknum;//���� �뿩 Ƚ��
	
	Book(String isbn, String title, String author, String com, int hold, String[] userlist, int taknum){
		isbn2 = new String[hold];
		for(int i = 0;i < hold; i += 1){
			isbn2[i] = isbn+"-"+(i+1);
		}
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.com = com;
		this.hold = hold;
		this.tak = hold;
		this.userlist = userlist;
		this.taknum = taknum;
	}
	public void rtBook(String isbn2, String userid){
		
	}
	
	public String[] getStringlist(){
		String[] str = new String[6];
		
		str[0] = isbn;
		str[1] = title;
		str[2] = author;
		str[3] = com;

		
		
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
	

	
	public void setcom(String com){
		this.com =com;
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
	
}
