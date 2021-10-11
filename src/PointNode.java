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

// Java Doc ----------------------------------------------------------------
/**
 * Basic node storing a Key and Value. Only has getNext and getData functions.
 * 
 * @author chenj (chenjeff4840), XC
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class PointNode<K, V> {

    // Fields ----------------------------------------------------------------
    private K key;
    private V[] value;
    private PointNode<K, V> next;

    // Constructor -------------------------------------------------------------
    public PointNode(K key, V[] value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }

    // Functions --------------------------------------------------------


    /**
     * Returns key
     * 
     * @return key
     */
    public K getKey() {
        return key;
    }


    /**
     * Returns value
     * 
     * @return value
     */
    public V[] getValue() {
        return value;
    }


    /**
     * Sets next node
     * 
     * @param next
     *            Node to set next to
     */
    public void setNext(PointNode<K, V> next) {
        this.next = next;
    }


    /**
     * Returns next node
     * 
     * @return next node
     */
    public PointNode<K,V> getNext() {
        return next;
    }
}
