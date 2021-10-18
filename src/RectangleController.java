import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;

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
    private PRQuadTree tree;

    // Constructor -----------------------------------------------------------

    /**
     * Constructs RectangleController with SkipList of maximum level 7
     * 
     * @param commands
     *            File containing commands to run
     * @throws FileNotFoundException
     *             if file does not exists.
     */
    public RectangleController(File commands) throws FileNotFoundException {
        parser = new CommandParser(commands);
        list = new SkipList<String, Dimensions>();
        tree = new PRQuadTree(new Integer[] { 0 , 0 }, new Integer[] { 1024 , 1024 });
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
            case "dump": // Same for prj1 and 2 but also prints tree dump
                dump();
                System.out.println("QuadTree Dump:");
                int treeSize = dump(tree.getRt(), -1, 0, 0).size();
                System.out.println("QuadTree Size: " + treeSize + " QuadTree Nodes Printed.");
                break;
            case "intersections":
                intersections();
                break;
            case "regionsearch":
                regionSearch(extractArr(tokens, 1)); // Same as prj1 but through
                                                     // tree
                break;
            case "remove":
                if (tokens.length == 2) {
                    remove(tokens[1]); // Same for prj1 and prj2
                }
                else {
                    remove(new Dimensions(extractArr(tokens, 1))); // Should
                                                                   // support
                                                                   // multiple
                                                                   // dimension
                                                                   // sizes >2
                }
                break;
            case "insert": // Should still work for prj1 smaller Dimensions
                insert(tokens[1], new Dimensions(extractArr(tokens, 2)));
                //below is for tree insert
                int x = Integer.parseInt(tokens[2]); 
                int y = Integer.parseInt(tokens[3]);
                tree.insert(tokens[1], new Integer[] { x , y });
                break;
            case "search":
                search(tokens[1]); // Same for prj1 and prj2 no changes at all
                break;
            case "duplicates":
                duplicates(); // prj2
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
     * Prints all points with duplicate coordinates
     * 
     * @implNote Calls duplicates from PRQuadTree
     */
    private void duplicates() {
        System.out.println("Duplicate Points:");
        // TODO implementation and print to screen
    }


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
        // TODO Send data to tree remove to sync list up. ********************
        
        if (data == null) {
            System.out.println("point Not Removed: " + name);
        }
        else {
            System.out.println("Point (" + shapeInfo(data.getKey(),
                data.getValue().getArr()) + ") Removed");
            
            //sync part for tree
            int a = data.getValue().getArr()[0];
            int b = data.getValue().getArr()[1];
            tree.remove(name, new Integer[] { a , b });
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
        // TODO check if insert less dimensions works
        if (!isPlacable(dimensions.getArr(), 1024, 1024)) {
            System.out.println("Point REJECTED: (" + shapeInfo(name, dimensions
                .getArr()) + ")");
        }
        else {
            list.insert(name, dimensions);
            System.out.println("Point Inserted: (" + shapeInfo(name,
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
            System.out.print("Point Rejected: ");
            printIntArr(dimensions.getArr());
            System.out.println("");
        }
        else {

            KVPair<String, Dimensions> data = list.remove(dimensions);
            if (data == null) {
                System.out.print("point Not Found:");
                this.printIntArr(dimensions.getArr());
                System.out.println("");
            }
            else {
                System.out.println("Point (" + shapeInfo(data
                    .getKey(), data.getValue().getArr()) + ") Removed");
                
                //sync for the tree
                int a = dimensions.getArr()[0];
                int b = dimensions.getArr()[1];
                tree.remove(data.getKey(), new Integer[] { a , b });
            }
        }
    }


    /**
     * Outputs all Rectangles in SkipList that intersect with parameter.
     * 
     * @precondition dimensions is a valid Rectangle array
     */
    private void regionSearch(int[] dimensions) {

        // TODO let regionSearch print out values from tree, not skiplist
        if (validLengthWidth(dimensions)) {
            Iterator<SkipNode<String, Dimensions>> iter = list.getIterator();
            System.out.print("Points intersecting region: ");
            printIntArr(dimensions);
            System.out.println("");

            while (iter.hasNext()) {
                KVPair<String, Dimensions> data = iter.next().getData();
                if (collide(data.getValue().getArr(), dimensions)) {
                    System.out.println("Point Found: (" + shapeInfo(data.getKey(), data
                        .getValue().getArr()) + ")");
                }
            }
        }
        else {
            System.out.print("Rectangle Rejected: ");
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
            System.out.println("Point Not Found: " + name);
        }
        else {
            
            for (int i = 0; data[i] != null; i++) {
                System.out.print("Point Found ");
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

        System.out.println("SkipList Dump:"); // For the head
        System.out.println("level: " + node.getForward().length
            + " Value: null");

        node = node.getForward()[0];
        while (node != null) {

            System.out.println("level: " + node.getLevel() + " Value: ("
                + shapeInfo(node.getData().getKey(), node.getData().getValue()
                    .getArr()) + ")");
            node = node.getForward()[0];
        }
        System.out.println("The SkipList's size is: " + list.getSize());
    }
    
    /**
     * 
     * @param base the default value is rt
     * @param x 
     * @param y 
     * @param level the starting level, in this case by default value -1
     */
    private LinkedList<BaseNode<String, Integer>> dump(BaseNode<String, 
        Integer> base, int level, int x, int y) {
        tree.getPreOrderList().add(base);
        level +=1;
        int mapSize = 1024; // 1024 depend on map size
        int side = mapSize / (int)(Math.pow(2, level)); 
        // format things
        for (int space = 0; space < level; space++) {
            System.out.print("  ");
        }
        // the recursion go through this if statement
        if (base.getNodeClass() == NodeClassification.ParentNode) {
            System.out.println("Node at " + x + ", " + y + ", " + side + ": Internal");
                int start = mapSize / (int)(Math.pow(2, level + 1)); 
                
                dump(((ParentNode<String, Integer>)base).getChild(0), level, x, y);
                x = x + start;
                dump(((ParentNode<String, Integer>)base).getChild(1), level, x, y);
                x = x - start;
                y = y + start;          
                dump(((ParentNode<String, Integer>)base).getChild(3), level, x, y);
                x = x + start;
                y = y - start;
                y = y + start;
                dump(((ParentNode<String, Integer>)base).getChild(2), level, x, y);
        }
        // if we met a flyweightNode, we need to print this
        if (base.getNodeClass() == NodeClassification.FlyweightNode) {
            System.out.println("Node at " + x + ", " + y + ", " + side +  ": Empty");
        }
        // if me met a leafNode, we need to print this
        if (base.getNodeClass() == NodeClassification.LeafNode) {
            System.out.println("Node at " + x + ", " + y + ", " + side + ":");
            Iterator ite = ((LeafNode<String, Integer>)base).getPoints();
            while (ite.hasNext()) {
                for (int space = 0; space < level; space++) {
                    System.out.print("  ");
                }
                PointNode pNode = (PointNode)ite.next();
                pNode.getKey();
                int xLoc = (int)pNode.getValue()[0];
                int yLoc = (int)pNode.getValue()[1];
                System.out.println("(" + pNode.getKey() + ", " + xLoc + ", " + yLoc + ")");
            }
        }
        return tree.getPreOrderList();
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

        if(dimensions.length == 2) {
            return true;
        }
        
        return (dimensions[0] + dimensions[2] <= x && dimensions[1]
            + dimensions[3] <= y);
    }


    /**
     * Checks if array is a valid dimension for a rectangle. A valid array is
     * {x,y,w,h} or {x,y} where:
     * 
     * Array length == 4 or 2
     * x & y >= 0 when length 4 or 2
     * w & h > 0 when length 4
     * 
     * @param dimensions
     *            dimension for rectangle to be check for validity
     * @return true if valid array, false if not
     * 
     * @precondition dimensions != null
     */
    private boolean isLegal(int[] dimensions) {

        if (dimensions.length >= 2) {
            if (dimensions[0] < 0 || dimensions[1] < 0) {
                return false;
            }
            if (dimensions.length == 2) {
                return true;
            }
        }

        return (dimensions.length == 4 && dimensions[2] > 0
            && dimensions[3] > 0);
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
