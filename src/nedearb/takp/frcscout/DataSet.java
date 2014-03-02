package nedearb.takp.frcscout;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

public abstract class DataSet implements Serializable {
	
	private static final long serialVersionUID = 1564609021431288340L;
	
	public HashMap<String, Value> values = new HashMap<String, Value>();
	public CustomSparseArray<String> valueIndexes = new CustomSparseArray<String>();
	
	private int nextIndex=0;
	
	public abstract String getMapName();

	public abstract String getFileName();

	public int getTotalDataQty(){
		return values.size();
	}
	
	public void saveData(PrintWriter pw){
		for(Entry<String, Value> i : values.entrySet()){
			pw.println(i.getKey()+":"+i.getValue().toString());
		}
	}
	
	public void loadData(BufferedReader inputStream){
		String line;
		do{
			try {
				line = inputStream.readLine();
				String[] split = line.split(":", 2);
				values.get(split[0]).loadFromString(split[1]);
			} catch (Exception e) {
				line = null;
			}
		}while(line!=null);
	}
	
	protected void addValue(String string, Value value) {
		value.name = string;
		values.put(string, value);
		valueIndexes.put(nextIndex, string);
		nextIndex++;
	}
}
