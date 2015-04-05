package tool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class RepacleChar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
            //中文注释
			String filePath = "story.txt";
			File file = new File(filePath);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String filePath2 = "out.txt";
			File file2 = new File(filePath2);
			FileWriter fw = new FileWriter(file2);
			BufferedWriter bw = new BufferedWriter(fw);
			String  str = "";
			while((str = br.readLine()) != null)
			{
				//System.out.println(str);
				str = str.replaceAll("[a-z0-9]{3,100}","");
				str = str.replaceAll("@ Copyright of ��ԭ���� @", "");
				str = str.replaceAll("���� @ C of ��ԭ���� @", "");
				if(str.endsWith("[a-z0-9]{1,5}"))
				{
					str = str.replaceAll("[a-z0-9]{1,5}", "");
				}
				System.out.println(str);
				bw.write(str+"\r\n");
				
			}
			
			br.close();
			fr.close();
			
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
