package levelEditor.mvc;

/**
 * @author Peggy Li (pl59)
 */


public class Controller {
	
	private Model myModel;
	private View myView;
	
	public Controller () {
		myModel = new Model(this);
		myView = new View(this);
	}
	
	
	
	public void displayMessageToUser (String message) {
		myView.displayMessage(message);
	}
	
	
	public boolean levelNameAvailable (String name) {
		return myModel.verifyNameAvailable(name);
	}
	
}
