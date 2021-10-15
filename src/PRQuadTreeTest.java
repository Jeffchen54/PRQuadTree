import java.util.Iterator;
import org.junit.Ignore;
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
// letter of this restriction. - JC

// Java Doc -------------------------------------------------------------------
/**
 * Tests PRQuadTree
 * 
 * @author chenj (chenjeff4840)
 * @version 10.5.2021
 */
public class PRQuadTreeTest extends TestCase {
    // Fields ---------------------------------------------------------------
    private PRQuadTree tree;
    private Integer[] min;
    private Integer[] max;

    // SetUp ----------------------------------------------------------------
    public void setUp() {
        min = new Integer[] { 0, 0 };
        max = new Integer[] { 1024, 1024 };

        tree = new PRQuadTree(min, max);
    }


    // Tests ----------------------------------------------------------------
    @Ignore
    public void testInsert() {
        // Inserts onto empty list
        tree.insert("Hello World", new Integer[] { 0, 125 });
        tree.peek();

        // Insert same quadrant
        tree.insert("IT WORKS???!!", new Integer[] { 0, 100 });
        tree.insert("IT WORKS???!!", new Integer[] { 0, 50 });
        tree.peek();

        // Split
        tree.insert("IT WORKS???!!", new Integer[] { 0, 75 });
        tree.peek();

        // Putting values onto other quadrants
        tree.insert("NE", new Integer[] { 700, 200 });
        tree.insert("SW", new Integer[] { 200, 700 });
        tree.insert("SE", new Integer[] { 700, 700 });
        tree.peek();
    }


    /**
     * Tests commands from P2 input
     */
    public void testP2Input() {
        tree.insert("p_p", new Integer[] { 1, 20 });
        tree.insert("p", new Integer[] { 10, 30 });
        tree.insert("p_42", new Integer[] { 1, 20 });
        tree.insert("far_point", new Integer[] { 200, 200 });
        tree.peek();
    }


    /**
     * Edge cases
     */
    public void testEdgeCases() {
        tree.insert("NE", new Integer[] { 512, 0 });
        tree.insert("SE", new Integer[] { 512, 512 });
        tree.insert("SE", new Integer[] { 1024, 512 });
        tree.insert("SE", new Integer[] { 512, 1024 });
        tree.insert("SW", new Integer[] { 0, 512 });
        tree.peek();
    }
}
