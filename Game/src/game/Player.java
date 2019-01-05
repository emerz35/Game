package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	int width,height;
	Color color;
	int OrbCount;
	public int MaxOrbs;
	Game game;
	Handler handler;
	int reloadnum = 75;
	int reload = reloadnum;
	
	public Player(float x, float y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this.game = game;
		this.handler = handler;
		this.width = 32;
		this.height = 32;
		this.color = Color.white;
		this.OrbCount = 0;
		this.MaxOrbs = 3;
	}

        @Override
	public void tick() {
				
		x+= xisFree(velX);
		y+= yisFree(velY);
		
		for(int i = 0; i < handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Orb && !tempObject.getShoot()) OrbCount++;
		}
		
		if(OrbCount != MaxOrbs && reload == 0) {
			handler.addObject(new MagicOrbs(x,y,ID.Orb,handler));
			reload = reloadnum;
		}
		else if(OrbCount != MaxOrbs) reload--;
		
		
		OrbCount = 0;
		
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		damage();
		handler.addObject(new Trail(x,y,ID.Trail,color, width,height,0.2f,handler,"Rect"));
	}
	
	public void damage(){
		
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
        @Override
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);	
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y, width, height);
	}

	

}
