package levelEditor.loader;

/**
 * @author Peggy Li (pl59)
 */

import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings("rawtypes")
public class SuperclassFilter {
	
	public SuperclassFilter () { }


	public <T> ArrayList<Class<T>> applyFilter(HashSet<Class> classes, Class superclass) {
		
		ArrayList<Class<T>> filteredClasses = new ArrayList<Class<T>>();
		for (Class<T> c : classes) {
			if (c.isAssignableFrom(superclass)) {
				filteredClasses.add(c);
			}
		}
		
		return filteredClasses;
	}
	
	

}
