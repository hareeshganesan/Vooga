package levelEditor.output;

import java.util.ArrayList;

import levelEditor.mvc.SpriteEditable;

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

	private static final String DEFAULT_BACKGROUND = "src/resources/title.png";
	
	private String myLevelName;
	private String myBgImageURL;
	private ArrayList<SpriteEditable> mySprites;
	private SpriteEditable myAI;					// computer player - can be NULL if no computer
	
	public LevelObject () { 
		myBgImageURL = DEFAULT_BACKGROUND;
		
		mySprites = new ArrayList<SpriteEditable>();
	}
	
	
	
	/* =================================== LEVEL NAME =================================== */ 
	
	public String getLevelName () {
		return myLevelName;
	}
	
	public void setLevelName (String name) {
		myLevelName = name;
		
		System.out.println("Level object name: " + myLevelName);
	}
	
	
	/* ================================= BACKGROUND IMAGE ================================= */ 
	
	public String getBackgroundImage () {
		return myBgImageURL;
	}
	
	public void setBackgoundImage (String url) {
		myBgImageURL = url;
	}
	
	
	/* ======================================== AI ======================================== */ 
	
	public void setAI (SpriteEditable ai) {
		if (ai != null) {
			myAI = ai;
		}
	}
	
	public SpriteEditable getAI () {
		return myAI;
	}
	
	
	/* =================================== SPRITES =================================== */ 
	
	public void addSprite (SpriteEditable s) {
		mySprites.add(s);
	}
	
	public ArrayList<SpriteEditable> getSprites () {
		return mySprites;
	}
	
	

}
