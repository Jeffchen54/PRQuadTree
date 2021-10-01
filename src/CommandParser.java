import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
 * Parser used by controller to interpret command file
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/12/2021
 */
public class CommandParser {

    // Fields -----------------------------------------------------------------
    private Scanner commands;

    // Constructor -------------------------------------------------------------

    /**
     * Constructs CommandParser using valid user file.
     * 
     * @param commands
     *            File containing commands
     * @throws FileNotFoundException
     */
    public CommandParser(File commands) throws FileNotFoundException {
        this.commands = new Scanner(commands);

    }

    // Functions --------------------------------------------------------------


    /**
     * Splices white space blocks in next line in command file and return
     * tokens. Advances to next line.
     * 
     * @return next line in command file tokens with white space delimiters
     */
    public String[] next() {
        // base case
        if (!commands.hasNextLine()) {
            return null;
        }

        // Parsing
        String line = commands.nextLine(); // grab next line

        line = line.trim(); // remove initial and end whiteSpace

        if (line.equals("")) { // Recursion when encountering line(s)
            return (this.next());
        }

        String[] tokens = line.split("\\s+"); // treat white blocks as delims

        return tokens;
    }


    /**
     * Closes the scanner used for reading file given in constructor
     */
    public void close() {
        if (commands != null) {
            commands.close();
            commands = null;
        }

    }

}
