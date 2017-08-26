package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Collision{
	
	int height;
	int width;
	float x;
	float y;

	public Collision(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x,(int)y,width,height);
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,width,height);
	}
}
