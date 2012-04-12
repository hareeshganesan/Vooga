package levelEditor.mvc;

import java.util.ArrayList;

import com.golden.gamedev.object.Sprite;


/**
 * 
 * @author Peggy Li (pl59)
 *
 */

public class Model {

	private Controller myController;
	
	private ArrayList<Sprite> mySprites;
	
	
	public Model (Controller controller) {
		myController = controller;
	}
	
	
	
	public void addSprite (Sprite s) {
		mySprites.add(s);
		
		System.out.println("Successfully added new sprite");
	}
	
	
	
	/**
	 * @TODO implement
	 */
	public void createLevelObject () {
		
	}
	
	
	
	/* =================================== INPUT CHECKING METHODS =================================== */
	
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
	 * 		or within _____ distance
	 */
	public boolean findLocationConflict (int x, int y, int radius) {
		
		return false;
	}
}
