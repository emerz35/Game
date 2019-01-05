package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject{
	private int width = 16;
	private int height = 18;
	public int noticeRadius = 200;
	private int health = 100;
        private float tempGrad;
        private float playerGrad;
	
	
	public boolean noticed = false;
	
	private Handler handler;
	GameObject player;
	
	public Enemy(float x, float y, ID id, Handler handler){
		super(x,y,id);
		
		this.handler = handler;
		player = player();
	}

	public GameObject player(){
		for(int i = 0; i < handler.object.size();i++){
			if(handler.object.get(i).getId() == ID.Player) return handler.object.get(i);
		}
		return null;
	}
	public void tick() {
		x += xisFree(velX);
		y += yisFree(velY);
		
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance =  (float) Math.sqrt( (x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		
		for(int i = 0; i < handler.collision.size(); i++){
                    Collision tempCollision = handler.collision.get(i);
                    tempGrad = (tempCollision.getY() - y) / (tempCollision.getX() - x);
                    playerGrad = (player.getY() - y) / (player.getX() - x);
                    if(tempGrad > playerGrad) noticed = true;
                    
                    tempGrad = (tempCollision.getY() + tempCollision.height - y) / (tempCollision.getX() - x);
                    playerGrad = (player.getY() - y) / (player.getX() - x);
                    if(tempGrad < playerGrad) noticed = true;
                    
                    tempGrad = (tempCollision.getY() - y) / (tempCollision.getX() + tempCollision.width - x);
                    playerGrad = (player.getY() - y) / (player.getX() - x);
                    if(tempGrad > playerGrad) noticed = true;
                    
                    tempGrad = (tempCollision.getY() + tempCollision.height - y) / (tempCollision.getX() + tempCollision.width - x);
                    playerGrad = (player.getY() - y) / (player.getX() - x);
                    if(tempGrad < playerGrad) noticed = true;
                    
                }
                //if(distance < noticeRadius) noticed = true;
		//handler.addObject(new Detect(x,y,ID.Detect,handler,this));
		
		if(noticed){
			velX = ((-1 / distance) * diffX) * 2;
			velY = ((-1 / distance) * diffY) * 2;
		}
                else{
                    velX = velY = 0;
                }
			
		if(y <= 0 || y >= Game.HEIGHT - 32) velY = -velY;
		if(x <= 0 || x >= Game.WIDTH - 16) velX = -velX;
		damage();
		
		if(health == 0) handler.removeObject(this);
	}
	
	public void damage(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Orb){
				if(tempObject.getBounds().intersects(getBounds()) && tempObject.shoot){
					health-=50;
					handler.removeObject(tempObject);
				}
			}
		}
	}
	public float xisFree(float velX){
		
		if(velX == 0) return 0;
		Rectangle collision = new Rectangle((int) (x + velX),(int)y ,width,height);
		for(Collision tempObject : handler.collision){
			if(tempObject.getBounds().intersects(collision)){
				return xisFree(slow(velX, 1));
			}
		}
		
		return velX;
	}
	public float yisFree(float velY){
		if(velY == 0) return 0;
		Rectangle collision = new Rectangle((int) x ,(int)(y + velY),width,height);
		for(Collision tempObject: handler.collision){
			if(tempObject.getBounds().intersects(collision)){
				return yisFree(slow(velY,1));
			}
		}
		return velY;
	}

	public float slow(float vel, float deceleration){
		return vel > 0 ? vel - deceleration : vel + deceleration;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect((int) x, (int)y, width, height);
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle((int) x, (int) y, width, height);
	}

}
