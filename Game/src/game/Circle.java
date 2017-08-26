package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Circle extends GameObject{
	
	float a = 0;
	int r = 45;
	float angle;
	
	Handler handler;
	private GameObject player;
	
	public Circle(float x, float y, ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		for(int i = 0; i < handler.object.size(); i ++){
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
			
		}
	}

	
	public void tick() {
		float diffX = MouseControls.mx - player.getX() - 8;
		float diffY = MouseControls.my - player.getY() - 8;
		if(diffY == 0) diffY = 1;
		float distance =  (float) Math.sqrt( (diffX) * (diffX) + (diffY) * (diffY));
		
		
		
		if(diffY > 0)angle = (float) Math.acos(diffX/distance);
		else angle = (float) -Math.acos(diffX/distance);
		//a += (float)0.05 * Game.delta;
		a = angle;
		x = (float) (player.getX()+8 + r * Math.cos(a));
		y = (float) (player.getY()+8 + r * Math.sin(a));
		
		//handler.addObject(new Trail(x,y,ID.Trail,Color.green,20,20,0.1f,handler));
		
		x+=velX;
		y+=velY;
		
	}

	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x,(int)y,16,16);
	}

	public void setShoot(boolean shoot) {
	}
}
