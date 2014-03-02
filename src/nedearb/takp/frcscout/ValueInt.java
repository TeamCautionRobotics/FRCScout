package nedearb.takp.frcscout;

import android.view.View;
import android.widget.*;

public class ValueInt extends Value {

	private static final long serialVersionUID = 575876651993996835L;

	public ValueInt(int widgetId) {
		super(widgetId);
	}

	private int value = 0;

	@Override
	public void loadFromString(String s) {
		if(!s.equals("")){
			value = Integer.parseInt(s);
		}else{
			value = 0;
		}
	}

	@Override
	public String toString() {
		return value+"";
	}

	@Override
	protected void setWidgetValue(View widget) {
		((EditText)widget).setText(value==0?"":toString());
	}

	@Override
	public void loadFromWidget(View widget) {
		loadFromString(((EditText)widget).getText().toString());
	}

}
