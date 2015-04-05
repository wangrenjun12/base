package org.wrj.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Test;

public class RegexTest {

	@Test
	public void testIP() throws Exception {
		 Pattern p = Pattern.compile("a*b");
		 Matcher m = p.matcher("aaaaab");
		 boolean b = m.matches();
		 Assert.assertEquals(true, b);
		
		 String regex = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}";
		 Pattern p2 = Pattern.compile(regex);
		 Matcher m2 = p2.matcher("192.168.0.4");
		 Assert.assertEquals("Hello", true, m2.matches());
	}
	
	@Test
	public void testSplit() throws Exception
	{
		Pattern p = Pattern.compile("\\.");
		System.out.println(p.toString());
		String[] strs = p.split("192.168.0.4");
		for(String str:strs)
		{
			System.out.println(str);
		}
	}
	
	@Test
	public void testReplace() throws Exception {
		String st1 = "c:\\\\1.jpg";
		String st2 = st1.replaceAll("\\\\", "/");
		System.out.println("st2:"+st2);
	}
	
}
