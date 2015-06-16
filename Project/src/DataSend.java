import java.util.ArrayList;


public class DataSend {
	String datainfo;
	ArrayList<String[][]> dataset = new ArrayList<String[][]>();
	DataSend(String datainfo, ArrayList<String[][]> dataset){
		this.datainfo = datainfo;
		this.dataset = dataset;
	}
	String getDatainfo(){
		return datainfo;
	}
	String[][] getData(int index){
		return dataset.get(index);
	}
}
