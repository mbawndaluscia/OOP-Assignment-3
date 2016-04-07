package kevOOP;
import processing.core.PApplet;
public class LettersRound extends GameRound{
	PApplet ap;
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
	
	
	//words chosen by each player
	Word p1Word,p2Word;
	
	//word checker object
	WordChecker wordChecker;
	
	
	
	
	
	public LettersRound(PApplet applet){
		letDis=new LettersDistribution();
		ap=applet;
		
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
	
	private void calculateScores(){
		
		//word checker object
		wordChecker=new WordChecker(roundLetters);
		
		//are the words valid?
		boolean p1Valid=wordChecker.validWord(p1Word.getWord());
		boolean p2Valid=wordChecker.validWord(p2Word.getWord());
		
		//#1 neither player has a valid word, both score 0
		if(!p1Valid&&
		   !p2Valid){
			setPlayerScores(0,0);
			
		//#2 only player 1 has a valid word
		}else if( p1Valid&&
		         !p2Valid){
			setPlayerScores(1,p1Word.getValue());
			
		//#3 only player 2 has a valid word	
		}else if( !p1Valid&&
		           p2Valid){
			setPlayerScores(2,p2Word.getValue());
			
		//#4 both words are valid
		}else{
			
			//#4a players have equal value words
			if(p1Word.getValue()==p2Word.getValue()){
				setPlayerScores(0,p1Word.getValue());
				
			//4b player 1 wins
			}else if(p1Word.getValue()>p2Word.getValue()){
				setPlayerScores(1,p1Word.getValue());
				
			//4b player 2 wins
			}else{
				setPlayerScores(2,p2Word.getValue());
			}
		}
	}
	
	void drawRoundLayout(){
		ap.background(127);
		ap.rect(200,100,400,200);
	}

	

	
	

	
	
	
	
}
