package nedearb.takp.frcscout;

import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public abstract class DataEditor extends Activity {

	public SparseArray<String> widgetMap = new SparseArray<String>();

	public DataSet data;

	private String originalName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();
		
		Intent intent = getIntent();
		data = ((DataSet)intent.getExtras().getSerializable("dataSet"));
		
		Log.d("onCreate DataEditor", "Data Size: "+data.values.size());
		
		for(Entry<String, Value> i : data.values.entrySet()){
			i.getValue().setupWidget(this);
		}

		originalName = data.getMapName();
		
		afterOnCreate();

	}
	
	protected abstract void initView();

	protected abstract void afterOnCreate();

	protected abstract void put(String name);

	protected abstract void remove(String name);
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	Log.d("onKeyDown", "Back was pressed");
	    	saveAll();
	    	finish();
	        return true;
	    }

	    return super.onKeyDown(keyCode, event);
	}


	private void saveAll() {
    	
    	//Save all data from widgets to dataSet
    	for(Entry<String, Value> i : data.values.entrySet()){
			i.getValue().loadFromWidget(this.findViewById(i.getValue().widgetId));
		}
		
    	//Remove old one if name changed:
		if(!originalName.equals(data.getMapName())){
			remove(originalName);
    		MainActivity.instance.deleteDataFile(data);
		}
		//Put new one:
		put(data.getMapName());
		MainActivity.instance.saveFile(data);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.data_editor, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case R.id.action_delete:
	    		MainActivity.scoutMap.remove(data.getMapName());
	    		MainActivity.instance.deleteDataFile(data);
	    		finish();
	            return true;
	     }
	    return super.onOptionsItemSelected(item);
	}

}
