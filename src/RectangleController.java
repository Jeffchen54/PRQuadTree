import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

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
 * Intermediary between SkipList and main/client. Reads commands from
 * client file and communicates changes to SkipList
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/02/2021
 */
public class RectangleController {
    // Fields ----------------------------------------------------------------
    private CommandParser parser;
    private SkipList<String, Dimensions> list;

    // Constructor -----------------------------------------------------------

    /**
     * Constructs RectangleController with SkipList of maximum level 7
     * 
     * @param commands
     *            File containing commands to run
     * @throws FileNotFoundException
     *             if file does not exists.
     */
    public RectangleController(File commands)
        throws FileNotFoundException {
        parser = new CommandParser(commands);
        list = new SkipList<String, Dimensions>();
    }

    // Functions ------------------------------------------------------------


    /**
     * Runs all commands
     * 
     * @precondition All commands refer only to Rectangles
     */
    public void runAll() {
        boolean remaining = true;
        while (remaining) {
            remaining = runNext();
        }
    }


    /**
     * Runs next command
     * 
     * @precondition All commands refer only to Rectangles
     * @return False if EOF, true otherwise
     */
    public boolean runNext() {
        String[] tokens = parser.next();

        if (tokens == null) {
            return false;
        }

        switch (tokens[0]) {
            case "dump":
                dump();
                break;
            case "intersections":
                intersections();
                break;
            case "regionsearch":
                regionSearch(extractArr(tokens, 1));
                break;
            case "remove":
                if (tokens.length == 2) {
                    remove(tokens[1]);
                }
                else {
                    remove(new Dimensions(extractArr(tokens, 1)));
                }
                break;
            case "insert":
                insert(tokens[1], new Dimensions(extractArr(tokens, 2)));
                break;
            case "search":
                search(tokens[1]);
                break;
            default:
                System.out.println("An unknown command was ran");
        }
        return true;
    }


    /**
     * Closes controller.
     */
    public void close() {
        parser.close();
    }


    // Helpers -------------------------------------------------------------
    /**
     * Attempts to remove a single rectangle from a SkipList matching
     * parameters. Outputs result.
     * 
     * @param name
     *            name of rectangle to remove
     * @precondition name != null
     */
    private void remove(String name) {

        KVPair<String, Dimensions> data = list.remove(name);

        if (data == null) {
            System.out.println("Rectangle not removed: (" + name + ")");
        }
        else {
            System.out.println("Rectangle removed: (" + shapeInfo(data.getKey(),
                data.getValue().getArr()) + ")");
        }
    }


    /**
     * Attempts to insert an rectangle with the specified parameters into
     * SkipList.
     * Outputs result.
     * 
     * @param name
     *            name of rectangle to insert
     * @param dimensions
     *            dimension of rectangle to insert
     * @precondition name & dimensions != null
     */
    private void insert(String name, Dimensions dimensions) {

        if (!isPlacable(dimensions.getArr(), 1024, 1024)) {
            System.out.println("Rectangle rejected: (" + shapeInfo(name,
                dimensions.getArr()) + ")");
        }
        else {
            list.insert(name, dimensions);
            System.out.println("Rectangle inserted: (" + shapeInfo(name,
                dimensions.getArr()) + ")");
        }
    }


    /**
     * Attempts to remove a single rectangle from a SkipList matching
     * parameters.
     * Outputs result.
     * 
     * @param dimensions
     *            dimension of rectangle to remove
     * @precondition dimensions != null
     */
    private void remove(Dimensions dimensions) {
        if (!isPlacable(dimensions.getArr(), 1024, 1024)) {
            System.out.print("Rectangle rejected: ");
            printIntArr(dimensions.getArr());
            System.out.println("");
        }
        else {

            KVPair<String, Dimensions> data = list.remove(dimensions);
            if (data == null) {
                System.out.print("Rectangle not found:");
                this.printIntArr(dimensions.getArr());
                System.out.println("");
            }
            else {
                System.out.println("Rectangle removed: (" + shapeInfo(data
                    .getKey(), data.getValue().getArr()) + ")");
            }
        }
    }


    /**
     * Outputs all Rectangles in SkipList that intersect with parameter.
     * 
     * @precondition dimensions is a valid Rectangle array
     */
    private void regionSearch(int[] dimensions) {

        if (validLengthWidth(dimensions)) {
            Iterator<SkipNode<String, Dimensions>> iter = list.getIterator();
            System.out.print("Rectangles intersecting region: ");
            printIntArr(dimensions);
            System.out.println("");

            while (iter.hasNext()) {
                KVPair<String, Dimensions> data = iter.next().getData();
                if (collide(data.getValue().getArr(), dimensions)) {
                    System.out.println("(" + shapeInfo(data.getKey(), data
                        .getValue().getArr()) + ")");
                }
            }
        }
        else {
            System.out.print("Rectangle rejected: ");
            this.printIntArr(dimensions);
            System.out.println(":");
        }

    }


    /**
     * Outputs all Rectangles in SkipList that intersect with each other
     */
    private void intersections() {
        System.out.println("Intersections pairs:");
        if (list.getSize() > 1) {
            Iterator<SkipNode<String, Dimensions>> iter = list.getIterator();
            SkipNode<String, Dimensions> remainder = iter.next();
            SkipNode<String, Dimensions> compare = remainder.getForward()[0];

            while (remainder != null) { // Loop outside limit

                compare = remainder.getForward()[0];

                while (compare != null) { // Compare inside components
                    if (collide(compare.getData().getValue().getArr(), remainder
                        .getData().getValue().getArr())) {

                        System.out.println("(" + shapeInfo(remainder.getData()
                            .getKey(), remainder.getData().getValue().getArr())
                            + " | " + shapeInfo(compare.getData().getKey(),
                                compare.getData().getValue().getArr()) + ")");
                        System.out.println("(" + shapeInfo(compare.getData()
                            .getKey(), compare.getData().getValue().getArr())
                            + " | " + shapeInfo(remainder.getData().getKey(),
                                remainder.getData().getValue().getArr()) + ")");
                    }
                    compare = compare.getForward()[0];
                }

                // Move limit and restore values
                remainder = remainder.getForward()[0];
            }
        }
    }


    /**
     * Outputs rectangle(s) matching parameter.
     * 
     * @param name
     *            name of entries to search for
     */
    private void search(String name) {
        KVPair<String, Dimensions>[] data = list.search(name);

        if (data[0] == null) {
            System.out.println("Rectangle not found: " + name);
        }
        else {
            System.out.println("Rectangles found:");
            for (int i = 0; data[i] != null; i++) {
                System.out.println("(" + shapeInfo(data[i].getKey(), data[i]
                    .getValue().getArr()) + ")");
            }
        }
    }


    /**
     * Outputs a dump of SkipList entries.
     */
    private void dump() {
        SkipNode<String, Dimensions> node = list.getHead();

        System.out.println("SkipList dump:"); // For the head
        System.out.println("Node has depth " + node.getForward().length
            + ", Value (null)");

        node = node.getForward()[0];
        while (node != null) {

            System.out.println("Node has depth " + node.getLevel() + ", Value ("
                + shapeInfo(node.getData().getKey(), node.getData().getValue()
                    .getArr()) + ")");
            node = node.getForward()[0];
        }
        System.out.println("SkipList size is: " + list.getSize());
    }


    /**
     * Creates an int[] containing integers starting from a starting index
     * from tokens. Ignores Strings that cannot be converted.
     * 
     * @param tokens
     *            tokens to get int from
     * @param startingIndex
     *            index in tokens to begin reading from
     * @precondition arr != null, newLength <= arr.length, newLength >= 0
     * @return integer array with all ints starting at startingIndex in
     *         tokens and with length == size.
     */
    private int[] extractArr(String[] tokens, int startingIndex) {

        int[] arr = new int[tokens.length - startingIndex];
        int currentIndex = 0;

        for (int i = startingIndex; i < tokens.length; i++) {

            try {
                arr[currentIndex] = Integer.parseInt(tokens[i]);
            }
            catch (NumberFormatException e) {
                currentIndex--;
            }
            currentIndex++;
        }
        return (shrinkArr(arr, currentIndex));
    }


    /**
     * Shrinks an int[].
     * 
     * @param arr
     *            array to shrink
     * @precondition arr != null, newLength <= arr.length, newLength >= 0
     * @return array with size equal to length and contents copied from arr
     */
    private int[] shrinkArr(int[] arr, int newLength) {
        int[] copy = new int[newLength];

        for (int i = 0; i < newLength; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }


    /**
     * Checks if a Rectangle collides/intersects another. Rectangles collide if
     * any part of a Rectangle is within another Rectangle. Sharing the same
     * edge
     * or corner does not count as colliding.
     * 
     * @param dimensions
     *            Rectangles dimension
     * @param other
     *            Another rectangle's dimension
     * @return true if collides, false if does not
     * @precondition dimensions and other are valid rectangle dimensions
     */
    private boolean collide(int[] dimensions, int[] other) {

        return ((other[0] <= dimensions[0] && other[0]
            + other[2] > dimensions[0]) || ((other[0] >= dimensions[0]
                && other[0] < dimensions[0] + dimensions[2])))

            && ((other[1] <= dimensions[1] && other[1]
                + other[3] > dimensions[1]) || ((other[1] >= dimensions[1]
                    && other[1] < dimensions[1] + dimensions[3])));
    }


    /**
     * Checks if a rectangle can be placed on a board represented by x and y.
     * A rectangle can be placed if:
     * 
     * dimensions array is valid for a rectangle
     * The entire rectangle fits in the board
     * 
     * @param x
     *            width of the board
     * @param y
     *            height of the board
     * @param dimensions
     *            dimensions of a rectangle
     * @return True if a rectangle can be placed on a board, false otherwise.
     * @precondition dimensions != null
     */
    private boolean isPlacable(int[] dimensions, int x, int y) {
        if (!isLegal(dimensions)) {
            return false;
        }

        return (dimensions[0] + dimensions[2] <= x && dimensions[1]
            + dimensions[3] <= y);
    }


    /**
     * Checks if array is a valid dimension for a rectangle. A valid array is
     * {x,y,w,h} where:
     * 
     * Array length == 4
     * x & y >= 0
     * w & h > 0
     * 
     * @param dimensions
     *            dimension for rectangle to be check for validity
     * @return true if valid array, false if not
     * 
     * @precondition dimensions != null
     */
    private boolean isLegal(int[] dimensions) {
        return !(dimensions.length != 4 || dimensions[0] < 0
            || dimensions[1] < 0 || dimensions[2] <= 0 || dimensions[3] <= 0);
    }


    /**
     * Checks if length and width of Rectangle > 0
     * 
     * @param dimensions
     *            Dimensions of a rectangle
     * @return true if length and width > 0, false otherwise.
     * @precondition dimensions is a valid rectangle dimension
     */
    private boolean validLengthWidth(int[] dimensions) {
        return dimensions[2] > 0 && dimensions[3] > 0;
    }


    /**
     * Prints int[] enclosed in () without '\n'
     * 
     * @param arr
     *            int[] to print out
     * @precondition arr != null
     */
    private void printIntArr(int[] arr) {

        System.out.print("(");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.print(arr[arr.length - 1] + ")");

    }


    /**
     * Returns Rectangle's name and dimension
     * 
     * @precondition name != null && dimensions != null
     * @return name and dimension of Rectangle as "name, x, y, w, h"
     */
    private String shapeInfo(String name, int[] dimensions) {
        StringBuilder line = new StringBuilder();
        line.append(name);

        for (int i : dimensions) {
            line.append(", ");
            line.append(Integer.toString(i));
        }
        return line.toString();
    }
}
