package org.wrj.jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class VMParam {

	@Test
	public void printVMParam(){
		RuntimeMXBean RuntimemxBean = ManagementFactory.getRuntimeMXBean();
		Map<String, String> osProp = RuntimemxBean.getSystemProperties();
		Set<String> keys = osProp.keySet();
		for(String str: keys){
			System.out.println(str+"="+osProp.get(str));
		}
		List<String> arguments = RuntimemxBean.getInputArguments();
		for (String str : arguments) {
			System.out.println(str);
		}
		String xss = System.getProperty("Xss");
		System.out.println(xss);
		
		String xms = System.getProperty("DXms");
		System.out.println(xms);
	}
}
