import java.util.ArrayList;
/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	
 *	@since	
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtils wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
	private ArrayList<String> resultWords = new ArrayList<>();
	private ArrayList<String> validWords = new ArrayList<>();
	private boolean gameOver = false;
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtils();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		while(!gameOver){
			runAnagramMaker();
			System.out.println("\nThanks for using AnagramMaker!\n");
		}
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		String anagram = Prompt.getString("Word(s), name or phrase (q to quit)");
		if(anagram.equals("q")){
			return;
		}
		anagram = removeSpaces(anagram);
		numWords = Prompt.getInt("Number of words in anagram");
		maxPhrases = Prompt.getInt("Maximum number of anagrams to print");
		
	}

	/**
	 * actually runs the recursive method and does the work
	 */
	public void createAnagrams(String letters, int wordNum){
		
		if(wordNum == numWords){
			
		}
		validWords = wordUtils.allWords(letters);
	}
	
	/**
	 * removes all spaces from a word so only characters remain, also removes uppercasing
	 * helper method for runAnagramMaker
	 */
	private String removeSpacesAndCase(String s){
		String result = "";
		for(int i = 0; i < s.length(); i++){
			if(s.charAt(i) != ' '){
				result += s.charAt(i).toLowerCase();
			}
		}
		return result;
	}
	
}
