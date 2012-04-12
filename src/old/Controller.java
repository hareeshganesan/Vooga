package old;
import com.golden.gamedev.object.Sprite;


/**
 * 
 * @author Peggy Li / pl59 
 */


public class Controller {

	private Model myModel;
	private View myView;
	
	public Controller () {
		myModel = new Model(this);
		myView = new View(this);
	}
	
	public void saveAndClose (String levelName, String bgImage) {
		myModel.saveToXML(levelName, bgImage);
		myView.close();
	}
	
	public void saveAndClose (String levelName) {
		myModel.saveToXML(levelName, "");
		myView.close();
	}
	
	/**
	 * Calls model to check whether or not the desired level name
	 * is already in use
	 * 
	 * @TODO: If taken, View asks user whether to save over existing file, 
	 * prompts for new name if user responds no
	 */
	public boolean nameAvailable (String name) {
		return myModel.nameAvailabe(name);
	}
	
	/**
	 * Displays a specified message to the user in a popup dialog box
	 * @param message
	 */
	public void notifyUser (String message) {
		myView.displayMessage(message);
	}
	
	
	public void addSprite (Sprite s, String imageURL, int x, int y) {
		s.setLocation(x, y);
		myModel.addSprite(s, imageURL);
	}
}
