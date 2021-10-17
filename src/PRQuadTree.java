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
    private PointNode<String, Integer> bufferSlot; // Saves last removed entry

    // Constructor ------------------------------------------------------------

    /**
     * Creates an empty PRQuadTree. Initializes fields
     */
    public PRQuadTree(Integer[] min, Integer[] max) {
        this.min = min;
        this.max = max;
        empty = new FlyweightNode<String, Integer>();
        rt = empty;
        bufferSlot = null;

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
    public boolean insert(String key, Integer[] value) {
        bufferSlot = null;
        rt = insert(rt, key, value, min, max);
        return bufferSlot != null;
    }


    /**
     * Removes selected KVPair from the tree. Does nothing if not in tree.
     * 
     * @param data
     *            Exact point record to remove
     * @implNote for remove {name} done in SkipList
     */
    public PointNode<String, Integer> remove(String key, Integer[] values) {
        bufferSlot = null;

        rt = this.remove(rt, key, values, min, max);
        return bufferSlot;
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
    public RegionSearchList<String, Integer> regionSearch(Integer[] rectangle) {
        RegionSearchList<String, Integer> list = new RegionSearchList<>();
        this.regionSearch(rt, rectangle, min, max, list);
        return list;
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
    public PointNodeList<String, Integer>.ValueRecordNode duplicates() {
        return this.duplicate(rt, null);
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
                bufferSlot = leaf.addPoint(key, value);
                return leaf;
            }

            // For leaf
            else {
                LeafNode<String, Integer> leaf =
                    (LeafNode<String, Integer>)curr;
                bufferSlot = leaf.addPoint(key, value);
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
    private BaseNode<String, Integer> remove(
        BaseNode<String, Integer> curr,
        String key,
        Integer[] value,
        Integer[] min,
        Integer[] max) {
        // Base case, reached destination
        if (curr.getNodeClass() != NodeClassification.ParentNode) {

            // For flyweight
            if (curr.getNodeClass() == NodeClassification.FlyweightNode) {
                return curr; // Nothing to be done
            }

            // For leaf
            else {
                LeafNode<String, Integer> leaf =
                    (LeafNode<String, Integer>)curr;
                bufferSlot = leaf.remove(key, value); // Saves removed entry
                return refactor(leaf);

            }
        }

        // For parent nodes, basically the same as insert
        Integer[] mid = this.midpoint(min, max);
        int direction = this.wayfinder(value, mid, min, max);
        ParentNode<String, Integer> parent = (ParentNode<String, Integer>)curr;
        Integer[] lBounds = new Integer[2];
        Integer[] uBounds = new Integer[2];

        switch (direction) {
            case 0:
                parent.setChild(remove(parent.getChild(direction), key, value,
                    min, mid), direction);
                break;
            case 1:
                lBounds[0] = mid[0];
                lBounds[1] = min[1];
                uBounds[0] = max[0];
                uBounds[1] = mid[1];
                parent.setChild(remove(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
            case 2:
                parent.setChild(remove(parent.getChild(direction), key, value,
                    mid, max), direction);
                break;
            case 3:
                lBounds[0] = min[0];
                lBounds[1] = mid[1];
                uBounds[0] = mid[0];
                uBounds[1] = max[1];
                parent.setChild(remove(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
        }

        return parent;

    }


    private PointNodeList<String, Integer>.ValueRecordNode duplicate(
        BaseNode<String, Integer> curr,
        PointNodeList<String, Integer>.ValueRecordNode tail) {
        // Base case, reached destination
        if (curr.getNodeClass() != NodeClassification.ParentNode) {

            // For flyweight
            if (curr.getNodeClass() == NodeClassification.FlyweightNode) {
                return null;
            }

            // For leaf
            else {
                LeafNode<String, Integer> leaf =
                    (LeafNode<String, Integer>)curr;

                return leaf.getDuplicates();

            }
        }

        // Parent node, visits all children
        ParentNode<String, Integer> parent = (ParentNode<String, Integer>)curr;
        PointNodeList<String, Integer>.ValueRecordNode head = tail;
        for (int i = 0; i < 4; i++) {
            if (head == null) {
                head = duplicate(parent.getChild(i), tail);
                tail = head;
            }
            else {
                tail = incrementTail(tail);
                tail.setNext(duplicate(parent.getChild(i), tail));
            }
        }
        return head;
    }


    private PointNodeList<String, Integer>.ValueRecordNode incrementTail(
        PointNodeList<String, Integer>.ValueRecordNode head) {
        while (head.getNext() != null) {
            head = head.getNext();
        }
        return head;
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
        if ((leaf.getDuplicates() == null || leaf.getDuplicates()
            .getCount() != leaf.getTotalSize()) && leaf.getTotalSize() > 3) {
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
    private BaseNode<String, Integer> refactor(LeafNode<String, Integer> leaf) {
        if (leaf.getTotalSize() == 0) {
            return empty;
        }

        return leaf;
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
            return 3; // SW

        }

        if (y >= 0) {
            return 2; // SE
        }
        return 1; // NE

    }


    private void regionSearch(
        BaseNode<String, Integer> curr,
        Integer[] region,
        Integer[] min,
        Integer[] max,
        RegionSearchList<String, Integer> list) {

        // Increment visit count
        list.incrementVisited();

        // Leaf node - Check if points within bounds
        if (curr.getNodeClass() == NodeClassification.LeafNode) {
            LeafNode<String, Integer> leaf = (LeafNode<String, Integer>)curr;
            Iterator<PointNode<String, Integer>> iter = leaf.getPoints();

            while (iter.hasNext()) {
                PointNode<String, Integer> temp = iter.next();
                if (this.withinRegion(region, temp.getValue())) {
                    list.insert(temp.getKey(), temp.getValue());

                }
            }
        }

        // ParentNode - can go to multiple branches
        if (curr.getNodeClass() == NodeClassification.ParentNode) {

            Integer[] mid = this.midpoint(min, max);
            ParentNode<String, Integer> parent =
                (ParentNode<String, Integer>)curr;

            // NW
            if (this.collide(region, min, mid)) {
                this.regionSearch(parent.getChild(0), region, min, mid, list);
            }
            // NE
            if (this.collide(region, new Integer[] { mid[0], min[1] },
                new Integer[] { max[0], mid[1] })) {
                this.regionSearch(parent.getChild(1), region, new Integer[] {
                    mid[0], min[1] }, new Integer[] { max[0], mid[1] }, list);
            }
            // SE
            if (this.collide(region, mid, max)) {
                this.regionSearch(parent.getChild(2), region, min, mid, list);
            }
            // SW
            if (this.collide(region, new Integer[] { min[0], mid[1] },
                new Integer[] { mid[0], max[1] })) {
                this.regionSearch(parent.getChild(3), region, new Integer[] {
                    min[0], mid[1] }, new Integer[] { mid[0], max[1] }, list);
            }
        }

        // EOF when flyweight is reached, nothing needed to be done.
    }


    /**
     * Checks if a Rectangle collides/intersects another. Rectangles collide if
     * any part of a Rectangle is within another Rectangle. Sharing the same
     * edge
     * or corner does not count as colliding.
     * 
     * @param dimensions
     *            Rectangles dimension
     * @param other
     *            Another rectangle's dimension
     * @return true if collides, false if does not
     * @precondition dimensions and other are valid rectangle dimensions
     */
    private boolean withinRegion(Integer[] region, Integer[] point) {
        if (region[0].compareTo(point[0]) == 0 && 
            region[1].compareTo(point[1]) == 0) {
            return true;
        }

        return ((point[0] >= region[0] && point[0] < region[0] + region[2])
            && (point[1] >= region[1] && point[1] < region[1] + region[3]));
    }


    /**
     * Checks if a Rectangle collides/intersects another. Rectangles collide if
     * any part of a Rectangle is within another Rectangle. Sharing the same
     * edge
     * or corner does not count as colliding.
     * 
     * @param dimensions
     *            Rectangles dimension
     * @param other
     *            Another rectangle's dimension
     * @return true if collides, false if does not
     * @precondition dimensions and other are valid rectangle dimensions
     */
    private boolean collide(
        Integer[] region,
        Integer[] lower,
        Integer[] upper) {
        int width = upper[0] - lower[0];
        int height = upper[1] - lower[1];
        Integer[] bounded = { lower[0], lower[1], width, height };

        return ((bounded[0] <= region[0] && bounded[0] + bounded[2] > region[0])
            || ((bounded[0] >= region[0] && bounded[0] < region[0]
                + region[2])))

            && ((bounded[1] <= region[1] && bounded[1] + bounded[3] > region[1])
                || ((bounded[1] >= region[1] && bounded[1] < region[1]
                    + region[3])));
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
