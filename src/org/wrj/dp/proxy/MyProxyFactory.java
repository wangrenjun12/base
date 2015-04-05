package org.wrj.dp.proxy;

import java.lang.reflect.Proxy;

public class MyProxyFactory 
{
	public static Object getProxy(Object obj)
	{
		ProxyHandler handler = new ProxyHandler();
		handler.setTarget(obj);
		
		return Proxy.newProxyInstance(DogImpl.class.getClassLoader(),
				obj.getClass().getInterfaces(),handler);
	}

}
