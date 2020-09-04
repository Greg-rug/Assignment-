package intSet;

/* If your IDE does not recognise annotations or assertions manually import the following */
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a dummy class. Feel free to delete it and generate your own using JUnit.
 * Important: make sure to fix your package directory yourself if you delete this!
 *
 * You can run the tests by clicking the green arrows next to "public class IntSetTest"
 */
public class IntSetTest {

    //  Test methods go here
	@Test
	public void testIsEmpty() {
		IntSet set = new IntSet(5);
		assertEquals(5, set.getCapacity());
	}
}
