package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected boolean shoot;
	
	
	public GameObject(float x2, float y2, ID id){
		this.x = x2;
		this.y = y2;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	
	
	public void setX(float x){
		this.x = x;
	}
	public void setY(float y){
		this.y = y;
	}
	public float getX(){
		return x;
	}
	public float getY(){
		return y;
	}
	public void setId(ID id){
		this.id = id;
	}
	public ID getId(){
		return id;
	}
	public void setVelX(float velX){
		this.velX = velX;
	}
	public void setVelY(float velY){
		this.velY = velY;
	}
	public float getVelX(){
		return velX;
	}
	public float getVelY(){
		return velY;
	}
	public void setShoot(boolean shoot){
		this.shoot = shoot;
	}
	public boolean getShoot(){
		return shoot;
	}
	
	
	
	
}