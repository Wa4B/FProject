import java.io.Serializable;


public class User implements Serializable{
	private String ID; // 아이디
	private String PW; // 패스워드
	private String name; // 이름
	private int brt; // 생년월일
	private String[] list; // 대여리스트
	private String pow; //권한
	
	User(String ID , String PW, String name, int brt, String[] list, String pow){
		this.ID = ID;
		this.PW = PW;
		this.name = name;
		this.brt = brt;
		this.list = list;
		this.pow = pow;
	}
	
	String getID(){
		return ID;
	}
	String getPW(){
		return PW;
	}
	String getname(){
		return name;
	}
	int getbrt(){
		return brt;
	}
	String[] getlist(){
		return list;
	}
	String getpow(){
		return pow;
	}
}
