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

// JavaDoc --------------------------------------------------------------
/**
 * Tests the FlyweightNode
 * 
 * @author Jeff Chen (chenjeff4840), XC
 * @version 10.17.2021
 */
public class FlyweightNodeTest extends TestCase {

    // SetUp -------------------------------------------------------------

    /**
     * Intentionally left empty
     */
    public void setUp() {
        // Intentionally left empty due to single test case
    }


    // Tests ------------------------------------------------------------
    /**
     * this is test for getNodeClass function
     */
    @SuppressWarnings("rawtypes")
    public void testGetNodeClass() {
        FlyweightNode fn = new FlyweightNode();
        assertEquals(NodeClassification.FlyweightNode, fn.getNodeClass());
    }
}
