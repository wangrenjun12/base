package org.wrj.syntax;

import org.junit.Assert;
import org.junit.Test;

public class StringTest {
	@Test
	public void test1(){
		String a = "a";
		String b = "b";
		String ab = a + b;
		Assert.assertTrue(ab == "a" + "b");
		Assert.assertTrue(ab == a + b);
		
	}

}
