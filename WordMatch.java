/**
 * Student Name: Mohammed Alshutwi
 * Student ID: 18187708
 */

import java.io.*;
import java.util.*;

public class WordMatch
{
    private static Scanner infile;

    private static Lexicon lexicon;
    private static String in_wordsFileNames;
    private static String out_wordsFileName;
    private static String in_patternsFileName;
    private static String out_patternsFileName;

    public static void main ( String[] args ) throws Exception
    {
        lexicon = new Lexicon();

        if ( args.length != 4 )
            {
               System.out.println("Usage: Please enter the correct command-line arguments\n"
                                + "       e.g. java WordMatch in1.txt out1.txt in2.txt out2.txt");
               return;
            }

        in_wordsFileNames = args[ 0 ];
        out_wordsFileName = args[ 1 ];
        in_patternsFileName = args[ 2 ];
        out_patternsFileName = args[ 3 ];

        loadWords();
        loadPatterns();

        lexicon.getExecutionTimeForEachTask();
    }

    // This method is to read the names of text files containing words
    // and for each file it invokes the lexicon to read the contained 
    // words and build itself. And, Finally, it invokes the lexicon
    // to write the words into a text file.
    private static void loadWords () throws Exception
    {
        infile = new Scanner( new File( in_wordsFileNames ) );
        String line;

        while ( infile.hasNext() )
            {
                line = infile.nextLine();
                String[] fileNames = line.split("\\s+");

                for ( String fileName : fileNames ) 
                    {
                        lexicon.loadWords( fileName );
                    }
            }

        lexicon.displayAndWriteLexicon( out_wordsFileName );
    }

    // This method is to read the patterns contained in the
    // text file named in the parameter and , for each, it
    // invoke the lexicon to search for its matches.
    // And, Finally, it invokes the lexicon to write 
    // the words into a text file
    private static void loadPatterns () throws Exception
    {
        infile = new Scanner( new File( in_patternsFileName ) );
        String pattern;
        
        while ( infile.hasNext() )
            {
                pattern = infile.nextLine().trim();

                lexicon.findMatches( pattern );
            }

        lexicon.displayAndWritePatterns( out_patternsFileName );
    }
}