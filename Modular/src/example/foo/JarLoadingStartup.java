package example.foo;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;

public class JarLoadingStartup {
	
	private static final String[] CLASSPATH = new String[] {
			"bin/", // Eclipse compiles here
			"myplugin.jar",
			"lib/aopalliance.jar",
			"lib/guice-3.0.jar",
			"lib/guice-multibindings-3.0.jar",
			"lib/javax.inject.jar",
	};
	
	public static void main(String[] args) throws Exception {
		
		/*
		 * I was unable to get using the parent class loader to work,
		 * it always throws ClassNotFoundException.  Therefore this
		 * list contains all the dependencies, and removes the old
		 * class loader completely.  This is the same way the Eclipse
		 * JIJ-loader works.
		 * 
		 * http://javasourcecode.org/html/open-source/eclipse/eclipse-3.5.2/org/eclipse/jdt/internal/jarinjarloader/JarRsrcLoader.java.html
		 */
		
		URL[] urls = new URL[CLASSPATH.length];
		for (int i = 0; i < urls.length; i++) {
			File f = new File(CLASSPATH[i]);
			if (!f.exists()) {
				System.out.println("ERROR:  " + f + " not found");
			}
			urls[i] = f.toURI().toURL();
		}
		
		ClassLoader currentClassLoader = Thread.currentThread().getContextClassLoader();
		MyURLClassLoader urlClassLoader = new MyURLClassLoader(urls, null);
		Thread.currentThread().setContextClassLoader(urlClassLoader);
		
		System.out.println("Classpath updated");
		
		// Invoke next startup class by reflection using the new class loader
		Class<?> c = Class.forName("example.foo.MainGuice", true, urlClassLoader);
		Class<?> paramType = (new String[0]).getClass();
		Method m = c.getMethod("main", paramType);
		m.invoke(null, (Object) args);
		
	}
	
}
