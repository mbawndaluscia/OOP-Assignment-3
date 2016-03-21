package kevOOP;

public class LettersRound {
	
	//Array of 9 letters for this round
	Letter[] roundLetters=new Letter[9];
	
	//Letter Distribution for this round
	LettersDistribution letDis;
	
	int nextLetterIndex=0;
	
	//Maximum numbers allowed of each letter type
	int maxVowels=5;
	int maxConsonants=6;
	
	//Count letters picked
	int countVowels=0;
	int countConsonants=0;
	
	
	
	public LettersRound(){
		letDis=new LettersDistribution();
	}
	
	public void addVowel(){
		if(countVowels<maxVowels){
		roundLetters[nextLetterIndex]=letDis.randomVowel();
		nextLetterIndex++;
		countVowels++;
		}
	}
	
	public void addConsonant(){
		if(countConsonants<maxConsonants){
		roundLetters[nextLetterIndex]=letDis.randomConsonant();
		nextLetterIndex++;
		countConsonants++;
		}
	}
	
	public Letter[] getSelection(){
		return roundLetters;
	}
	
	
	
}
