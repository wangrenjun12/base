package tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.net.URL;
import java.net.URLConnection;

public class Jinjiang {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			 StringBuffer document = new StringBuffer();
			URL url = new URL("http://www.jjwxc.net/onebook.php?novelid=708185");
			// 10.0.16.12  80
			SocketAddress  sa = new InetSocketAddress("10.0.16.12",80);
			Proxy proxy = new Proxy(Proxy.Type.HTTP,sa);
			URLConnection conn = url.openConnection(proxy);
			System.out.println(conn.getHeaderField("charset"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
            {
            	 document.append(line + "\r\n");
            }
            String str = document.toString();
            str = new String(str.getBytes("ISO-8859-1"),"GB2312");
            System.out.println(str);
            
            reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
