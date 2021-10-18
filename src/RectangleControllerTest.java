import java.io.File;
import java.io.FileNotFoundException;
import org.junit.Ignore;
import student.TestCase;
import student.TestableRandom;

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
 * JUnit for RectangleController
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/12/2021
 */
public class RectangleControllerTest extends TestCase {

    // Fields -----------------------------------------------------------------
    private RectangleController controller;

    // Set up -----------------------------------------------------------------

    /**
     * Sets up controller with an existing command file
     */
    public void setUp() throws FileNotFoundException {
        
    }

    // Tests ------------------------------------------------------------------


    /**
     * Runs modified spec example input files and closes the controller
     * Files modified to account for randomness, does not test dump().
     * 
     * @throws FileNotFoundException
     */

    public void testRun() throws FileNotFoundException {
        controller = new RectangleController(new File("P2test2.txt"));
        controller.runAll();
        assertFuzzyEquals("Point Inserted: (p_p, 1, 20)\n" + 
            "Point Inserted: (p, 10, 30)\n" + 
            "Point Inserted: (p_42, 1, 20)\n" + 
            "Point Inserted: (far_point, 200, 200)\n" + 
            "SkipList Dump:\n" + 
            "level: 3 Value: null\n" + 
            "level: 1 Value: (far_point, 200, 200)\n" + 
            "level: 3 Value: (p, 10, 30)\n" + 
            "level: 1 Value: (p_42, 1, 20)\n" + 
            "level: 1 Value: (p_p, 1, 20)\n" + 
            "The SkipList's size is: 4\n" + 
            "QuadTree Dump:\n" + 
            "Node at 0, 0, 1024: Internal\n" + 
            "  Node at 0, 0, 512: Internal\n" + 
            "    Node at 0, 0, 256: Internal\n" + 
            "      Node at 0, 0, 128:\n" + 
            "      (p_p, 1, 20)\n" + 
            "      (p, 10, 30)\n" + 
            "      (p_42, 1, 20)\n" + 
            "      Node at 128, 0, 128: Empty\n" + 
            "      Node at 0, 128, 128: Empty\n" + 
            "      Node at 128, 128, 128:\n" + 
            "      (far_point, 200, 200)\n" + 
            "    Node at 256, 0, 256: Empty\n" + 
            "    Node at 0, 256, 256: Empty\n" + 
            "    Node at 256, 256, 256: Empty\n" + 
            "  Node at 512, 0, 512: Empty\n" + 
            "  Node at 0, 512, 512: Empty\n" + 
            "  Node at 512, 512, 512: Empty\n" + 
            "QuadTree Size: 13 QuadTree Nodes Printed.\n" + 
            "Duplicate Points:\n" + 
            "(1, 20)\n" + 
            "Point Found (p_p, 1, 20)\n" + 
            "Points intersecting region: (0, 0, 25, 25)\n" + 
            "Point Found: (p_42, 1, 20)\n" + 
            "Point Found: (p_p, 1, 20)\n" + 
            "4 QuadTree Nodes Visited\n" + 
            "Point (p_p, 1, 20) Removed\n" + 
            "Point (p, 10, 30) Removed\n" + 
            "Duplicate Points:\n" + 
            "SkipList Dump:\n" + 
            "level: 3 Value: null\n" + 
            "level: 1 Value: (far_point, 200, 200)\n" + 
            "level: 1 Value: (p_42, 1, 20)\n" + 
            "The SkipList's size is: 2\n" + 
            "QuadTree Dump:\n" + 
            "Node at 0, 0, 1024:\n" + 
            "(far_point, 200, 200)\n" + 
            "(p_42, 1, 20)\n" + 
            "QuadTree Size: 1 QuadTree Nodes Printed.", systemOut()
            .getHistory());
        systemOut().clearHistory();
        controller.close();
        



        // JUnit can shut up about long methods
    }


    public void testSecondInput() throws FileNotFoundException {
        controller = new RectangleController(new File("P2test1.txt"));
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        systemOut().clearHistory();
        controller.runAll();
        assertFuzzyEquals("Point REJECTED: (p_p, -1, -20)\n" + 
            "Point REJECTED: (p, 7, -8)\n" + 
            "Duplicate Points:\n" + 
            "SkipList Dump:\n" + 
            "level: 1 Value: null\n" + 
            "The SkipList's size is: 0\n" + 
            "QuadTree Dump:\n" + 
            "Node at 0, 0, 1024: Empty\n" + 
            "QuadTree Size: 1 QuadTree Nodes Printed.\n" + 
            "Point Not Found: p_p\n" + 
            "point Not Removed: p_p\n" + 
            "Point Rejected: (1, -1)\n" + 
            "point Not Found:(1, 1)\n" + 
            "Points intersecting region: (-5, -5, 20, 20)\n" + 
            "1 QuadTree Nodes Visited\n" + 
            "Rectangle Rejected: (5, 5, 4, -2):",
            systemOut().getHistory());
        systemOut().clearHistory();
        controller.close();
    }


    /**
     * Tests exceptions
     */
    public void testFileNotFoundException() {
        try {
            controller = new RectangleController(new File("fake"));
        }
        catch (FileNotFoundException e) {
            assertNotNull(e);
        }
    }


    /**
     * Tests coverage not met by modified input files. Due to dump, not tested
     * with assert.
     * 
     * @throws FileNotFoundException
     * 
     */
    @Ignore
    public void testMissedCoverage() throws FileNotFoundException {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        systemOut().clearHistory();

        controller = new RectangleController(new File(
            "P1Coverage.txt"));
        controller.runAll();
        controller.close();

        assertFuzzyEquals("Rectangle inserted: (r2, 15, 15, 5, 5)\r\n"
            + "Rectangle inserted: (r3, 7, 7, 10, 10)\r\n"
            + "Rectangle inserted: (r4, 20, 25, 7, 9)\r\n"
            + "Rectangle inserted: (r4, 20, 12, 3, 3)\r\n"
            + "Rectangle inserted: (r5, 6, 7, 11, 9)\r\n"
            + "An unknown command was ran\r\n"
            + "Rectangle not found:(69, 420, 69, 420)\r\n"
            + "Rectangles found:\r\n" + "(r5, 6, 7, 11, 9)\r\n"
            + "Rectangle not found: r69420\r\n"
            + "Rectangle rejected: (r99, 1, 2, 3, 4, 5)\r\n"
            + "Rectangle rejected: (r99, -1, 2, 3, 4)\r\n"
            + "Rectangle rejected: (r99, 1, -2, 3, 4)\r\n"
            + "Rectangle rejected: (r99, 1, 2, -3, 4)\r\n"
            + "Rectangle rejected: (r99, 1, 2, 3, -4)", systemOut()
            .getHistory());

        // Tests dump
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");

        controller = new RectangleController(new File("P1Dump.txt"));
        System.out.println(
            "Should have empty dump, 5 definite inserts, then a dump");
        controller.runAll();
        controller.close();
    }

    /**
     * Runs P2Test1 command file
     * @throws FileNotFoundException 
     */
    @Ignore
    public void testRunP2T1() throws FileNotFoundException {
        TestableRandom.setNextInts(5, 10, 22, 13, 12, 47);
        controller = new RectangleController(new File("P2test1.txt"));
        controller.runAll();
    }

    /**
     * Runs P2Test2 command file
     * @throws FileNotFoundException 
     */
    public void testRunP2T2() throws FileNotFoundException {
        TestableRandom.setNextInts(5, 10, 22, 13, 12, 47);
        controller = new RectangleController(new File("P2test2.txt"));
        controller.runAll();  
    }
}
