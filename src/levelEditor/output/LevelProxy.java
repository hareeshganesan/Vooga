package levelEditor.output;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;

/**
 * Creates object to hold all information associated with
 * a newly designed level.
 * Facilitates writing out level data to external file by
 * serving as intermediary between GUI and file writer
 * 
 * @author Peggy Li (pl59) 
 *
 */

@SuppressWarnings("unused")
public class LevelProxy {

	private String myLevelName;
	private String myBgImageURL;
	private ArrayList<Sprite> mySprites;
	
	public LevelProxy (String name) {
		myLevelName = name;
	}

	
	
	public String getLevelName () {
		return myLevelName;
	}
	
	
	
	public String getWriteable () {
		StringBuilder build = new StringBuilder("");
		
		
		return build.toString();
	}
	
}
