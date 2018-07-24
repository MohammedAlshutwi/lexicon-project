/**
 * Student Name: Mohammed Alshutwi
 * Student ID: 18187708
 */

import java.io.*;
import java.util.*;

// AVLTree class is managing the collection of AVLNode objects
// wich is representing Word objects
public class AVLTree
{
    private AVLNode root;

    public AVLTree ()
    {
        this.root = null;
    }

    // A search method which is going to be needed in searching
    // for frequencies of a word
    public Word search ( String letters )
    {
        if ( root == null )
            {
                return null;
            }

        AVLNode p = root;

        while ( true )
            {
                if ( p == null )
                    {
                        return null;
                    }
                else if ( letters.compareTo( p.getLettersOfWord() ) == 0 )
                    {
                        return p.getWord();
                    }
                else if ( letters.compareTo( p.getLettersOfWord() ) < 0 )
                    {
                        p = p.getLeftChild();
                    }
                else
                    {
                        p = p.getRightChild();
                    }
            }
    }

    // An inserting method
    public void insert ( Word word )
    {
        if ( root == null )
            {
                root = new AVLNode( word );
                return;
            }

        AVLNode p = root;

        while ( true )
            {
                if ( word.compareTo( p.getWord() ) <= 0 &&
                     p.getLeftChild() != null )
                    {
                        p = p.getLeftChild();
                    }
                else if ( word.compareTo( p.getWord() ) > 0 &&
                          p.getRightChild() != null )
                    {
                        p = p.getRightChild();
                    }
                else
                    {
                        break;
                    }
            }

        AVLNode newNode = new AVLNode( word );

        if ( word.compareTo( p.getWord() ) <= 0  )
            {
                p.setLeftChild( newNode );
            }
        else
            {
                p.setRightChild( newNode );
            }
        
        rebalancePath( newNode );
    }

    // This method is for rebalancing the AVLTree after each insertion
    private void rebalancePath ( AVLNode newInsertedNode )
    {
        ArrayList<AVLNode> nodes = getPath( newInsertedNode.getWord() );
        Collections.reverse( nodes );

        for ( int i = 0; i < nodes.size(); i++ )
            {
                setHeight( nodes.get( i ) );
            }

        for ( int i = 3; i < nodes.size(); i++ )
            {
                AVLNode pp = nodes.get( i );
                AVLNode pc = nodes.get( i-1 );

                if ( pp.getLeftChild() == pc )
                    {
                        AVLNode localRoot = rebalance( pc );
                        pp.setLeftChild( localRoot );

                        if ( localRoot != pc )
                            {
                                break;
                            }
                    }
                else
                    {
                        AVLNode localRoot = rebalance( pc );
                        pp.setRightChild( localRoot );

                        if ( localRoot != pc )
                            {
                                break;
                            }
                    }
            }
        
        root = rebalance( root );
    }

    // This method returns the path of the new
    // iserted word starting from the root
    private ArrayList<AVLNode> getPath ( Word word )
    {
        ArrayList<AVLNode> nodes = new ArrayList<AVLNode>();

        nodes.add( root );

        AVLNode p = root;

        while ( p != null && word.compareTo( p.getWord() ) != 0 )
            {
                if ( word.compareTo( p.getWord() ) < 0 )
                    {
                        p = p.getLeftChild();
                    }
                else
                    {
                        p = p.getRightChild();
                    }

                nodes.add( p );
            }

        return nodes;
    }

    // method calculates and sets the height
    // of the node in the parameter
    private void setHeight ( AVLNode node )
    {
        AVLNode leftChild = node.getLeftChild();
        AVLNode rightChild = node.getRightChild();
        
		int leftHeight = leftChild == null? -1 : leftChild.getHeight();
        int rightHeight = rightChild == null? -1 : rightChild.getHeight();
        
        if ( leftHeight >= rightHeight )
            {
                node.setHeight( leftHeight + 1 );
            }
        else
            {
                node.setHeight( rightHeight + 1 );
            }
    }

    // This method checks the height difference 
    // between the node's children sent in the
    // parameter and invokes the appropriate method 
    // to performe the appropriate rotation if needed
    private AVLNode rebalance ( AVLNode node )
    {
        int heightDifference = getHeightDifference( node );

        if ( heightDifference == 2 )
            {
                if ( getHeightDifference( node.getLeftChild() ) == -1 )
                    {
                        node = leftRightRotation( node );
                    }
                else
                    {
                        node = rightRotation( node );
                    }
            }
        else if ( heightDifference == -2 )
            {
                if ( getHeightDifference( node.getRightChild() ) == 1)
                    {
                        node = rightLeftRotation( node );
                    }
                else
                    {
                        node = leftRotation( node );
                    }
            }

        return node;
    }

    // This method calculates and returns the heigh
    // difference between the node's childern sent
    // in the parameter
    private int getHeightDifference ( AVLNode node )
    {
        AVLNode leftChild = node.getLeftChild();
        AVLNode rightChild = node.getRightChild();
        
        int leftHeight = leftChild == null? -1 : leftChild.getHeight();
        int rightHeight = rightChild == null? -1 : rightChild.getHeight();

        return leftHeight - rightHeight;
    }

    // This method performs right rotation
    private AVLNode rightRotation ( AVLNode g )
    {
        AVLNode p = g.getLeftChild();
        AVLNode x = p.getRightChild();
        
        p.setRightChild( g );
        g.setLeftChild( x );
      
        setHeight( g );
        setHeight( p );
      
        return p;
    }

    // This method performs left rotation
    private AVLNode leftRotation ( AVLNode g )
    {
        AVLNode p = g.getRightChild();
        AVLNode x = p.getLeftChild();
      
        p.setLeftChild( g );
        g.setRightChild( x );
      
        setHeight( g );
        setHeight( p );
      
        return p;
    }

    // This method performs right left rotation
    private AVLNode rightLeftRotation ( AVLNode g )
    {
        AVLNode p = g.getRightChild();
      
        g.setRightChild( rightRotation( p ) );
      
        return leftRotation( g );
    }

    // This method performs left right rotation
    private AVLNode leftRightRotation ( AVLNode g )
    {
        AVLNode p = g.getLeftChild();
      
        g.setLeftChild( leftRotation( p ) );
      
        return rightRotation( g );
    }

    // This method is to display all elements in the tree
    public void displayAndWriteElements ( PrintWriter outFile )
    {
        displayAndWriteElements( root, outFile );
    }

    // This recursive method gets called by displayElements()
    // and traverses inorder through the tree
    public void displayAndWriteElements ( AVLNode localRoot, PrintWriter outFile )
    {
        if ( localRoot != null )
            {
                displayAndWriteElements( localRoot.getLeftChild(), outFile );

                outFile.println( localRoot.getWord() );
                //System.out.println( localRoot.getWord() );

                displayAndWriteElements( localRoot.getRightChild(), outFile );
            }
    }

    // This method is to search for neighbours
    public void checkNeighboursOfWord ( Word word )
    {
        checkNeighboursOfWord( root, word );
    }

    // This recursive method gets called by checkNeighboursOfWord()
    // and traverses inorder through the tree to search for neighbours
    private void checkNeighboursOfWord ( AVLNode localRoot, Word word )
    {
        if ( localRoot != null )
            {
                checkNeighboursOfWord( localRoot.getLeftChild(), word );
                Helper.checkNeighbours( word, localRoot.getWord() );
                checkNeighboursOfWord( localRoot.getRightChild(), word );
            }
    }

    // This method is to search for matches
    public void findMatches ( Pattern pattern )
    {
        findMatches( root, pattern );
    }

    // This recursive method gets called by findMatches() and
    // traverses inorder through the tree to search for matches
    private void findMatches ( AVLNode localRoot, Pattern pattern )
    {
        if ( localRoot != null )
            {
                findMatches( localRoot.getLeftChild(), pattern );
                Helper.checkMatch( pattern, localRoot.getWord() );
                findMatches( localRoot.getRightChild(), pattern );
            }
    }
}