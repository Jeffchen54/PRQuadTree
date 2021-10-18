import student.TestCase;

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

// Java Doc -----------------------------------------------------------------
/**
 * Tests ParentNode
 * 
 * @author chenj (chenjeff4840), XC
 * @version 10.11.2021
 */
public class ParentNodeTest extends TestCase {

    // Fields -------------------------------------------------------------
    private ParentNode<String, Integer> node;
    private FlyweightNode<String, Integer> empty;

    // Set up -------------------------------------------------------------
    /**
     * this is set up
     */
    public void setUp() {
        empty = new FlyweightNode<String, Integer>();
        node = new ParentNode<String, Integer>(empty);
    }


    // Tests -------------------------------------------------------------
    /**
     * this is test for getNodeClass function
     */
    public void testGetNodeClass() {
        assertEquals(NodeClassification.ParentNode, node.getNodeClass());
    }


    /**
     * Tests setting and getting children
     */
    public void testChildren() {
        // Checking if all children are flyweights
        for (int i = 0; i < 4; i++) {
            assertEquals(empty, node.getChild(i));
        }

        // Invalid directions
        assertNull(node.getChild(-1));
        assertNull(node.getChild(4));
        node.setChild(empty, -1);
        node.setChild(empty, 4);

        // setting and getting children at directions
        LeafNode<String, Integer> leaf;
        for (int i = 0; i < 4; i++) {
            leaf = new LeafNode<String, Integer>();
            leaf.addPoint("Earth 200" + Integer.toString(i), new Integer[] { 1,
                2, 3 });
            node.setChild(leaf, i);
            assertEquals(leaf, node.getChild(i));
        }
    }

}
