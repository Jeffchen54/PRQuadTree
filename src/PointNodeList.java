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
// letter of this restriction. - JC

// Java Doc ----------------------------------------------------------------
/**
 * List of PointNode, has basic getNode, remove, add, size functionality.
 * Unsorted list meant for small number of entries. Extreme flexibility
 * with insert, find, and remove.
 * 
 * @author chenj (chenjeff4840)
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 * @version (2021-10-18)
 */
public class PointNodeList<K extends Comparable<K>, V extends Comparable<V>> {

    // Fields -------------------------------------------------------------
    private PointNode<K, V> head;
    private ValueRecord record;
    private int size;

    // Constructor --------------------------------------------------------

    /**
     * Creates an empty list
     */
    public PointNodeList() {
        head = null;
        size = 0;
        record = new ValueRecord();
    }

    // Functions ----------------------------------------------------------


    /**
     * Removes a point
     * 
     * @param key
     *            Key of point to remove, can be null
     * @param value
     *            Value of point to remove
     * @return Removed point, null if not found
     * @precondition key != null
     */
    public PointNode<K, V> remove(K key, V[] value) {
        PointNode<K, V> temp = null;
        if (key == null) {
            if (value == null) {
                return null;
            }
            temp = removeValue(value);

        }

        // Key is not null at this point
        else if (value == null) {
            temp = removeKey(key);

        }
        else {
            temp = removePair(key, value);
        }
        if (temp != null) {
            record.remove(temp.getValue());
        }
        return temp; // computes value

    }


    /**
     * Finds a point in the list
     * 
     * @param key
     *            Key of point to find, can be null
     * @param value
     *            Value of point to be found
     * @return point matching parameters, null if not found.
     * @precondition key != null
     */
    public PointNode<K, V> find(K key, V[] value) {
        if (key == null) {
            if (value == null) {
                return null;
            }
            return findValue(value); // computes value
        }

        // Key is not null at this point
        if (value == null) {
            return findKey(key);
        }
        return findPair(key, value);

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
     * Returns all duplicate values
     * 
     * @return returns all duplicate values including number of duplicate
     *         values.
     */
    public ValueRecordNode reportDuplicates() {
        return record.reportDuplicates();
    }


    /**
     * Returns an iterator of the whole list
     * 
     * @return iterator
     */
    public PointIterator getIterator() {
        return new PointIterator();
    }


    // Helpers -------------------------------------------------------------
    /**
     * Inserts K and V into front of list
     * 
     * @param key
     *            Key to insert
     * @param value
     *            Value to insert
     * @return point inserted into list
     * @precondition key or value != null
     */
    public PointNode<K, V> insert(K key, V[] value) {
        PointNode<K, V> temp = head;
        head = new PointNode<K, V>(key, value);
        head.setNext(temp);
        size++;
        record.insert(value);
        return head;
    }


    /**
     * Removes first entry with the key
     * 
     * @param key
     *            key to remove
     * @return entry removed or null if not found
     * @precondition key != null
     */
    private PointNode<K, V> removeKey(K key) {
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
    private PointNode<K, V> removeValue(V[] value) {
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
    private PointNode<K, V> removePair(K key, V[] value) {
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
    private PointNode<K, V> findKey(K key) {
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
    private PointNode<K, V> findValue(V[] value) {
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
     * @return first entry matching value and key
     * @precondition key or value != null
     */
    private PointNode<K, V> findPair(K key, V[] value) {
        PointNode<K, V> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.getKey().compareTo(key) == 0 && this.valueEquals(temp
                .getValue(), value)) {
                return temp;
            }
            temp = temp.getNext();
        }

        return null;
    }


    /**
     * Checks if two objects are equal
     * 
     * @param value
     *            V[] object to compare to obj
     * @param obj
     *            Object to compare to V[] value
     * @return true if data values of 2 objects are identical
     * @precondition value and obj != null
     */
    private boolean valueEquals(V[] value, V[] obj) {

        if (obj == value) {
            return true;
        }

        if (obj.length != value.length) {
            return false;
        }

        for (int i = 0; i < value.length; i++) {
            if (value[i].compareTo(obj[i]) != 0) {
                return false;
            }
        }
        return true;

    }
    // Helper classes --------------------------------------------------------

    // ValueRecordNode Java Doc ----------------------------------------------
    /**
     * Node which stores a value and # of times it occurs
     * 
     * @author Jeff Chen (chenjeff4840)
     * @version 10.17.2021
     */
    public class ValueRecordNode {
        // Fields --------------------------------------------------------------
        private ValueRecordNode next;
        private int count;
        private V[] data;

        // Constructor ------------------------------------------------------
        /**
         * Constructs a ValueRecordNode
         * 
         * @param data
         *            Value
         * @param count
         *            # of times the Value occurs
         * @precondition params != null
         */
        public ValueRecordNode(V[] data, int count) {
            this.data = data;
            this.count = count;
            next = null;
        }


        // Functions ------------------------------------------------------
        /**
         * Returns value
         * 
         * @return value
         */
        public V[] getValue() {
            return data;
        }


        /**
         * Returns count
         * 
         * @return count
         */
        public int getCount() {
            return count;
        }


        /**
         * Returns next node
         * 
         * @return next node
         */
        public ValueRecordNode getNext() {
            return next;
        }


        /**
         * Sets next node
         * 
         * @param next
         *            Node to set next to
         */
        public void setNext(ValueRecordNode next) {
            this.next = next;
        }
    }


    // ValueRecord Java Doc ---------------------------------------------------
    /**
     * List storing ValueRecordNodes, remove, insert, and report dupe
     * functionality.
     * 
     * @author Jeff Chen (chenjeff4840)
     * @version 10.17.2021
     */
    public class ValueRecord {

        // Fields -----------------------------------------------------------
        private ValueRecordNode head;

        // Constructor ------------------------------------------------------
        /**
         * Constructs an empty list
         */
        public ValueRecord() {
            head = null;
        }


        // Functions ---------------------------------------------------------
        /**
         * Removes a value from the list
         * 
         * @param value
         *            Value of point to remove
         * @precondition value != null
         */
        public void remove(V[] value) {

            ValueRecordNode temp = head;
            ValueRecordNode prev = null;
            boolean found = false;

            while (temp != null && !found) {
                if (valueEquals(temp.data, value)) {
                    // Decrements count of value
                    temp.count--;

                    // Removal case
                    if (temp.count < 2) {
                        // Head cases
                        if (temp == head) {
                            head = head.getNext();
                        }
                        else {
                            prev.setNext(temp.getNext());
                        }
                    }
                    found = true;
                }
                prev = temp;
                temp = temp.getNext();
            }
        }


        /**
         * Inserts a value into the list
         * 
         * @param value
         *            Value to insert
         * @precondition value != null
         */
        public void insert(V[] value) {

            ValueRecordNode temp = head;
            boolean found = false;

            while (temp != null && !found) {
                if (valueEquals(temp.data, value)) {
                    found = true;
                    temp.count++;
                }
                temp = temp.next;
            }

            if (!found) {
                // Not found
                temp = head;
                head = new ValueRecordNode(value, 1);
                head.setNext(temp);
            }
        }


        /**
         * Returns all duplicate values
         * 
         * @return duplicate values
         */
        public ValueRecordNode reportDuplicates() {
            ValueRecordNode curr = head;
            ValueRecordNode record1 = null;

            while (curr != null) {
                if (curr.count > 1) {
                    if (record1 == null) {
                        record1 = new ValueRecordNode(curr.data, curr.count);
                    }
                    else {
                        ValueRecordNode dupe = new ValueRecordNode(curr.data,
                            curr.count);
                        dupe.next = record1;
                        record1 = dupe;
                    }
                }
                curr = curr.next;
            }
            return record1;
        }
    }


    // Iterator --------------------------------------------------------------
    // Java Doc ---------------------------------------------------------------
    /**
     * Iterator which iterates starting from the beginning of the PointNodeList
     * to the end.
     * 
     * @author chenj (chenjeff4840)
     * @version 10.17.2021
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
