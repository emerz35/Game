package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -8056194281387030261L;
	
	public static final int WIDTH = 800, HEIGHT = WIDTH /12*9;

	private Thread thread;
	private boolean running = false;
	public static double delta;
	
	private Handler handler;
	private Spawn spawn;
	
	public Game(){
	
		handler = new Handler();
		spawn = new Spawn(handler);
		
		new Window(WIDTH, HEIGHT, "Game", this);
		
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseMotionListener(new MouseControls(handler,this));
		this.addMouseListener(new MouseClickControls(handler,this));
		
		handler.addObject(new Player((float)WIDTH / 2,(float)HEIGHT / 2, ID.Player,handler,this));
		handler.addCollision(new Collision(220f,200f,200,10));
		handler.addObject(new Circle(100f,100f,ID.Circle,handler));
		handler.addObject(new MagicOrbs(100f,100f,ID.Orb,handler));
		handler.addObject(new Enemy(200f,200f,ID.Enemy,handler));
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
        @Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	
		
	}
	
	public void tick(){
		handler.tick();
		spawn.tick();
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(4);
			return;
		}
		Graphics g = bs.getDrawGraphics();	
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		g.dispose();
		bs.show();
	}
	public static float clamp(float var, float min, float max){
		if(var >= max) return var = max;
		else if(var <= min) return var = min;
		else return var;
	}
	
	public static void main(String[] args){
		new Game();
		
	}

}