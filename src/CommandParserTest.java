
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
import java.io.File;
import java.io.FileNotFoundException;
import student.TestCase;

// Java Doc -------------------------------------------------------------------
/**
 * Junit for CommandParser
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/12/2021
 */
public class CommandParserTest extends TestCase {
    // Fields ---------------------------------------------------------------
    private CommandParser parser;

    // Set up ----------------------------------------------------------------

    /**
     * Sets up CommandParser with sample file
     */
    public void setUp() throws FileNotFoundException {
        parser = new CommandParser(new File("test.txt"));
    }

    // Tests -----------------------------------------------------------------


    /**
     * Tests next() and close()
     * 
     * @throws FileNotFoundException
     */
    public void testNext() throws FileNotFoundException {
        String[] line = parser.next();
        assertEquals(line[0], "regionsearch"); // Normal
        assertEquals(line[1], "-2147483644");
        assertEquals(line[2], "-2147483644");
        assertEquals(line[3], "2147483647");
        assertEquals(line[4], "2147483647");

        parser.next();
        line = parser.next();

        assertEquals(line[0], "regionsearch"); // Large white space
        assertEquals(line[1], "11");
        assertEquals(line[2], "11");
        assertEquals(line[3], "0");
        assertEquals(line[4], "0");

        line = parser.next();

        assertEquals(line[0], "dump");

        line = parser.next(); // Should skip new line

        assertEquals(line[0], "remove"); // large initial white space
        assertEquals(line[1], "r4");

        parser.next();
        parser.next();
        parser.next();
        parser.next();
        line = parser.next(); // Should be at EOF

        assertNull(line);
        parser.close();
        parser.close();

        parser = new CommandParser(new File("P1test1.txt"));
        while ((line = parser.next()) != null) {
            for (String i : line) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }

    }

}
