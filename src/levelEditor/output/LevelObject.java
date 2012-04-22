package levelEditor.output;

import java.util.ArrayList;

import ai.AIAgent;

import com.golden.gamedev.object.Sprite;

/**
 * Creates object to hold all information associated with
 * a newly designed level, updated as level is built
 * 
 * Facilitates writing out level data to external file by
 * serving as intermediary between GUI and file writer
 * 
 * @author Peggy Li (pl59) 
 *
 */

public class LevelObject {

	private String myLevelName;
	private String myBgImageURL;
	private ArrayList<Sprite> mySprites;
	private AIAgent myAI;					// computer player - can be NULL if no computer
	
	public LevelObject () { }
	
	
	/* =================================== LEVEL NAME =================================== */ 
	
	public String getLevelName () {
		return myLevelName;
	}
	
	public void setLevelName (String name) {
		myLevelName = name;
	}
	
	
	/* ================================= BACKGROUND IMAGE ================================= */ 
	
	public String getBackgroundImage () {
		return myBgImageURL;
	}
	
	public void setBackgoundImage (String url) {
		myBgImageURL = url;
	}
	
	
	/* ======================================== AI ======================================== */ 
	
	public void setAI (AIAgent ai) {
		if (ai != null) {
			myAI = ai;
		}
	}
	
	public AIAgent getAI () {
		return myAI;
	}
	
	
	/* =================================== SPRITES =================================== */ 
	
	public void addSprite (Sprite s) {
		mySprites.add(s);
	}
	
	public ArrayList<Sprite> getSprites () {
		return mySprites;
	}
	
	public void removeSprite (Sprite s) {
		for (Sprite sprite : mySprites) {
			if (sprite.equals(s)) 
				mySprites.remove(sprite);
		}
	}
	
	
	
	
	
	public String getWriteable () {
		StringBuilder build = new StringBuilder("");
		
		
		return build.toString();
	}
	
	
}
