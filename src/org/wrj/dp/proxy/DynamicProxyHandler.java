package org.wrj.dp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DynamicProxyHandler implements InvocationHandler {
	private Object proxied;

	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		/*System.out.println("******** proxy: " + proxy.getClass()
				+ " , method: " + method + " ,args: " + args);*/
		if (method.getName().equals("doSomething")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sdf.format(new Date()));
			return method.invoke(proxied, args);
		} else {
			System.out.println("Today is nothing");
		}
		/*if (args != null) {
			for (Object arg : args) {
				System.out.println("   " + arg);
			}
		}*/
		return method.invoke(proxied, args);
	}
}
