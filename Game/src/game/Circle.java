package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

public class Circle extends GameObject{

    float a = 0;
    int r = 40;
    float angle;
    private float spinAngle;

    Handler handler;
    private GameObject player;

    public Circle(float x, float y, ID id,Handler handler){
            super(x,y,id);
            this.handler = handler;
            for(int i = 0; i < handler.object.size(); i ++){
                    if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);

            }
    }

    @Override
    public void tick() {
            float diffX = MouseControls.mx - player.getX() - 8;
            float diffY = MouseControls.my - player.getY() - 8;
            if(diffY == 0) diffY = 1;
            float distance =  (float) Math.sqrt((diffX) * (diffX) + (diffY) * (diffY));



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
    @Override
    public void render(Graphics g) {
            g.setColor(Color.green);
            Graphics2D g2d = (Graphics2D) g;
            AffineTransform at = AffineTransform.getRotateInstance(spinAngle, x+5, y+5);
            g2d.fill(at.createTransformedShape(getBounds()));
            spinAngle+=Math.PI/120;
    }
    @Override
    public Rectangle getBounds() {
            return new Rectangle((int)x,(int)y,10,10);
    }
    @Override
    public void setShoot(boolean shoot) {
    }
    public float xisFree(float velX){
            if(velX == 0) return 0;
            Rectangle collision = new Rectangle((int) (x + velX),(int)y ,10,10);
            for(Collision tempObject : handler.collision){
                    if(tempObject.getBounds().intersects(collision)){
                            return xisFree(slow(velX, 1));
                    }
            }

            return velX;
    }
    public float yisFree(float velY){
            if(velY == 0) return 0;
            Rectangle collision = new Rectangle((int) x ,(int)(y + velY),10,10);
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
}
