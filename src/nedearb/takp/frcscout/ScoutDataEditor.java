package nedearb.takp.frcscout;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class ScoutDataEditor extends DataEditor implements OnClickListener {

	@Override
	protected void initView() {
		setContentView(R.layout.activity_scout_data_editor);
	}
	
	@Override
	protected void afterOnCreate() {
		
		((CheckBox)findViewById(R.id.checkBoxScoutAutonomous)).setOnClickListener(this);
		
		updateAuto();
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.checkBoxScoutAutonomous){
			updateAuto();
		}
	}

	private void updateAuto() {
		boolean a = ((CheckBox)findViewById(R.id.checkBoxScoutAutonomous)).isChecked();
		findViewById(R.id.scrollViewScoutEditorAuto).setVisibility(a?View.VISIBLE:View.GONE);
	}

	@Override
	protected void put(String name) {
		MainActivity.scoutMap.put(name, (ScoutData) data);
	}

	@Override
	protected void remove(String name) {
		MainActivity.scoutMap.remove(name);
	}
}
