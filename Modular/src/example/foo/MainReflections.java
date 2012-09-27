package example.foo;

import java.util.Set;

import org.reflections.Reflections;

public class MainReflections {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		long t1 = System.currentTimeMillis();
		
		Reflections r = new Reflections("");
		Set<Class<? extends MyInt>> set = r.getSubTypesOf(MyInt.class);
		System.out.println("Result: " + set);
		
		for (Class<? extends MyInt> c : set) {
			MyInt impl = c.newInstance();
			impl.foo();
		}
		
		long t2 = System.currentTimeMillis();
		
		System.out.println("Took " + (t2 - t1) + " ms");
	}
	
	
}
