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
public class PRQuadTree<K, V extends Comparable<V>> {

    // Data ------------------------------------------------------------------
    // TODO implementation

    // Constructor ------------------------------------------------------------

    /**
     * Creates an empty PRQuadTree
     * 
     * @implNote Creates root node, initialize data fields
     */
    public PRQuadTree() {
        // TODO implementation
    }


    // Functions -------------------------------------------------------------
    /**
     * Inserts a point into the tree.
     * 
     * Rejects points if:
     * - another point with the same key and value exists
     * - key or value is null
     * 
     * @return KVPair of inserted element, false if rejected.
     * @implNote key must start with a letter, do this in the controller
     * @implNote check if contains negative cords or out of world box in
     *           controller
     * @implNote send output to skiplist
     */
    public KVPair<K, V> insert(K key, V[] value) {
        // TODO implementation
        return null;
    }


    /**
     * Removes selected KVPair from the tree.
     * 
     * @implNote for remove {name} done in SkipList
     */
    public void remove(KVPair<K, V> data) {
        // TODO implementation
    }


    /**
     * Removes a single point from tree matching values.
     * 
     * @return returns KVPair removed from tree, null otherwise
     * @implNote Send this output to SkipList remove
     */
    public KVPair<K, V> remove(V[] values) {
        // TODO implementation
        return null;
    }


    /**
     * Returns all points that are contained in the query values
     * 
     * @return linked chain of nodes containing all points matching query values
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
     * @return linked chain of nodes containing all dupe points
     * @implNote Explore each branch separetly and pick out all dupes.
     * @implNote same return type and method as regionSearch()
     */
    public Object duplicates() {
        // TODO implementation and change return type
        return null;
    }


    /**
     * Returns an TreeIterator of the tree acquired from preorder traversal
     * 
     * @return TreeIterator of nodes from preorder traversal
     * @implNote call tree Iterator constructor
     */
    public TreeIterator getIterator() {
        return null;
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
            // TODO do preorder traversal with cursor at first node
            // cursor = head.getForward()[0]; // Advances past head
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
         * Returns a chain of nodes from the tree in preorderTraversal
         * 
         * @return chain of nodes from tree in preorderTraversal
         * @implNote Return value sent to constructor.
         */
        private Object preorderTraversal() {
            // TODO implementation and change return type
            return null;
        }

    }

}
