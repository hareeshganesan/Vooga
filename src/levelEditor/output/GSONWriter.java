package levelEditor.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.google.gson.Gson;

@SuppressWarnings("unused")
public class GSONWriter {
	

	public GSONWriter () { }
	
	
	public void writeToFile(String filename) throws FileNotFoundException {
		Gson gson = new Gson();
		FileOutputStream out = new FileOutputStream(
				new File("levels" + filename + ".json"));
		
	}
	

}
