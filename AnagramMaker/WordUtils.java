import java.util.Scanner;
import java.util.ArrayList;
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Justin Chen
 *	@since	10/26/22
 */
 
public class WordUtils
{
	private String[] words;		// the dictionary of words

	private int numWords = 0;	// later used to determine number of words,
								//temporarily used to load words array
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "wordList.txt";
	
	private int finalWordNum = 0; //number of words in final array
	/* Constructor */
	public WordUtils() { 
		words = new String[100000];
		loadWords();
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () { 
		Scanner sc = FileUtils.openToRead(WORD_FILE);
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			words[numWords] = line;
			numWords++;
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{		
		//cycles through all words in dictionary

		String[] resultWords = new String[100000];
		for(int i = 0; i < numWords; i++){
			boolean isUsed[] = new boolean[letters.length()];
			for(int c = 0; c < letters.length(); c++){

			}
			boolean finishLoop = false;
			//cycles through all the letters and compares to the letters in each word
			for(int j = 0; j < words[i].length(); j++){
				//if one letter isnt found, continues through all remaining cycles
				//as an alternative to breaking
				if(finishLoop){
					continue;
				}
				boolean foundLetter = false;
				//part that compares letters. if letter is found, foundLetter is 
				//set to true
				for(int k = 0; k < letters.length(); k++){
					if(foundLetter){continue;}
					if(words[i].charAt(j) == letters.charAt(k) && isUsed[k] == false){
						isUsed[k] = true;
						foundLetter = true;
					}
				}
				//if any letter isnt found after cycling through all the letters,
				//continue through all letters in loops.
				if(!foundLetter){
					finishLoop = true;
				}
			}
			//if the loop was not forcibly finished, add word to array.
			if(!finishLoop){
				resultWords[finalWordNum] = words[i];
				finalWordNum++;
			}
		}
		return resultWords;
	}
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) { 
		for(int i = 0; i < finalWordNum; i++){
			if(wordList[i].length() > 0){
				System.out.printf("%-8s", wordList[i]);
				if((i + 1) % 5 == 0){
					System.out.println();
				}
			}
		}
		System.out.println();
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		char[] letterList = {'a','b','c','d','e','f','g','h','i','j','k','l','m',							
							'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int maxSum = 0;
		String maxWord = "";
		for(int i = 0; i < finalWordNum; i++){
			int sum = 0;
			for(int j = 0; j < wordList[i].length(); j++){
				boolean finished = false;
				for(int k = 0; k < 26; k++){
					//skips through if already used
					if(finished){
						continue;
					}
					//adds to sum based on which letter is at an index
					if(letterList[k] == wordList[i].charAt(j)){
						sum += scoreTable[k];
						finished = true;
					}
				}
			}
			if(sum > maxSum){
				maxSum = sum;
				maxWord = wordList[i];
			}
		}
		return maxWord;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{
		char[] letterList = {'a','b','c','d','e','f','g','h','i','j','k','l','m',							
							'n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int sum = 0;
		for(int j = 0; j < word.length(); j++){
			boolean finished = false;
			for(int k = 0; k < 26; k++){
				//skips through if already used
				if(finished){
					continue;
				}
				//adds to sum based on which letter is at an index
				if(letterList[k] == word.charAt(j)){
					sum += scoreTable[k];
					finished = true;
				}
			}
		}
		return sum;
	}
	
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
		/**
	 *	Determines if a word's characters match a group of letters
	 *	@param word		the word to check
	 *	@param letters	the letters
	 *	@return			true if the word's chars match; false otherwise
	 */
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
	/**
	 *	finds all words that match some or all of a group of alphabetic characters
	 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
	 *	@param letters		group of alphabetic characters
	 *	@return				an ArrayList of all the words that match some or all
	 *						of the characters in letters
	 */
	public ArrayList<String> allWords(String letters) {
		ArrayList<String> wordsFound = new ArrayList<String>();
		// check each word in the database with the letters
		for (String word: words)
			if (wordMatch(word, letters))
				wordsFound.add(word);
		return wordsFound;
	}
	
	/**
	 *	Sort the words in the database
	 */
	public void sortWords() {
		SortMethods sm = new SortMethods();
		sm.mergeSort(words);
	}

}
