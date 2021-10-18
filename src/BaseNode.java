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
 * Interface representing a base node that can only return class information
 * 
 * @author chenj (chenjeff4840), XC
 * @version 10.11.2021
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public interface BaseNode<K, V extends Comparable<V>> {

    // Functions -----------------------------------------------------------
    /**
     * Returns the type of node according to enum NodeClassification.
     * 
     * @return NodeClassification of this node.
     */
    public NodeClassification getNodeClass();

}
