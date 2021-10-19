import java.util.Iterator;
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

// Java Doc -------------------------------------------------------------------
/**
 * Tests the LeafNode
 * 
 * @author chenj (chenjeff4840), XC
 * @version 10.17.2021
 */
public class LeafNodeTest extends TestCase {

    // Fields ----------------------------------------------------------------
    private LeafNode<String, Integer> node1;
    private String s1;
    private String s2;
    private String s3;
    private Integer[] i1;
    private Integer[] i2;
    private Integer[] i3;

    // Set Up --------------------------------------------------------------
    /**
     * Creates empty leaf node
     */
    public void setUp() {
        node1 = new LeafNode<String, Integer>();
        s1 = "Earth";
        s2 = "Mars";
        s3 = "Jupiter";
        i1 = new Integer[] { 360, 0 };
        i2 = new Integer[] { 420, 1 };
        i3 = new Integer[] { 980, 2 };
    }


    // Tests --------------------------------------------------------------
    /**
     * this is test for getNodeClass function
     */
    public void testGetNodeClass() {
        assertEquals(NodeClassification.LeafNode, node1.getNodeClass());
    }


    /**
     * Tests adding and getting points
     */
    public void testAddGetPoints() {
        // Empty get
        Iterator<PointNode<String, Integer>> iter = node1.getPoints();
        assertFalse(iter.hasNext());

        // Adding 3 points
        node1.addPoint(s1, i1);
        node1.addPoint(s2, i2);
        node1.addPoint(s3, i3);

        // getPoints
        iter = node1.getPoints();
        assertEquals(s3, iter.next().getKey());
        assertEquals(s2, iter.next().getKey());
        assertEquals(s1, iter.next().getKey());

        // Adding dupe entry
        node1.addPoint(s1, i1);

        // Adding value dupe entry
        node1.addPoint(s1, i2);
        assertTrue(node1.exists(s1, i2));

        // remove points
        node1.remove(s1, i2);
        node1.remove(s1, i1);
        iter = node1.getPoints();

        assertEquals(s3, iter.next().getKey());
        assertEquals(s2, iter.next().getKey());
        assertNull(iter.next());
    }


    /**
     * Tests size and numUniquePoint
     */
    public void testSize() {
        // Empty
        assertNull(node1.getDuplicates());
        assertEquals(0, node1.getTotalSize());

        // Entries
        node1.addPoint(s1, i1);
        assertEquals(1, node1.getTotalSize());

        node1.addPoint(s2, i2);
        assertEquals(2, node1.getTotalSize());

        // Dupe entry
        node1.addPoint(s1, i1);
        assertEquals(2, node1.getTotalSize());

        // Removing dupe values
        node1.addPoint(s1, i2);
        node1.addPoint(s3, i2);

        assertNotNull(node1.getDuplicates());
        assertEquals(4, node1.getTotalSize());
        assertNotNull(node1.remove(s1, i2));
        assertNotNull(node1.remove(null, i2));
        assertEquals(2, node1.getTotalSize());
        assertNull(node1.getDuplicates());

        // All removed
        node1.remove(s1, i1);
        assertEquals(1, node1.getTotalSize());

        node1.remove(s2, i2);
        assertEquals(0, node1.getTotalSize());
    }


    /**
     * Tests remove
     */
    public void testRemove() {
        // Remove empty
        assertNull(node1.remove(s1, i1));
        assertNull(node1.remove(null, i1));

        // Coverage (more specific tests done in PointNodeList)
        node1.addPoint(s1, i1);
        node1.addPoint(s1, i1);
        node1.addPoint(s2, i3);
        node1.addPoint(s1, i2);
        assertEquals(3, node1.getNumUniquePoints());

        assertEquals(s1, node1.remove(s1, i1).getKey());
        assertEquals(s1, node1.remove(null, i2).getKey());
        assertEquals(1, node1.getNumUniquePoints());

        // Trying to remove invalid addition
        assertNull(node1.remove(s1, i1));
        assertEquals(1, node1.getNumUniquePoints());

        // Remove all entries
        assertNotNull(node1.remove(null, i3));
        assertEquals(0, node1.getNumUniquePoints());

    }

}
