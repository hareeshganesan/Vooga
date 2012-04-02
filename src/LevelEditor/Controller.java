package LevelEditor;

import java.io.FileNotFoundException;

import com.golden.gamedev.object.Sprite;

public class Controller {
	private boolean isActive;
	private Model myModel;
	@SuppressWarnings("unused")
	private View myView;
	
	public Controller () {
		myModel = new Model(this);
		myView = new View(this);
		isActive = true;
	}

	
	public void addInanimateSprite (Sprite item, String imageURL, int x, int y) {
		item.setLocation(x, y);
		myModel.addNonFighterSprite(item, imageURL);
		
	}
	public boolean isActive(){
		return isActive;
		
	}
	public void setIsActive(boolean b){
		isActive = b;
	}
	public void addFighterSprite( FighterSprite item,String imageURL){
		myModel.addFighterSprite(item, imageURL);
	}
	
	public void finish (String levelName) throws FileNotFoundException {
		myModel.setLevelName(levelName);
		myModel.save();
	}
	
}
