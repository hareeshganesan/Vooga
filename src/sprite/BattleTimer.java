package sprite;


import java.awt.Graphics2D;

import com.golden.gamedev.object.*;


public class BattleTimer {
	GameFont font;
	int time;
	Timer speed;
	
	Boolean timeUp;
	
	public BattleTimer(int timePerLevel, GameFont font){
		this.time = timePerLevel;
		this.font = font;
		timeUp = false;
		speed = new Timer(1000);
	}
	
	public boolean gameEnd(){
		return timeUp;
		
	}
	public void update(long elapsedTime){
		if(!timeUp){
			if(speed.action(elapsedTime)){
				time -=time;
				if(time ==0){
					timeUp = true;
				}
			}
		}
	}
	public void render(Graphics2D pen){
		font.drawString(pen, Integer.toString(time), 8, 8);
	}
}
