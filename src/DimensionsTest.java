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
 * JUnit for Dimensions
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/18/2021
 */
public class DimensionsTest extends TestCase {

    // Fields ----------------------------------------------------------------
    private Dimensions data;

    // Set up ----------------------------------------------------------------

    /**
     * Setups up Dimension
     */
    public void setUp() {
        data = new Dimensions(new int[] { 1, 2, 3, 4, 5 });
    }

    // Functions ------------------------------------------------------------


    /**
     * Tests equals
     */
    public void testEquals() {
        // null
        assertFalse(data.equals(null));

        // same object
        assertTrue(data.equals(data));

        // wrong class
        assertFalse(data.equals("Eclipse Screaming"));

        // bad length
        int[] arr = { 1, 2, 3, 4 };
        Dimensions other = new Dimensions(arr);
        assertFalse(data.equals(other));

        // equal length
        int[] arr2 = { 1, 2, 3, 4, 7 };
        other = new Dimensions(arr2);
        assertFalse(data.equals(other));

        // equal
        int[] arrEquals = { 1, 2, 3, 4, 5 };
        other = new Dimensions(arrEquals);
        assertTrue(data.equals(other));
    }


    /**
     * Tests getArr()
     */
    public void testGetArr() {
        int[] arr = data.getArr();

        for (int i = 0; i < 5; i++) {
            assertEquals(arr[i], i + 1);
        }
    }
}
