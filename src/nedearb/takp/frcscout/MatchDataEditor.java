package nedearb.takp.frcscout;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class MatchDataEditor extends DataEditor implements OnClickListener {

	@Override
	protected void initView() {
		setContentView(R.layout.activity_match_data_editor);
	}

	@Override
	protected void afterOnCreate() {
		((CheckBox)findViewById(R.id.checkBoxMatchAutonomous)).setOnClickListener(this);
		updateAuto();
		
	}
	
	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.checkBoxMatchAutonomous){
			updateAuto();
		}
	}

	private void updateAuto() {
		boolean a = ((CheckBox)findViewById(R.id.checkBoxMatchAutonomous)).isChecked();
		findViewById(R.id.scrollViewMatchEditorAuto).setVisibility(a?View.VISIBLE:View.GONE);
	}

	@Override
	protected void put(String name) {
		MainActivity.matchMap.put(name, (MatchData) data);
	}

	@Override
	protected void remove(String name) {
		MainActivity.matchMap.remove(name);
	}

}
