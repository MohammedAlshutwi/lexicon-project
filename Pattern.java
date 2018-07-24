/**
 * Student Name: Mohammed Alshutwi
 * Student ID: 18187708
 */

import java.util.ArrayList;

public class Pattern implements Comparable<Pattern>
{
    private String characters;
    private ArrayList<Word> matches;

    public Pattern ( String characters )
    {
        this.characters = characters;
        this.matches = new ArrayList<Word>();
    }

    public void addMatch ( Word match )
    {
        matches.add( match );
    }

    public String getCharacters ()
    {
        return characters;
    }

    // a method to get the length of a Pattern object's letters
    // it makes it easier to treat an object of Pattern as a simple 
    // String object when needed
    public int length()
    {
        return characters.length();
    }

    // a method acting like the charAt() method for Strings
    // it makes it easier to treat an object of Pattern as a simple 
    // String object when needed
    public char charAt ( int index )
    {
        return characters.charAt( index );
    }

    public ArrayList<Word> getMatches ()
    {
        return matches;
    }

    public int compareTo ( Pattern pattern )
    {
        return 99999;
    }

    public String toString ()
    {
        String pattern  = String.format( "Pattern: %s\n", characters );
        
        String results = "Results:\n";

        if ( matches.size() > 0 )
            {
                for ( Word match: matches )
                    {
                        results = results 
                                + match.getLetters() + " " 
                                + match.getFrequency() + "\n";
                    }
            }
        else
            {
                results = results
                        + "No words in the lexicon match the pattern\n";
            }

        return pattern + results;
    }
}