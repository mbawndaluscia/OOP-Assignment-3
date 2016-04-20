package kevOOP;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WordFinder extends WordChecker {

	ArrayList<String> wordsFound;
	
	public WordFinder(Letter[] roundLetters){
		super(roundLetters);
		wordsFound=new ArrayList<String>();
		findWords();
	}
	
	private void findWords(){
		for(String word : dictionary){
			if(matchesLetters(word, roundLetters)){
				wordsFound.add(word.toUpperCase());
			}
		}
		sortWords();
	}
	
	public ArrayList<String> getAllWordsFound(){
		return wordsFound;
	}
	
	private void sortWords(){
		Comparator<String> comp = new Comparator<String>()
	    {
	        public int compare(String a, String b)
	        {
	            if(a.length() > b.length())
	                return -1;

	            if(b.length() > a.length())
	                return 1;

	            return 0;
	        }
	    };

	    Collections.sort(wordsFound,  comp);
	}
}
