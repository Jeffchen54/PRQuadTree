import student.TestCase;
// On my honor:
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
 * Test for SkipNode
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/07/2021
 */

public class SkipNodeTest extends TestCase {

    // Tests -----------------------------------------------------------------

    /**
     * Tests constructor
     */
    public void testConstructor() {
        KVPair<Integer, Integer> pair = new KVPair<>(5, 9);
        SkipNode<Integer, Integer> node = new SkipNode<>(pair, -1);

        assertEquals(1, node.getLevel());
        node = new SkipNode<>(pair, -69420);
        assertEquals(1, node.getLevel());
        node = new SkipNode<>(pair, 4);
        assertEquals(5, node.getLevel());
    }


    /**
     * Tests getters for key and value
     */
    public void testGetData() {
        KVPair<Integer, Integer> pair = new KVPair<>(5, 9);
        Integer i = 5;

        // key
        SkipNode<Integer, Integer> node = new SkipNode<>(pair, -1);
        assertEquals(i, node.getData().getKey(), 1);

        // value
        i = 9;
        assertEquals(i, node.getData().getValue(), 1);

    }


    /**
     * Tests getForward()
     */
    public void testGetForward() {
        // Regular levels
        KVPair<Integer, Integer> pair = new KVPair<>(5, 9);
        SkipNode<Integer, Integer> node = new SkipNode<>(pair, 7);
        assertEquals(8, node.getForward().length);

        // negative levels
        node = new SkipNode<>(pair, -1);
        assertEquals(1, node.getForward().length);
    }
}
