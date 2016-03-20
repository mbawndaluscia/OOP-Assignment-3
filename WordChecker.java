package kevOOP;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordChecker  {
	static ArrayList<String> dictionary = new ArrayList<String>();
	public WordChecker(){
		loadWords();
	}
	

		
	
	
	public boolean validWord(String word){
		if(dictionary.contains(word)){
			return true;
		}else{
			return false;
		}
	}
	
	private void loadWords(){
		BufferedReader br = null;
		
		try {
			br = new BufferedReader( new FileReader("All Words.csv"));
		} catch (FileNotFoundException e1) {
			
			e1.printStackTrace();
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
	
	public static void main(String[] args){
		WordChecker wc=new WordChecker();
		
		
		System.out.println(WordChecker.dictionary.get(3458));
		System.out.println(wc.validWord(""));
		System.out.println(wc.validWord("pop"));
		
		
		
		
	
	}
}
