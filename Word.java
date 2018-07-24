/**
 * Student Name: Mohammed Alshutwi
 * Student ID: 18187708
 */

import java.util.ArrayList;

public class Word implements Comparable<Word>
{
    private String letters;
    private int frequency;
    private ArrayList<String> neighbours;

    public Word ( String letters )
    {
        this.letters = letters;
        this.frequency = 1;
        this.neighbours = new ArrayList<String>();
    }

    public void setFrequency ()
    {
        frequency++;
    }

    public void addNeighbour ( String neighbour )
    {
        neighbours.add( neighbour );
    }

    public String getLetters ()
    {
        return letters;
    }

    // a method to get the length of a Word object's letters
    // it makes it easier to treat an object of Word as a simple 
    // String object when needed
    public int length()
    {
        return letters.length();
    }

    // a method acting like the charAt() method for Strings
    // it makes it easier to treat an object of Word as a simple 
    // String object when needed
    public char charAt ( int index )
    {
        return letters.charAt( index );
    }

    public int getFrequency ()
    {
        return frequency;
    }

    public ArrayList<String> getNeighbours ()
    {
        return neighbours;
    }

    // a method acting like compareTo() method for Strings
    // it makes it easier to treat an object of Word as a simple 
    // String object when needed
    public int compareTo ( Word word )
    {
        return ( letters.compareTo( word.getLetters() ) );
    }

    public String toString ()
    {
        String wordInformation  = String.format( "%s %d ", letters, frequency )
                                + neighbours;

        return wordInformation;
    }
}