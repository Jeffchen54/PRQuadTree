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
public class LeafNode<K, V extends Comparable<V>> implements BaseNode<K, V> {

    private KVPair<K, V>[] list;
    private KVPair<K, V> data;
    private NodeClassification node;
    private Node collection;
    private int size;

    @SuppressWarnings("unchecked")
    public LeafNode() {
        list = (KVPair<K, V>[]) new KVPair[3];
        size = 0;
        collection = new Node(null);
    }
    
    /**
     * return size for a leafNode
     * @return
     */
    public int getSize() {
        return size;
    }
    
    /**
     * get the name of the class
     * @return
     */
    public String getNodeClass() {
        return this.getClass().getName(); 
    }
    
    /**
     * this is the node class for storing
     * the duplicate point
     * @author ben chen
     *
     */
    public class Node {
        private KVPair<K, V> data;
        private Node next;
    
        /**
         * constructor takes a pair
         * @param pair
         */
        public Node(KVPair<K, V> pair) {
            this.data = pair;
            next = null;
        }
        
        public KVPair<K, V> add(KVPair<K, V> data){
            Node temp = new Node(data);
            this.next = temp;
            return data;
        }
        
        public Node getNext() {
            return this.getNext();
        }
        
        public KVPair<K, V> getData() {
            return this.data;
        }
        
    }
    
    
}
