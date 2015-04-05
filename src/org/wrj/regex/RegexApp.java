package org.wrj.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexApp {
	
	private static final String regex = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Pattern p = Pattern.compile("^A$");
//		String seq = "AAAAAAAA";
////		String[] strs = p.split(seq);
////		for(String str:strs)
////		{
////			System.out.println(str);
////		}
////		System.out.println(Pattern.quote("A"));
//		
//		p = Pattern.compile("A");
//		Matcher matcher = p.matcher(seq);
//		System.out.println(matcher.matches());
		 Pattern p = Pattern.compile("a*b");
		 Matcher m = p.matcher("aaaaab");
		 System.out.println(m.matches());
		 System.out.println(m.start()+":"+m.end());
		 System.out.println(regex.matches("tom@sina.cn"));

	}

}
