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

// Java Doc -------------------------------------------------------------------
/**
 * Basic generic SkipList with remove, search, and insert functions.
 * 
 * @author Jeff Chen (chenjeff4840)
 * @author OpenDSA
 * @version 9/01/2021
 */

import java.util.Iterator;
import java.util.Random;

/**
 * SkipList capable of containing 2 types of data - key and value. The list
 * is sorted by key's compareTo().
 * 
 * Provides general insert, search, remove, Iterator, and getters for some
 * fields
 * not provided by the Iterator.
 * 
 * @param <K>
 *            Key type - compareTo(K) function
 * @param <V>
 *            Value type - equals(V) function
 *
 * @version 9.18.2021
 * @author chenj (chenjeff4840)
 * @author openDSA
 */
public class SkipList<K extends Comparable<K>, V> {

    // Fields ---------------------------------------------------------------
    private SkipNode<K, V> head;
    private int level;
    private int size;
    private Random ran;

    // Constructor ---------------------------------------------------------

    /**
     * Constructs an empty SkipList with user defined max level
     */
    public SkipList() {
        level = -1;
        size = 0;
        ran = new Random();
        head = new SkipNode<K, V>(null, 0);
    }


    // Functions -----------------------------------------------------------
    /**
     * Inserts KV pair into list by order of key's comapreTo() function
     * 
     * @param key
     *            key of KV pair to insert into list
     * @param value
     *            value of KV pair to insert into list
     */
    @SuppressWarnings("unchecked")
    public void insert(K key, V value) {

        if (key != null && value != null) {
            int newLevel = randomLevel(); // New node's level
            if (newLevel > level) { // If new node is deeper
                adjustHead(newLevel);
            } // adjust the header
              // Track end of level
            SkipNode<K, V>[] update = new SkipNode[level + 1];
            SkipNode<K, V> node = head; // Start at header node
            for (int i = level; i >= 0; i--) { // Find insert position
                while ((node.getForward()[i] != null) && (node.getForward()[i]
                    .getData().getKey().toString().compareTo(key
                        .toString()) < 0)) {
                    node = node.getForward()[i];
                }
                update[i] = node; // Track end at level i
            }

            node = new SkipNode<K, V>(new KVPair<K, V>(key, value), newLevel);
            for (int i = 0; i <= newLevel; i++) { // Splice into list
                node.getForward()[i] = update[i].getForward()[i]; // Who x
                                                                  // points
                                                                  // to
                update[i].getForward()[i] = node; // Who points to x
            }
            size++; // Increment dictionary size

        }
    }


    /**
     * Returns array with entries matching key
     * 
     * @param key
     *            key of KV pair to search for
     * @return Array containing all KVPair objects with key matching parameter.
     *         Guaranteed null at the end.
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V>[] search(K key) {
        KVPair<K, V>[] list = new KVPair[size + 1];
        int listCounter = 0;
        SkipNode<K, V> node = head;
        node = node.getForward()[0]; // Head isn't searchable

        while (node != null) {
            if (node.getData().getKey().equals(key)) {
                list[listCounter] = node.getData();
                listCounter++;
            }
            node = node.getForward()[0];
        }
        return list;
    }


    /**
     * Removes node with matching value. Removes a maximum of 1 matching node.
     * Uses value's .equals(V).
     * 
     * @param value
     *            value of node to be removed.
     * @return KVPair of node that was removed matching parameter.
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(V value) {

        if (value != null) {
            // Track end of level
            SkipNode<K, V>[] update = new SkipNode[level + 2];
            SkipNode<K, V> node = null;

            for (int i = level; i >= 0; i--) { // Find insert position
                node = head; // reset to beginning
                while ((node.getForward()[i] != null) && (!node.getForward()[i]
                    .getData().getValue().equals(value))) {
                    node = node.getForward()[i];
                }

                update[i] = node; // Track end at level i
            }
            if (node != null) {
                node = node.getForward()[0];
            }
            if ((node != null)) {
                KVPair<K, V> temp = node.getData();

                // Adjusting forward nodes to point to new level
                for (int i = 0; update[i] != null; i++) { // Splice into
                                                          // list
                    if (update[i].getForward()[i] != null && update[i]
                        .getForward()[i] == node) {
                        update[i].getForward()[i] = node.getForward()[i];
                    }
                }
                size--;
                return temp;
            }
        }
        return null;
    }


    /**
     * Removes node with matching key. Removes a maximum of 1 matching node.
     * Uses key's .compareTo(K) function.
     * 
     * @param key
     *            key of node to be removed.
     * @return KVPair of node that was removed matching parameter.
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        if (key != null) {

            // Track end of level
            SkipNode<K, V>[] update = new SkipNode[level + 1];
            SkipNode<K, V> node = head; // Start at header node
            for (int i = level; i >= 0; i--) { // Find insert position
                while ((node.getForward()[i] != null) && (node.getForward()[i]
                    .getData().getKey().compareTo(key) < 0)) {
                    node = node.getForward()[i];
                }
                update[i] = node; // Track end at level i
            }

            node = node.getForward()[0];
            if ((node != null) && (node.getData().getKey().compareTo(
                key) == 0)) {
                KVPair<K, V> temp = node.getData();

                // Adjusting forward nodes to point to new level
                for (int i = 0; i <= level; i++) { // Splice into list
                    if (update[i].getForward()[i] != null && update[i]
                        .getForward()[i] == node) {
                        update[i].getForward()[i] = node.getForward()[i];
                    }
                }
                size--;
                return temp;

            }

        }
        return null;
    }


    /**
     * Returns head
     * 
     * @return head
     */
    public SkipNode<K, V> getHead() {
        return head;
    }


    /**
     * Returns size of list starting at 1
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }


    /**
     * Returns an iterator of the current ordered list.
     * 
     * @return iterator of the currently ordered list.
     */
    public SkipListIterator getIterator() {
        return new SkipListIterator();
    }


    // Helpers ---------------------------------------------------------------
    /**
     * Adjust head node's forward array to match user defined levels. Copies
     * pre-existing data from old forward array to new forward array.
     * 
     * @param newLevel
     *            New level for head node's forward array.
     */
    private void adjustHead(int newLevel) {
        SkipNode<K, V> temp = head;
        head = (new SkipNode<K, V>(null, newLevel));
        for (int i = 0; i <= level; i++) {
            head.getForward()[i] = temp.getForward()[i];
        }
        level = (newLevel);
    }


    /**
     * Pick a level using a geometric distribution
     * 
     */
    private int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(ran.nextInt()) % 2 == 0; lev++) { // ran is
            // random
            // generator
            // Do nothing
        }
        return lev;
    }

    // Iterator ---------------------------------------------------------------

    // Java Doc ---------------------------------------------------------------
    /**
     * Iterator which iterates starting from the beginning of the list to
     * the end.
     * 
     */
    private class SkipListIterator implements Iterator<SkipNode<K, V>> {

        // Fields -----------------------------------------------------------
        private SkipNode<K, V> cursor;

        // Constructor ------------------------------------------------------
        /**
         * Initializes the iterator by setting the cursor to the first node
         * after head.
         */
        public SkipListIterator() {
            cursor = head.getForward()[0]; // Advances past head
        }

        // Functions --------------------------------------------------------


        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {

            return cursor != null;
        }


        /** {@inheritDoc} */
        @Override
        public SkipNode<K, V> next() {
            if (!hasNext()) {
                return null;
            }

            SkipNode<K, V> temp = cursor;
            cursor = cursor.getForward()[0];
            return temp;
        }

    }
}
