package nedearb.takp.frcscout;

import java.util.Iterator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class ListMenu extends Activity implements OnItemClickListener, OnTouchListener {

	private int type = 0;
	private ListView list;
	private Button newButton;
	private ArrayAdapter<String> itemAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_menu);

		type = getIntent().getExtras().getInt("type");

		list = ((ListView) findViewById(R.id.dataList));
		list.setOnItemClickListener(this);

		((TextView) findViewById(R.id.dataType)).setText(type==MainActivity.SCOUTING?"Scouting":(type==MainActivity.MATCHES?"Matches":"Error"));
		
		newButton = ((Button)findViewById(R.id.newButton));
		newButton.setOnTouchListener(this);
		
		reloadList();
	}

	private void reloadList() {

		itemAdapter = new ArrayAdapter<String>(this, R.layout.simple_list_item);

		if(type == MainActivity.SCOUTING){
			Iterator<String> it = MainActivity.scoutMap.keySet().iterator();
			while(it.hasNext()){
				itemAdapter.add(it.next());
			}
		}else if(type == MainActivity.MATCHES){
			Iterator<String> it = MainActivity.matchMap.keySet().iterator();
			while(it.hasNext()){
				itemAdapter.add(it.next());
			}
		}
		
		list.setAdapter(itemAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.list_menu, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		if(type == MainActivity.SCOUTING){
			ScoutData a = MainActivity.scoutMap.get(itemAdapter.getItem(position));
			openScoutEditor(a);
			
		}else if(type == MainActivity.MATCHES){
			MatchData a = MainActivity.matchMap.get(itemAdapter.getItem(position));
		    openMatchEditor(a);
		}
	}

	private void openScoutEditor(ScoutData a) {
		Intent i = new Intent(this, ScoutDataEditor.class);
		i.putExtra("dataSet", a);
		startActivity(i);
	}
	

	private void openMatchEditor(MatchData a) {
		Intent i = new Intent(this, MatchDataEditor.class);
		i.putExtra("dataSet", a);
		startActivity(i);
	}

	@Override
	public boolean onTouch(View v, MotionEvent me) {
		if(me.getAction()==MotionEvent.ACTION_DOWN){
			Log.d("ListMenu", "new");
			if(type == MainActivity.SCOUTING){
				openScoutEditor(new ScoutData());
			}
			if(type == MainActivity.MATCHES){
				openMatchEditor(new MatchData());
			}
		}
		return super.onTouchEvent(me);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		reloadList();
	}

}
