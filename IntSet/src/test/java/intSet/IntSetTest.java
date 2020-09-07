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
	int capacity1 = 5;
	int capacity2 = 6;
	int value1 = -1;
	int value2 = 3;
	int value3 = 5;

	@BeforeEach
	public void initialiseSet() {
		set1 = new IntSet(capacity1);
		set2 = new IntSet(capacity2);
	}

    //  Test methods go here
	@Test
	public void testConstructor() {
		assertNotNull(set1);
		assertEquals(capacity1, set1.getCapacity());
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
		set1.add(value1);
		assertTrue(set1.has(value1));
	}

	@Test
	public void testAdd() {
		set1.add(value1);
		assertEquals(1, set1.getCount());
		testHasValue();

		set1 = new IntSet(capacity1);
		set1.add(value1);
		set1.add(value1);
		assertEquals(1, set1.getCount());
		set1.add(value2);
		assertEquals(2, set1.getCount());

		set1 = new IntSet(1);
		assertTrue(set1.add(value1));
		assertFalse(set1.add(value2));
		assertEquals(1, set1.getCount());
	}

	@Test
	public void testRemove() {
		set1.add(value1);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
		set1.remove(value1);
		assertEquals(0, set1.getCount());
		assertFalse(set1.has(value1));

		set1.add(value1);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
		set1.remove(value2);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
	}

	@Test
	public void testIntersect() {
		set3 = set1.intersect(set3);
		assertNull(set3);

		set1.add(value1);
		set2.add(value1);
		set1.add(value2);

		set3 = set1.intersect(set2);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value1));
		assertFalse(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set3 = set2.intersect(set1);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value1));
		assertFalse(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set1 = new IntSet(0);
		set3 = set1.intersect(set2);
		assertEquals(0, set3.getCount());
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testUnion() {
		set3 = set1.union(set3);
		assertNull(set3);

		set1.add(value1);
		set2.add(value1);
		set1.add(value2);

		set3 = set1.union(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set3 = set2.union(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set1 = new IntSet(0);
		set3 = set1.union(set2);
		assertEquals(1, set3.getCount());
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testSymmetricDifference() {
		set3 = set1.symmetricDifference(set3);
		assertNull(set3);

		set1.add(value1);
		set2.add(value1);
		set1.add(value2);
		set2.add(value3);

		set3 = set1.symmetricDifference(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value2));
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set3 = set2.symmetricDifference(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value2));
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set1 = new IntSet(0);
		set3 = set1.symmetricDifference(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value2));
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testDifference() {
		set3 = set1.difference(set3);
		assertNull(set3);

		set1.add(value1);
		set2.add(value1);
		set1.add(value2);
		set2.add(value3);

		set3 = set1.difference(set2);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value2));
		assertFalse(set3.has(value3));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set3 = set2.difference(set1);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value2));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());

		set1 = new IntSet(0);
		set3 = set1.difference(set2);
		assertEquals(0, set3.getCount());
		assertFalse(set3.has(value1));
		assertFalse(set3.has(value3));
		assertEquals(capacity2, set3.getCapacity());

		set3 = set2.difference(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value3));
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testGetArray() {
		set1.add(value1);
		set1.add(value2);
		set1.add(value3);
		int [] arr = set1.getArray();
		assertEquals(arr.length, set1.getCount());
		for (int value : arr) {
			assertTrue(set1.has(value));
		}
	}

	@Test
	public void testToString() {
		//test for empty set
		assertEquals("{}", set1.toString());
		set1.add(value1);
		//test for set with 1 element
		assertEquals("{" + value1 + "}", set1.toString());
		set1.add(value2);
		//test for set with multiple elements
		assertEquals("{" + value1 + ", " + value2 + "}", set1.toString());
	}

}
