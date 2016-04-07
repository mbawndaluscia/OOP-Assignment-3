//package kevOOP;
//import java.util.Scanner;
//public class TestLetters {
//
//	public  void test() {
//		//LettersRound lr=new LettersRound();
//		WordChecker checker=new WordChecker(lr.roundLetters);
//		Scanner in=new Scanner(System.in);
//		
//		System.out.println("Press v for a vowel, c for a consonant");
//		
//		while(lr.nextLetterIndex<9){
//		String next=in.next();
//		if(next.equals("v")){
//			lr.addVowel();
//		}
//		if(next.equals("c")){
//			lr.addConsonant();
//		}
//		for(int i=0; i<lr.nextLetterIndex; i++){
//		System.out.print(lr.roundLetters[i].getLetter()+ " ");
//		}
//		
//		}
//		String w;
//		do{
//		System.out.println("Enter word");
//		w=in.next();
//		Word word=new Word(w);
//		if(checker.inDictionary(word.getWord())&&checker.matchesLetters(word.getWord(), lr.roundLetters)){
//			System.out.println("yes");
//			System.out.println(word.getValue());
//		}else{
//			System.out.println("no");
//			
//		}
//		}while(w!="1");
//		in.close();
//	}
//
//}
