package events;

import model.Colors;
import model.Direction;
import model.Name;

public interface OnMessageListener {
	
	void OnNameReceived(Name name);
	void OnDirectionReceived(Direction direction);
	void OnColorReceived(Colors color);

}
