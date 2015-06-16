import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Booklistmaker {
	private ArrayList<Book> book = new ArrayList<Book>();
	
	private ArrayList<LibraryBook> library = new ArrayList<LibraryBook>(); 
	private ArrayList<Library> librarydata =new ArrayList<Library>(); 
	
	public static void main(String arg[]){
		Booklistmaker bk = new Booklistmaker();
		bk.OpenBook();
		
		bk.setlibbook("상록도서관");
		bk.setlibbook("고잔도서관");
		
		bk.SavelibBook();
		bk.SaveBook();
	}
	public void OpenBook(){
		BufferedReader br = null;
		try {
			String isbn = "";
			int n= 0;
			String title;
			String Author;
			String com;
			int price = 0;
			int years[] = new int[15];
			
			String str;
			for(int i = 0 ; i < years.length; i+=1){
				years[i] = 2015 - i;
			}
			
			
			br = new BufferedReader(new FileReader("booklist2.txt"));
			while ((str = br.readLine()) != null) {
				int[] set = new int[6];
				for(int i = 0 ; i < set.length; i+= 1){
					if(i==0){
						set[i] = str.indexOf("/");
					}else{
						set[i] = str.indexOf("/", set[i-1]+1);
					}
				}
				
				title = str.substring(set[0]+1, set[1]);
				Author = str.substring(set[1]+1, set[2]);
				com = str.substring(set[2]+1, set[3]);
				
				
				if(n < 10){
					isbn = "a000"+n;
				}else if(n < 100){
					isbn = "a00"+n;
				}else if(n < 1000){
					isbn = "a0"+n;
				}else if(n < 10000){
					isbn = "a" + n;
				}
				String[] taklist = {"미대출","미대출"};
				book.add(new Book(isbn,title,Author,com,2,taklist,0));
				n +=1;
				
				
			}
			System.out.println(n);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setlibbook(String name){
		ArrayList<Book> libbook = new ArrayList<Book>();
		String libname = name;
		
		String title;
		String Author;
		String com;
		
		int n = 0;
		String isbn = "a0000";
		for(int i = 0 ; i < book.size() ; i += 1){
			if(n < 10){
				isbn = "a000"+n;
			}else if(n < 100){
				isbn = "a00"+n;
			}else if(n < 1000){
				isbn = "a0"+n;
			}else if(n < 10000){
				isbn = "a" + n;
			}
			int rani = (int) Math.round(Math.random()*2);
			String[] aaa = {};
			String[] taklist = {"미대출","미대출"};
			
			title = book.get(i).gettitle();
			Author = book.get(i).getauthor();
			com = book.get(i).getcom();
			
			libbook.add(new Book(isbn,title,Author,com,2,taklist,0));
			i += rani;
			n +=1;
		}
		library.add(new LibraryBook(name,libbook));
		
	}
	
	public void SaveBook(){
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
	
	public void SavelibBook(){
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
	
}
