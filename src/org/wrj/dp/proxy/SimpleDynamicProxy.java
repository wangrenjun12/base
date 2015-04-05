package org.wrj.dp.proxy;

import java.lang.reflect.Proxy;

public class SimpleDynamicProxy {

	public static void consumer(MyInterface iterface) {
		iterface.doSomething();
		iterface.somethingElse("imaingecup");
	}

	public static void main(String[] args) {
		MyObject real = new MyObject();
		consumer(real);

		MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
				MyInterface.class.getClassLoader(),
				new Class[] { MyInterface.class },
				new DynamicProxyHandler(real));
		consumer(proxy);
	}

}
