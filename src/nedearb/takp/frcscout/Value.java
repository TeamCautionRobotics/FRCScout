package nedearb.takp.frcscout;

import java.io.Serializable;

import android.view.View;

public abstract class Value implements Serializable {
	
	private static final long serialVersionUID = 7779361171146885333L;
	
	public int widgetId = 0;
	public String name = "";
	
	public Value(int widgetId){
		this.widgetId = widgetId;
	}

	public abstract void loadFromString(String s);
	
	@Override
	public abstract String toString();

	public void setupWidget(DataEditor dataEditor) {
		View widget = dataEditor.findViewById(widgetId);
		setWidgetValue(widget);
		dataEditor.widgetMap.put(widget.getId(), name);
	}

	protected abstract void setWidgetValue(View widget);

	public abstract void loadFromWidget(View widget);
}
