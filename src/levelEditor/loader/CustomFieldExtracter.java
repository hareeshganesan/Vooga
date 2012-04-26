package levelEditor.loader;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class CustomFieldExtracter {
	
	private int height;
	private int weight;
	private double cost;
	private String name;
	
	public CustomFieldExtracter () {
		
	}
	
	public ArrayList<Field> getFields (Class c) {
		ArrayList<Field> fields = new ArrayList<Field>();
		for (Field f : c.getFields()) {
			fields.add(f);
		}
		return fields;
	}

	public static void main (String[] args) {
		CustomFieldExtracter e = new CustomFieldExtracter();
		System.out.print(e.getFields(e.getClass()));
		
	}
	
	
}
