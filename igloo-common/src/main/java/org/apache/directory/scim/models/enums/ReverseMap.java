/**
 * 
 */
package org.apache.directory.scim.models.enums;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author swm16
 *
 */
public class ReverseMap<T extends Enum<T>> {
	
	private Map<String, T> reverseMap = new HashMap<String, T>();

	public ReverseMap(Class<T> enumType) {
		Method valuesMethod;
		try {
			valuesMethod = enumType.getMethod("values");
			T[] values = (T[]) valuesMethod.invoke(null);
			for(T value: values) {
				reverseMap.put(value.toString(), value);
			}
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public T valueOf(String alternativeName) {
		return reverseMap.get(alternativeName);
	}
	
}
