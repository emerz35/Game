package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseClickControls extends MouseAdapter{
	
	Handler handler;
	Game game;
	GameObject orb;
	GameObject circle;
	
	public MouseClickControls(Handler handler, Game game){
	
		this.handler = handler;
		this.game = game;
		}
	public void mousePressed(MouseEvent e){
		int mx = e.getX() - 18;
		int my = e.getY() - 20;
		if(e.getButton() == e.BUTTON1){
			for(int i = 0; i < handler.object.size(); i ++){
				if(handler.object.get(i).getId() == ID.Orb){ 
					orb = handler.object.get(i);
					for(GameObject object: handler.object) if(object.getId() == ID.Circle) circle = object;
					if(!orb.getShoot()){
						orb.setShoot(true);
						orb.setX(circle.getX() + 4);
						orb.setY(circle.getY() + 4);
						float diffX = circle.getX() - mx - 8;
						float diffY = circle.getY() - my - 8;
						float distance =  (float) Math.sqrt( (circle.getX() - mx) * (circle.getX() - mx) + (circle.getY() - my) * (circle.getY() - my));
								
						orb.setVelX(((-1 / distance) * diffX)*6);
						orb.setVelY(((-1 / distance) * diffY)*6);
						return;
					}				
				}
			}
		}
	}
}