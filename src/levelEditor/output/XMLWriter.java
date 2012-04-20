package levelEditor.output;

/**
 * @author Peggy Li (pl59)
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.golden.gamedev.object.Sprite;

import levelEditor.mvc.LevelObject;

public class XMLWriter {
	
	LevelObject myLevelObject;
	
	Element myRoot;

	public XMLWriter (LevelObject levelObject) {
		myLevelObject = levelObject;
	}


	public void save() {
		System.out.println("Now writing to XML...");

		Document doc = new Document();
		Element root = new Element(myLevelObject.getLevelName());
		doc.setRootElement(root);
		
		myRoot = root;

		saveLevelInfo();
		saveAI();
		saveSprites();

		writeToXML(doc);
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
		Element bg = new Element("background");
		bg.addContent(myLevelObject.getBackgroundImage());
		myRoot.addContent(bg);
	}



	private void saveAI() {
		Element ai = new Element("ai");
		
	}

	private void saveSprites() {
		for (Sprite s : myLevelObject.getSprites()) {
			saveSprite(s);
		}
	}

	private void saveSprite (Sprite s) {
		
		String name = s.getClass().getSimpleName();
		Element elt = new Element(name);
		
		Class c = s.getClass();
		Field[] fields = c.getFields();
		for (Field f : fields) {
			System.out.println(f.toGenericString());
		}
	}
}
