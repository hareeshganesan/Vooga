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
	private HashMap<Sprite, String> mySpriteMap;

	public XMLWriter (String name, HashMap<Sprite, String> sprites) {
		myFileName = name;
		mySpriteMap = sprites;
	}

	public void save () throws FileNotFoundException {

		System.out.println("Now writing to XML");
		
		try {
			Document doc = new Document();
			Element root = new Element("Sprites");
			doc.setRootElement(root);

			for (Sprite s : mySpriteMap.keySet()) {
				Element type = new Element("Block");
				root.addContent(type);
				
				Element imageURL = new Element("image");
				imageURL.addContent(mySpriteMap.get(s));
				type.addContent(imageURL);
				
				Element x = new Element("x");
				x.addContent(Double.toString(s.getX()));
				type.addContent(x);
				
				Element y = new Element("y");
				y.addContent(Double.toString(s.getY()));
				type.addContent(y);
				
			}

			XMLOutputter outputter = new XMLOutputter();
			FileOutputStream out = new FileOutputStream(
					new File("src/resources/"+myFileName + ".xml"));

			outputter.output(doc, out);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}



	}
}