package game;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseControls extends MouseAdapter{
	Handler handler;
	public static int mx;
	public static int my;
	public MouseControls(Handler handler, Game game){
		this.handler = handler;
	}
	
	public void mouseMoved(MouseEvent e){
		mx = e.getX();
		my = e.getY();
	}
	
}
