package levelEditor.output;

/**
 * @author Peggy Li (pl59)
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import levelEditor.mvc.SpriteEditable;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import ai.AIAgent;

import com.golden.gamedev.object.Sprite;


public class XMLWriter {
	
	LevelObject myLevelObject;
	
	Element myRoot;

	public XMLWriter (LevelObject levelObject) {
		myLevelObject = levelObject;
	}


	public void save() {
		System.out.println("Now writing to XML...");

		Document doc = new Document();
		System.out.println(myLevelObject.getLevelName());
		Element root = new Element(myLevelObject.getLevelName());
		doc.setRootElement(root);
		
		myRoot = root;

		saveLevelInfo();
		System.out.println("level saved");
		saveAI();
		saveSprites();

		writeToXML(doc);
		System.out.printf("Finished saving to %s.xml\n" , myLevelObject.getLevelName());
	}

	private void writeToXML(Document doc) {
		try { 
			XMLOutputter writer = new XMLOutputter();
			FileOutputStream out = new FileOutputStream(
				new File(myLevelObject.getLevelName() + ".xml"));
			writer.output(doc, out);
		} 
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveLevelInfo() {
		Element name = new Element("level");
		name.addContent(myLevelObject.getLevelName());
		myRoot.addContent(name);
		
		Element bg = new Element("background");
		bg.addContent(myLevelObject.getBackgroundImage());
		myRoot.addContent(bg);
	}


	/**
	 * @TODO - Use Reflection instead of hard-coded properties
	 * 	after requisite changes are implemented in AIAgent
	 */
	private void saveAI() {
		SpriteEditable agent = myLevelObject.getAI();
		
		if (agent == null)	return;
		
		HashMap<String, String> map = agent.getPropertyMap();
		
		Element ai = new Element("ai");
		
		Element location = new Element("location");
		
		Element x = new Element("x");
		x.addContent(map.get("x"));
		location.addContent(x);
		
		Element y = new Element("y");
		y.addContent(map.get("y"));
		location.addContent(y);
		
		ai.addContent(location);
		
		Element speed = new Element("speed");
		speed.addContent(map.get("speed"));
		ai.addContent(speed);
		
		myRoot.addContent(ai);
		
	}

	private void saveSprites() {
		for (SpriteEditable s : myLevelObject.getSprites()) {
			saveSprite(s);
		}
	}

	private void saveSprite (SpriteEditable s) {
		
		HashMap<String, String> properties = s.getPropertyMap();
		
		Element sprite = new Element("type");
		
		for (String key : properties.keySet()) {
			if (!key.equals("type")) {
				
				Element elt = new Element(key);
				String val = "" + properties.get(key);
				elt.addContent(val);
				
				sprite.addContent(elt);
			}
		}
		
		myRoot.addContent(sprite);
	}
	
	
}
