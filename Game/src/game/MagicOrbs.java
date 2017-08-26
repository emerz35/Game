package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class MagicOrbs extends GameObject{
	
	float a;
	int r = 18;
	int life = 100;
	private int orbCount = 1;
	private int beforeOrbs;
	
	Random rand = new Random();
	Handler handler;
	private GameObject circle;
	
	public MagicOrbs(float x, float y, ID id,Handler handler){
		super(x,y,id);
		this.handler = handler;
		for(int i = 0; i < handler.object.size(); i ++){
			if(handler.object.get(i).getId() == ID.Circle) circle = handler.object.get(i);
			else if(handler.object.get(i).getId() == ID.Orb) orbCount ++;
			if(handler.object.get(i) == this) beforeOrbs = orbCount + 1;
		}
		this.a = (360/orbCount) * beforeOrbs;
	}

	
	public void tick() {
		/*for(int i = 0; i < handler.object.size(); i ++){
			if(handler.object.get(i).getId() == ID.Orb) orbCount ++;
			if(handler.object.get(i) == this) beforeOrbs = orbCount + 1;
		}
		a = (360/orbCount) * beforeOrbs;*/
		
		if(!shoot){
			a +=(float)0.05 * Game.delta;
			x = (float) (circle.getX()+4 + r * Math.cos(a));
			y = (float) (circle.getY()+4 + r * Math.sin(a));
		}
		else if(life == 0) handler.removeObject(this);
		else life--;
		
		handler.addObject(new Trail(x,y,ID.Trail,Color.blue,8,8,0.15f,handler,"Circ"));
		
		x+=velX;
		y+=velY;
		collision();
	}
	
	public void collision(){
		for(Collision tempObject : handler.collision){
			if(tempObject.getBounds().intersects(getBounds()) && shoot) handler.removeObject(this);
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval((int)x, (int)y, 8, 8);
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}
}
