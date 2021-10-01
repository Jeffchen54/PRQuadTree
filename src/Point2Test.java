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
        assertFuzzyEquals("Invocation: java Point2 {commandFile}\n",
            systemOut().getHistory());
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n");
        systemOut().clearHistory();

        // Invocation with P1Test2 without
        Point2.main(new String[] { "P1test2.txt" });
        assertFuzzyEquals("Rectangle rejected: (inExist_Rec0, 1, 1, 0, 10)\r\n"
            + "Rectangle rejected: (inExist_Rec1, 1, 1, 7, -10)\r\n"
            + "Rectangle rejected: (inExist_Rec2, 0, 0, 0, 0)\r\n"
            + "Rectangle rejected: (inExist_Rec3, -1, -2, 7, 10)\r\n"
            + "Rectangle rejected: (inExist_Rec4, -1, 1, -2, 10)\r\n"
            + "Rectangle rejected: (inExist_Rec5, 1, -1, 0, 10)\r\n"
            + "Rectangle rejected: (inExist_Rec6, 12, 1, 7, -6)\r\n"
            + "Intersections pairs:\r\n"
            + "Rectangle not removed: (inExist_Rec1)\r\n"
            + "Rectangle rejected: (12, 1, 7, -6)\r\n"
            + "Rectangle rejected: (r1, 0, 0, 217, 1474647)\r\n"
            + "Rectangle inserted: (r2, 10, 10, 15, 15)\r\n"
            + "Rectangle inserted: (R2, 11, 11, 5, 5)\r\n"
            + "Rectangle inserted: (r3, 0, 0, 1000, 10)\r\n"
            + "Rectangle inserted: (r4, 0, 0, 10, 1000)\r\n"
            + "Rectangle rejected: (900, 5, 0, 0)\r\n"
            + "Rectangles intersecting region (900, 5, 1, 1): \r\n"
            + "(r3, 0, 0, 1000, 10)\r\n"
            + "Rectangle rejected: (5, 900, 0, 1)\r\n"
            + "Rectangle rejected: (0, 0, 217, 1474647)\r\n"
            + "Intersections pairs:\r\n"
            + "(R2, 11, 11, 5, 5 | r2, 10, 10, 15, 15)\r\n"
            + "(r2, 10, 10, 15, 15 | R2, 11, 11, 5, 5)\r\n"
            + "(r3, 0, 0, 1000, 10 | r4, 0, 0, 10, 1000)\r\n"
            + "(r4, 0, 0, 10, 1000 | r3, 0, 0, 1000, 10)\r\n"
            + "Rectangle removed: (R2, 11, 11, 5, 5)\r\n"
            + "Rectangle removed: (r2, 10, 10, 15, 15)\r\n"
            + "Rectangle inserted: (r5, 56, 72, 80, 80)\r\n"
            + "Rectangle inserted: (r6, 61, 73, 11, 9)\r\n"
            + "Rectangle inserted: (r6, 61, 73, 11, 9)\r\n"
            + "Rectangle rejected: (61, 73, 0, 0)\r\n" 
            + "Rectangles found:\r\n"
            + "(r6, 61, 73, 11, 9)\r\n" + "(r6, 61, 73, 11, 9)\r\n"
            + "Rectangle inserted: (r5, 20, 12, 3, 3)\r\n"
            + "Rectangle inserted: (r7, 23, 15, 100, 100)\r\n"
            + "Rectangle inserted: (r8, 23, 12, 3, 3)\r\n"
            + "Rectangle inserted: (r9, 20, 15, 3, 3)\r\n"
            + "Intersections pairs:\r\n"
            + "(r3, 0, 0, 1000, 10 | r4, 0, 0, 10, 1000)\r\n"
            + "(r4, 0, 0, 10, 1000 | r3, 0, 0, 1000, 10)\r\n"
            + "(r5, 56, 72, 80, 80 | r6, 61, 73, 11, 9)\r\n"
            + "(r6, 61, 73, 11, 9 | r5, 56, 72, 80, 80)\r\n"
            + "(r5, 56, 72, 80, 80 | r6, 61, 73, 11, 9)\r\n"
            + "(r6, 61, 73, 11, 9 | r5, 56, 72, 80, 80)\r\n"
            + "(r5, 56, 72, 80, 80 | r7, 23, 15, 100, 100)\r\n"
            + "(r7, 23, 15, 100, 100 | r5, 56, 72, 80, 80)\r\n"
            + "(r6, 61, 73, 11, 9 | r6, 61, 73, 11, 9)\r\n"
            + "(r6, 61, 73, 11, 9 | r6, 61, 73, 11, 9)\r\n"
            + "(r6, 61, 73, 11, 9 | r7, 23, 15, 100, 100)\r\n"
            + "(r7, 23, 15, 100, 100 | r6, 61, 73, 11, 9)\r\n"
            + "(r6, 61, 73, 11, 9 | r7, 23, 15, 100, 100)\r\n"
            + "(r7, 23, 15, 100, 100 | r6, 61, 73, 11, 9)\r\n"
            + "Rectangle rejected: (23, 15, 0, 0)\r\n"
            + "Rectangles intersecting region (22, 14, 2, 2): \r\n"
            + "(r5, 20, 12, 3, 3)\r\n" + "(r7, 23, 15, 100, 100)\r\n"
            + "(r8, 23, 12, 3, 3)\r\n" + "(r9, 20, 15, 3, 3)\r\n"
            + "Rectangle rejected: (22, 14, 2, 0)\r\n"
            + "Rectangle removed: (r4, 0, 0, 10, 1000)\r\n"
            + "Rectangle removed: (r6, 61, 73, 11, 9)\r\n"
            + "Rectangle removed: (r5, 20, 12, 3, 3)\r\n"
            + "Rectangle removed: (r6, 61, 73, 11, 9)\r\n"
            + "Rectangle removed: (r7, 23, 15, 100, 100)\r\n"
            + "Rectangle not found: r2\r\n" + "Rectangle not found: 20\r\n"
            + "Rectangle rejected: (biggestRec, 0, 0, "
            + "2147483647, 2147483647)\r\n"
            + "Rectangle inserted: (smallest_Rec, 0, 0, 1, 1)\r\n"
            + "Intersections pairs:\r\n"
            + "(r3, 0, 0, 1000, 10 | smallest_Rec, 0, 0, 1, 1)\r\n"
            + "(smallest_Rec, 0, 0, 1, 1 | r3, 0, 0, 1000, 10)\r\n"
            + "Rectangle removed: (smallest_Rec, 0, 0, 1, 1)\r\n"
            + "Rectangle not found: biggestRec\r\n"
            + "Rectangle rejected: (0, 0, 2147483647, 2147483640)\r\n"
            + "Rectangle rejected: (0, 0, 2147483647, 2147483647)\r\n"
            + "Rectangle not found: biggestRec\r\n"
            + "Rectangle not removed: (smallest_Rec)\r\n"
            + "Rectangles intersecting region "
            + "(0, 0, 2147483647, 2147483647): \r\n"
            + "(r3, 0, 0, 1000, 10)\r\n" + "(r5, 56, 72, 80, 80)\r\n"
            + "(r8, 23, 12, 3, 3)\r\n" + "(r9, 20, 15, 3, 3)\r\n"
            + "Intersections pairs:\r\n"
            + "Rectangles intersecting region "
            + "(-21, -21, 2147483647, 2147483647): \r\n"
            + "(r3, 0, 0, 1000, 10)\r\n" + "(r5, 56, 72, 80, 80)\r\n"
            + "(r8, 23, 12, 3, 3)\r\n" 
            + "(r9, 20, 15, 3, 3)\r\n"
            + "Rectangles intersecting region (-2147483644, "
            + "-2147483644, 2147483647, 2147483647): \r\n"
            + "(r3, 0, 0, 1000, 10)\r\n"
            + "Rectangle rejected: (0, 0, -2147483644, -2147483644)",
            systemOut().getHistory());
    }
}
