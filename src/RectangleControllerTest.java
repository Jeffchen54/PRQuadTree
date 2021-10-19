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
        controller.setSkipListLevels(new int[] { 1, 3, 1, 1 });
        controller.runAll();

        assertFuzzyEquals("Point Inserted: (p_p, 1, 20)\n"
            + "Point Inserted: (p, 10, 30)\n"
            + "Point Inserted: (p_42, 1, 20)\n"
            + "Point Inserted: (far_point, 200, 200)\n" + "SkipList Dump:\n"
            + "level: 3 Value: null\n"
            + "level: 1 Value: (far_point, 200, 200)\n"
            + "level: 3 Value: (p, 10, 30)\n"
            + "level: 1 Value: (p_42, 1, 20)\n"
            + "level: 1 Value: (p_p, 1, 20)\n" + "The SkipList's size is: 4\n"
            + "QuadTree Dump:\n" + "Node at 0, 0, 1024: Internal\n"
            + "  Node at 0, 0, 512: Internal\n"
            + "    Node at 0, 0, 256: Internal\n" + "      Node at 0, 0, 128:\n"
            + "      (p_p, 1, 20)\n" + "      (p, 10, 30)\n"
            + "      (p_42, 1, 20)\n" + "      Node at 128, 0, 128: Empty\n"
            + "      Node at 0, 128, 128: Empty\n"
            + "      Node at 128, 128, 128:\n" + "      (far_point, 200, 200)\n"
            + "    Node at 256, 0, 256: Empty\n"
            + "    Node at 0, 256, 256: Empty\n"
            + "    Node at 256, 256, 256: Empty\n"
            + "  Node at 512, 0, 512: Empty\n"
            + "  Node at 0, 512, 512: Empty\n"
            + "  Node at 512, 512, 512: Empty\n"
            + "QuadTree Size: 13 QuadTree Nodes Printed.\n"
            + "Duplicate Points:\n" + "(1, 20)\n" + "Point Found (p_p, 1, 20)\n"
            + "Points intersecting region: (0, 0, 25, 25)\n"
            + "Point Found: (p_42, 1, 20)\n" + "Point Found: (p_p, 1, 20)\n"
            + "4 QuadTree Nodes Visited\n" + "Point (p_p, 1, 20) Removed\n"
            + "Point (p, 10, 30) Removed\n" + "Duplicate Points:\n"
            + "SkipList Dump:\n" + "level: 3 Value: null\n"
            + "level: 1 Value: (far_point, 200, 200)\n"
            + "level: 1 Value: (p_42, 1, 20)\n" + "The SkipList's size is: 2\n"
            + "QuadTree Dump:\n" + "Node at 0, 0, 1024:\n"
            + "(far_point, 200, 200)\n" + "(p_42, 1, 20)\n"
            + "QuadTree Size: 1 QuadTree Nodes Printed.", systemOut()
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
        assertFuzzyEquals("Point REJECTED: (p_p, -1, -20)\r\n"
            + "Point REJECTED: (p, 7, -8)\r\n" + "Duplicate Points:\r\n"
            + "SkipList Dump:\r\n" + "level: 1 Value: null\r\n"
            + "The SkipList's Size is: 0\r\n" + "QuadTree Dump:\r\n"
            + "Node at 0, 0, 1024: Empty\r\n"
            + "QuadTree Size: 1 QuadTree Nodes Printed.\r\n"
            + "Point Not Found: p_p\r\n" + "point Not Removed: p_p\r\n"
            + "Point Rejected: (1, -1)\r\n" + "point Not Found: (1, 1)\r\n"
            + "Points Intersecting Region: (-5, -5, 20, 20)\r\n"
            + "1 QuadTree Nodes Visited\r\n"
            + "Rectangle Rejected: (5, 5, 4, -2)\r\n" + "", systemOut()
                .getHistory());
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
    public void testMissedCoverage() throws FileNotFoundException {

        // Tests more missed coverage
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        systemOut().clearHistory();
        controller = new RectangleController(new File("testp2.txt"));
        controller.runAll();
        assertFuzzyEquals("Point Inserted: (p_p, 1, 20)\r\n"
            + "Point REJECTED: (p_p, 1, 20)\r\n"
            + "Point REJECTED: (p_bad_length, 1)\r\n"
            + "rectangle rejected (11, 11, 0, 0)\r\n"
            + "Point REJECTED: (p_bad_toolargexy, 121212, 12313)\r\n"
            + "Point REJECTED: (p_bad_toolargex, 121212, 13)\r\n"
            + "Point REJECTED: (p_bad_toolargy, 122, 12313)\r\n"
            + "An unknown command was ran\r\n"
            + "rectangle rejected (11, 11, 0, 0)\r\n"
            + "rectangle rejected (11, 11, -1, 1)\r\n"
            + "rectangle rejected (11, 11, 1, -1)\r\n"
            + "rectangle rejected (5, 5, 4, 2, 3)\r\n"
            + "rectangle rejected (5, 5, 4, -2, 3)", systemOut().getHistory());
        controller.close();
    }
}
