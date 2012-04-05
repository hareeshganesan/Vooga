package LevelEditor;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public class FighterSprite extends AdvanceSprite {
	int maxHealth;
	int x;
	int y;
	String FighterName;
	Double speed;
	
	public FighterSprite(int maxHealth, int x, int y, String FighterName, Double speed){
		this.maxHealth = maxHealth;
		this.x = x;
		this.y = y;
		this.FighterName = FighterName;
		this.speed = speed;
	}
	public int getHP(){
		return maxHealth;
	}
	public int getXCoord(){
		return x;
		
	}
	public int getYCoord(){
		return y;
	}
	public String getName(){
		return FighterName; 
	}
	public Double getSpeed(){
		return speed;
	}
	                     
}
