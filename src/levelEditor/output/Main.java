package levelEditor.output;

/**
 * @author Peggy Li (pl59)
 */


public class Main {

	public static void main (String[] args) {
		LevelObject obj = new LevelObject();
		obj.setLevelName("test3");
		obj.setBackgoundImage("src/block.png");
		
		/*
		AIAgent ai = new BasicAIAgent("LE", new HealthDisplay(10, 20, 30), 2, 
				new CombatInstance(new MainGame()));
		obj.setAI(ai);
		*/
		
		XMLWriter xml = new XMLWriter(obj);
		xml.save();
	}
}
