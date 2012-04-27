package levelEditor.loader;

import java.util.ArrayList;

public abstract class AbstractFilter {

	public abstract <T> ArrayList<Class<T>> applyFilter ();
	
}
