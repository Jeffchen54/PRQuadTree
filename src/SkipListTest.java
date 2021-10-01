import java.util.Iterator;
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
 * JUnit for ShapeSkipList
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/02/2021
 */
public class SkipListTest extends TestCase {
    // Fields ----------------------------------------------------------------
    private KVPair<String, Dimensions> a;
    private KVPair<String, Dimensions> b;
    private KVPair<String, Dimensions> c;
    private KVPair<String, Dimensions> c2;
    private SkipList<String, Dimensions> list;
    private Iterator<SkipNode<String, Dimensions>> iter;

    // Set up ----------------------------------------------------------------
    /**
     * Sets up fields
     */
    public void setUp() {
        a = new KVPair<String, Dimensions>("a", new Dimensions(new int[] { 10,
            10, 5, 5 }));

        b = new KVPair<String, Dimensions>("b", new Dimensions(new int[] { 15,
            10, 5, 5 }));
        c = new KVPair<String, Dimensions>("c", new Dimensions(new int[] { 14,
            10, 5, 5 }));
        c2 = new KVPair<String, Dimensions>("c2", new Dimensions(new int[] { 11,
            11, 1, 1 }));
        list = new SkipList<String, Dimensions>();

    }

    // Tests ---------------------------------------------------------------


    /**
     * Tests the iterator
     */
    public void testIterator() {
        // Empty
        iter = list.getIterator();
        assertFalse(iter.hasNext());

        // With items
        list.insert(c.getKey(), c.getValue());
        list.insert(a.getKey(), a.getValue());
        list.insert(b.getKey(), b.getValue());
        iter = list.getIterator();

        assertEquals(a.getKey(), iter.next().getData().getKey());
        assertEquals(b.getKey(), iter.next().getData().getKey());
        assertEquals(c.getKey(), iter.next().getData().getKey());

        assertNull(iter.next());
    }


    /**
     * Tests remove(String)
     */

    public void testRemoveName() {
        // Empty list remove
        list.remove("Steve Jobs Got Ligma");

        // Inserting
        list.insert(a.getKey(), a.getValue());
        list.insert(b.getKey(), b.getValue());

        // Removing single existing KVPair<String, Dimensions>
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());

        // Removing 2 dupe KVPair<String, Dimensions>s
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());

        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());

        // Seeing if it still exists
        assertNull(list.remove(a.getKey()));

        iter = list.getIterator();
        assertEquals(b.getKey(), iter.next().getData().getKey());
        assertFalse(iter.hasNext());

        // Removing null KVPair<String, Dimensions>
        String nullString = null;
        list.remove(nullString);

        // Removing nonexistant entry from beginning and end
        assertNull(list.remove("a"));
        assertNull(list.remove("z"));

        // Case randomness coverage
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());
        assertEquals(a.getKey(), list.remove(a.getKey()).getKey());

    }


    /**
     * Tests remove(Dimensions))
     */

    public void testRemoveDimensions() {
        // Empty list remove
        list.remove(a.getValue());

        // Removing nonexistant KVPair<String, Dimensions>
        list.insert(a.getKey(), a.getValue());
        list.insert(b.getKey(), b.getValue());
        assertNull(list.remove(c.getValue()));

        // Removing single existing KVPair<String, Dimensions>
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());

        // Removing 2 dupe KVPair<String, Dimensions>s
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());

        // Seeing if it still exists
        assertNull(list.remove(a.getValue()));

        iter = list.getIterator();
        assertEquals(b.getKey(), iter.next().getData().getKey());
        assertFalse(iter.hasNext());

        // Removing null KVPair<String, Dimensions>
        Dimensions nullDimensions = null;
        list.remove(nullDimensions);

        // Randomness Coverage
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        list.insert(a.getKey(), a.getValue());
        list.insert(a.getKey(), a.getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());
        assertEquals(a.getValue(), list.remove(a.getValue()).getValue());

        list.insert("r1", new Dimensions(new int[] { 10, 10, 5, 5 }));
        list.insert("r12", new Dimensions(new int[] { 10, 10, 5, 5 }));
        list.insert("r14", new Dimensions(new int[] { 10, 10, 5, 5 }));
        list.insert("r115", new Dimensions(new int[] { 10, 10, 5, 5 }));
        list.insert("r2", new Dimensions(new int[] { 10, 10, 5, 5 }));
        list.insert("r3", new Dimensions(new int[] { 10, 10, 5, 5 }));
        list.insert("r4", new Dimensions(new int[] { 10, 10, 5, 5 }));
        list.insert("r5", new Dimensions(new int[] { 6, 7, 11, 9 }));
        Dimensions arr = new Dimensions(new int[] { 1, 2, 3, 4 });

        assertNotNull(list.remove(new Dimensions(new int[] { 6, 7, 11, 9 })));
        assertNull(list.remove("r5"));
    }


    /**
     * Tests search(String)
     */

    public void testSearch() {
        // Empty list search
        list.search("Ligma");

        // Non existant search
        list.insert(a.getKey(), a.getValue());
        list.search("Balls");

        // Searching single existing KVPair<String, Dimensions>
        list.search(a.getKey());

        // Searching from dupe KVPair<String, Dimensions>s
        list.insert(a.getKey(), a.getValue());
        list.insert(b.getKey(), b.getValue());

        KVPair<String, Dimensions>[] results = list.search(a.getKey());
        assertEquals(a.getKey(), results[0].getKey());
        assertEquals(a.getKey(), results[1].getKey());
        assertNull(results[2]);

        // Searching for removed KVPair<String, Dimensions>
        list.remove(b.getKey());
        assertNull(list.search(b.getKey())[0]);

        // Searching for null
        String nullKey = null;
        list.search(nullKey);
    }


    /**
     * Tests insert
     */

    public void testInsert() {
        // Adding null entry
        list.insert(null, null);
        list.insert(null, a.getValue());
        list.insert(a.getKey(), null);
        assertEquals(0, list.getSize());
        
        // Adding to empty list
        list.insert(c2.getKey(), c2.getValue());
        assertEquals(1, list.getSize());
        
        // Adding before first entry
        list.insert(a.getKey(), a.getValue());
        assertEquals(2, list.getSize());
        
        // Adding between
        list.insert(c.getKey(), c.getValue());
        assertEquals(3, list.getSize());
        
        // Adding after first entry
        list.insert(b.getKey(), b.getValue());
        assertEquals(4, list.getSize());
        
        // Adding dupe key
        list.insert(a.getKey(), a.getValue());
        assertEquals(5, list.getSize());
    }


    /**
     * Tests getters
     */
    public void testGet() {
        assertEquals(0, list.getSize());
        list.insert(a.getKey(), a.getValue());
        assertEquals(1, list.getSize());
        assertEquals(null, list.getHead().getData());

    }

}
