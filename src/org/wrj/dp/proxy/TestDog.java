package org.wrj.dp.proxy;

public class TestDog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dog targetObject = new DogImpl();
		Dog dog = null;
		Object proxy = MyProxyFactory.getProxy(targetObject);
		if(proxy instanceof Dog)
		{
			dog = (Dog)proxy;
		}
		dog.info();
		dog.run();
	}

}
