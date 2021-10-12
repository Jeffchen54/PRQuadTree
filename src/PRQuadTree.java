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
 * Entries added to the tree dependent on Integer's compareTo(Integer)
 * 
 * @param String
 *            Key for KVPair
 * @param Integer
 *            Value for KVPair
 * @author chenj (chenjeff4840)
 * @version 10.5.2021
 */
public class PRQuadTree {

    // Data ------------------------------------------------------------------
    private Integer[] min;
    private Integer[] max;
    private BaseNode<String, Integer> rt;
    private FlyweightNode<String, Integer> empty;

    // Constructor ------------------------------------------------------------

    /**
     * Creates an empty PRQuadTree. Initializes fields
     */
    public PRQuadTree(Integer[] min, Integer[] max) {
        this.min = min;
        this.max = max;
        empty = new FlyweightNode<String, Integer>();
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
    public KVPair<String, Integer> insert(String key, Integer[] value) {
        rt = insert(rt, key, value, min, max);
        return null;
    }


    /**
     * Removes selected KVPair from the tree. Does nothing if not in tree.
     * 
     * @param data
     *            Exact point record to remove
     * @implNote for remove {name} done in SkipList
     */
    public void remove(KVPair<String, Integer> data) {
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
    public KVPair<String, Integer> remove(Integer[] values) {
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
    public Integer[][] duplicates() {
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
    @SuppressWarnings("unchecked")
    private BaseNode<String, Integer> insert(
        BaseNode<String, Integer> curr,
        String key,
        Integer[] value,
        Integer[] min,
        Integer[] max) {
        // Base case, reached destination
        if (curr.getNodeClass() != NodeClassification.ParentNode) {

            // For flyweight
            if (curr.getNodeClass() == NodeClassification.FlyweightNode) {
                LeafNode<String, Integer> leaf =
                    new LeafNode<String, Integer>();
                leaf.addPoint(key, value);
                return leaf;
            }

            // For leaf
            else {
                LeafNode<String, Integer> leaf =
                    (LeafNode<String, Integer>)curr;
                leaf.addPoint(key, value);
                return decompositionRule(leaf);

            }
        }

        // For parent nodes
        int direction = this.wayfinder(value, min, max);
        Integer[] parentLocation = this.midpoint(min, max);
        ParentNode<String, Integer> parent = (ParentNode<String, Integer>)curr;
        Integer[] lBounds = (Integer[])new Object[2];
        Integer[] uBounds = (Integer[])new Object[2];

        switch (direction) {
            case 0:
                parent.setChild(insert(parent.getChild(direction), key, value,
                    min, parentLocation), direction);
                break;
            case 1:
                lBounds[0] = parentLocation[0];
                lBounds[1] = min[1];
                uBounds[0] = max[0];
                uBounds[1] = parentLocation[1];
                parent.setChild(insert(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
            case 2:
                parent.setChild(insert(parent.getChild(direction), key, value,
                    parentLocation, max), direction);
                break;
            case 3:
                lBounds[0] = min[0];
                lBounds[1] = parentLocation[1];
                uBounds[0] = parentLocation[0];
                uBounds[1] = max[1];
                parent.setChild(insert(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
        }

        return parent;

    }


    /**
     * Recursively removes a point
     */
    private BaseNode<String, Integer> remove(String key, Integer[] value) {
        return null;
    }


    /**
     * Applies decomposition rule to a leafnode
     * 
     * @param leaf
     *            Leafnode to apply decompositionRule to
     * @return leaf after decompositionRule has been applied
     */
    private BaseNode<String, Integer> decompositionRule(
        LeafNode<String, Integer> leaf) {

        return null;
    }


    /**
     * Checks if a leaf node should be turned into a flyweight node
     * 
     * @param leaf
     *            Leafnode to check for decomposition
     * @return leaf node or flyweight node
     */
    private BaseNode<String, Integer> refactor(Object leaf) {

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
    private Integer[] midpoint(Integer[] lower, Integer[] upper) {
        Integer[] mid = (Integer[])new Object[2];
        int x = (Integer)lower[0] - (Integer)upper[0];
        int y = ((Integer)lower[0] + x) >> 2;
        mid[0] = (Integer)new Integer(y);
        return mid;
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
    private int wayfinder(Integer[] dest, Integer[] lower, Integer[] upper) {
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
        // private SkipNode<String, Integer> cursor;
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
        public BaseNode<String, Integer> next() {
            // TODO change return type
            if (!hasNext()) {
                return null;
            }

            // TODO modify code
            // SkipNode<String, Integer> temp = cursor;
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
        private BaseNode<String, Integer> preorderTraversal() {
            // TODO implementation and change return type
            return null;
        }

    }

}
