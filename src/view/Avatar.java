package view;

import processing.core.PApplet;

public class Avatar {
	private int posx, posy,color,r,g,b;
	private String nickname;
	private PApplet app;

	public Avatar(String nickname, PApplet app) {
		this.nickname = nickname;
		this.app = app;
		r = 255;
		g = 255;
		b = 0;
		color = app.color(r,g,b);
		posx = app.width / 2;
		posy = app.height / 2;
	}

	public void move(int direction) {
		switch (direction) {

		case 1:// move left
			if (posx <= 0 || posx >= app.width) {

			} else {
				posx -= 3;
			}

			break;
		case 2:// move right

			if (posx <= 0 || posx >= app.width) {

			} else {
				posx -= 3;
			}

			break;
		case 3:// move up
			if (posy <= 0 || posy >= app.height) {

			} else {
				posy += 3;
			}
			break;
		case 4: // move down
			if (posy <= 0 || posy >= app.height) {

			} else {
				posy -= 3;
			}
			break;

		}
	}

	public void draw() {
		app.fill(color);
		app.ellipse(posx, posy, 50, 50);
		app.text(nickname, posx - 25, posy + 40);
	}
	
	public void setColor(int r, int g, int b) {
		color = app.color(r,g,b);
	}

}
