package levelEditor.mvc;

/**
 * @author Peggy Li (pl59)
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.JFileChooser;


public class SpriteClassFinder {
	
	private static final String PATH = 
			"/Users/peggyli/Desktop/Computer Science";
	
	public static void main (String[] args) {
		SpriteClassFinder finder = new SpriteClassFinder();
		finder.findSprites();
	}

	private ArrayList<URL> myURLs;

	public SpriteClassFinder () {
		myURLs = new ArrayList<URL>();
	}

	public void findSprites () {
		/*
		File[] selectedFiles = getRootFiles();
		for (File file : selectedFiles) {
			System.out.println(file.getPath());
			iterate(file);
		}*/
		
		File selectedFile = getRootFile();
		iterate(selectedFile);
		
		makeClassLoader();
	}
	
	private void makeClassLoader () {
		URL[] urls = new URL[myURLs.size()];
		for (int i = 0; i < myURLs.size(); i++) {
			urls[i] = myURLs.get(i);
		}
		
		URLClassLoader loader = new URLClassLoader(urls);
		for (URL url : loader.getURLs()) {
			System.out.println(url.toString());
		}
	}

	private File[] getRootFiles () {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFiles();

	}
	
	private File getRootFile () {
		JFileChooser chooser = new JFileChooser(PATH);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile();
	}

	@SuppressWarnings("deprecation")
	private void iterate (File file) {
		if (file.isFile() && 
				file.getName().endsWith(".class")) {
			try {
				myURLs.add(file.toURL());
			} 
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		File[] subfiles = file.listFiles();
		if (subfiles == null)	return;

		for (File sub : subfiles) {
			iterate(sub);
		}
	}

	
}
