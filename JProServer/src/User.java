import java.io.Serializable;


public class User implements Serializable{
	private String ID; // ���̵�
	private String PW; // �н�����
	private String name; // �̸�
	private int brt; // �������
	private String[] list; // �뿩����Ʈ
	private String pow; //����
	
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
