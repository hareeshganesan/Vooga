package levelEditor.mvc;

import java.util.ArrayList;
import java.util.HashSet;

import levelEditor.loader.SpriteClassLoader;
import levelEditor.loader.SuperclassFilter;
import levelEditor.output.LevelObject;
import levelEditor.output.XMLWriter;


import com.golden.gamedev.object.Sprite;


/**
 * 
 * @author Peggy Li (pl59)
 *
 */

public class Model {

	private Controller myController;
	
	private LevelObject myLevel;
	
	private ArrayList<SpriteEditable> mySprites;
	
	
	public Model (Controller controller) {
		myController = controller;
		myLevel = new LevelObject();
		
		mySprites = new ArrayList<SpriteEditable>();
	}
	
	
	public void addSprite (SpriteEditable s) {
		mySprites.add(s);
		myLevel.addSprite(s);
		
		System.out.println("Successfully added new sprite");
	}
	
	
	public void addAI (SpriteEditable ai) {
		mySprites.add(ai);
		myLevel.setAI(ai);
	}
	
	
	public void setLevelName (String name) {
		System.out.println("Model name");
		myLevel.setLevelName(name);
	}
	
	
	public void saveToFile () {
		XMLWriter writer = new XMLWriter(myLevel);
		writer.save();
	}
	
	/* ================================= INPUT CHECKING METHODS ================================= */
	
	public <T> String[] getClassNames () {
		SpriteClassLoader loader = new SpriteClassLoader();
		loader.load();
		//HashSet<Class> allClasses = loader.getClasses();
	
		
		//SuperclassFilter filter = new SuperclassFilter();
		//ArrayList<Class<T>> sprites = filter.applyFilter(allClasses, null);
		
		return loader.getSpriteClassNames();
	}
	
	
	
	/* ================================= INPUT CHECKING METHODS ================================= */
	
	/**
	 * Checks whether a level with the given name already exists
	 * @return true if name has not already been used
	 * 
	 * @TODO implement file checking
	 */
	public boolean verifyNameAvailable (String name) {
		return true;
	}
	
	
	/**
	 * Checks whether a new Sprite can be placed at location (x, y)
	 * 	e.g. there is not already a Sprite at that location
	 * 		or within distance of <radius>
	 * @return if location conflict occurs
	 */
	public boolean findLocationConflict (int x, int y, int radius) {
		for (SpriteEditable s : mySprites) {
			double xSquared = Math.pow(Integer.parseInt(s.getPropertyMap().get("x")) - x, 2);
			double ySquared = Math.pow(Integer.parseInt(s.getPropertyMap().get("y")) - y, 2);
			if (Math.sqrt(xSquared + ySquared) > radius) {
				myController.displayMessageToUser(String.format(
						"Sprite already exists within %d of location (%d, %d)", 
						radius, x, y));
				return true;
			}
		}
		return false;
	}
	
}
