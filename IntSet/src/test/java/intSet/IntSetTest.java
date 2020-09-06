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

	IntSet set1;
	IntSet set2;
	IntSet set3;

	@BeforeEach
	public void initialiseSet() {
		set1 = new IntSet(5);
		set2 = new IntSet(6);
	}

    //  Test methods go here
	@Test
	public void testConstructor() {
		assertNotNull(set1);
		assertEquals(5, set1.getCapacity());
		try {
			set3 = new IntSet(-1);
		} catch (IllegalArgumentException e) {
			//empty on purpose
		}
		assertNull(set3);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(set1.isEmpty());
	}

	@Test
	public void testHasValue() {
		set1.add(5);
		assertTrue(set1.has(5));
	}

	@Test
	public void testAdd() {
		set1.add(5);
		assertEquals(1, set1.getCount());
		testHasValue();
		set1 = new IntSet(5);
		set1.add(5);
		set1.add(5);
		assertEquals(1, set1.getCount());
		set1.add(4);
		assertEquals(2, set1.getCount());
		set1 = new IntSet(1);
		set1.add(5);
		set1.add(4);
		assertEquals(1, set1.getCount());
	}

	@Test
	public void testRemove() {
		set1.add(5);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(5));
		set1.remove(5);
		assertEquals(0, set1.getCount());
		assertFalse(set1.has(5));

		set1.add(5);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(5));
		set1.remove(6);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(5));
	}

	@Test
	public void testIntersect() {
		set3 = set1.intersect(set3);
		assertNull(set3);

		set1.add(5);
		set2.add(5);
		set1.add(6);

		set3 = set1.intersect(set2);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(5));
		assertFalse(set3.has(6));

		set3 = set2.intersect(set1);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(5));
		assertFalse(set3.has(6));

		set1 = new IntSet(5);
		set3 = set1.intersect(set2);
		assertEquals(0, set3.getCount());
	}

	@Test
	public void testUnion() {
		set3 = set1.union(set3);
		assertNull(set3);

		set1.add(5);
		set2.add(5);
		set1.add(6);

		set3 = set1.union(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(5));
		assertTrue(set3.has(6));

		set3 = set2.union(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(5));
		assertTrue(set3.has(6));

		set1 = new IntSet(5);
		set3 = set1.union(set2);
		assertEquals(1, set3.getCount());
	}

	@Test
	public void testSymmetricDifference() {
		set3 = set1.symmetricDifference(set3);
		assertNull(set3);

		set1.add(5);
		set2.add(5);
		set1.add(6);
		set2.add(7);

		set3 = set1.symmetricDifference(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(6));
		assertTrue(set3.has(7));
		assertFalse(set3.has(5));

		set3 = set2.symmetricDifference(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(6));
		assertTrue(set3.has(7));
		assertFalse(set3.has(5));

		set1 = new IntSet(5);
		set3 = set1.symmetricDifference(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(5));
		assertTrue(set3.has(7));
	}

	@Test
	public void testDifference() {
		set3 = set1.difference(set3);
		assertNull(set3);

		set1.add(5);
		set2.add(5);
		set1.add(6);
		set2.add(7);

		set3 = set1.difference(set2);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(6));
		assertFalse(set3.has(7));
		assertFalse(set3.has(5));

		set3 = set2.difference(set1);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(7));
		assertFalse(set3.has(6));
		assertFalse(set3.has(5));

		set1 = new IntSet(5);
		set3 = set1.difference(set2);
		assertEquals(0, set3.getCount());
		assertFalse(set3.has(5));
		assertFalse(set3.has(7));

		set3 = set2.difference(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(5));
		assertTrue(set3.has(7));
	}

	@Test
	public void testGetArray() {
		set1.add(3);
		set1.add(4);
		set1.add(5);
		int [] arr = set1.getArray();
		assertEquals(arr.length, set1.getCount());
		for (int i = 0; i < arr.length; i++) {
			assertTrue(set1.has(arr[i]));
		}
	}

	@Test
	public void testToString() {
		assertEquals("{}", set1.toString());
		set1.add(1);
		assertEquals("{1}", set1.toString());
		set1.add(2);
		assertEquals("{1, 2}", set1.toString());
	}

}
