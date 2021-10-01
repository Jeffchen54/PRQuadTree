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
 * Generic SkipNode class supporting getData() and getForward()
 * 
 * @param <K>
 *            Type of key
 * @param <V>
 *            Type of value
 * @author Jeff Chen (chenjeff4840)
 * @author openDSA
 * @version 9/01/2021
 */

public class SkipNode<K, V> {

    // Fields -----------------------------------------------------------------
    private KVPair<K, V> data;
    private SkipNode<K, V>[] forward;
    private int level;

    // Constructor--------------------------------------------------------------
    /**
     * Creates a SkipNode with user defined data and levels.
     * 
     * @param data
     *            Data to load into a SkipNode
     * @param levels
     *            # of levels for the SkipNode (begins at 0). Defaults to 0 if
     *            invalid parameter sent.
     */
    @SuppressWarnings("unchecked")
    public SkipNode(KVPair<K, V> data, int levels) {
        if (levels < 0) {
            levels = 0;
        }

        this.data = data;
        forward = (SkipNode<K, V>[])new SkipNode[levels + 1];

        for (int i = 0; i < levels + 1; i++) {
            forward[i] = null;
        }

        this.level = levels + 1;
    }

    // Functions --------------------------------------------------------------


    /**
     * Returns KVPair
     * 
     * @return KVPair
     */
    public KVPair<K, V> getData() {
        return data;
    }


    /**
     * Returns forward array
     * 
     * @return forward array
     */
    public SkipNode<K, V>[] getForward() {
        return forward;
    }


    /**
     * Returns number of levels in forward array starting at 1
     * 
     * @return number of levels in forward array.
     */
    public int getLevel() {
        return level;
    }
}
