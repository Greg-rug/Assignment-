package intSet;

import java.util.ArrayList;

/**
 * Representation of a finite set of integers.
 *
 * @invariant getCount() >= 0
 * @invariant getCount() <= getCapacity()
 */
public class IntSet {

    private final ArrayList<Integer> set;
    private final int capacity;
    private int count;

    /**
     * Creates a new set with 0 elements.
     *
     * @param capacity the maximal number of elements this set can have
     * @pre capacity >= 0
     * @post getCount() == 0
     * @post getCapacity() == capacity
     */
    public IntSet(int capacity) throws IllegalArgumentException {
        if (capacity < 0) throw new IllegalArgumentException("capacity of a set must be bigger or equal to 0");
        set = new ArrayList<>();
        this.capacity = capacity;
        count = 0;
    }

    /**
     * Test whether the set is empty.
     *
     * @return getCount() == 0
     */
    public boolean isEmpty() {
        return getCount() == 0;
    }

    /**
     * Test whether a value is in the set
     *
     * @return exists int v in getArray() such that v == value
     */
    public boolean has(int value) {
        for (Integer i : set) {
            if (value == i) return true;
        }
        return false;
    }

    /**
     * Adds a value to the set.
     *
     * @pre getCount() < getCapacity()
     * @post has(value)
     * @post !this@pre.has(value) implies (getCount() == this@pre.getCount() + 1)
     * @post this@pre.has(value) implies (getCount() == this@pre.getCount())
     */
    public void add(int value) throws SetCapacityFullException {
        if (!has(value)) {
            if (count >= capacity) throw new SetCapacityFullException("adding to full set");
            set.add(value);
            count++;
        }
    }

    /**
     * Removes a value from the set.
     *
     * @post !has(value)
     * @post this@pre.has(value) implies (getCount() == this@pre.getCount() - 1)
     * @post !this@pre.has(value) implies (getCount() == this@pre.getCount())
     */
    public void remove(int value) {
        for (Integer i : set) {
            if (i == value) {
                set.remove(i);
                count--;
                return;
            }
        }
    }

    /**
     * Returns the intersection of this set and another set.
     *
     * @param other the set to intersect this set with
     * @return the intersection
     * @pre other != null
     * @post forall int v: (has(v) and other.has(v)) implies return.has(v)
     * @post forall int v: return.has(v) implies (has(v) and other.has(v))
     */
    public IntSet intersect(IntSet other) throws NullPointerException, SetCapacityFullException {
        if (other != null) {
            IntSet newSet = new IntSet(capacity + other.getCapacity());
            for (Integer i : set) {
                if (other.has(i)) newSet.add(i);
            }
            return newSet;
        }
        throw new NullPointerException("null set part of set operation");
    }

    /**
     * Returns the union of this set and another set.
     *
     * @param other the set to union this set with
     * @return the union
     * @pre other != null
     * @post forall int v: has(v) implies return.has(v)
     * @post forall int v: other.has(v) implies return.has(v)
     * @post forall int v: return.has(v) implies (has(v) or other.has(v))
     */
    public IntSet union(IntSet other) throws NullPointerException, SetCapacityFullException {
        if (other != null) {
            IntSet newSet = new IntSet(capacity + other.getCapacity());
            for (Integer i : set) {
                newSet.add(i);
            }
            for (Integer i : other.getSet()) {
                if (!has(i)) newSet.add(i);
            }
            return newSet;
        }
        throw new NullPointerException("null set part of set operation");
    }

    /**
     * @param other the set to do symmetric difference with
     * @return the symmetric difference of this set and other
     */
    public IntSet symmetricDifference(IntSet other) throws NullPointerException, SetCapacityFullException {
        if (other != null) {
            IntSet newSet = new IntSet(capacity + other.getCapacity());
            for (Integer i : set) {
                if (!other.has(i)) newSet.add(i);
            }
            for (Integer i : other.getSet()) {
                if (!has(i)) newSet.add(i);
            }
            return newSet;
        }
        throw new NullPointerException("null set part of set operation");
    }

    /**
     * @param other the set to do difference with
     * @return the difference of this set and other
     */
    public IntSet difference(IntSet other) throws NullPointerException, SetCapacityFullException {
        if (other != null) {
            IntSet newSet = new IntSet(capacity + other.getCapacity());
            for (Integer i : set) {
                if (!other.has(i)) newSet.add(i);
            }
            return newSet;
        }
        throw new NullPointerException("null set part of set operation");
    }

    /**
     * Returns a representation of this set as an array.
     *
     * @post return.length == getCount()
     * @post forall int v in return: has(v)
     */
    public int[] getArray() {
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = set.get(i);
        }
        return arr;
    }

    /**
     * Returns the number of elements in the set.
     */
    public int getCount() {
        return count;
    }

    /**
     * Returns the maximal number of elements in the set.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * Returns ArrayList representation of this set.
     */
    public ArrayList<Integer> getSet() {
        return set;
    }

    /**
     * Returns a string representation of the set. The empty set is represented
     * as {}, a singleton set as {x}, a set with more than one element like {x,
     * y, z}.
     */
    public String toString() {
        String output = "{";
        boolean first = true;
        for (Integer i : set) {
            if (!first) output += ", ";
            output += i;
            first = false;
        }
        output += "}";
        return output;
    }

}
