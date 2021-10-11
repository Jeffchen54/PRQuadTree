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

// Java Doc ------------------------------------------------------------------
/**
 * Represents a leaf node of key and values for PRQuadTree. Only stores and
 * keeps track of non duplicate coordinate points. Does not have decomposition
 * rule. Rejects dupe key and value entries
 * 
 * For Example:
 * <A, 1>, <B, 2>, <C, 1>, size == 2.
 * <A, 1>, <B, 2>, <C, 3>, size == 3.
 * this.addPoint(dupe Entry) -> Rejected
 * this.addPoint(Entry w/ dupe value not dupe key) -> Accepted
 * this.addPoint(Distinct Entry) -> Accepted
 * 
 * @author chenj (chenjeff4840), XC
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class LeafNode<K extends Comparable<K>, V extends Comparable<V>>
    implements BaseNode<K, V> {

    // Fields ----------------------------------------------------------------
    private PointNodeList<K, V> points;
    private static final NodeClassification CLASSIFICATION =
        NodeClassification.LeafNode;
    private int size;

    // Constructor -----------------------------------------------------------
    public LeafNode() {
        points = new PointNodeList<K, V>();
        size = 0;
    }


    // Functions -------------------------------------------------------------
    /**
     * return size for a leafNode
     * 
     * @return
     */
    public int getSize() {
        return size;
    }


    /**
     * Returns all points in node
     * 
     * @return points
     */
    public Iterator<PointNode<K, V>> getPoints() {
        return points.getIterator();
    }


    /**
     * Adds a point
     * 
     * @param key
     *            key to add
     * @param value
     *            value to add
     * @precondition key or value != null
     */
    public void addPoint(K key, V[] value) {
        if (!this.exists(key, value)) {
            if (!this.exists(value)) {
                size++;
            }
            points.insert(key, value);
        }
    }


    /**
     * Removes a point according to value
     * 
     * @param value
     *            Value to remove
     * @precondition value not null
     * @return entry removed
     */
    public PointNode<K, V> removePoint(V[] value) {

        PointNode<K, V> temp = points.removeValue(value);
        if (temp != null && !exists(temp.getValue())) {
            size--;
        }
        return temp;
    }


    /**
     * Removes a point with exact key and value
     * 
     * @param key
     *            Key to remove
     * @param value
     *            Value to remove
     * @precondition key or value != null
     */
    public PointNode<K, V> removeEntry(K key, V[] value) {
        PointNode<K, V> temp = points.removePair(key, value);
        if (temp != null && !exists(temp.getValue())) {
            size--;
        }
        return temp;
    }


    /**
     * returns classification of node
     * 
     * @return classification of node
     */
    public NodeClassification getNodeClass() {
        return CLASSIFICATION;
    }


    /**
     * Checks if the value already exists in the node
     * 
     * @param value
     *            Value to check for existance
     * @return true if exists, false if not
     * @precondition value != null
     */
    public boolean exists(V[] value) {
        return (points.findValue(value) != null);
    }


    /**
     * Checks if the value and key already exists in the node
     * 
     * @param key
     *            Key to check for existance
     * @param value
     *            Value to check for existance
     * @return true if exists, false if not
     * @precondition key or value != null
     */
    public boolean exists(K key, V[] value) {
        return (points.findEntry(key, value));
    }

    // Deprecated Inner Node class --------------------------------------------
    /**
     * this is the node class for storing
     * the duplicate point
     * 
     * @author ben chen
     * @deprecated list added with more advanced functionality
     *
     */
    public class Node {
        private KVPair<K, V> data;
        private Node next;

        /**
         * constructor takes a pair
         * 
         * @param pair
         */
        public Node(KVPair<K, V> pair) {
            this.data = pair;
            next = null;
        }


        public KVPair<K, V> add(KVPair<K, V> data) {
            Node temp = new Node(data);
            this.next = temp;
            return data;
        }


        public Node getNext() {
            return this.getNext();
        }


        public KVPair<K, V> getData() {
            return this.data;
        }

    }

}
