import java.util.Iterator;
import java.util.LinkedList;

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
                return decompositionRule(leaf, min, max);

            }
        }

        // For parent nodes
        Integer[] mid = this.midpoint(min, max);
        int direction = this.wayfinder(value, mid, min, max);
        ParentNode<String, Integer> parent = (ParentNode<String, Integer>)curr;
        Integer[] lBounds = new Integer[2];
        Integer[] uBounds = new Integer[2];

        switch (direction) {
            case 0:
                parent.setChild(insert(parent.getChild(direction), key, value,
                    min, mid), direction);
                break;
            case 1:
                lBounds[0] = mid[0];
                lBounds[1] = min[1];
                uBounds[0] = max[0];
                uBounds[1] = mid[1];
                parent.setChild(insert(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
            case 2:
                parent.setChild(insert(parent.getChild(direction), key, value,
                    mid, max), direction);
                break;
            case 3:
                lBounds[0] = min[0];
                lBounds[1] = mid[1];
                uBounds[0] = mid[0];
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
        LeafNode<String, Integer> leaf,
        Integer[] min,
        Integer[] max) {
        if (leaf.getNumUniquePoints() > 1 && leaf.getTotalSize() > 3) {
            ParentNode<String, Integer> parent = new ParentNode<>(empty);
            Iterator<PointNode<String, Integer>> iter = leaf.getPoints();

            while (iter.hasNext()) {
                PointNode<String, Integer> next = iter.next();
                this.insert(parent, next.getKey(), next.getValue(), min, max);
            }
            return parent;
        }

        return leaf;
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
        int x = lower[0] + ((upper[0] - lower[0]) >> 1);
        int y = lower[1] + ((upper[1] - lower[0]) >> 1);
        return new Integer[] { x, y };
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
    private int wayfinder(
        Integer[] dest,
        Integer[] midpoint,
        Integer[] lower,
        Integer[] upper) {

        // Some arithmetic to simplify the problem a bit
        int x = dest[0] - midpoint[0];
        int y = dest[1] - midpoint[1];

        if (x < 0) {
            if (y < 0) {
                return 0; // NW
            }
            else {
                return 3; // SW
            }
        }

        if (x >= 0) {
            if (y <= 0) {
                return 2; // SE
            }
            else {
                return 1; // NE
            }
        }

        return -1;
    }


    /**
     * For peeking at tree for testing
     */
    public void peek() {
        // TODO remove when dump is complete.
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
        private LinkedList<BaseNode<String, Integer>> list;
        private LinkedList<BaseNode<String, Integer>> parentList;
        private BaseNode<String, Integer> head;
        private int count;

        // Constructor ------------------------------------------------------
        /**
         * Initializes the iterator by setting the cursor to the first node
         * after head.
         */
        public TreeIterator() {
            list = new LinkedList<BaseNode<String, Integer>>();
            head = list.getFirst();
            count = 0;

        }

        // Functions --------------------------------------------------------


        /** {@inheritDoc} */
        @Override
        public boolean hasNext() {
            // TODO uncomment return statement
            // return cursor != null;
            return count != list.size();
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
            BaseNode<String, Integer> next = list.get(count);
            count++;
            return next;

        }

        /**
         * Performs a single step in preorder traversal.
         * 
         * @return node after taking a step
         * @implNote Set as cursor in next
         */
        /*
         * private BaseNode<String, Integer> preorderTraversal(BaseNode<String,
         * Integer> start) {
         * // TODO implementation and change return type
         * 
         * if(((ParentNode<String, Integer>)start).getChild(i).getNodeClass() ==
         * NodeClassification.ParentNode) {
         * //System.out.print("Node at ");
         * return ((ParentNode<String, Integer>)start).getChild(i);
         * 
         * }
         * if(((ParentNode<String, Integer>)start).getChild(i).getNodeClass() ==
         * NodeClassification.FlyweightNode) {
         * //empty node
         * return start;
         * }
         * LeafNode<String, Integer> leaf = (LeafNode<String,
         * Integer>)((ParentNode<String, Integer>)start).getChild(i);
         * Iterator<PointNode<String, Integer>> pointIte = leaf.getPoints();
         * 
         * 
         * 
         * 
         * return null;
         * }
         */

    }

}
