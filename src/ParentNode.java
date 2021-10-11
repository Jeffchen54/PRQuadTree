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
public class ParentNode<K, V extends Comparable<V>> implements BaseNode<K, V> {

    private BaseNode NW;
    private BaseNode NE;
    private BaseNode SW;
    private BaseNode SE;
    private LeafNode<K, V>[] leafNode;
    private NodeClassification type;


    /**
     * this is the constructor of a quadNode
     * @param pair is the parameter
     */
    public ParentNode() {
        NW = null;
        NE = null;
        SW = null;
        SE = null;

    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public BaseNode getNW(){
        return NW;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public BaseNode getNE(){
        return NE;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public BaseNode getSW(){
        return SW;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public BaseNode getSE(){
        return SE;
    }
    
    /**
     * this function return the name of the class
     * @return a string
     */
    public String getNodeClass() {
        return this.getClass().getName(); 
    }
}
