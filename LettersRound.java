package kevOOP;

import controlP5.*;

import processing.core.PApplet;

public class LettersRound extends GameRound{
	PApplet ap;
	ControlP5 cp5;
	//Array of 9 letters for this round
	Letter[] roundLetters=new Letter[9];
	
	//Buttons for round letters and selected letters
	Button[] roundLetterButtons=new Button[9];
	Button[] selectedLetterButtons=new Button[9];
	
	
	//Letter Distribution for this round
	LettersDistribution letterDistribution;
	
	int nextLetterIndex=0;
	
	int nextSelectedLetterIndex=0;
	
	//Maximum numbers allowed of each letter type
	int maxVowels=5;
	int maxConsonants=6;
	
	//Count letters picked
	int countVowels=0;
	int countConsonants=0;
	
	
	//words chosen by each player
	Word p1Word,p2Word;
	
	 boolean p1Valid,p2Valid;
	
	//word checker object
	WordChecker wordChecker;
	
	
	
	
	
	public LettersRound(PApplet applet){
		letterDistribution=new LettersDistribution();
		ap=applet;
		cp5=Countdown.cp5;
		
		for(int i=0; i<9; i++){
			roundLetterButtons[i]=new Button();
			selectedLetterButtons[i]=new Button();
			Countdown.buttons.add(roundLetterButtons[i]);
			Countdown.buttons.add(selectedLetterButtons[i]);
		}
		
	
	}
	
	public void addVowel(){
		if(countVowels<maxVowels&&nextLetterIndex<9){
			roundLetters[nextLetterIndex]=letterDistribution.randomVowel();
			drawLetter();
			nextLetterIndex++;
			countVowels++;
		}
		if(nextLetterIndex==9){
			testScore();
		}
	}
	
	public void addConsonant(){
		if(countConsonants<maxConsonants&&nextLetterIndex<9){
			roundLetters[nextLetterIndex]=letterDistribution.randomConsonant();
			drawLetter();

			nextLetterIndex++;
			countConsonants++;
		}
		if(nextLetterIndex==9){
			testScore();
		}
	}
	
	public Letter[] getSelection(){
		return roundLetters;
	}
	
	private void calculateScores(){
		
		//word checker object
		wordChecker=new WordChecker(roundLetters);
		
		//are the words valid?
		 p1Valid=wordChecker.validWord(p1Word.getWord());
		 p2Valid=wordChecker.validWord(p2Word.getWord());
		
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
				
			//#4b player 1 wins
			}else if(p1Word.getValue()>p2Word.getValue()){
				setPlayerScores(1,p1Word.getValue());
				
			//#4c player 2 wins
			}else{
				setPlayerScores(2,p2Word.getValue());
			}
		}
	}
	
	void drawRoundLayout(){
		ap.background(185);
		ap.fill(0,0,225);
		ap.rect(103,300,594,132);
		
		ap.line(103, 366, 697, 366);
		for(int i=103; i<697; i+=66){
			ap.line(i,300,i,432);
		}
		
		Countdown.btnConsonant.drawButton();
		Countdown.btnVowel.drawButton();
		
	}
	
	private void drawLetter(){
		int textSize=44;
		roundLetterButtons[nextLetterIndex]=new Button(
				ap,roundLetters[nextLetterIndex].getLetter(),103+nextLetterIndex*66,300,66,66,textSize );
		
		
		ap.fill(255);
		roundLetterButtons[nextLetterIndex].drawButton();
		if(nextLetterIndex==8){
			
			Countdown.btnVowel.toggleButton();
			Countdown.btnConsonant.toggleButton();			
			Countdown.btnDel.toggleButton();
			Countdown.btnClear.toggleButton();
			Countdown.btnSelectWord.toggleButton();
			
			addCP5Controls();
			
			
			for(int i=0; i<9; i++){
				roundLetterButtons[i].toggleButton();
			}
		}
	}
	public void addCP5Controls(){
		Countdown.cp5.addTextfield("wordInput")
		    .setPosition(370,480)
	        .setSize(330,70)
	        .setFont(Countdown.font)
	        .setFocus(true)
	        .setCaptionLabel("Type words here")
		    .setColor(ap.color(255,255,255))
		    ;
		
		Countdown.cp5.addBang("wordEntered")
			.setLabel("Enter Word")
		    .setPosition(700,480)
	        .setSize(80,70)
			;
		
		Countdown.cp5.addTextlabel("yourWord")
			.setPosition(697, 366)
	        .setSize(100,66)
	        .setFont(Countdown.font)
	        .setText("Your Word")
		    //.setColor(ap.color(255,255,255))
			
			;
	}
	
	

	
	
	public void pickLetter(String letter){
		
		selectedLetterButtons[nextSelectedLetterIndex]=new Button(ap,letter.toUpperCase(),
																  103+nextSelectedLetterIndex*66,
																  366,66,66,44);
	
		selectedLetterButtons[nextSelectedLetterIndex].drawButton();
		selectedLetterButtons[nextSelectedLetterIndex].toggleButton();

		if(nextSelectedLetterIndex<8){
			nextSelectedLetterIndex++;
		}
	}
	
	public void deleteLetter(){
		if(selectedLetterButtons[8].btnText!=""){
			selectedLetterButtons[8].btnText="";
			selectedLetterButtons[8].drawButton();
		}
		else if(nextSelectedLetterIndex>0){
			nextSelectedLetterIndex--;
			selectedLetterButtons[nextSelectedLetterIndex].btnText="";
			selectedLetterButtons[nextSelectedLetterIndex].drawButton();
		}
		
	}
	
	public void clearLetters(){
		for(int i=0; i<9; i++){
			if(selectedLetterButtons[i].btnText!=""){
				selectedLetterButtons[i].btnText="";
				selectedLetterButtons[i].drawButton();
			}
		}
		nextSelectedLetterIndex=0;
	}
	
	public void selectWord(){
		String word="";
		for(int i=0; i<9; i++){
			word+=selectedLetterButtons[i].btnText;
			
		}
		
		p1Word=new Word(word);
		p2Word=new Word(word);
		//if(timeUp){
			calculateScores();
			ap.stroke(0);
			String valid="is not a valid word";
			if(wordChecker.validWord(p1Word.word)){
				valid="is a valid word";
			}
			ap.text("selected word: "+ p1Word.getWord()+" ("+getPlayerOneScore()+")"+valid,300,50);
			
		//}
	}
	
	public void testScore(){
		startTimer();
		showTimer();
		
	}
	
	public void showTimer(){
		ap.fill(255);
		ap.rect(0,0,100,100);
		ap.fill(0);
		ap.text(getTimer(),55,55);
		
		
	}
	
	

	

	
	

	
	
	
	
}
