package levelEditor.mvc;

/**
 * @author Peggy Li (pl59)
 */


public class Controller {

	private Model myModel;
	private View myView;

	/* ================================== SINGLETON ================================== */

	private static Controller controller;

	private Controller () {
		myModel = new Model(this);
		myView = new View(this);
	}

	public static Controller getInstance () {
		if (controller == null) 
			controller = new Controller();
		return controller;
	}


	/* ================================ CALL MODEL/VIEW ================================ */

	/**
	 * Save level-being-edited to external text file and close GUI window 
	 */
	public void saveAndClose () {
		myModel.saveToFile();
		myView.close();
	}


	public String[] getClassNamesArray () {
		return null;
	}
	
	
	public void setProperty (String property, String value) {
		System.out.printf("Set %s to %s \n", property, value);
	}
	

	public void displayMessageToUser (String message) {
		myView.displayMessage(message);
	}


	public boolean levelNameAvailable (String name) {
		return myModel.verifyNameAvailable(name);
	}


}
