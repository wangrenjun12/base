package org.wrj.syntax;

import org.junit.Assert;
import org.junit.Test;

public class Test1 {
	{
		System.out.println("{}");
	}
	
	static{
		System.out.println("static");
		System.out.println(1 + 2 + "Java"+ 3 + 4);
	}

	
	@Test
	public void test1() throws Exception {
		String p1 = "C:\\1.jpg";
		String p2 = p1.replace("\\", "/");
		System.out.println(p2);
		Assert.assertEquals(p2,"C:/1.jpg");
		
	}
	
	@Test
	public void test2(){
		Integer i = Integer.parseInt("12", 16);
		System.out.println(i);
	}
	

}
