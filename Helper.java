/**
 * Student Name: Mohammed Alshutwi
 * Student ID: 18187708
 */

import java.io.*;
import java.util.*;

public class Helper
{
	// A generic method to perform bubble sort on words
	public static <E extends Comparable<E>> 
    void sort ( List<E> wordsList )
	{
        int left = 0;
        int right = wordsList.size() - 1;

		for ( int i = right ; i > left ; i-- )
			{
				for ( int j = left ; j < i ; j++ )
					{
                        if ( wordsList.get( j ).compareTo( wordsList.get( j+1 ) ) > 0 )
							{
								swap( wordsList, j, j+1 );
                            }
					}
			}
    }

	// A swapping method
    private static <E extends Comparable<E>> 
    void swap ( List<E> wordsList, int i, int j )
	{
		E temp = wordsList.get( i );
		wordsList.set( i, wordsList.get( j ) );
        wordsList.set( j, temp );
	}
	
	// A method to check if the word is new, otherwise it will increase its frequency
	public static boolean checkNewWord ( HashTable lexicon, String newWord )
	{
		Word word = lexicon.search( newWord );

		if ( word != null )
			{
				word.setFrequency();
				return false;
			}

		return true;
	}

	// This method is to check if the two words are neighbours
	// and adds them to each other's neighbourLists
	public static void checkNeighbours ( Word firstWord, Word secondWord )
	{
		boolean isNeighbour = checkSimilarity( firstWord, secondWord );

			if ( isNeighbour )
				{
					firstWord.addNeighbour( secondWord.getLetters() );
					secondWord.addNeighbour( firstWord.getLetters() );

					sort( firstWord.getNeighbours() );
					sort( secondWord.getNeighbours() );
				}
	}

	// This method is to check the similarities between two words and returns
	// true only if the they have same length and differ in 1 letter
	private static boolean checkSimilarity ( Word firstWord, Word secondWord )
	{
		if ( firstWord.length() != secondWord.length() )
			{
				return false;
			}
		else
			{
				int count = 0;

				for ( int i = 0; i < firstWord.length(); i++ )
					{
						if ( firstWord.charAt( i ) != secondWord.charAt( i ) )
							{
								count++;
							}
					}

				return count == 1;
			}
	}

	// This method is to check the if a word matches a given pattern using
	// regular expressions and returns true if the word matches the pattern
	public static void checkMatch ( Pattern pattern, Word word )
	{
		String patternCharacters = "";
		char character;

		for ( int i = 0; i < pattern.length(); i++ )
			{
				character = pattern.charAt( i );

				if ( character == '*' )
					{
						patternCharacters = patternCharacters + "[a-z]*";
					}
				else if ( character == '?' )
					{
						patternCharacters = patternCharacters + "[a-z]";
					}
				else
					{
						patternCharacters = patternCharacters + character;
					}
			}

			boolean isMatch = word.getLetters().matches( patternCharacters );

			if ( isMatch )
                {
                    pattern.addMatch( word );
                }
	}
}