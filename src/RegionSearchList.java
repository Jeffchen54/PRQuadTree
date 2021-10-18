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
 * List to be used for regionSearch. Stores and maintains a record for
 * points acquired through regionSearch as well as keep track of the number
 * of nodes visited.
 * 
 * @param <K>
 *            Key of point
 * @param <V>
 *            Value of point
 * @author Jeff Chen (chenjeff4840)
 * @version 10/17/2021
 */
public class RegionSearchList<K extends Comparable<K>, V extends Comparable<V>>
    extends PointNodeList<K, V> {
    // Fields --------------------------------------------------------------
    private int visited;

    // Constructor ---------------------------------------------------------
    /**
     * Creates an empty list
     */
    public RegionSearchList() {
        super();
        visited = 0;
    }


    // Functions ------------------------------------------------------------
    /**
     * Increments the number of nodes visted by 1
     */
    public void incrementVisited() {
        visited++;
    }


    /**
     * Returns number of nodes visited
     * 
     * @return number of nodes visited
     */
    public int numVisited() {
        return visited;
    }

}
