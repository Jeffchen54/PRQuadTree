import student.TestCase;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than the instructor, ACM/UPE tutors, programming
//partner (if allowed in this class), or the TAs assigned to
//this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction. - JC & XC

// Java Doc ----------------------------------------------------------------
/**
 * Tests the PointNode
 * @author chenj (chenjeff4840), XC
 */
public class PointNodeTest extends TestCase{
    // Fields -------------------------------------------------------------
    private PointNode<String, Integer> node;
    
    
    // SetUp -------------------------------------------------------------
    /**
     * Sets up the PointNode
     */
    public void setUp() {
        node = new PointNode<String, Integer>("Earth", 360);
    }
    
    // Tests -------------------------------------------------------------
    
    /**
     * Tests getters
     */
    public void testGetters() {
        assertEquals("Earth", node.getKey());
        assertEquals(360, node.getValue(), 0.1);
        assertNull(node.getNext());
        
        PointNode<String, Integer> other = new PointNode<>("Mars", 420);
        node.setNext(other);
        assertEquals(other, node.getNext());
        
    }
}