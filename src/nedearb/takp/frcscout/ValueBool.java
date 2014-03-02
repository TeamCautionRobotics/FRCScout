package nedearb.takp.frcscout;

import android.view.View;
import android.widget.*;

public class ValueBool extends Value {
	
	private static final long serialVersionUID = 8352593549751088988L;
	
	public ValueBool(int widgetId) {
		super(widgetId);
	}

	private boolean value;

	@Override
	public void loadFromString(String s) {
		value = s.equals("true");
	}

	@Override
	public String toString() {
		return value?"true":"false";
	}

	@Override
	protected void setWidgetValue(View widget) {
		if(widget instanceof CheckBox){
			((CheckBox)widget).setChecked(value);
		}else if(widget instanceof ToggleButton){
			((ToggleButton)widget).setChecked(value);
		}
	}

	@Override
	public void loadFromWidget(View widget) {
		if(widget instanceof CheckBox){
			value = ((CheckBox)widget).isChecked();
		}else if(widget instanceof ToggleButton){
			value = ((ToggleButton)widget).isChecked();
		}
		
	}
}
