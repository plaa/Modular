package myplugin;

import example.foo.MyInt;

public class MyPlugin implements MyInt {
	
	@Override
	public void foo() {
		System.out.println("foo() called for MyPlugin");
	}
	
}
