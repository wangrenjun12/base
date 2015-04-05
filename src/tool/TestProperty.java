package tool;

import java.io.FileInputStream;
import java.util.Properties;

public class TestProperty {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("src/tool/jdbc.properties"));
			System.out.println("driver="+prop.getProperty("driver"));
			System.out.println("host="+prop.getProperty("host"));
			System.out.println("port="+prop.getProperty("port"));
			System.out.println("servername="+prop.getProperty("servername"));
			System.out.println("username="+prop.getProperty("username"));
			System.out.println("password="+prop.getProperty("password"));
			prop.clone();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
