/**
 * Student Name: Mohammed Alshutwi
 * Student ID: 18187708
 */

 // This class is representing a Word object in an AVL-Node
 public class AVLNode
 {
    private Word word;
    private AVLNode leftChild;
    private AVLNode rightChild;
    private int height;

    public AVLNode ( Word word )
    {
        this.word = word;
        this.leftChild = null;
        this.rightChild = null;
        this.height = 0;
    }

    // defining mutator methods
    public void setLeftChild ( AVLNode leftChild )
    {
        this.leftChild = leftChild;
    }

    public void setRightChild ( AVLNode rightChild )
    {
        this.rightChild = rightChild;
    }

    public void setHeight ( int height )
    {
        this.height = height;
    }

    // defining accessor methods
    public Word getWord ()
    {
        return word;
    }

    public String getLettersOfWord ()
    {
        return word.getLetters();
    }

    public AVLNode getLeftChild ()
    {
        return leftChild;
    }

    public AVLNode getRightChild ()
    {
        return rightChild;
    }

    public int getHeight ()
    {
        return height;
    }
 }