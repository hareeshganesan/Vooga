package levelEditor.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JFileChooser;

/**
 * 
 * @author Peggy Li (pl59)
 * @author Nathan Klug
 */

@SuppressWarnings("rawtypes")
public class SpriteClassLoader {
	
	private HashSet<URL> myURLs;
	
	private HashSet<Class> myLoadedClasses;
	
	public SpriteClassLoader () {
		myURLs = new HashSet<URL>();
		
		myLoadedClasses = new HashSet<Class>();
	}

	
	public void load () {
		//File root = getRootFile();
		File root = new File(System.getProperty("user.dir")); 
		iterate(root);
		loadClasses();
	}
	
	
	public HashSet<Class> getClasses () {
		return myLoadedClasses;
	}
	
	
	
	public String[] getSpriteClassNames() {
		ArrayList<Class> classes = new ArrayList<Class>(myLoadedClasses);
		String[] names = new String [classes.size()];
		
		for (int i = 0; i < names.length; i++) {
			names[i] = classes.get(i).getSimpleName();
		}
		return names;
	}
	
	
	private void loadClasses () {
		URL[] urls = new URL[myURLs.size()];
		urls = myURLs.toArray(urls);
		
		URLClassLoader loader = new URLClassLoader(urls);
		
		for (URL url : loader.getURLs()) {
			try {
				String s = url.toURI().getPath();
				File dir = new File(s);
				
				for (File f : dir.listFiles()) {
					String path = f.getAbsolutePath();
					
					int dotclass = path.lastIndexOf(".class");
					if (dotclass == -1) continue;
					
					path = path.substring(0, dotclass);
					
					Class c = loadClassFromPath(path, 0, loader);
					
					if (c != null) {
						myLoadedClasses.add(c);
					}
					
				}
			}
			catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Try loading class 
	 * 
	 * NOTE ClassLoader uses pathname in <package.classname> format when loading class
	 * 		to allow for multi-level packaging, recursively check parent directory
	 * 		until local root file is reached
	 * 
	 * EXAMPLE /user/name/desktop/vooga/src/package/subpackage/target(.class)
	 * 		First try to load target, then subpackage.target... package.subpackage.target
	 * 		until user.name...target is reached, then base case is reached
	 * 
	 * @param path full file path for a class file
	 * @param level number of file hierarchy levels relative to class file
	 * @param loader ClassLoader object
	 * @return class loaded using given path name
	 */
	private Class loadClassFromPath (String path, int level, ClassLoader loader) {

		Class c;

		int backslash = path.lastIndexOf("/");
		for (int i = 0; i < level; i ++) {
			path = path.substring(0, backslash) + "." + path.substring(backslash + 1);

			/**
			 * BASE CASE occurs once root directory is reached recursively
			 */
			if (path.contains("/") == false) {
				return null;
			}			
			backslash = path.lastIndexOf("/");
		}
		
		String subpath = path.substring(backslash + 1);

		try {	
			c = loader.loadClass(subpath);
		}
		
		catch (ClassNotFoundException e) {
			c = loadClassFromPath(path, level + 1, loader);
		}
		catch (java.lang.NoClassDefFoundError err) {
			c = loadClassFromPath(path, level + 1, loader);
		}

		return c;
	}

	
	/**
	 * Prompt user to select starting directory in which to search for files
	 * @return file selected by user
	 */
	@SuppressWarnings("unused")
	private File getRootFile () {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.showOpenDialog(null);
		return chooser.getSelectedFile();
	}
	
	
	/**
	 * Checks each file to see whether name ends in .class
	 * 	and adds PARENT directory of each class file to list
	 * Recursively checks files within a directory
	 */
	private void iterate (File file) {
		if (file.isFile() && file.getName().endsWith(".class")) {
			try {
				myURLs.add(file.getParentFile().toURI().toURL());
			} 
			catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

		// param <file> is a directory
		File[] subfiles = file.listFiles();
		if (subfiles == null)	return;

		for (File sub : subfiles) {
			iterate(sub);
		}
	}
	


}
