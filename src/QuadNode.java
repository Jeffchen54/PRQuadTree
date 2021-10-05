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
public class QuadNode<K, V> {

    private KVPair<K, V> data;
    private QuadNode<K, V> NW;
    private QuadNode<K, V> NE;
    private QuadNode<K, V> SW;
    private QuadNode<K, V> SE;


    /**
     * this is the constructor of a quadNode
     * @param pair is the parameter
     */
    public QuadNode(KVPair<K, V> pair) {
        this.data = pair;

    }

    /**
     * this is a getter method for
     * parent node
     * @return a KVPair
     */
    public KVPair<K, V> getData(){
        return data;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public QuadNode<K, V> getNW(){
        return NW;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public QuadNode<K, V> getNE(){
        return NE;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public QuadNode<K, V> getSW(){
        return SW;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public QuadNode<K, V> getSE(){
        return SE;
    }
    
    /**
     * this is a setter method for parent
     * node, set the data
     * @param pair takes a KVPair as param
     */
    public void setData(KVPair<K, V> pair) {
        this.data = pair;
    }
    
    /**
     * this is a setter method for
     * each quadrant
     * @param node the parameter
     */
    public void setNW(QuadNode<K, V> node) {
        NW = node;
    }
    
    /**
     * this is a setter method for
     * each quadrant
     * @param node the parameter
     */
    public void setNE(QuadNode<K, V> node) {
        NE = node;
    }
    
    /**
     * this is a setter method for
     * each quadrant
     * @param node the parameter
     */
    public void setSW(QuadNode<K, V> node) {
        SW = node;
    }
    
    /**
     * this is a setter method for
     * each quadrant
     * @param node the parameter
     */
    public void setSE(QuadNode<K, V> node) {
        SE = node;
    }
}
