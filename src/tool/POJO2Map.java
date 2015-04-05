package tool;

import java.awt.Point;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class POJO2Map {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		Calendar cal  = Calendar.getInstance();
		Map<String, Object> properties = mapProperties(cal);
		printMap(properties);
		Point p = new Point(20,30);
		properties = mapProperties(p);
		printMap(properties);

	}

	public static Map<String, Object> mapProperties(Object bean)
			throws Exception {
		Map<String, Object> properties = new HashMap<String, Object>();
		for (Method method : bean.getClass().getDeclaredMethods()) {
			if (Modifier.isPublic(method.getModifiers())
					&& method.getParameterTypes().length == 0
					&& method.getReturnType() != void.class
					&& method.getName().matches("^(get|is).+")) {
				String name = method.getName().replaceAll("^(get|is)", "");
				name = Character.toLowerCase(name.charAt(0))
						+ (name.length() > 1 ? name.substring(1) : "");
				Object value = method.invoke(bean);
				properties.put(name, value);
			}
		}
		return properties;
	}
	
	
	public static void printMap(Map<String,Object> map)
	{
		Set<String> keys = map.keySet();
		for(String str:keys)
		{
			System.out.println(str+":"+map.get(str));
		}
	}

}
