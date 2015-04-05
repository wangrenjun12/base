package org.wrj.dp.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
	
	private Object target;
	
	DogIntercepter di = new DogIntercepter();

	public Object invoke(Object arg0, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		if(method.getName().equals("info"))
		{
			di.method1();
			result = method.invoke(target, args);
			di.method2();
		}
		else
		{
			result = method.invoke(target, args);
		}
		return result;
	}
	
	public void setTarget(Object o)
	{
		this.target = 0;
	}

	public Object getTarget() {
		return target;
	}

}
