package example.foo;

import java.util.Set;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

public class MainGuice {
	
	@Inject
	private Set<MyInt> impls;
	
	
	public void run() {
		System.out.println("Plugin count: " + impls.size());
		for (MyInt i : impls) {
			i.foo();
		}
	}
	
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new PluginModule(), new BuiltInPluginModule());
		injector.getInstance(MainGuice.class).run();
	}
}
