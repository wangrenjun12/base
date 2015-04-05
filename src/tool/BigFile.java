package tool;

import java.io.BufferedReader;
import java.io.FileReader;

public class BigFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"D:\\Program Files\\easyMule\\Incoming\\www.csdn.net.sql"));

			String line = null;
			int i = 0;
			long begin = System.currentTimeMillis();
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				i++;
				
			}
			long end = System.currentTimeMillis();
			System.out.println((end-begin)/1000.0);
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
