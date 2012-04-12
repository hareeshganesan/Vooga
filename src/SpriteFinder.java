import java.io.File;
import java.util.ArrayList;

/**
 * @author Peggy Li (pl59)
 */

@SuppressWarnings("rawtypes")
public class SpriteFinder {

	private ArrayList<Class> mySprites;
	
	public SpriteFinder () {
		mySprites = new ArrayList<Class>();
	}
	
	public void findSprites (File file) {
		if (file.isFile()) {
			System.out.println(file.getName());
			return;
		}
		else {
			for (File sub : file.listFiles()) {
				findSprites(sub);
			}
		}
	}
	
	public static void main (String[] args) {
		SpriteFinder f = new SpriteFinder();
		//f.findSprites(new File(System.getProperty("user.dir")));
		
		System.out.println(f.getClass().getResource("src/"));
	}
}
