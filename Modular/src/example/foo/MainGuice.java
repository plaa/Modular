package example.foo;

import java.util.Set;

import org.reflections.Reflections;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.multibindings.Multibinder;

public class MainGuice {
	
	private final Set<MyInt> impls;
	
	@Inject
	public MainGuice(Set<MyInt> impls) {
		this.impls = impls;
	}
	
	
	public void run() {
		System.out.println("Count: " + impls.size());
		for (MyInt i : impls) {
			i.foo();
		}
	}
	
	
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new PluginModule());
		
		injector.getInstance(MainGuice.class).run();
		
	}
}

class PluginModule extends AbstractModule {
	@Override
	public void configure() {
		Multibinder<MyInt> myint = Multibinder.newSetBinder(binder(), MyInt.class);
		
		
		Reflections r = new Reflections("");
		Set<Class<? extends MyInt>> set = r.getSubTypesOf(MyInt.class);
		System.out.println("Found interfaces: " + set);
		
		for (Class<? extends MyInt> c : set) {
			myint.addBinding().to(c);
		}
		
		//		myint.addBinding().to(MyIntImpl.class);
		//		myint.addBinding().to(MyImpl2.class);
	}
}
