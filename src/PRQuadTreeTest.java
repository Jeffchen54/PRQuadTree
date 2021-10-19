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
 * Tests PRQuadTree
 * 
 * @author chenj (chenjeff4840)
 * @version 10.5.2021
 */
public class PRQuadTreeTest extends TestCase {
    // Fields ---------------------------------------------------------------
    private PRQuadTree tree;
    private Integer[] min;
    private Integer[] max;

    // SetUp ----------------------------------------------------------------
    /**
     * this is setUp
     */
    public void setUp() {
        min = new Integer[] { 0, 0 };
        max = new Integer[] { 1024, 1024 };

        tree = new PRQuadTree(min, max);
    }


    // Tests ----------------------------------------------------------------
    /**
     * Tests insert()
     */
    public void testInsert() {
        // Inserts onto empty list
        assertTrue(tree.insert("Hello World", new Integer[] { 0, 125 }));
        assertEquals("Leaf-Hello World, \n", tree.toString());

        // Insert same quadrant
        assertTrue(tree.insert("IT WORKS???!!", new Integer[] { 0, 100 }));
        assertTrue(tree.insert("IT WORKS???!!", new Integer[] { 0, 50 }));
        assertEquals("Leaf-IT WORKS???!!, IT WORKS???!!, Hello World, \r\n",
            tree.toString());

        // Split
        assertTrue(tree.insert("IT WORKS???!!", new Integer[] { 0, 75 }));
        assertEquals("Parent\r\n" + "\tNW-Parent\r\n" + "\tNW-Parent\r\n"
            + "\tNW-Parent\r\n" + "\tNW-Leaf-IT WORKS???!!, \r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n"
            + "\tSW-Leaf-IT WORKS???!!, IT WORKS???!!, Hello World, \r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n"
            + "\tSW-Flyweight\r\n", tree.toString());

        // Putting values onto other quadrants
        assertTrue(tree.insert("NE", new Integer[] { 700, 200 }));
        assertTrue(tree.insert("SW", new Integer[] { 200, 700 }));
        assertTrue(tree.insert("SE", new Integer[] { 700, 700 }));
        assertEquals("Parent\r\n" + "\tNW-Parent\r\n" + "\tNW-Parent\r\n"
            + "\tNW-Parent\r\n" + "\tNW-Leaf-IT WORKS???!!, \r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n"
            + "\tSW-Leaf-IT WORKS???!!, IT WORKS???!!, Hello World, \r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Leaf-NE, \r\n" + "\tSE-Leaf-SE, \r\n" + "\tSW-Leaf-SW, \n",
            tree.toString());
        // Inserting same point diff key
        assertTrue(tree.insert("NE1", new Integer[] { 700, 200 }));

        // Inserting a clone point and key
        assertFalse(tree.insert("NE", new Integer[] { 700, 200 }));

        // Inserting 2 pairs of duplicate points
        tree = new PRQuadTree(min, max);
        tree.insert("NE1", new Integer[] { 200, 200 });
        tree.insert("NE2", new Integer[] { 200, 200 });
        tree.insert("NEV1", new Integer[] { 150, 200 });
        tree.insert("NEV2", new Integer[] { 150, 200 });

        System.out.println(tree.toString());
    }


    /**
     * Tests commands from P2 input
     */
    public void testP2Input() {
        assertTrue(tree.insert("p_p", new Integer[] { 1, 20 }));
        assertTrue(tree.insert("p", new Integer[] { 10, 30 }));
        assertTrue(tree.insert("p_42", new Integer[] { 1, 20 }));
        assertTrue(tree.insert("far_point", new Integer[] { 200, 200 }));
        assertEquals("Parent\r\n" + "\tNW-Parent\r\n" + "\tNW-Parent\r\n"
            + "\tNW-Leaf-p_p, p, p_42, \r\n" + "\tNE-Flyweight\r\n"
            + "\tSE-Leaf-far_point, \r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Flyweight\n",
            tree.toString());
        assertEquals("p_p", tree.remove("p_p", new Integer[] { 1, 20 })
            .getKey());
        assertEquals("p", tree.remove(null, new Integer[] { 10, 30 }).getKey());
        assertEquals("Leaf-far_point, p_42, \r\n", tree.toString());

        tree = new PRQuadTree(min, max);
        assertTrue(tree.insert("p_p", new Integer[] { 1, 20 }));
        assertTrue(tree.insert("p", new Integer[] { 10, 30 }));
        assertTrue(tree.insert("p_42", new Integer[] { 1, 20 }));
        assertTrue(tree.insert("far_point", new Integer[] { 200, 200 }));
        RegionSearchList<String, Integer> list = tree.regionSearch(
            new Integer[] { 0, 0, 25, 25 });

        Iterator<PointNode<String, Integer>> iter = list.getIterator();
        assertTrue(arrayEquals(new Integer[] { 1, 20 }, iter.next()
            .getValue()));
        assertTrue(arrayEquals(new Integer[] { 1, 20 }, iter.next()
            .getValue()));
        assertFalse(iter.hasNext());

        assertEquals(4, list.numVisited());

        PointNodeList<String, Integer>.ValueRecordNode record = tree
            .duplicates();
        assertEquals(2, record.getCount());
        assertTrue(this.arrayEquals(new Integer[] { 1, 20 }, record
            .getValue()));
        assertNull(record.getNext());

        assertEquals("Parent\r\n" + "\tNW-Parent\r\n" + "\tNW-Parent\r\n"
            + "\tNW-Leaf-p_p, p, p_42, \r\n" + "\tNE-Flyweight\r\n"
            + "\tSE-Leaf-far_point, \r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n"
            + "\tSW-Flyweight\r\n", tree.toString());
        tree.remove("p_p", new Integer[] { 1, 20 });
        tree.remove(null, new Integer[] { 10, 30 });
        tree.peek();

    }


    /**
     * Tests add remove edge cases
     */
    public void testEdgeCases() {
        assertTrue(tree.insert("NE", new Integer[] { 512, 0 }));
        assertTrue(tree.insert("SE1", new Integer[] { 512, 512 }));
        assertTrue(tree.insert("SE2", new Integer[] { 1024, 512 }));
        assertTrue(tree.insert("SE3", new Integer[] { 512, 1024 }));
        assertTrue(tree.insert("SW", new Integer[] { 0, 512 }));
        tree.peek();

        assertEquals("NE", tree.remove(null, new Integer[] { 512, 0 })
            .getKey());
        assertEquals("SE1", tree.remove(null, new Integer[] { 512, 512 })
            .getKey());
        assertEquals("SE2", tree.remove(null, new Integer[] { 1024, 512 })
            .getKey());
        assertEquals("SE3", tree.remove(null, new Integer[] { 512, 1024 })
            .getKey());
        assertEquals("SW", tree.remove(null, new Integer[] { 0, 512 })
            .getKey());
        tree.peek();

        // regionsearch boundary points
        tree = new PRQuadTree(min, max);
        tree.insert("NW1", new Integer[] { 100, 0 });
        tree.insert("NW2", new Integer[] { 0, 100 });
        tree.insert("NW3", new Integer[] { 100, 200 });
        tree.insert("NW4", new Integer[] { 200, 100 });
        RegionSearchList<String, Integer> list = tree.regionSearch(
            new Integer[] { 0, 0, 200, 200 });
        Iterator<PointNode<String, Integer>> points = list.getIterator();
        assertEquals(4, list.getSize());
        assertEquals("NW3", points.next().getKey());
        assertEquals("NW4", points.next().getKey());
        assertEquals("NW2", points.next().getKey());
        assertEquals("NW1", points.next().getKey());

        // Quadrant boundary points
        tree = new PRQuadTree(min, max);
        tree.insert("NE", new Integer[] { 512, 0 });
        tree.insert("SE", new Integer[] { 1024, 512 });
        tree.insert("SE", new Integer[] { 512, 1024 });
        tree.insert("SW", new Integer[] { 0, 512 });
        tree.insert("SE", new Integer[] { 512, 512 });
        tree.insert("WEB", new Integer[] { 512, 600 });
        tree.insert("WEB", new Integer[] { 512, 650 });
        tree.insert("WEB", new Integer[] { 512, 700 });
        System.out.println(tree.toString());

    }


    /**
     * Tests remove
     */
    public void testRemove() {
        // Remove from empty list
        assertNull(tree.remove("BAD", new Integer[] { 1, 2 }));

        // Inserting 3 distinct elems removing non existant entry
        tree.insert("NE", new Integer[] { 700, 200 });
        tree.insert("SW", new Integer[] { 200, 700 });
        tree.insert("SE", new Integer[] { 700, 700 });
        tree.insert("NE2", new Integer[] { 700, 200 });
        tree.insert("SW2", new Integer[] { 200, 700 });
        tree.insert("SE2", new Integer[] { 700, 700 });

        assertNull(tree.remove("BAD", new Integer[] { 1, 2 }));
        assertNull(tree.remove("BAD", new Integer[] { 700, 200 }));
        assertNull(tree.remove("NE", new Integer[] { 1, 2 }));
        assertNull(tree.remove(null, new Integer[] { 1, 2 }));

        // Removing all
        assertEquals("NE", tree.remove("NE", new Integer[] { 700, 200 })
            .getKey());
        assertEquals("SW", tree.remove("SW", new Integer[] { 200, 700 })
            .getKey());
        assertEquals("SE", tree.remove("SE", new Integer[] { 700, 700 })
            .getKey());
        assertEquals("NE2", tree.remove(null, new Integer[] { 700, 200 })
            .getKey());
        assertEquals("SW2", tree.remove(null, new Integer[] { 200, 700 })
            .getKey());
        assertEquals("SE2", tree.remove(null, new Integer[] { 700, 700 })
            .getKey());

        // Insert 3 elems with same point and remove all
        tree.insert("NE1", new Integer[] { 700, 200 });
        tree.insert("NE2", new Integer[] { 700, 200 });
        tree.insert("NE3", new Integer[] { 700, 200 });
        tree.insert("NE4", new Integer[] { 700, 200 });
        assertEquals("NE4", tree.remove(null, new Integer[] { 700, 200 })
            .getKey());
        assertEquals("NE3", tree.remove(null, new Integer[] { 700, 200 })
            .getKey());
        assertEquals("NE2", tree.remove(null, new Integer[] { 700, 200 })
            .getKey());
        assertEquals("NE1", tree.remove(null, new Integer[] { 700, 200 })
            .getKey());

        // Removing a element such that decomposition is applied to
        // Parent
        // -Parent, leaf, fly, fly which does not break it down
        tree = new PRQuadTree(min, max);
        tree.insert("NE1", new Integer[] { 700, 50 });
        tree.insert("NE2", new Integer[] { 700, 100 });
        tree.insert("NE3", new Integer[] { 700, 150 });
        tree.insert("NE4", new Integer[] { 700, 199 });

        tree.insert("SE1", new Integer[] { 700, 850 });
        tree.insert("SE2", new Integer[] { 700, 900 });
        tree.insert("SE3", new Integer[] { 700, 950 });
        tree.insert("SE4", new Integer[] { 700, 1000 });

        // Confirming tree structure
        assertEquals("Parent\r\n" + "\tNW-Flyweight\r\n" + "\tNE-Parent\r\n"
            + "\tNW-Parent\r\n" + "\tNW-Flyweight\r\n"
            + "\tNE-Leaf-NE1, NE2, \r\n" + "\tSE-Leaf-NE3, NE4, \r\n"
            + "\tSW-Flyweight\r\n" + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n"
            + "\tSW-Flyweight\r\n" + "\tSE-Parent\r\n" + "\tNW-Flyweight\r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n" + "\tSW-Parent\r\n"
            + "\tNW-Flyweight\r\n" + "\tNE-Leaf-SE1, \r\n"
            + "\tSE-Leaf-SE4, SE3, SE2, \r\n" + "\tSW-Flyweight\r\n"
            + "\tSW-Flyweight\r\n", tree.toString());

        // Removing a point to trigger decomposition event where a parentNode
        // exists where Leaf is trying to collapse from 4 nodes to 1
        tree.remove(null, new Integer[] { 700, 50 });
        assertEquals("Parent\r\n" + "\tNW-Flyweight\r\n"
            + "\tNE-Leaf-NE2, NE3, NE4, \r\n" + "\tSE-Parent\r\n"
            + "\tNW-Flyweight\r\n" + "\tNE-Flyweight\r\n" + "\tSE-Flyweight\r\n"
            + "\tSW-Parent\r\n" + "\tNW-Flyweight\r\n" + "\tNE-Leaf-SE1, \r\n"
            + "\tSE-Leaf-SE4, SE3, SE2, \r\n" + "\tSW-Flyweight\r\n"
            + "\tSW-Flyweight\r\n", tree.toString());

    }


    /**
     * Tests for duplicate values not duplicate()
     */
    public void testDuplicateValues() {
        // Testing with 4 duplicate values
        tree = new PRQuadTree(min, max);
        tree.insert("NW1", new Integer[] { 0, 100 });
        tree.insert("NW2", new Integer[] { 0, 100 });
        tree.insert("NW3", new Integer[] { 0, 100 });
        tree.insert("NW4", new Integer[] { 0, 100 });
        assertEquals("Leaf-NW4, NW3, NW2, NW1, \r\n", tree.toString());

        // Adding nondupe value but to another tree, should split the tree up
        tree.insert("SE", new Integer[] { 800, 800 });
        assertEquals("Parent\r\n" + "\tNW-Leaf-NW1, NW2, NW3, NW4, \r\n"
            + "\tNE-Flyweight\r\n" + "\tSE-Leaf-SE, \r\n" + "\tSW-Flyweight\n",
            tree.toString());

        // Remove that point, should break the tree into a single leaf
        tree.remove("SE", new Integer[] { 800, 800 });
        assertEquals("Leaf-NW4, NW3, NW2, NW1, \r\n", tree.toString());

    }


    /**
     * Tests regionSearch
     */
    public void testRegionSearch() {

        // Empty list region search
        RegionSearchList<String, Integer> list = tree.regionSearch(
            new Integer[] { 0, 0, 25, 25 });
        assertEquals(1, list.numVisited());
        assertEquals(0, list.getSize());

        // Example output regionsearch
        tree.insert("a", new Integer[] { 1, 1 });
        tree.insert("a1", new Integer[] { 2, 1 });
        tree.insert("b1", new Integer[] { 551, 1 });
        tree.insert("b2", new Integer[] { 553, 1 });

        list = tree.regionSearch(new Integer[] { 0, 0, 768, 768 });
        Iterator<PointNode<String, Integer>> iter = list.getIterator();
        assertTrue(this.arrayEquals(new Integer[] { 553, 1 }, iter.next()
            .getValue()));
        assertTrue(this.arrayEquals(new Integer[] { 551, 1 }, iter.next()
            .getValue()));
        assertTrue(this.arrayEquals(new Integer[] { 2, 1 }, iter.next()
            .getValue()));
        assertTrue(this.arrayEquals(new Integer[] { 1, 1 }, iter.next()
            .getValue()));
        assertEquals(5, list.numVisited());

        list = tree.regionSearch(new Integer[] { 752, 752, 224, 224 });
        iter = list.getIterator();
        assertFalse(iter.hasNext());
        assertEquals(2, list.numVisited());

        // Edge cases
        tree = new PRQuadTree(min, max);
        tree.insert("NE", new Integer[] { 700, 200 });
        tree.insert("SW", new Integer[] { 200, 700 });
        tree.insert("SE", new Integer[] { 700, 700 });
        tree.insert("NW", new Integer[] { 200, 200 });

        // All 4 sides, not intersecting NW point
        assertFalse(((Iterator<PointNode<String, Integer>>)tree.regionSearch(
            new Integer[] { 0, 0, 100, 400 }).getIterator()).hasNext());
        assertFalse(((Iterator<PointNode<String, Integer>>)tree.regionSearch(
            new Integer[] { 300, 300, 100, 400 }).getIterator()).hasNext());
        assertFalse(((Iterator<PointNode<String, Integer>>)tree.regionSearch(
            new Integer[] { 0, 0, 400, 100 }).getIterator()).hasNext());
        assertFalse(((Iterator<PointNode<String, Integer>>)tree.regionSearch(
            new Integer[] { 300, 300, 400, 100 }).getIterator()).hasNext());

        // Well within bounds of NW
        iter = tree.regionSearch(new Integer[] { 0, 0, 400, 400 })
            .getIterator();
        assertTrue(arrayEquals(new Integer[] { 200, 200 }, iter.next()
            .getValue()));

        // 4 edge bounds
        iter = tree.regionSearch(new Integer[] { 0, 0, 200, 400 })
            .getIterator();
        assertEquals("NW", iter.next().getKey());
        iter = tree.regionSearch(new Integer[] { 200, 0, 200, 400 })
            .getIterator();
        assertTrue(iter.hasNext());
        iter = tree.regionSearch(new Integer[] { 0, 0, 400, 200 })
            .getIterator();
        assertEquals("NW", iter.next().getKey());
        iter = tree.regionSearch(new Integer[] { 0, 200, 400, 200 })
            .getIterator();
        assertTrue(iter.hasNext());

        // Corner cases
        iter = tree.regionSearch(new Integer[] { 0, 0, 200, 200 })
            .getIterator();
        assertEquals("NW", iter.next().getKey());
        iter = tree.regionSearch(new Integer[] { 200, 0, 200, 200 })
            .getIterator();
        assertEquals("NW", iter.next().getKey());
        iter = tree.regionSearch(new Integer[] { 0, 200, 200, 200 })
            .getIterator();
        assertEquals("NW", iter.next().getKey());
        iter = tree.regionSearch(new Integer[] { 200, 200, 200, 200 })
            .getIterator();
        assertEquals("NW", iter.next().getKey());

        // entire space
        iter = tree.regionSearch(new Integer[] { 0, 0, 1024, 1024 })
            .getIterator();
        assertTrue(this.arrayEquals(new Integer[] { 200, 700 }, iter.next()
            .getValue()));
        assertTrue(this.arrayEquals(new Integer[] { 700, 700 }, iter.next()
            .getValue()));
        assertTrue(this.arrayEquals(new Integer[] { 700, 200 }, iter.next()
            .getValue()));
        assertTrue(this.arrayEquals(new Integer[] { 200, 200 }, iter.next()
            .getValue()));
        assertFalse(iter.hasNext());

        // Point intersection
        iter = tree.regionSearch(new Integer[] { 200, 200, 0, 0 })
            .getIterator();
        assertTrue(this.arrayEquals(new Integer[] { 200, 200 }, iter.next()
            .getValue()));
        assertFalse(iter.hasNext());

        // no intersections
        iter = tree.regionSearch(new Integer[] { 0, 0, 10, 10 }).getIterator();
        assertFalse(iter.hasNext());
        iter = tree.regionSearch(new Integer[] { 0, 250, 250, 0 })
            .getIterator();
        assertFalse(iter.hasNext());

        // Additional test cases:
        tree = new PRQuadTree(min, max);
        tree.insert("a", new Integer[] { 1, 1 });
        tree.insert("a1", new Integer[] { 2, 1 });
        tree.insert("b1", new Integer[] { 551, 1 });
        tree.insert("b2", new Integer[] { 553, 1 });

        iter = tree.regionSearch(new Integer[] { 0, 0, 768, 768 })
            .getIterator();
        assertEquals(5, tree.regionSearch(new Integer[] { 0, 0, 768, 768 })
            .numVisited());
        assertNotNull(iter.next());
        assertNotNull(iter.next());
        assertNotNull(iter.next());
        assertNotNull(iter.next());
        assertNull(iter.next());

        iter = tree.regionSearch(new Integer[] { 752, 752, 224, 224 })
            .getIterator();
        assertEquals(2, tree.regionSearch(new Integer[] { 752, 752, 224, 224 })
            .numVisited());
        assertFalse(iter.hasNext());
    }


    /**
     * Tests duplicates
     */
    public void testDuplicates() {
        // Empty
        assertNull(tree.duplicates());

        // No duplicates 1 leaf
        tree.insert("NW1", new Integer[] { 0, 200 });
        tree.insert("Non dupe NW", new Integer[] { 0, 201 });
        assertNull(tree.duplicates());

        // Duplicates 1 leaf
        tree.insert("NW2", new Integer[] { 0, 200 });
        PointNodeList<String, Integer>.ValueRecordNode record = tree
            .duplicates();
        assertEquals(2, record.getCount());
        assertTrue(this.arrayEquals(record.getValue(), new Integer[] { 0,
            200 }));

        // Branching duplicates each leaf
        tree.insert("NE1", new Integer[] { 800, 200 });
        tree.insert("NE2", new Integer[] { 800, 200 });
        tree.insert("SE1", new Integer[] { 800, 800 });
        tree.insert("SE2", new Integer[] { 800, 800 });
        tree.insert("SW1", new Integer[] { 200, 800 });
        tree.insert("SW2", new Integer[] { 200, 800 });
        record = tree.duplicates();

        assertEquals(2, record.getCount());
        assertTrue(this.arrayEquals(record.getValue(), new Integer[] { 0,
            200 }));
        record = record.getNext();
        assertEquals(2, record.getCount());
        assertTrue(this.arrayEquals(record.getValue(), new Integer[] { 800,
            200 }));
        record = record.getNext();
        assertEquals(2, record.getCount());
        assertTrue(this.arrayEquals(record.getValue(), new Integer[] { 800,
            800 }));
        record = record.getNext();
        assertEquals(2, record.getCount());
        assertTrue(this.arrayEquals(record.getValue(), new Integer[] { 200,
            800 }));
        record = record.getNext();
        assertNull(record);

        // Removing NE and SW duplicates
        tree.remove(null, new Integer[] { 800, 200 });
        tree.remove(null, new Integer[] { 200, 800 });
        record = tree.duplicates();

        assertEquals(2, record.getCount());
        assertTrue(this.arrayEquals(record.getValue(), new Integer[] { 0,
            200 }));
        record = record.getNext();
        assertTrue(this.arrayEquals(record.getValue(), new Integer[] { 800,
            800 }));
        record = record.getNext();
        assertNull(record);
    }


    /**
     * test for helper method for dump
     */
    public void testGetPreOrderList() {
        assertEquals(0, tree.getPreOrderList().size());
    }


    /**
     * test for helper method for dump
     */
    public void testGetRt() {
        assertEquals(tree.getRt().getNodeClass(),
            NodeClassification.FlyweightNode);
    }


    /**
     * Quick function to check contents of 2 Integer Arrays
     * 
     * @param value
     *            Array to compare
     * @param obj
     *            Array to compare
     * @return true if data values of 2 Arrays are identical
     */
    private boolean arrayEquals(Integer[] value, Integer[] obj) {
        if (obj == null || value == null) {
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
