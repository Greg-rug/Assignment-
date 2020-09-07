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
	//capacities should be over 3 to ensure all tests work
	int capacity1 = 5;
	int capacity2 = 6;
	//values cannot have same value
	int value1 = -1;
	int value2 = 3;
	int value3 = 5;

	@BeforeEach
	public void initialiseSet() {
		set1 = new IntSet(capacity1);
		set2 = new IntSet(capacity2);
	}

	@Test
	public void testConstructor() {
		assertNotNull(set1);
		assertEquals(capacity1, set1.getCapacity());
	}

	@Test
	public void testConstructorWithNegativeCapacity() {
		assertThrows(IllegalArgumentException.class,
			()->{
				set3 = new IntSet(-1);
			});
		assertNull(set3);
	}

	@Test
	public void testIsEmpty() {
		assertTrue(set1.isEmpty());
	}

	@Test
	public void testHasValue() throws SetCapacityFullException {
		set1.add(value1);
		assertTrue(set1.has(value1));
		assertFalse(set1.has(value2));
	}

	@Test
	public void testAdd() throws SetCapacityFullException {
		set1.add(value1);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
	}

	@Test
	public void testAddSameValueTwice() throws SetCapacityFullException {
		set1 = new IntSet(capacity1);
		set1.add(value1);
		set1.add(value1);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
	}

	@Test
	public void testAddOverCapacity() throws SetCapacityFullException {
		set1 = new IntSet(1);
		set1.add(value1);
		assertThrows(SetCapacityFullException.class,
				()->{
					set1.add(value2);
				});
		assertNull(set3);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
		assertFalse(set1.has(value2));
	}

	@Test
	public void testRemove() throws SetCapacityFullException {
		set1.add(value1);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
		set1.remove(value1);
		assertEquals(0, set1.getCount());
		assertFalse(set1.has(value1));
	}

	@Test
	public void testRemoveElementNotInSet() throws SetCapacityFullException {
		set1.add(value1);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
		set1.remove(value2);
		assertEquals(1, set1.getCount());
		assertTrue(set1.has(value1));
	}

	@Test
	public void testNullSetInSetOperations() {
		assertThrows(NullPointerException.class,
				()->{
					set3 = set1.intersect(set3);;
				});
		assertNull(set3);

		assertThrows(NullPointerException.class,
				()->{
					set3 = set1.union(set3);;
				});
		assertNull(set3);

		assertThrows(NullPointerException.class,
				()->{
					set3 = set1.symmetricDifference(set3);;
				});
		assertNull(set3);

		assertThrows(NullPointerException.class,
				()->{
					set3 = set1.difference(set3);;
				});
		assertNull(set3);
	}

	@Test
	public void testIntersect() throws SetCapacityFullException {
		//initialisation
		set1.add(value1);
		set2.add(value1);
		set1.add(value2);
		//normal case
		set3 = set1.intersect(set2);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value1));
		assertFalse(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//reciprocal
		set3 = set2.intersect(set1);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value1));
		assertFalse(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//intersect on empty set
		set1 = new IntSet(0);
		set3 = set1.intersect(set2);
		assertEquals(0, set3.getCount());
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testUnion() throws SetCapacityFullException {
		//initialisation
		set1.add(value1);
		set2.add(value1);
		set1.add(value2);
		//normal case
		set3 = set1.union(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//reciprocal
		set3 = set2.union(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value2));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//union on empty set
		set1 = new IntSet(0);
		set3 = set1.union(set2);
		assertEquals(1, set3.getCount());
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testSymmetricDifference() throws SetCapacityFullException {
		//initialisation
		set1.add(value1);
		set2.add(value1);
		set1.add(value2);
		set2.add(value3);
		//normal case
		set3 = set1.symmetricDifference(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value2));
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//reciprocal
		set3 = set2.symmetricDifference(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value2));
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//symmetric difference on empty set
		set1 = new IntSet(0);
		set3 = set1.symmetricDifference(set2);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value2));
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testDifference() throws SetCapacityFullException {
		//initialisation
		set1.add(value1);
		set2.add(value1);
		set1.add(value2);
		set2.add(value3);
		//normal case
		set3 = set1.difference(set2);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value2));
		assertFalse(set3.has(value3));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//reciprocal
		set3 = set2.difference(set1);
		assertEquals(1, set3.getCount());
		assertTrue(set3.has(value3));
		assertFalse(set3.has(value2));
		assertFalse(set3.has(value1));
		assertEquals(capacity1 + capacity2, set3.getCapacity());
		//difference from empty set
		set1 = new IntSet(0);
		set3 = set1.difference(set2);
		assertEquals(0, set3.getCount());
		assertFalse(set3.has(value1));
		assertFalse(set3.has(value3));
		assertEquals(capacity2, set3.getCapacity());
		//difference of empty set
		set3 = set2.difference(set1);
		assertEquals(2, set3.getCount());
		assertTrue(set3.has(value1));
		assertTrue(set3.has(value3));
		assertEquals(capacity2, set3.getCapacity());
	}

	@Test
	public void testGetArray() throws SetCapacityFullException {
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
	public void testToString() throws SetCapacityFullException {
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
