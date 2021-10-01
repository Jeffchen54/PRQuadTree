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
 * KVPair to be used in SkipNode
 * 
 * @param <K>
 *            Key type
 * @param <V>
 *            Value type
 * @author Jeff Chen (chenjeff4840)
 * @version 9/18/2021
 */
public class KVPair<K, V> {
    // Fields -----------------------------------------------------------------
    private K key;
    private V value;

    // Constructor -------------------------------------------------------------
    /**
     * Creates a KVPair from parameters.
     * 
     * @param key
     *            key for KVPair
     * @param value
     *            value for KVPair
     */
    public KVPair(K key, V value) {
        this.key = key;
        this.value = value;
    }


    // Functions -------------------------------------------------------------
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
    public V getValue() {
        return value;
    }
}
