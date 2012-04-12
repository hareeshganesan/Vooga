package old;
import java.io.File;
import java.util.HashMap;

import com.golden.gamedev.object.Sprite;

/**
 * 
 * @author Peggy Li / pl59 
 */


public class Model {
	
	private static final String SAVE_TO_DIR = 
			System.getProperty("user.dir");
	
	private Controller myController;
	
	// maps each Sprite object to its image path
	private HashMap<Sprite, String> mySprites;
	
	
	
	public Model (Controller c) {
		myController = c;
		mySprites = new HashMap<Sprite, String>();
	}
	
	
	public void addSprite (Sprite s, String imageURL) {
		mySprites.put(s, imageURL);
	}

	
	public boolean nameAvailabe (String filename) {
		File startDir = new File(SAVE_TO_DIR);
		
		for (File f : startDir.listFiles()){
			if (f.isFile() && f.getName().equals(filename + ".xml"))
				return false;
		}
		return true;
	}
	
	public void saveToXML (String filename, String bgImage) {
		mySprites.put(new BlockSprite(), "image");
		if (mySprites.size() > 0) {
			XMLWriter writer = new XMLWriter(filename, bgImage, mySprites);
			writer.saveToXML();
		}
		else {
			myController.notifyUser("No Sprites found");
		}
	}
	
}
