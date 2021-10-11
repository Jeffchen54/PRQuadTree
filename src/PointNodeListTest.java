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
// letter of this restriction. - JC & XC

// Java Doc ----------------------------------------------------------------
/**
 * Tests the PointNodeList class
 */
public class PointNodeListTest extends TestCase {

    // Fields ------------------------------------------------------------
    private PointNodeList<String, Integer> list;
    private String s1;
    private String s2;
    private String s3;
    private Integer i1;
    private Integer i2;
    private Integer i3;

    // Set Up ------------------------------------------------------------
    /**
     * Creates an empty PointNodeList and several fields
     */
    public void setUp() {
        list = new PointNodeList<String, Integer>();
        s1 = "Earth";
        s2 = "Mars";
        s3 = "Jupiter";
        i1 = 360;
        i2 = 420;
        i3 = 980;

    }

    // Tests ------------------------------------------------------------


    /**
     * Tests insert
     */
    public void testInsert() {
        assertEquals(0, list.getSize());

        list.insert(s1, i1);

        assertEquals(1, list.getSize());

        list.insert(s1, i2);
        assertEquals(2, list.getSize());
    }


    /**
     * Tests remove
     */
    public void testRemoveKey() {
        // Remove empty list
        assertNull(list.removeKey("Utopia"));

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        // Remove nonexistant entry
        assertNull(list.removeKey("Utopia"));

        // removeKey - Remove first, last , only, then middle entry.
        assertEquals(i1, list.removeKey(s1).getValue(), 0.1);
        assertEquals(2, list.getSize());

        assertEquals(i3, list.removeKey(s3).getValue(), 0.1);
        assertEquals(1, list.getSize());

        assertEquals(i2, list.removeKey(s2).getValue(), 0.1);
        assertEquals(0, list.getSize());

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert("Middle Earth", 4000);
        list.insert(s3, i3);

        assertEquals(4000, list.removeKey("Middle Earth").getValue(), 0.1);
        assertEquals(3, list.getSize());
    }


    /**
     * Tests removeValue
     */
    public void testRemoveValue() {
        // Remove empty list
        assertNull(list.removeValue(1516));

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        // Remove nonexistant entry
        assertNull(list.removeValue(1516));

        // removeKey - Remove first, last , only, then middle entry.
        assertEquals(s1, list.removeValue(i1).getKey());
        assertEquals(2, list.getSize());

        assertEquals(s3, list.removeValue(i3).getKey());
        assertEquals(1, list.getSize());

        assertEquals(s2, list.removeValue(i2).getKey());
        assertEquals(0, list.getSize());

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert("Middle Earth", 4000);
        list.insert(s3, i3);

        assertEquals("Middle Earth", list.removeValue(4000).getKey());
        assertEquals(3, list.getSize());
    }


    /**
     * Tests removeEntry
     */
    public void testRemoveEntry() {
        // Remove empty list
        assertNull(list.removePair("Utopia", 1516));

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        // Remove nonexistant entry
        assertNull(list.removePair("Utopia", 1516));

        // removeKey - Remove first, last , only, then middle entry.
        assertEquals(list.findKey(s1), list.removePair(s1, i1));
        assertEquals(2, list.getSize());

        assertEquals(list.findKey(s3), list.removePair(s3, i3));
        assertEquals(1, list.getSize());

        assertEquals(list.findKey(s2), list.removePair(s2, i2));
        assertEquals(0, list.getSize());

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert("Middle Earth", 4000);
        list.insert(s3, i3);

        assertEquals(list.findKey("Middle Earth"), list.removePair(
            "Middle Earth", 4000));
        assertEquals(3, list.getSize());

        // Edge cases
        assertNull(list.removePair(s1, i2));
    }


    /**
     * Tests find
     */
    public void testFind() {
        // empty list
        list.findEntry(s1, i1);
        list.findKey(s1);
        list.findValue(i1);

        // Nonexistant
        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        assertNull(list.findKey("Utopia"));
        assertNull(list.findValue(1516));
        assertFalse(list.findEntry("Utopia", 1516));

        // Existant
        assertEquals(i1, list.findKey(s1).getValue(), 0.1);
        assertEquals(i2, list.findKey(s2).getValue(), 0.1);
        assertEquals(i3, list.findKey(s3).getValue(), 0.1);

        assertEquals(s1, list.findValue(i1).getKey());
        assertEquals(s2, list.findValue(i2).getKey());
        assertEquals(s3, list.findValue(i3).getKey());

        assertTrue(list.findEntry(s1, i1));
        assertTrue(list.findEntry(s2, i2));
        assertTrue(list.findEntry(s3, i3));

        // Edge cases for findEntry
        assertFalse(list.findEntry(s1, i2));

        // Removed
        list.removeKey(s1);
        list.removeKey(s3);
        list.removeKey(s2);

        assertNull(list.findKey(s1));
        assertNull(list.findKey(s2));
        assertNull(list.findKey(s3));

        assertNull(list.findValue(i1));
        assertNull(list.findValue(i2));
        assertNull(list.findValue(i3));

        assertFalse(list.findEntry(s1, i1));
        assertFalse(list.findEntry(s2, i2));
        assertFalse(list.findEntry(s3, i3));
    }
    
    /**
     * Tests the pointIterator
     */
    public void testIterator() {
        // Empty
        Iterator<PointNode<String, Integer>> iter = list.getIterator();
        assertFalse(iter.hasNext());
        
        // with entries
        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);
        
        iter = list.getIterator();
        assertTrue(iter.hasNext());
        assertEquals(s3, iter.next().getKey());
        assertTrue(iter.hasNext());
        assertEquals(s2, iter.next().getKey());
        assertTrue(iter.hasNext());
        assertEquals(s1, iter.next().getKey());
        assertFalse(iter.hasNext());
        assertNull(iter.next());
    }

}
