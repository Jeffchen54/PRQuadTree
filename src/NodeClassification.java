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
enum Node{
        ParentNode, LeafNode, FlyweightNode;
    }
public class NodeClassification {
    
    Node node;
    
    public NodeClassification(Node node) {
        this.node = node;
    }
    
    public void tellNode() {
        switch (node) {
            case FlyweightNode:
                System.out.println("FlyweightNode");
                break;
                    
            case LeafNode:
                System.out.println("LeafNode");
                break;
                         
            case ParentNode:
                System.out.println("ParentNode");
                break;
                        
            default:
                System.out.println("Can't recognize node");
                break;
        }
    }
    
    public void find(String[] args) {
        NodeClassification unknownNode = new NodeClassification(node.FlyweightNode);
        unknownNode.tellNode();
        NodeClassification unknownNode1 = new NodeClassification(node.LeafNode);
        unknownNode1.tellNode();
        NodeClassification unknownNode2 = new NodeClassification(node.ParentNode);
        unknownNode2.tellNode();
    }
}
