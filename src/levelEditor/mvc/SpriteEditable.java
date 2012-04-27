package levelEditor.mvc;

/**
 * @author Peggy Li (pl59)
 */

import java.util.HashMap;

/**
 * Provides simple wrapper for Sprite class
 * Stores type of sprite (class name) and map of various properties
 * 	of the sprite that have been custom-set in the level editor
 */
public class SpriteEditable {

	private HashMap<String, String> properties;
	
	private String spriteType;
	
	public SpriteEditable(String type) {
		properties = new HashMap<String, String>();
		properties.put("type", type);
	}
 	
	public void addProperty (String key, String value) {
		properties.put(key,  value);
	}
	
	public HashMap<String, String> getPropertyMap () {
		return properties;
	}
}
