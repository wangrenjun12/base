package org.wrj.common;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Test {

	public static void main(String[] args) throws IOException {
		/*
		 * System.out.println("Test"); String st1 = "aaa";
		 * System.out.println(st1+null);
		 */
		// test1();
		// test2();
		// test4();
		// printArray();
		// test5();
		// test6();
		// test7();
		// test8("2010-09-33","yyyy-MM-dd");
		// test9();
		//test10();
		test11();
	}

	public static void test1() {
		long[] a = new long[10000];
		Arrays.fill(a, 1L);
		long curr = System.currentTimeMillis();
		System.out.println(System.currentTimeMillis() - curr);
	}

	public static void test2() {
		DateFormat df = DateFormat.getInstance();
		String st1 = df.format(new Date());
		System.out.println(st1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String st2 = sdf.format(new Date());
		System.out.println(st2);
	}

	public static void test3() {
		Double d1 = 34.0;
		double d2 = 2.0;
		double d3 = d1 / d2;
	}

	public static void test4() throws IOException {
		StringBuffer sb = new StringBuffer();
		File folder = new File("E:\\dev\\doc\\TaskList\\");
		String str = getFilePath(folder);
		System.out.println(str);
	}

	private static String getFilePath(File f) throws IOException {
		StringBuffer sb = new StringBuffer();
		if (f.isFile()) {
			// sb.append(f.getName()).append("\r\n");
			// sb.append(f.getPath()).append("\r\n");
			sb.append(f.getCanonicalPath()).append("\r\n");
		} else {
			File[] fileList = f.listFiles();
			for (File tmp : fileList) {
				sb.append(getFilePath(tmp));
			}
		}

		return sb.toString();
	}

	private static void printArray() {
		String aa = "锟斤拷锟斤拷锟叫癸拷锟斤拷";
		byte[] b1 = aa.getBytes();
		for (byte by1 : b1) {
			System.out.print(by1 + ",");
		}
		System.out.println();
		String ab = "I am chinese";
		byte[] b2 = ab.getBytes();
		for (byte by2 : b2) {
			System.out.print(by2 + ",");
		}
	}

	private static void test5() {
		String formatStr = "yyyy-MM-dd";
		DateFormat format = new SimpleDateFormat(formatStr);
		try {
			Date d = format.parse("2010-08-17bb");
			System.out.println("锟斤拷锟斤拷时锟斤拷锟斤拷,时锟斤拷为:" + d);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("锟斤拷锟斤拷时锟斤拷锟届常");
		}
	}

	private static void test6() {
		String st1 = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";
		System.out.println("2010-30-1b".matches(st1));

	}

	private static void test7() {
		String st1 = URLEncoder.encode("abcd");
		System.out.println("abcd->" + st1);
	}

	private static void test8(String dateStr, String formatStr) {
		DateFormat format = new SimpleDateFormat(formatStr);
		Date d = null;
		try {
			d = format.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("时锟斤拷锟绞斤拷锟斤拷戏锟�");
		}
		System.out.println("时锟斤拷锟绞矫伙拷锟斤拷锟�,时锟斤拷锟角ｏ拷" + d);
	}

	private static void test9() {
		String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
		System.out.println("2008-01-12".matches(regex));
		System.out.println("2008-02-30".matches(regex));
		System.out.println("2008-02-29".matches(regex));
		System.out.println("2000-02-30".matches(regex));
		System.out.println("2008-11-12".matches(regex));
	}

	private static void test10() {
		List lst = new ArrayList<String>();
		lst.add(1);
		lst.add("Test004");
		List<Integer> ls = lst;
		ls.add(2);
		for (int i = 0; i < ls.size(); i++) {
			System.out.println(ls.get(i));
		}
	}

	private static void test11() {
		String str = Encode.decodeFromBase64("PGgxPrjQvvXE+rbUt++7y834tcTWp7PWPGEgaHJlZj0naHR0cDovL3d3dy5pZmVuZy5jb20nPsfr"
				+ "teO79zwvYT4gPGgxPg==");
		System.out.println(str);
	}
}
