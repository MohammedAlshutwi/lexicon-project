public class LexiconTester
{
    private static String fileName1 = "t3-BigWordList.txt";
    private static String fileName2 = "t4-bb.txt";
    private static String fileName3 = "sample3-results.txt";

    public static void main ( String[] args ) throws Exception
    {
        Lexicon lexicon = new Lexicon();
        lexicon.loadWords( fileName1 );
        lexicon.loadWords( fileName2 );
        lexicon.displayAndWriteLexicon( fileName3 );
    }
}