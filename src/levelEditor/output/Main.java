package levelEditor.output;


import levelEditor.mvc.LevelObject;

public class Main {

	public static void main (String[] args) {
		LevelObject obj = new LevelObject();
		obj.setLevelName("test3");
		obj.setBackgoundImage("src/block.png");
		
		
		
		
		XMLWriter xml = new XMLWriter(obj);
		xml.save();
	}
}
