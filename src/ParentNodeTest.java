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
import student.TestCase;

public class ParentNodeTest extends TestCase {

    private ParentNode node;
    
    
    /**
     * this is set up
     */
    public void setUp() {
        node = new ParentNode();
    }
    
    /**
     * this is test for getNodeClass function
     */
    public void testGetNodeClass() {
        assertEquals("ParentNode", node.getNodeClass());
    }
    
//    /**
//     * this is test for getData
//     */
//    public void testGetData() {
//        assertEquals("a", node.getData().getKey());
//    }
//    
//    /**
//     * this is test for getter and setter
//     */
//    public void testGetNW() {
//        assertNull(node.getNE());
//        node.setNW(node);
//        assertEquals("a", node.getNW().getData().getKey());
//    }
//    
//    /**
//     * this is test for getter and setter
//     */
//    public void testGetNE() {
//        node.setNE(node);
//        assertEquals("a", node.getNE().getData().getKey());
//    }
//    
//    /**
//     * this is test for getter and setter
//     */
//    public void testGetSW() {
//        node.setSW(node);
//        assertEquals("a", node.getSW().getData().getKey());
//    }
//    
//    /**
//     * this is test for getter and setter
//     */
//    public void testGetSE() {
//        node.setSE(node);
//        assertEquals("a", node.getSE().getData().getKey());
//    }
//    
//    /**
//     * this is test for set data to current node
//     */
//    public void testSetData() {
//        KVPair<String, Integer> pair1 = new KVPair<String, Integer>("7", 4);
//        node.setData(pair1);
//        assertEquals("7", node.getData().getKey());
//    }
    
}
