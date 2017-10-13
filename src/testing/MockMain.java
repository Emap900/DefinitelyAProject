package testing;

import java.util.ArrayList;
import java.util.List;

public class MockMain {
	public static void main(String[] args) {
		String a = "1";
		String b = "2";
		List<String> c = new ArrayList();
		c.add(a);
		c.add(b);
		List<List> d = new ArrayList();
		d.add(c);
	}
}
