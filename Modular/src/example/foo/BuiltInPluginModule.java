package example.foo;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class BuiltInPluginModule extends AbstractModule {
	
	@Override
	public void configure() {
		Multibinder<MyInt> myint = Multibinder.newSetBinder(binder(), MyInt.class);
		myint.addBinding().to(MyIntImpl.class);
	}
	
}
