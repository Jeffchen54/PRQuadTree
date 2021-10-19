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
 * @author chenj (chenjeff4840), chenx (xiangkec19)
 * @version 10.5.2021
 */
public class PRQuadTree {

    // Data ------------------------------------------------------------------
    private Integer[] min;
    private Integer[] max;
    private BaseNode<String, Integer> rt;
    @SuppressWarnings("rawtypes")
    private static FlyweightNode empty = new FlyweightNode();
    private PointNode<String, Integer> bufferSlot; // Saves last removed entry

    private LinkedList<BaseNode<String, Integer>> nodeList; // this is for dump
    private int count;

    // Constructor ------------------------------------------------------------

    /**
     * Creates an empty PRQuadTree. Initializes fields
     * 
     * @param min
     *            Minimum dimensions of tree
     * @param max
     *            Maximum dimensions of tree
     * @precondition min is always lower than max, min and max != null
     */
    public PRQuadTree(Integer[] min, Integer[] max) {
        this.min = min;
        this.max = max;
        empty = new FlyweightNode<String, Integer>();
        rt = empty;
        bufferSlot = null;

        nodeList = new LinkedList<BaseNode<String, Integer>>();
        count = 0;

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
     *            Value of point to insert
     * @return true if inserted, false if not
     * @precondition key and value != null, key meets proper naming
     *               convention.
     */
    public boolean insert(String key, Integer[] value) {
        bufferSlot = null;
        rt = insert(rt, key, value, min, max);
        return bufferSlot != null;
    }


    /**
     * Removes selected point from the tree. Does nothing if not in tree.
     * 
     * @param key
     *            key of point
     * @param values
     *            value of point
     * @return removed point, null if not found
     * @precondition key and value != null
     */
    public PointNode<String, Integer> remove(String key, Integer[] values) {
        bufferSlot = null;

        rt = this.remove(rt, key, values, min, max);
        return bufferSlot;
    }


    /**
     * Returns all points that are contained in the query rectangle
     * 
     * @param rectangle
     *            Range of values to be searched in the
     * @return Data structure containing all points matching query values
     *         also containing meta data on # of nodes visited
     * @precondition rectangle is a valid rectangle array
     */
    public RegionSearchList<String, Integer> regionSearch(Integer[] rectangle) {
        RegionSearchList<String, Integer> list = new RegionSearchList<>();
        this.regionSearch(rt, rectangle, min, max, list);
        return list;
    }


    /**
     * Returns list of nodes containing all duplicate values
     * 
     * @return list containing all duplicate values as well as how many
     *         of each duplicate values exists
     */
    public PointNodeList<String, Integer>.ValueRecordNode duplicates() {
        return this.duplicate(rt, null);
    }


    // this section is helper method for dump ----------------------------
    /**
     * this will be a helper method for dump
     * 
     * @return this return a preordered linkedlist
     */
    public LinkedList<BaseNode<String, Integer>> getPreOrderList() {
        return nodeList;
    }


    /**
     * this reset the visited node to 0
     */
    public void resetCount() {
        this.count = 0;
    }


    /**
     * this increment after visiting a node
     */
    public void increment() {
        count++;
    }


    /**
     * this return the visited node number
     * 
     * @return number of node visited
     */
    public int getCount() {
        return this.count;
    }


    /**
     * this return the root node
     * 
     * @return the root node
     */
    public BaseNode<String, Integer> getRt() {
        return rt;
    }

    // Helpers ----------------------------------------------------------------


    /**
     * Recursively inserts a point.
     * 
     * @param curr
     *            Current node
     * @param key
     *            Key of point to insert
     * @param value
     *            Value of point to insert
     * @param min
     *            Lower bound
     * @param max
     *            Upper bound
     * @return the current node after being inserted to.
     * @precondition All params are != null. value, min, max size is 2.
     *               and falls within this.min and this.max. min < max.
     */
    private BaseNode<String, Integer> insert(
        BaseNode<String, Integer> curr,
        String key,
        Integer[] value,
        Integer[] min1,
        Integer[] max1) {
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
                return decompositionRule(leaf, min1, max1);

            }
        }

        // For parent nodes
        Integer[] mid = this.midpoint(min1, max1);
        int direction = this.wayfinder(value, mid, min1, max1);
        ParentNode<String, Integer> parent = (ParentNode<String, Integer>)curr;
        Integer[] lBounds = new Integer[2];
        Integer[] uBounds = new Integer[2];

        // BEGONE STACK OVERFLOW DUE TO MIDPOINT CALCULATION!!!
        // System.out.println(min[0] + ", " + min[1] + " --- " + max[0] + ", " +
        // max[1]);

        switch (direction) {
            case 0:
                parent.setChild(insert(parent.getChild(direction), key, value,
                    min, mid), direction);
                break;
            case 1:
                lBounds[0] = mid[0];
                lBounds[1] = min1[1];
                uBounds[0] = max1[0];
                uBounds[1] = mid[1];
                parent.setChild(insert(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
            case 2:
                parent.setChild(insert(parent.getChild(direction), key, value,
                    mid, max1), direction);
                break;
            case 3:
                lBounds[0] = min1[0];
                lBounds[1] = mid[1];
                uBounds[0] = mid[0];
                uBounds[1] = max1[1];
                parent.setChild(insert(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
            default:
                // this is default case
                break;
        }

        return parent;

    }


    /**
     * Recursively removes a point
     * 
     * @param curr
     *            Current node
     * @param key
     *            Key of point to remove, can be null
     * @param value
     *            Value of point to remove
     * @param min
     *            Lower bound
     * @param max
     *            Upper bound
     * @return the current node after having contents removed
     * @precondition curr, value, min, max are != null. value, min, max size is
     *               2 and falls within this.min and this.max. min < max.
     */
    private BaseNode<String, Integer> remove(
        BaseNode<String, Integer> curr,
        String key,
        Integer[] value,
        Integer[] min2,
        Integer[] max2) {
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
        Integer[] mid = this.midpoint(min2, max2);
        int direction = this.wayfinder(value, mid, min2, max2);
        ParentNode<String, Integer> parent = (ParentNode<String, Integer>)curr;
        Integer[] lBounds = new Integer[2];
        Integer[] uBounds = new Integer[2];

        switch (direction) {
            case 0:
                parent.setChild(remove(parent.getChild(direction), key, value,
                    min2, mid), direction);
                break;
            case 1:
                lBounds[0] = mid[0];
                lBounds[1] = min2[1];
                uBounds[0] = max2[0];
                uBounds[1] = mid[1];
                parent.setChild(remove(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
            case 2:
                parent.setChild(remove(parent.getChild(direction), key, value,
                    mid, max2), direction);
                break;
            case 3:
                lBounds[0] = min2[0];
                lBounds[1] = mid[1];
                uBounds[0] = mid[0];
                uBounds[1] = max2[1];
                parent.setChild(remove(parent.getChild(direction), key, value,
                    lBounds, uBounds), direction);
                break;
            default:
                // this is default case
                break;
        }

        // refactor parent
        boolean isParent = false;
        int totalPoints = 0;
        int flyweights = 0;
        LeafNode<String, Integer> combination = new LeafNode<>();

        for (int i = 0; i < 4 && !isParent; i++) {
            if (parent.getChild(i)
                .getNodeClass() == NodeClassification.ParentNode) {
                isParent = true;
            }
            else if (parent.getChild(i)
                .getNodeClass() == NodeClassification.FlyweightNode) {
                flyweights++;
            }
            else {
                totalPoints += ((LeafNode<String, Integer>)parent.getChild(i))
                    .getTotalSize();
                Iterator<PointNode<String, Integer>> iter =
                    ((LeafNode<String, Integer>)parent.getChild(i)).getPoints();
                while (iter.hasNext()) {
                    PointNode<String, Integer> next = iter.next();
                    combination.addPoint(next.getKey(), next.getValue());
                }
            }
        }

        if (!isParent) {
            if (totalPoints <= 3) {
                return combination;
            }
            if (flyweights == 3) {
                return combination;
            }
        }
        return parent;

    }


    /**
     * Recursively reports all duplicate values
     * 
     * @param curr
     *            Current node
     * @param tail
     *            tail of node chain storing duplicate points
     * @return duplicate values from a node/series of nodes
     * @precondition curr != null
     */
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
     * Applies decomposition rule to a leafnode. If node consists of multiple
     * values and size > 3, leafnode will be broken down.
     * 
     * @param leaf
     *            Leafnode to apply decompositionRule to
     * @param min
     *            Lower bound
     * @param max
     *            Upper bound
     * @return leaf after decompositionRule has been applied
     * @precondition params != null, min and max size 2 and within this.min
     *               and this.max
     */
    private BaseNode<String, Integer> decompositionRule(
        LeafNode<String, Integer> leaf,
        Integer[] min3,
        Integer[] max3) {
        if ((leaf.getDuplicates() == null || leaf.getDuplicates()
            .getCount() != leaf.getTotalSize()) && leaf.getTotalSize() > 3) {
            ParentNode<String, Integer> parent = new ParentNode<>(empty);
            Iterator<PointNode<String, Integer>> iter = leaf.getPoints();

            while (iter.hasNext()) {
                PointNode<String, Integer> next = iter.next();
                this.insert(parent, next.getKey(), next.getValue(), min3, max3);
            }
            return parent;
        }

        return leaf;
    }


    /**
     * Checks if a leaf node should be turned into a flyweight node.
     * leaf nodes broken down if leaf node has no children
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
     * @precondition lower and upper != null
     */
    private Integer[] midpoint(Integer[] lower, Integer[] upper) {
        int x = lower[0] + ((upper[0] - lower[0]) >> 1);
        int y = lower[1] + ((upper[1] - lower[1]) >> 1);
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
     * @param midpoint
     *            Midpoint of lower and upper bound
     * @return 0 -> NW, 1 -> NE, 2 -> SW , 3 -> SE, -1 -> cannot be calculated
     * @precondition params != null, lower bound is actually lower than upper
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


    /**
     * Recursively performs regionSearch on tree and saves all points onto
     * a list
     * 
     * @param curr
     *            Current node
     * @param region
     *            Rectangular region to search points in
     * @param min
     *            Lower bound
     * @param max
     *            Upper bound
     * @param list
     *            list to save points from regionSearch to
     * @precondition params != null, min < max and size 2. region is a
     *               valid rectangle dimensions. region, min, max is
     *               within this.min and this.max.
     */
    private void regionSearch(
        BaseNode<String, Integer> curr,
        Integer[] region,
        Integer[] lower,
        Integer[] upper,
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

            Integer[] mid = this.midpoint(lower, upper);
            ParentNode<String, Integer> parent =
                (ParentNode<String, Integer>)curr;

            // NW
            if (this.collide(region, lower, mid)) {
                this.regionSearch(parent.getChild(0), region, lower, mid, list);
            }
            // NE
            if (this.collide(region, new Integer[] { mid[0], lower[1] },
                new Integer[] { upper[0], mid[1] })) {
                this.regionSearch(parent.getChild(1), region, new Integer[] {
                    mid[0], lower[1] }, new Integer[] { upper[0], mid[1] },
                    list);
            }
            // SE
            if (this.collide(region, mid, upper)) {
                this.regionSearch(parent.getChild(2), region, lower, mid, list);
            }
            // SW
            if (this.collide(region, new Integer[] { min[0], mid[1] },
                new Integer[] { mid[0], upper[1] })) {
                this.regionSearch(parent.getChild(3), region, new Integer[] {
                    lower[0], mid[1] }, new Integer[] { mid[0], upper[1] },
                    list);
            }
        }

        // EOF when flyweight is reached, nothing needed to be done.
    }


    /**
     * Checks if a point exists within a rectangular region. Lower bounds
     * are inclusive while upper bounds are exclusive.
     * 
     * @param region
     *            Rectangles dimension
     * @param point
     *            value of a point
     * @return true if point is within region, false otherwise
     * @precondition point of size 2 and region is valid rectangle dimensions.
     *               point and region != null.
     */
    private boolean withinRegion(Integer[] region, Integer[] point) {

        return (region[0].compareTo(point[0]) == 0 && region[1].compareTo(
            point[1]) == 0) || point[0] >= region[0] && point[0] < region[0]
                + region[2] && point[1] >= region[1] && point[1] < region[1]
                    + region[3];
    }


    /**
     * Checks if a Rectangle collides/intersects another. Rectangles collide if
     * any part of a Rectangle is within another Rectangle. Lower bounds are
     * inclusive and upper bounds are exclusive.
     * 
     * @param region
     *            Rectangular region
     * @param lower
     *            Lower bound of another rectangle
     * @param upper
     *            Upper bound of another rectangle
     * @return true if collides, false if does not
     * @precondition dimensions and other are valid rectangle dimensions
     *               and bounds
     */
    private boolean collide(
        Integer[] region,
        Integer[] lower,
        Integer[] upper) {

        return ((region[0] < lower[0] && region[0] + region[2] > lower[0])
            || (region[0] >= lower[0] && region[0] < upper[0]))
            && ((region[1] < lower[1] && region[1] + region[3] > lower[1])
                || (region[1] >= lower[1] && region[1] < upper[1]));
    }


    /**
     * For peeking at tree for testing
     */
    public void peek() {
        // TODO remove when dump is complete.
    }


    /**
     * Returns the each node from top to bottom as seen in the debugger.
     * For example:
     * 
     * Debugger view:
     * Parent1
     * \tFlyNode1
     * \tFlyNode2
     * \tParent2
     * \t\tLeafNode1
     * \t\tFlyNode3
     * \t\tFlyNode4
     * \t\tLeafnode2
     * \tLeafnode3
     * 
     * String View:
     * Parent
     * \tNW-Flyweight
     * \tNE-Flyweight
     * \tSE-Parent
     * \tNW-Leaf-pointKey,pointKey,pointKey...
     * \tNE-Flyweight
     * \tSE-Flyweight
     * \tSW-Leaf-pointKey,pointKey,pointKey...
     * \tSW-Leaf-pointKey,pointKey,pointKey...
     * 
     * 
     * @return String view of the tree
     */
    public String toString() {
        return this.getTree(rt);

    }


    /**
     * Builds string view of the tree
     * 
     * @param curr
     *            Current node
     * @return Token of the string view, return entire string view on last
     *         return.
     */
    private String getTree(BaseNode<String, Integer> curr) {
        // Flyweight
        if (curr.getNodeClass() == NodeClassification.FlyweightNode) {
            return "Flyweight\n";
        }

        // Child
        if (curr.getNodeClass() == NodeClassification.LeafNode) {
            Iterator<PointNode<String, Integer>> iter =
                ((LeafNode<String, Integer>)curr).getPoints();
            String keys = "Leaf-";
            while (iter.hasNext()) {
                keys += iter.next().getKey() + ", ";
            }
            keys += "\n";
            return keys;
        }
        String build = "Parent\n";

        // Parent

        build += "\tNW-" + (this.getTree(((ParentNode<String, Integer>)curr)
            .getChild(0)));
        build += "\tNE-" + (this.getTree(((ParentNode<String, Integer>)curr)
            .getChild(1)));
        build += "\tSE-" + (this.getTree(((ParentNode<String, Integer>)curr)
            .getChild(2)));
        build += "\tSW-" + (this.getTree(((ParentNode<String, Integer>)curr)
            .getChild(3)));

        return build;
    }

}
