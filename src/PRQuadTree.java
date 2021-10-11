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

// Java Doc -------------------------------------------------------------------
/**
 * Generic PRQuadtree supporting insert, remove, regionSearch, duplicate,
 * and dump functionality.
 * 
 * Entries added to the tree dependent on V's compareTo(V)
 * 
 * @param K
 *            Key for KVPair
 * @param V
 *            Value for KVPair
 * @author chenj (chenjeff4840)
 * @version 10.5.2021
 */
public class PRQuadTree<K extends Comparable<K>, V extends Comparable<V>> {

    // Data ------------------------------------------------------------------
    private V[] min;
    private V[] max;
    private BaseNode<K, V> rt;
    private FlyweightNode<K, V> empty;

    // Constructor ------------------------------------------------------------

    /**
     * Creates an empty PRQuadTree. Initializes fields
     */
    public PRQuadTree(V[] min, V[] max) {
        this.min = min;
        this.max = max;
        empty = new FlyweightNode<K, V>();
        rt = empty;
        
    }


    // Functions -------------------------------------------------------------
    /**
     * Inserts a point into the tree.
     * 
     * Rejects points if:
     * - another point with the same key and value exists
     * - key or value is null
     * 
     * @param key
     *            Key of point to insert
     * @param value
     *            Value of key to insert
     * @return KVPair of inserted element, null if rejected.
     * @implNote key must start with a letter, do this in the controller
     * @implNote check if contains negative cords or out of world box in
     *           controller
     * @implNote send output to skiplist
     * @implNote Check size of list, if it is the same, return null, else return
     *           KVPair
     */
    public KVPair<K, V> insert(K key, V[] value) {
        // TODO implementation
        return null;
    }


    /**
     * Removes selected KVPair from the tree. Does nothing if not in tree.
     * 
     * @param data
     *            Exact point record to remove
     * @implNote for remove {name} done in SkipList
     */
    public void remove(KVPair<K, V> data) {
        // TODO implementation
    }


    /**
     * Removes a single point from tree matching values.
     * 
     * @param values
     *            Point of record to remove
     * @return returns KVPair removed from tree, null otherwise
     * @implNote Send this output to SkipList remove
     * @implNote How to return entire KVPair from recursive function? Have
     *           special object containing node and removed KVPair? Or even a
     *           private KVPAir
     *           variable which will point to the removed entry?
     */
    public KVPair<K, V> remove(V[] values) {
        // TODO implementation
        return null;
    }


    /**
     * Returns all points that are contained in the query values
     * 
     * @return Data structure containing all points matching query values
     *         also containing meta data on # of nodes visited
     * @implNote Reject illegal values in Controller
     * @implNote Copy data over to a chain of nodes then return
     * @implNote Port regionsearch to Dimensions class
     */
    public Object regionSearch() {
        // TODO implementation and change return type
        return null;
    }


    /**
     * Returns chain of nodes containing all points with duplicate values
     * 
     * @return 2D array with row corresponding to a point and column
     *         representing
     *         a value from that point.
     * @implNote Explore each branch separetly and pick out all dupes.
     * @implNote same return type and method as regionSearch()
     */
    public Object[][] duplicates() {
        // TODO implementation and change return type
        return null;
    }


    /**
     * Returns an TreeIterator of the tree acquired from preorder traversal
     * 
     * @implNote Basically the dump function
     * @return TreeIterator of nodes from preorder traversal
     * @implNote call tree Iterator constructor
     */
    public TreeIterator getIterator() {
        return null;
    }

    // Helpers ----------------------------------------------------------------


    /**
     * Recursively inserts a point.
     */
    private Object insert(K key, V[] value, V max, V min) {
        return null;
    }


    /**
     * Recursively removes a point
     */
    private Object remove(K key, V[] value) {
        return null;
    }


    /**
     * Applies decomposition rule to a leafnode
     * 
     * @param leaf
     *            Leafnode to apply decompositionRule to
     * @return leaf after decompositionRule has been applied
     */
    private Object decompositionRule(Object leaf) {
        return null;
    }


    /**
     * Checks if a leaf node should be turned into a flyweight node
     * 
     * @param leaf
     *            Leafnode to check for decomposition
     * @return leaf node or flyweight node
     */
    private Object refactor(Object leaf) {

        return null;
    }


    /**
     * Calculates midpoint given a set of bounds
     * 
     * @param bounds
     *            [0][] = lower bound
     *            [1][] = upper bound
     * @return midpoint calculated from the 2 points
     */
    private V[] midpoint(V[][] bounds) {
        return null;
    }


    /**
     * Calculates which direction to go in the tree
     * 
     * @param dest
     *            Destination
     * @param lower
     *            Lower bound of current quadrant
     * @param upper
     *            Upperbound of current quadant
     * @return 0 -> NW, 1 -> NE, 2 -> SW , 3 -> SE, -1 -> cannot be calculated
     */
    private int wayfindinder(V[] dest, V[] lower, V[] upper) {
        return -1;
    }

    // Iterator ---------------------------------------------------------------

    // Java Doc ---------------------------------------------------------------
    /**
     * Iterator storing nodes from tree in preorder traversal.
     */
    private class TreeIterator implements Iterator<Object> {
        // TODO change Iterator type

        // Fields -----------------------------------------------------------
        // private SkipNode<K, V> cursor;
        // TODO change type

        // Constructor ------------------------------------------------------
        /**
         * Initializes the iterator by setting the cursor to the first node
         * after head.
         */
        public TreeIterator() {
            // TODO do preorderTraversal
        }

        // Functions --------------------------------------------------------


        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            // TODO uncomment return statement
            // return cursor != null;
            return false;
        }


        /** {@inheritDoc} */
        @Override
        public SkipNode<K, V> next() {
            // TODO change return type
            if (!hasNext()) {
                return null;
            }

            // TODO modify code
            // SkipNode<K, V> temp = cursor;
            // cursor = cursor.getForward()[0];
            // return temp;
            return null;
        }


        /**
         * Performs a single step in preorder traversal.
         * 
         * @return node after taking a step
         * @implNote Set as cursor in next
         */
        private Object preorderTraversal() {
            // TODO implementation and change return type
            return null;
        }

    }

}
