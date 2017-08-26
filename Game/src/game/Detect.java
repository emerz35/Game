package game;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Detect extends GameObject{
	Handler handler;
	Enemy enemy;
	Player player;
	private int width = 32;
	private int height = 32;

	public Detect(float x, float y, ID id, Handler handler, Enemy enemy) {
		super(x, y, id);
		this.handler = handler;
		this.enemy = enemy;
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) player = (Player) tempObject;
		}
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance =  (float) Math.sqrt( (x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		velX = ((-1 / distance) * diffX) * 2;
		velY = ((-1 / distance) * diffY) * 2;
	}

	
	public void tick() {
		if(xisFree(velX))x += velX;
		if(yisFree(velY))y += velY;
		
		
		if(player.getBounds().intersects(getBounds())) enemy.noticed = true;
		
		if(enemy.x - Math.abs(x) > enemy.noticeRadius || enemy.y - y > enemy.noticeRadius) handler.removeObject(this);
		else enemy.noticed = false;
	}
	
	public void render(Graphics g) {}
	
	public boolean xisFree(float velX){
		Rectangle collision = new Rectangle((int) (x + velX),(int)y ,width,height);
		for(Collision tempObject : handler.collision){
			if(tempObject.getBounds().intersects(collision)){
				return false;
			}
		}
		
		return true;
	}
	public boolean yisFree(float velY){
		Rectangle collision = new Rectangle((int) x ,(int)(y + velY),width,height);
		for(Collision tempObject: handler.collision){
			if(tempObject.getBounds().intersects(collision)){
				return false;
			}
		}
		return true;
	}

	public float slow(float vel, float deceleration){
		return vel > 0 ? vel - deceleration : vel + deceleration;
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y,32,32);
	}

}
