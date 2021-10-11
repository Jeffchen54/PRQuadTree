import java.util.Iterator;

// On my honor:
//
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than the instructor, ACM/UPE tutors, programming
// partner (if allowed in this class), or the TAs assigned to
// this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction. - JC & XC

// Java Doc ----------------------------------------------------------------
/**
 * List of PointNode, has basic getNode, remove, add, size functionality.
 * Unsorted list meant for small number of entries.
 * 
 * @author chenj (chenjeff4840), XC
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class PointNodeList<K extends Comparable<K>, V extends Comparable<V>> {

    // Fields -------------------------------------------------------------
    private PointNode<K, V> head;
    private int size;

    // Constructor --------------------------------------------------------

    /**
     * Creates an empty list
     */
    public PointNodeList() {
        head = null;
        size = 0;
    }

    // Functions ----------------------------------------------------------


    /**
     * Inserts K and V into front of list
     * 
     * @param key
     *            Key to insert
     * @param value
     *            Value to insert
     * @precondition key or value != null
     */
    public void insert(K key, V[] value) {
        PointNode<K, V> temp = head;
        head = new PointNode<K, V>(key, value);
        head.setNext(temp);
        size++;
    }


    /**
     * Removes first entry with the key
     * 
     * @param key
     *            key to remove
     * @return entry removed or null if not found
     * @precondition key != null
     */
    public PointNode<K, V> removeKey(K key) {
        PointNode<K, V> temp = head;
        PointNode<K, V> prev = null;

        for (int i = 0; i < size; i++) {
            if (temp.getKey().compareTo(key) == 0) {
                if (temp == head) {
                    head = head.getNext();

                }
                else {
                    prev.setNext(temp.getNext());
                }
                size--;
                return temp;
            }
            prev = temp;
            temp = temp.getNext();
        }

        return null;
    }


    /**
     * Removes first entry with the value
     * 
     * @param value
     *            value to remove
     * @return entry removed or null if not found
     * @precondition value != null
     */
    public PointNode<K, V> removeValue(V[] value) {
        PointNode<K, V> temp = head;
        PointNode<K, V> prev = null;

        for (int i = 0; i < size; i++) {
            if (this.valueEquals(temp.getValue(), value)) {
                if (temp == head) {
                    head = head.getNext();
                }
                else {
                    prev.setNext(temp.getNext());
                }
                size--;
                return temp;
            }
            prev = temp;
            temp = temp.getNext();
        }

        return null;
    }


    /**
     * Removes first entry with key and value
     * 
     * @param key
     *            key to remove
     * @param value
     *            value to remove
     * @return entry removed or null if not found
     * @precondition key or value != null
     */
    public PointNode<K, V> removePair(K key, V[] value) {
        PointNode<K, V> temp = head;
        PointNode<K, V> prev = null;

        for (int i = 0; i < size; i++) {
            if (temp.getKey().compareTo(key) == 0 && this.valueEquals(temp
                .getValue(), value)) {
                if (temp == head) {
                    head = head.getNext();
                }
                else {
                    prev.setNext(temp.getNext());
                }
                size--;
                return temp;
            }
            prev = temp;
            temp = temp.getNext();
        }

        return null;
    }


    /**
     * Returns first entry matching key
     * 
     * @param key
     *            key to find
     * @return first entry matching key
     * @precondition key != null
     */
    public PointNode<K, V> findKey(K key) {
        PointNode<K, V> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.getKey().compareTo(key) == 0) {
                return temp;
            }
            temp = temp.getNext();
        }

        return null;
    }


    /**
     * Returns first entry matching value
     * 
     * @param value
     *            value to find
     * @return first entry matching value
     * @precondition value != null
     */
    public PointNode<K, V> findValue(V[] value) {
        PointNode<K, V> temp = head;

        for (int i = 0; i < size; i++) {
            if (this.valueEquals(temp.getValue(), value)) {
                return temp;
            }
            temp = temp.getNext();
        }

        return null;
    }


    /**
     * Returns if key and value is present in list
     * 
     * @param key
     *            key to find
     * @param value
     *            value to find
     * @return true if found, false if not found
     * @precondition key or value != null
     */
    public boolean findEntry(K key, V[] value) {
        PointNode<K, V> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.getKey().compareTo(key) == 0 && this.valueEquals(temp
                .getValue(), value)) {
                return true;
            }
            temp = temp.getNext();
        }

        return false;
    }


    /**
     * Returns size of list
     * 
     * @return size of list
     */
    public int getSize() {
        return size;
    }


    /**
     * Returns an iterator of the whole list
     * 
     * @return iterator
     */
    public PointIterator getIterator() {
        return new PointIterator();
    }


    /**
     * Checks if two objects are equal
     * 
     * @param value
     *            V[] object to compare to obj
     * @param obj
     *            Object to compare to V[] value
     * @return true if data values of 2 objects are identical
     */
    @SuppressWarnings("unchecked")
    private boolean valueEquals(V[] value, Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == value) {
            return true;
        }

        if (value.getClass() == obj.getClass()) {
            V[] other = (V[])(obj);

            if (other.length != value.length) {
                return false;
            }

            for (int i = 0; i < value.length; i++) {
                if (value[i].compareTo(other[i]) != 0) {
                    return false;
                }
            }
            return true;
        }

        return false;

    }

    // Iterator --------------------------------------------------------------
    // Java Doc ---------------------------------------------------------------
    /**
     * Iterator which iterates starting from the beginning of the list to
     * the end.
     * 
     * @author chenj (chenjeff4840)
     */
    private class PointIterator implements Iterator<PointNode<K, V>> {

        // Fields -----------------------------------------------------------
        private PointNode<K, V> cursor;

        // Constructor ------------------------------------------------------
        /**
         * Initializes the iterator by setting the cursor to head
         */
        public PointIterator() {
            cursor = head;
        }

        // Functions --------------------------------------------------------


        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {

            return cursor != null;
        }


        /** {@inheritDoc} */
        @Override
        public PointNode<K, V> next() {
            if (!hasNext()) {
                return null;
            }

            PointNode<K, V> temp = cursor;
            cursor = cursor.getNext();
            return temp;
        }

    }
}
