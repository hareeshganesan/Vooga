package levelEditor.loader;

/**
 * @author Peggy Li (pl59)
 */

import java.util.ArrayList;
import java.util.HashSet;

@SuppressWarnings("rawtypes")
public class SuperclassFilter extends AbstractFilter {
	
	HashSet<Class> myClasses;
	
	public SuperclassFilter (HashSet<Class> classes) {
		myClasses = classes;
	}


	@Override
	public <T> ArrayList<Class<T>> applyFilter() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
