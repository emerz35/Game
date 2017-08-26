package game;

import java.awt.Graphics;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;



public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	LinkedList<Collision> collision = new LinkedList<Collision>();
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject  = object.get(i);
			
			tempObject.tick();
		}
	}
	public void render(Graphics g){
		for(Collision object : this.collision) object.render(g);
		try{
			for(GameObject object : this.object) object.render(g);
		}catch(ConcurrentModificationException e){}
		
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	/*public void addObject(GameObject object, int depth){
		if(depth > this.object.size()) depth = this.object.size();
		this.object.add(depth,object);
	}*/
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	public void addCollision(Collision object){
		this.collision.add(object);
	}
	public void removeCollision(Collision object){
		this.collision.remove(object);
	}

}

