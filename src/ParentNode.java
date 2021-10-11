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

// Java Doc --------------------------------------------------------------
/**
 * Represents ParentNode with fixed number of children for PRQuadTree.
 * Only stores children data and type info.
 * 
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 * @author chenj (chenjeff4840), XC
 * @version 10.11.2021
 */
public class ParentNode<K, V extends Comparable<V>> implements BaseNode<K, V> {

    // Fields --------------------------------------------------------------
    private BaseNode<K, V>[] children;
    private NodeClassification type;
    @SuppressWarnings("unused")
    private FlyweightNode<K, V> empty;
    private static final int NUMCHILDREN = 4;

    // Constructor ---------------------------------------------------------
    /**
     * this is the constructor of a quadNode
     * 
     * @param empty
     *            flyweightNode
     */
    @SuppressWarnings("unchecked")
    public ParentNode(FlyweightNode<K, V> empty) {
        children = (BaseNode<K, V>[])new BaseNode[NUMCHILDREN];
        type = NodeClassification.ParentNode;
        this.empty = empty;

        for (int i = 0; i < NUMCHILDREN; i++) {
            children[i] = empty;
        }

    }


    // Functions ----------------------------------------------------------
    /**
     * Get child node according to direction with 0 pointing to NW and furthest
     * direction at SW
     * 
     * @param direction
     *            of child to return
     * @return child at direction
     */
    public BaseNode<K, V> getChild(int direction) {
        if (direction < 0 || direction >= NUMCHILDREN) {
            return null;
        }

        return children[direction];
    }


    /**
     * Sets a child branch at the specified direction
     * 
     * @param child
     *            child to set branch to
     * @param direction
     *            direction of child branch to change
     * @precondition child != null
     */
    public void setChild(BaseNode<K, V> child, int direction) {
        if (direction >= 0 && direction < NUMCHILDREN) {
            children[direction] = child;
        }
    }


    /**
     * this function return the name of the class
     * 
     * @return a string
     */
    public NodeClassification getNodeClass() {
        return type;
    }
}
