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
 * Object containing an int[] and an equals function for comparing that int[]
 * 
 * @author Jeff Chen (chenjeff4840)
 * @version 9/18/2021
 */
public class Dimensions {

    // Fields ----------------------------------------------------------------
    private int[] data;

    // Constructor ----------------------------------------------------------
    /**
     * Constructs a Dimension using user provided int[]
     * 
     * @param data
     *            User provided int[]
     * @precondition data != null
     */
    public Dimensions(int[] data) {
        this.data = data;
    }


    // Functions ------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (this.getClass() == obj.getClass()) {
            Dimensions other = (Dimensions)(obj);

            if (other.getArr().length != data.length) {
                return false;
            }

            for (int i = 0; i < data.length; i++) {
                if (data[i] != other.getArr()[i]) {
                    return false;
                }
            }
            return true;
        }
        else {
            return false;
        }

    }


    /**
     * Returns int[] data field
     * 
     * @return int[] data field
     */
    public int[] getArr() {
        return data;
    }
}
