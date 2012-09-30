package example.foo;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

class PluginModule extends AbstractModule {
	@Override
	public void configure() {
		Multibinder<MyInt> myint = Multibinder.newSetBinder(binder(), MyInt.class);
		
		try {
			// Find plugin by name using reflection
			// In reality the name would be got from examining the JAR file contents
			Class<? extends MyInt> c = (Class<? extends MyInt>) Class.forName("myplugin.MyPlugin");
			myint.addBinding().to(c);
			
			Class.forName("ShouldNotFoundClass"); // Get stack trace anyway
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load MyPlugin: " + e);
			e.printStackTrace();
		}
	}
}