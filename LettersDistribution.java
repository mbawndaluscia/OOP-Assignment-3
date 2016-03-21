package kevOOP;
import java.util.ArrayList;
import java.util.Random;
public class LettersDistribution{
	
	//Array lists of 67 vowels and 74 consonants available in each letters round
	
	ArrayList<Vowel> vowels=new ArrayList<Vowel>();
	
	ArrayList<Consonant> consonants=new ArrayList<Consonant>();
	
	
	//Populate letter arrays 
	
	public LettersDistribution(){
		
		/*Add 67 vowels to array
		   A      15  
		   E      21  
		   I      13  
		   O      13  
		   U       5  
		*/
		
		
		//15 A's
		for(int i=0; i<15; i++){
			vowels.add(new Vowel("A"));
		}
		//21 E's
		for(int i=0; i<21; i++){
			vowels.add(new Vowel("E"));
		}
		//13 I's
		for(int i=0; i<13; i++){
			vowels.add(new Vowel("I"));
		}
		//13 O's
		for(int i=0; i<13; i++){
			vowels.add(new Vowel("O"));
		}
		//5 U's
		for(int i=0; i<5; i++){
			vowels.add(new Vowel("U"));
		}
		
		/*Add 74 coconsonants
		 B       2  
		 C       3  
		 D       6  
		 F       2  
		 G       3  
		 H       2  
		 J       1  
		 K       1  
		 L       5  
		 M       4  
		 N       8 
		 P       4  
  		 Q       1  
         R       9  
         S       9  
         T       9  
         V       1  
         W       1  
         X       1  
         Y       1  
         Z       1  */
		
		//2 B's
		for(int i=0; i<2; i++){
			consonants.add(new Consonant("B"));
		}
		//3 C's
		for(int i=0; i<3; i++){
			consonants.add(new Consonant("C"));
		}
		//6 D's
		for(int i=0; i<6; i++){
			consonants.add(new Consonant("D"));
		}
		//2 F's
		for(int i=0; i<2; i++){
			consonants.add(new Consonant("F"));
		}
		//3 G's
		for(int i=0; i<3; i++){
			consonants.add(new Consonant("G"));
		//2 H's
		}
		for(int i=0; i<2; i++){
			consonants.add(new Consonant("H"));
		}
		//1 J
		consonants.add(new Consonant("J")); 
		//1 K
		consonants.add(new Consonant("K")); 
		//5 L's
		for(int i=0; i<5; i++){
			consonants.add(new Consonant("L"));
		}
		//4 M's
		for(int i=0; i<4; i++){
			consonants.add(new Consonant("M"));
		}
		//8 N's
		for(int i=0; i<8; i++){
			consonants.add(new Consonant("N"));
		}
		//4 P's
		for(int i=0; i<4; i++){
			consonants.add(new Consonant("P"));
		}
		//1 Q
			consonants.add(new Consonant("Q"));
		// 9 R's
		for(int i=0; i<9; i++){
			consonants.add(new Consonant("R"));
		}
		//9 S's
		for(int i=0; i<9; i++){
			consonants.add(new Consonant("S"));
		}
		//9 T's
		for(int i=0; i<9; i++){
			consonants.add(new Consonant("T"));
		}
		//1 V
		consonants.add(new Consonant("V"));
	
		//1 W
		consonants.add(new Consonant("W"));
	
		//1 X
		consonants.add(new Consonant("X"));
	
		//1 Y
		consonants.add(new Consonant("Y"));
	
		//1 Z
		consonants.add(new Consonant("Z"));
		
	}
	
	
	//Select a random vowel and remove it from the array list
	public Vowel randomVowel(){
		Vowel picked=null;
		
		Random random=new Random();
		int randomVowel=random.nextInt(vowels.size());
		
		picked= vowels.get(randomVowel);
		vowels.remove(picked);
		
		return picked;
		
	}
	
	//Select a random consonant and remove it from the array list
		public Consonant randomConsonant(){
			Consonant picked=null;
			
			Random random=new Random();
			int randomConsonant=random.nextInt(consonants.size());
			
			picked= consonants.get(randomConsonant);
			consonants.remove(picked);
			
			return picked;
			
		}
}
