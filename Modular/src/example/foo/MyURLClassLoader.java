package example.foo;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

public class MyURLClassLoader extends URLClassLoader {
	
	public MyURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
		super(urls, parent, factory);
	}
	
	public MyURLClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
	
	public MyURLClassLoader(URL[] urls) {
		super(urls);
	}
	
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		System.out.println("TRYING TO FIND CLASS " + name);
		return super.findClass(name);
	}
}