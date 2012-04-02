package LevelEditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jdom.*;
import org.jdom.output.XMLOutputter;

import com.golden.gamedev.object.Sprite;

public class XMLWriter {

	private String myFileName;
	private HashMap<Sprite, String> myInanimateSpriteMap;
	private HashMap<FighterSprite, String> myFighterSpriteMap;

	public XMLWriter (String name, HashMap<Sprite, String> InanimateSprites,HashMap<FighterSprite, String> fighterSprites ) {
		myFileName = name;
		myInanimateSpriteMap = InanimateSprites;
		myFighterSpriteMap = fighterSprites;

	}

	public void save () throws FileNotFoundException {

		System.out.println("Now writing to XML");
		
		try {
			Document doc = new Document();
			Element root = new Element("Sprites");
			doc.setRootElement(root);

			for (Sprite s : myInanimateSpriteMap.keySet()) {
				Element type = new Element("Block");
				root.addContent(type);
				
				Element imageURL = new Element("image");
				imageURL.addContent(myInanimateSpriteMap.get(s));
				type.addContent(imageURL);
				
				Element x = new Element("x");
				x.addContent(Double.toString(s.getX()));
				type.addContent(x);
				
				Element y = new Element("y");
				y.addContent(Double.toString(s.getY()));
				type.addContent(y);
				
			}
			

			for (FighterSprite s : myFighterSpriteMap.keySet()) {
				Element type = new Element("Fighter");
				root.addContent(type);
				
				Element imageURL = new Element("img");
				imageURL.addContent(myFighterSpriteMap.get(s));
				type.addContent(imageURL);
				
				Element x = new Element("x");
				x.addContent(Double.toString(s.getXCoord()));
				type.addContent(x);
				
				Element y = new Element("y");
				y.addContent(Double.toString(s.getYCoord()));
				type.addContent(y);
				
				Element MaxHealth = new Element("health");
				MaxHealth.addContent(Integer.toString(s.getHP()));
				type.addContent(MaxHealth);
				
				Element Name = new Element("name");
				Name.addContent(s.getName());
				type.addContent(Name);
				
				Element Speed = new Element("speed");
				Speed.addContent(Double.toString(s.getSpeed()));
				type.addContent(Speed);
				
				
				
			}

			XMLOutputter outputter = new XMLOutputter();
			FileOutputStream out = new FileOutputStream(
					new File("src/resources/" + myFileName + ".xml"));

			outputter.output(doc, out);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}



	}
}