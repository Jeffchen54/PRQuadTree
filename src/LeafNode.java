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
 * this.addPoint(dupe Entry) -> Rejected
 * this.addPoint(Entry w/ dupe value not dupe key) -> Accepted
 * this.addPoint(Distinct Entry) -> Accepted
 * 
 * @author chenj (chenjeff4840), XC
 * @version 10.17.2021
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
    private int unique;

    // Constructor -----------------------------------------------------------
    /**
     * Constructs an empty leaf node
     */
    public LeafNode() {
        points = new PointNodeList<K, V>();
        unique = 0;
    }

    // Functions -------------------------------------------------------------


    /**
     * Reports duplicate points
     * 
     * @return list of duplicate points and their count
     */
    public PointNodeList<K, V>.ValueRecordNode getDuplicates() {
        return points.reportDuplicates();
    }


    /**
     * Returns total number of points in leafnode including duplicate valued
     * points
     * 
     * @return total number of points
     */
    public int getTotalSize() {
        return points.getSize();
    }


    /**
     * Returns number of unique points
     * 
     * @return number of unique points
     */
    public int getNumUniquePoints() {
        return unique;
    }


    /**
     * Returns all points in node
     * 
     * @return iterator of all the points
     */
    public Iterator<PointNode<K, V>> getPoints() {
        return points.getIterator();
    }


    /**
     * Adds a point
     * 
     * @param key
     *            key to add, can be null
     * @param value
     *            value to add
     * @return a pointNode that if it successfully added
     * @precondition value != null
     */
    public PointNode<K, V> addPoint(K key, V[] value) {
        if (!this.exists(key, value)) {
            if (!this.exists(null, value)) {
                unique++;
            }
            return points.insert(key, value);
        }
        return null;
    }


    /**
     * Removes a point with exact key and value
     * 
     * @param key
     *            Key to remove, can be null
     * @param value
     *            Value to remove
     * @return removed point, null if not removed
     * @precondition value != null
     */
    public PointNode<K, V> remove(K key, V[] value) {
        PointNode<K, V> temp = points.remove(key, value);

        if (temp != null && !this.exists(null, value)) {
            unique--;
        }
        return temp;
    }


    /**
     * {@inheritDoc}
     */
    public NodeClassification getNodeClass() {
        return CLASSIFICATION;
    }


    /**
     * Checks if the value and key already exists in the node
     * 
     * @param key
     *            Key to check for existance, can be null
     * @param value
     *            Value to check for existance
     * @return true if exists, false if not
     * @precondition value != null
     */
    public boolean exists(K key, V[] value) {
        return (points.find(key, value)) != null;
    }

}
