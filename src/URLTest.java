import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


public class URLTest {
	
	public static void main(String[] args) throws Exception{
		System.out.println("输入URL是:"+args[0]);
		//String urlstr = args[0].replace("http://", "");
		//urlstr = URLEncoder.encode(urlstr);
		//urlstr = "http://"+urlstr;
		//System.out.println("经过URL编码后为:"+urlstr);
		URL url = new URL(args[0]);
		URLConnection connection = url.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String result = "";
		String line = null;
		while((line = br.readLine()) != null){
			result += line;
		}
		
		System.out.println(result);
		br.close();
	}

}
