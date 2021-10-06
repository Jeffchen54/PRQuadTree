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
public class LeafNode<K, V> {

    private KVPair<K, V> data;
    private LeafNode<K, V> NW;
    private LeafNode<K, V> NE;
    private LeafNode<K, V> SW;
    private LeafNode<K, V> SE;


    /**
     * this is the constructor of a quadNode
     * @param pair is the parameter
     */
    public LeafNode(KVPair<K, V> pair) {
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
    public LeafNode<K, V> getNW(){
        return NW;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public LeafNode<K, V> getNE(){
        return NE;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public LeafNode<K, V> getSW(){
        return SW;
    }

    /**
     * this is getter method for one
     * of quadrant
     * @return a node
     */
    public LeafNode<K, V> getSE(){
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
    public void setNW(LeafNode<K, V> node) {
        NW = node;
    }
    
    /**
     * this is a setter method for
     * each quadrant
     * @param node the parameter
     */
    public void setNE(LeafNode<K, V> node) {
        NE = node;
    }
    
    /**
     * this is a setter method for
     * each quadrant
     * @param node the parameter
     */
    public void setSW(LeafNode<K, V> node) {
        SW = node;
    }
    
    /**
     * this is a setter method for
     * each quadrant
     * @param node the parameter
     */
    public void setSE(LeafNode<K, V> node) {
        SE = node;
    }
}
