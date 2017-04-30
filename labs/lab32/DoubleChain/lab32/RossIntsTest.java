package lab32;

import static org.junit.Assert.*;

import org.junit.Test;

public class RossIntsTest {

	@Test
	public void test() {
		RossInts x = new RossInts(2);
		int num = x.get();
		System.out.println(num);
		assertEquals(num, 20);
	}

}
