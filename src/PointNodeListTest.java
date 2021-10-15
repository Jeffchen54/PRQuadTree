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
    private Integer[] i1;
    private Integer[] i2;
    private Integer[] i3;

    // Set Up ------------------------------------------------------------
    /**
     * Creates an empty PointNodeList and several fields
     */
    public void setUp() {
        list = new PointNodeList<String, Integer>();
        s1 = "Earth";
        s2 = "Mars";
        s3 = "Jupiter";
        i1 = new Integer[] { 360, 0 };
        i2 = new Integer[] { 420, 1 };
        i3 = new Integer[] { 980, 2 };

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
        assertNull(list.remove("Utopia", null));

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        // Remove nonexistant entry
        assertNull(list.remove("Utopia", null));

        // remove - Remove first, last , only, then middle entry.
        assertNotNull(arrayEquals(i1, list.remove(s1, null).getValue()));
        assertEquals(2, list.getSize());

        assertNotNull(this.arrayEquals(i3, list.remove(s3, null).getValue()));
        assertEquals(1, list.getSize());

        assertNotNull(this.arrayEquals(i2, list.remove(s2, null).getValue()));
        assertEquals(0, list.getSize());

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert("Middle Earth", new Integer[] { 4000 });
        list.insert(s3, i3);

        assertNotNull(this.arrayEquals(new Integer[] { 4000 }, list.remove(
            "Middle Earth", null).getValue()));
        assertEquals(3, list.getSize());
    }
    
    /**
     * Coverage for remove
     */
    public void testRemoveCoverage() {
        list.insert(s1, i1);
        assertNull(list.remove(null, null));
    }


    /**
     * Tests remove
     */
    public void testRemoveValue() {
        // Remove empty list
        assertNull(list.remove(null, new Integer[] { 1516 }));

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        // Remove nonexistant entry
        assertNull(list.remove(null, new Integer[] { 1516 }));

        // remove - Remove first, last , only, then middle entry.
        assertEquals(s1, list.remove(null, i1).getKey());
        assertEquals(2, list.getSize());

        assertEquals(s3, list.remove(null, i3).getKey());
        assertEquals(1, list.getSize());

        assertEquals(s2, list.remove(null, i2).getKey());
        assertEquals(0, list.getSize());

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert("Middle Earth", new Integer[] { 4000 });
        list.insert(s3, i3);

        assertEquals("Middle Earth", list.remove(null, new Integer[] { 4000 })
            .getKey());
        assertEquals(3, list.getSize());
    }


    /**
     * Tests removeEntry
     */
    public void testRemoveEntry() {
        // Remove empty list
        assertNull(list.remove("Utopia", new Integer[] { 1516 }));

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        // Remove nonexistant entry
        assertNull(list.remove("Utopia", new Integer[] { 1516 }));

        // remove - Remove first, last , only, then middle entry.
        assertEquals(list.find(s1, null), list.remove(s1, i1));
        assertEquals(2, list.getSize());

        assertEquals(list.find(s3, null), list.remove(s3, i3));
        assertEquals(1, list.getSize());

        assertEquals(list.find(s2, null), list.remove(s2, i2));
        assertEquals(0, list.getSize());

        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert("Middle Earth", new Integer[] { 4000 });
        list.insert(s3, i3);

        assertEquals(list.find("Middle Earth", null), list.remove(
            "Middle Earth", new Integer[] { 4000 }));
        assertEquals(3, list.getSize());

        // Edge cases
        assertNull(list.remove(s1, i2));
    }


    /**
     * Tests find
     */
    public void testFind() {
        // empty list
        list.find(s1, i1);
        list.find(s1, null);
        list.find(null, i1);

        // Nonexistant
        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        assertNull(list.find("Utopia", null));
        assertNull(list.find(null, new Integer[] { 1516 }));
        assertNull(list.find("Utopia", new Integer[] { 1516 }));

        // Existant
        assertNotNull(this.arrayEquals(i1, list.find(s1, null).getValue()));
        assertNotNull(this.arrayEquals(i2, list.find(s2, null).getValue()));
        assertNotNull(this.arrayEquals(i3, list.find(s3, null).getValue()));

        assertEquals(s1, list.find(null, i1).getKey());
        assertEquals(s2, list.find(null, i2).getKey());
        assertEquals(s3, list.find(null, i3).getKey());

        assertNotNull(list.find(s1, i1));
        assertNotNull(list.find(s2, i2));
        assertNotNull(list.find(s3, i3));

        // Edge cases for find
        assertNull(list.find(s1, i2));

        // Removed
        list.remove(s1, null);
        list.remove(s3, null);
        list.remove(s2, null);

        assertNull(list.find(s1, null));
        assertNull(list.find(s2, null));
        assertNull(list.find(s3, null));

        assertNull(list.find(null, i1));
        assertNull(list.find(null, i2));
        assertNull(list.find(null, i3));

        assertNull(list.find(s1, i1));
        assertNull(list.find(s2, i2));
        assertNull(list.find(s3, i3));
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


    /**
     * Tests equalValue
     */
    public void testEqualValue() {
        list.insert(s1, i1);
        list.insert(s2, i2);
        list.insert(s3, i3);

        // null
        assertNull(list.find(null, null));

        // same obj
        assertNotNull(list.find(null, i1));

        // different length
        assertNull(list.find(null, new Integer[] { 360, 0, 0 }));

        // same class, length, diff values
        assertNull(list.find(null, new Integer[] { 360, 5 }));

        // same everything
        assertNotNull(list.find(null, new Integer[] { 360, 0 }));
    }
    
    /**
     * Tests private helper function arrayEquals
     */
    public void testArrayEquals() {
        // null
        assertFalse(this.arrayEquals(i1, null));
        assertFalse(this.arrayEquals(null, i1));

        // same obj
        assertTrue(this.arrayEquals(i1, i1));

        // different length
        assertFalse(this.arrayEquals((new Integer[] { 360, 0, 0 }), i1));

        // same class, length, diff values
        assertFalse(this.arrayEquals((new Integer[] { 360, 5 }), i1));

        // same everything
        assertTrue(this.arrayEquals((new Integer[] { 360, 0 }), i1));
    }


    /**
     * Quick function to check contents of 2 Integer Arrays
     * 
     * @param value
     *           Array to compare
     * @param obj
     *            Array to compare
     * @return true if data values of 2 Arrays are identical
     */
    private boolean arrayEquals(Integer[] value, Integer[] obj) {
        if(obj == null || value == null) {
            return false;
        }
        
        if (obj == value) {
            return true;
        }

        if (obj.length != value.length) {
            return false;
        }

        for (int i = 0; i < value.length; i++) {
            if (value[i].compareTo(obj[i]) != 0) {
                return false;
            }
        }
        return true;
    }

}
