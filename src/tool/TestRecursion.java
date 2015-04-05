package tool;

import java.util.ArrayList;
import java.util.List;

public class TestRecursion {
	
	public static void main(String[] args) {
		MyObject my = new MyObject();
		my.addChild(my);
	}

}


class MyObject{
	List<MyObject> objs = new ArrayList<MyObject>();
	static int count = 1;
	void addChild(MyObject o ){
		objs.add(o);
		for(MyObject my : o.objs){
			my.addChild(o);
			System.out.println(count++);
		}
	}
}
