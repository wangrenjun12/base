package org.wrj.generic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * ����java.lang.Class���÷�
 * @author think
 *
 */
public class ClassTest {

	
	@Test
	public void testClass(){
		try {
			Class<?> c = Class.forName("test.generic.MyClass");
			MyClass myClass = new MyClass();
			Assert.assertTrue(c.isInstance(myClass));
			Assert.assertFalse(c.isArray());
			Assert.assertFalse(c.isInterface());
			Assert.assertFalse(c.isPrimitive());
			Assert.assertFalse(c.isLocalClass());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Long> longList = new ArrayList<>();
		longList.add(1L);
		longList.add(2L);
		addNumber(longList);
		
	}
	
	
	public Number addNumber(List<? extends Number> list) {
		Number resultVal = 0;
		
		return resultVal;
	}

}
