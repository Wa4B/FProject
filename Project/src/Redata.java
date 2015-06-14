
public class Redata {
	public static void main (String arg[]){
		DataManager dm = new DataManager();
		
		dm.Allsave();
		String line = "a b c ds e";
		int set[] = new int[4];
		for(int i = 0 ; i< set.length ; i += 1){
			if(i == 0){
				set[0] = line.indexOf(" ");
			}else{
				set[i] = line.indexOf(" ",set[i-1]+1);
			}
		}
		for(int i = 0; i <4 ; i+=1){
			System.out.println(line.substring(set[1]+1, set[2]));
		}
	}
}
