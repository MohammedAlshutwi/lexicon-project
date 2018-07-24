public class WordMatchTester
{
    private static String fileName1 = "sample1-pp.txt";
    private static String fileName2 = "sample2-zoo.txt";
    private static String fileName3 = "sample4-results.txt";
    
    private static Lexicon lexicon;

    public static void main ( String[] args ) throws Exception
    {
        lexicon = new Lexicon();
        lexicon.loadWords( fileName1 );
        lexicon.loadWords( fileName2 );

        // test cases for patterns containing only the char '?'
        testCases_1();
        
        // test cases for patterns containing only the char '*'
        testCases_2();

        // test cases for patterns containing both '?' and '*'
        testCases_3();

        lexicon.displayAndWritePatterns( fileName3 );
    }

    // a method to test patterns containing only the char '?'
    private static void testCases_1 ()
    {
        // successful test cases:

        // to find matches for a pattern starting with '?'
        lexicon.findMatches( "?nswer" );

        // to find matches for a pattern has '?' in the middle
        lexicon.findMatches( "ben?et" );

        // to find matches for a pattern ending with '?'
        lexicon.findMatches( "cu?" );

        // to find matches for a pattern has '?' at start and middle
        lexicon.findMatches( "?n??n" );

        // to find matches for a pattern has '?' at middle and end
        lexicon.findMatches( "un?v?rs??l?" );

        // to find matches for a pattern has '?' at start, middle and end
        lexicon.findMatches( "?o?se?sio?" );

        // failure test case:
        lexicon.findMatches( "a?sw??r" ); 
    }

    // a method to test patterns containing only the char '*'
    private static void testCases_2 ()
    {
        // successful test cases:

        // to find matches for a pattern starting with '*'
        // this star might represent one letter or more
        lexicon.findMatches( "*udice" );

        // to find matches for a pattern ending with '*'
        // this star might represent one letter or more
        lexicon.findMatches( "chapt*" );

        // to find matches for a pattern ending with '*'
        // this star might represent zero letters
        lexicon.findMatches( "daughters*" );

        // to find matches for a pattern starting with '*'
        // this star might represent zero letters
        lexicon.findMatches( "*enough" );

        // to find matches for a pattern having '*' in the middle
        // each star might represent one letter or more
        lexicon.findMatches( "in*t*t*n" );

        // to find matches for a pattern having '*' at start and middle
        // each star might represent zero letters or more
        lexicon.findMatches( "*rs*t" );

        // to find matches for a pattern having '*' at start and middle
        // each star might represent one letter or more
        lexicon.findMatches( "*r*t" );

        // to find matches for a pattern having '*' at end and middle
        // each star might represent zero letters or more
        lexicon.findMatches( "imp*atien*" );

        // to find matches for a pattern having '*' at end and middle
        // each star might represent one letter or more
        lexicon.findMatches( "im*atien*" );

        // to find matches for a pattern having '*' at start, middle and end
        // each star might represent one letter or more
        lexicon.findMatches( "*n*l*dg*" );

        // to find matches for a pattern having '*' at start, middle and end
        // each star might represent zero letters or more
        lexicon.findMatches( "*ac*kn*owl*edg*ed*" );

        // failure test case:
        lexicon.findMatches( "salk*dfrt" );
    }

    // a method to test patterns containing both '?' and '*'
    private static void testCases_3 ()
    {
        // successful test cases:

        // to find matches for a pattern having '?' in the middle 
        // and '*' at the end
        lexicon.findMatches( "he?r*" );

        // to find matches for a pattern having '*' in the middle 
        // and '?' at the end
        lexicon.findMatches( "li*l?" );

        // to find matches for a pattern starting with '*'
        // and having '?' in the middle and at the end
        lexicon.findMatches( "*ghbou?h?o?" );

        // to find matches for a pattern starting with '?'
        // and having '*' in the middle
        lexicon.findMatches( "?onsi*red" );

        // to find matches for a pattern starting and ending with '*'
        // and having '?' in the middle
        lexicon.findMatches( "*in?in*" );

        // to find matches for a pattern starting and ending with '?'
        // and having '*' in the middle
        lexicon.findMatches( "?i*l?" );

        // to find matches for a pattern starting and ending with '?'
        // and having '*' in the middle
        lexicon.findMatches( "?eth*f*l?" );

        // failure test case:
        lexicon.findMatches( "*ghb??u?h?o?" );
    }
}