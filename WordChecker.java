package kevOOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordChecker  {
	
	//Static list of all valid words
	static ArrayList<String> dictionary = new ArrayList<String>();
	
	//Constructor
	public WordChecker(){
		loadWords();
	}
	
	//Load valid words from csv to dictionary
	private void loadWords(){
		BufferedReader br = null;
		
		try {
			br = new BufferedReader( new FileReader("All Words.csv"));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		 String word=""; 
		
		 try {
			while((word=br.readLine())!=null){
				dictionary.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	


	//Check if a word is in dictionary
	public boolean inDictionary(String word){
		if(dictionary.contains(word)){
			return true;
		}else{
			return false;
		}
	}
	
	//Check if word contains only letters available
	public boolean matchesLetters(String word, Letter[]_roundLetters){
		String[] wordLetters=word.split("");
		ArrayList<String> roundLetters=new ArrayList<String>();
		for(Letter l :_roundLetters){
			roundLetters.add(l.getLetter());
			
		}
		for(String s :wordLetters){
			if(roundLetters.contains(s.toUpperCase())){
				roundLetters.remove(s);
			}
			else{
				return false;
			}
		}
		return true;
	}
	//testing dictionary
	 public static void main(String[] args){
		 WordChecker wc=new WordChecker();
		 System.out.println(wc.inDictionary("woof"));
		 
	 }
}

	