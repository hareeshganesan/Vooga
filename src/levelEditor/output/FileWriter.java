package LevelEditor.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author Peggy Li
 */


public class FileWriter {

	private LevelProxy myLevel;
	
	public FileWriter (LevelProxy level) {
		myLevel = level;
	}
	
	
	
	
	
	public String getFileName () {
		return myLevel.getLevelName();
	}
	
	
	public void writeToFile () {
		try {
			FileOutputStream out = new FileOutputStream (
					new File(getFileName() + ".json"));
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
