package levelEditor.output;

import ai.AIAgent;
import ai.BasicAIAgent;

/**
 * @author Peggy Li (pl59)
 */


public class Main {

	public static void main (String[] args) {
		LevelObject obj = new LevelObject();
		obj.setLevelName("random");
		obj.setBackgoundImage("src/block.png");
		
		
		
		XMLWriter xml = new XMLWriter(obj);
		xml.save();
	}
}
