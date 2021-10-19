import java.io.FileNotFoundException;
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
 * JUnit for Point2
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/18/2021
 */
public class Point2Test extends TestCase {
    // Setup -------------------------------------------------------------------

    /**
     * Intentionally left empty due to nature of testing
     */
    public void setUp() {
        // Intentionally left empty
    }

    // Tests -------------------------------------------------------------------


    /**
     * Tests main
     * 
     * @throws FileNotFoundException
     */
    public void testMain() throws FileNotFoundException {
        // Empty invocation
        Point2.main(new String[] {});
        assertFuzzyEquals("Invocation: java Point2 {commandFile}\n", systemOut()
            .getHistory());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        systemOut().clearHistory();

        // Invocation with P1Test2 without
        Point2.main(new String[] { "mainSample.txt" });
        assertFuzzyEquals("Point Inserted: (a1, 3, 2)\r\n", systemOut()
            .getHistory());
    }
}
