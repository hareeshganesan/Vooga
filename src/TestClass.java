import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLDecoder;


public class TestClass {

	public TestClass () {
		
	}
	
	public String toString () {
		return "Test passed";
	}
	
	public static void main (String[] args) throws UnsupportedEncodingException, URISyntaxException {
		String filename = "file:/Users/peggyli/Desktop/Computer%20Science/Vooga/bin/action/";
		//filename = URLDecoder.decode(filename, "utf-8");
		URI uri = new URI(filename);
		
		
		File f = new File(uri.getPath());
		//System.out.println("Exists " + (f.exists()));
		//System.out.println("File " + f.isFile());
		//System.out.println("dir " + f.isDirectory());
		//System.out.println("Hidden " + f.isHidden());
		
		
		URL[] urls = new URL[1];
		try {
			urls[0] = uri.toURL();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ClassLoader load = new URLClassLoader(urls);
		try {
			Class c = load.loadClass("action.Action");
			
			
			System.out.println(c != null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}

// replace %20 with " " 
// remove "file:" at start

