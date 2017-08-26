package game;

public class Spawn {
	private Handler handler;
	private int enemyCount = 0;
	private int maxEnemies = 1;
	private int respawn = 100;
	
	public Spawn(Handler handler){
		this.handler = handler;
	}
	
	public void tick(){
		for(int i = 0; i< handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Enemy) enemyCount++;
		}
		
		if(enemyCount < maxEnemies && respawn == 0) {
			handler.addObject(new Enemy(200f,200f,ID.Enemy,handler));
			respawn = 100;
		}
		else if(enemyCount < maxEnemies) respawn--;
		
		enemyCount = 0;
	}
	
}
