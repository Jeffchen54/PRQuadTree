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

// Java Doc ---------------------------------------------------------------
/**
 * Enumerated type specifying the different types of BaseNodes used
 * in the PRQuadTree
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 10.17.2021
 */
enum NodeClassification {
    /**
     * parent Node
     */
    ParentNode,
    /**
     * leaf Node
     */
    LeafNode,
    /**
     * flyweight Node
     */
    FlyweightNode;
}
