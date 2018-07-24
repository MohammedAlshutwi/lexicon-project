/**
 * Student Name: Mohammed Alshutwi
 * Student ID: 18187708
 */

import java.io.*;
import java.util.*;
/**
 * Lexicon class is storing and managing the collection of words
 * and patterns using Hash Tables and AVL-Trees
 */
public class Lexicon
{
    private long insertionTime = 0;
    private long frequencySearchTime = 0;
    private long neighboursSearchTime = 0;
    private long writingWordsToFileTime = 0;
    private long matchesSearchTime = 0;
    private long writingMatchesToFileTime = 0;

    private HashTable lexicon;
    private ArrayList<Pattern> patternList;

    public Lexicon ()
    {
        this.lexicon = new HashTable();
        this.patternList = new ArrayList<Pattern>();
    }
    
    // This method is adding words to lexicon
    private void populateLexicon ( String[] words )
    {
        for ( String word : words )
            {
                if ( word.length() > 0 )
                    {
                        long feqSrch_startTime = System.nanoTime();

                        // the method first invokes Helper class to check 
                        //if the word is actually new
                        boolean isNewWord = Helper.checkNewWord( lexicon, word );

                        long feqSrch_endTime = System.nanoTime();
                        frequencySearchTime += ( feqSrch_endTime - feqSrch_startTime ) / 1000000;
                        

                        if ( isNewWord )
                            {
                                Word newWord = new Word( word );

                                long insert_startTime = System.nanoTime();

                                // ivoking the HashTable to insert the new word
                                lexicon.insert( newWord );

                                long insert_endTime = System.nanoTime();
                                insertionTime += ( insert_endTime - insert_startTime ) / 1000000;


                                long nghbrSrch_startTime = System.nanoTime();

                                // invoking the HashTable to search for
                                // the new word's neighbours
                                lexicon.checkNeighboursOfWord( newWord );

                                long nghbrSrch_endTime = System.nanoTime();
                                neighboursSearchTime += ( nghbrSrch_endTime - nghbrSrch_startTime ) / 1000000;
                            }
                    }
            }
    }

    // This method invokes the HashTable to performe
    // traverse operation to search for a pattern's matches
    public void findMatches ( String characters )
    {
        Pattern pattern = new Pattern( characters );

        long matchSrch_startTime = System.nanoTime();

        // invoking HashTable to search for matches
        lexicon.findMatches( pattern );

        long matchSrch_endTime = System.nanoTime();
        matchesSearchTime += ( matchSrch_endTime - matchSrch_startTime ) / 1000000;

        // adding the pattern along with its matches
        // to the pattern collections
        patternList.add( pattern );
    }

    // This method reads words from  a text file and then cleans them
    public void loadWords ( String fileName ) throws Exception
    {
        File inStream = new File( fileName );
        Scanner reader = new Scanner( inStream );

        String line;
  
        while( reader.hasNext() )
        {
            line = reader.nextLine();
            String[] words = line.replaceAll("[^a-zA-Z ]", "")
                                 .toLowerCase()
                                 .split("\\s+");

            // adding words contained in each line
            populateLexicon( words );
        }

        reader.close();
    }

    // This method displays and write all words in lexicon
    public void displayAndWriteLexicon ( String fileName ) throws Exception
    {
        PrintWriter outFile = new PrintWriter( new File( fileName ) );

        long writingWords_startTime = System.nanoTime();

        // invoking HashTable to display and write
        lexicon.displayAndWrite( outFile );

        long writingWords_endTime = System.nanoTime();
        writingWordsToFileTime += ( writingWords_endTime - writingWords_startTime ) / 1000000;

        outFile.close();
    }

    // This method displays and write all patterns
    public void displayAndWritePatterns ( String fileName ) throws Exception
    {
        PrintWriter outFile = new PrintWriter( new File( fileName ) );

        long writingMatches_startTime = System.nanoTime();

        for ( Pattern pattern: patternList )
            {
                outFile.println( pattern );
                //System.out.println( pattern );
            }

        long writingMatches_endTime = System.nanoTime();
        writingMatchesToFileTime += ( writingMatches_endTime - writingMatches_startTime ) / 1000000;
        
        outFile.close();
    }

    public void getExecutionTimeForEachTask ()
    {
        System.out.println( "- The execution time for insertion is: " + insertionTime + " milliseconds" );
        System.out.println( "- The execution time for frequency search is: " + frequencySearchTime + " milliseconds" );
        System.out.println( "- The execution time for neighbour search is: " + neighboursSearchTime + " milliseconds" );
        System.out.println( "- The execution time for writing words to file is: " + writingWordsToFileTime + " milliseconds" );
        System.out.println( "- The execution time for matches search is: " + matchesSearchTime + " milliseconds" );
        System.out.println( "- The execution time for writing matches to file is: " + writingMatchesToFileTime + " milliseconds" );
    }
}