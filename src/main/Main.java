package main;

import communication.TCPConnection;
import events.OnMessageListener;
import model.Colors;
import model.Direction;
import model.Name;
import processing.core.PApplet;
import view.Avatar;

public class Main extends PApplet implements OnMessageListener{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");
	}
	
	private TCPConnection tcp;
	
	private Avatar avatar;
	
	public void settings() {
		size(600,600);
	}
	
	public void setup() {
		tcp = TCPConnection.getInstance();
		tcp.setObserver(this);
	
	}
	
	public void draw() {
		background(255);
		if(avatar != null) {
			avatar.draw();
		}
				
	}

	@Override
	public void OnNameReceived(Name name) {
		System.out.println("Recibi nombre");
		avatar = new Avatar(name.getNickname(), this);
		
	}

	@Override
	public void OnDirectionReceived(Direction direction) {
		// TODO Auto-generated method stub
		avatar.move(direction.getDirection());
		
	}

	@Override
	public void OnColorReceived(Colors color) {
		avatar.setColor(color.getR(),color.getG(),color.getB());		
	}

}
