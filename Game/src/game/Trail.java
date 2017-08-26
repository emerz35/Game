package game;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject{

	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width,height;
	private float life;
	private String shape;
	
	/*
	 * @param shape "Circ" for an oval, "Rect" for a rectangle. 
	 */
	public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler,String shape) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.shape = shape;
	}

	
	public void tick() {
		if(alpha > life){
			alpha -= (life - 0.0001f);
		}
		else handler.removeObject(this);
	}

	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		
		g.setColor(color);
		if(shape.equals("Rect"))g.fillRect((int)x, (int)y, width, height);
		else if(shape.equals("Circ"))g.fillOval((int)x, (int)y, width, height);
		
		g2d.setComposite(makeTransparent(1));
		
	}
	
	private AlphaComposite makeTransparent(float Alpha){
		int type = AlphaComposite.SRC_OVER;
		return AlphaComposite.getInstance(type, Alpha);
	}

	
	public Rectangle getBounds() {
		return null;
	}



}
