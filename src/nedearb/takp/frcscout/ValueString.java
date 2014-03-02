package nedearb.takp.frcscout;

import android.view.View;
import android.widget.EditText;

public class ValueString extends Value {

	private static final long serialVersionUID = 4980223365830892036L;
	
	private String value = "";

	public ValueString(int widgetId) {
		super(widgetId);
	}

	@Override
	public void loadFromString(String s) {
		value = s;
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	protected void setWidgetValue(View widget) {
		((EditText)widget).setText(value);
	}

	@Override
	public void loadFromWidget(View widget) {
		loadFromString(((EditText)widget).getText().toString());
	}
}
