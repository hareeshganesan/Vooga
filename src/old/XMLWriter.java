package old;
/**
 * 
 * @author Peggy Li / pl59 
 */


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.golden.gamedev.object.Sprite;


public class XMLWriter {

	private String myFileName;
	private String myBackgroundImage;
	private HashMap<Sprite, String> mySprites;

	public XMLWriter (String nameOfLevel, String backgroundImage, HashMap<Sprite, String> sprites) {
		myFileName = nameOfLevel;
		myBackgroundImage = backgroundImage;
		mySprites = sprites;
	}

	/**
	 * Saves current state of user-designed level from GUI view
	 * by writing information to a new XML document that can be
	 * loaded to create a working custom-built level
	 * 
	 * Each element represents one Sprite added, with tags for
	 * properties such as x and y positions, health
	 */
	public void saveToXML () {

		System.out.println("Now writing to XML");

		try {
			Document doc = new Document();
			Element root = new Element(myFileName + "_data");
			doc.setRootElement(root);

			Element background = new Element("background");
			background.addContent(myBackgroundImage);
			root.addContent(background);


			for (Sprite s : mySprites.keySet()) {
				saveSprite(s, root);
			}

			XMLOutputter outputter = new XMLOutputter();
			FileOutputStream out = new FileOutputStream(
					new File("levels/" + myFileName + ".xml"));

			outputter.output(doc, out);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveSprite (Sprite s, Element root) {
		
		Element type = new Element(s.getClass().getSimpleName());
		root.addContent(type);

		Element imageURL = new Element("image");
		imageURL.addContent(mySprites.get(s));
		type.addContent(imageURL);

		Element x = new Element("x");
		x.addContent(Double.toString(s.getX()));
		type.addContent(x);

		Element y = new Element("y");
		y.addContent(Double.toString(s.getY()));
		type.addContent(y);
	}
}
