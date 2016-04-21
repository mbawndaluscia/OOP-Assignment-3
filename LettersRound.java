package kevOOP;

import java.util.List;
import java.util.Random;

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
	
	 Random rand;
	//words chosen by each player
	Word p1Word,p2Word;
	
	boolean p1Valid,p2Valid;
	
	
	
	//word checker object
	WordChecker wordChecker;
	WordFinder wordFinder;
	int playerChoosing;
	
	
	
	public LettersRound(PApplet applet, int roundNumber){
		letterDistribution=new LettersDistribution();
		ap=applet;
		cp5=Countdown.cp5;
		rand=new Random();
		p1Word=new Word("");
		p2Word=new Word("");
		
		for(int i=0; i<9; i++){
			roundLetterButtons[i]=new Button();
			selectedLetterButtons[i]=new Button();
			Countdown.buttons.add(roundLetterButtons[i]);
			Countdown.buttons.add(selectedLetterButtons[i]);
		}
		setRoundNumber(roundNumber);
		setRoundTitle("Letters Round");
		if(getRoundNumber()==1||
		   getRoundNumber()==4||
		   getRoundNumber()==7||
		   getRoundNumber()==10||
		   getRoundNumber()==12){
			playerChoosing=1;
			
		}else{
			playerChoosing=2;
			

		}
		
		
		
	}
	
	public void pickAILetters(){
		int[]selection=new int[9];
		selection=pickRandomLetters();
		for(int i:selection){
			if(i==0){
				addConsonant();
			}else{
				addVowel();
			}
		}
		Countdown.btnDel.show();
		Countdown.btnClear.show();
		Countdown.btnConfirmWord.show();
		
	}
	public int[] pickRandomLetters(){
		int numVowels=3;
		numVowels +=rand.nextInt(3);
		int[] selection=new int[9];
		for(int i =0; i<numVowels; i++ ){
			selection[i]=1;
		}
		selection=shuffleArray(selection); 
		
		return selection;
	}
	
	private int[] shuffleArray(int[] array){
		  
	    
	    for (int i = array.length - 1; i > 0; i--)
	    {
	      int index = rand.nextInt(i + 1);
	      int temp = array[index];
	      array[index] = array[i];
	      array[i] = temp;
	    }
	    return array;
	 }
	public void addVowel(){
		if(countVowels<maxVowels&&nextLetterIndex<9){
			roundLetters[nextLetterIndex]=letterDistribution.randomVowel();
			drawLetter();
			nextLetterIndex++;
			countVowels++;
		}
		if(nextLetterIndex==9){
			showTimer();
			startTimer();
			Countdown.btnDel.show();
			Countdown.btnClear.show();
			Countdown.btnConfirmWord.show();
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
			showTimer();
			startTimer();
			Countdown.btnDel.show();
			Countdown.btnClear.show();
			Countdown.btnConfirmWord.show();
		}
	}
	
	public Letter[] getSelection(){
		return roundLetters;
	}
	
	public void calculateScores(){
		
		//word checker and finder objects
		wordChecker=new WordChecker(roundLetters);
		wordFinder=new WordFinder(roundLetters);
		String p2SelectedWord="";
		p2SelectedWord=wordFinder.getAllWordsFound().get(Countdown.aiPlayer.pickRandomWord());
		p2Word=new Word(p2SelectedWord);
		
		

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
	
	String p1ValidTxt="Valid",p2ValidTxt="Valid";
	if(!p1Valid)
		p1ValidTxt="Invalid!";
	if(!p2Valid)
		p2ValidTxt="Invalid!";
	Countdown.lblP1Word
	    .setText(p1Word.getWord()+" ("+p1Word.value+") " + p1ValidTxt)
		.setPosition(ap.width/2-60,10)
		.setFont(Countdown.font)
		;
	 Countdown.lblP2Word
	    .setText(p2Word.getWord()+" ("+p2Word.value+") " + p2ValidTxt)
		.setPosition(ap.width/2-60,60)
		.setFont(Countdown.font)
		;
	 
	 ap.fill(255,140,0);
	 ap.stroke(255,140,0);
	// ap.rect(ap.width/2-110,10,50,100);
	 Countdown.lblP1Score
		.setText(PApplet.str(Countdown.humanPlayer.getScore()));
	 Countdown.lblP2Score
		.setText(PApplet.str(Countdown.aiPlayer.getScore()));
	 
	 Countdown.btnNextRound.show();
	 Countdown.btnDel.hide();
	 Countdown.btnClear.hide();
	 Countdown.btnConfirmWord.hide();
	 Countdown.wordInput.setVisible(false);
	 Countdown.wordEntered.setVisible(false);
	 
	 List<String>s=wordFinder.getAllWordsFound().subList(0, 5);
	 Countdown.goodWordsList.setVisible(true)
	 						.addItems(s)
	 				 
	 			;
	}
	
	void drawRoundLayout(){
		
		
		drawBoard();
		if(playerChoosing==1){
			Countdown.btnVowel.show();
			Countdown.btnConsonant.show();
		}else{
			Countdown.btnVowel.hide();
			Countdown.btnConsonant.hide();
			
		}
		Countdown.btnDel.hide();
		Countdown.btnClear.hide();
		Countdown.btnConfirmWord.hide();
		
		showLetters();
	
		Countdown.lblP1Word.setText("");
		Countdown.lblP2Word.setText("");
		Countdown.lblTargetNumber.setText("");
	}
	public void drawBoard(){
		ap.fill(0,0,225);
		ap.stroke(210);
		ap.rect(103,300,594,132);
		
		ap.line(103, 366, 697, 366);
		
		
		for(int i=103; i<697; i+=66){
			ap.line(i,300,i,432);
		}
	}
	private void drawLetter(){
		int textSize=44;
		roundLetterButtons[nextLetterIndex]=new Button(
				ap,roundLetters[nextLetterIndex].getLetter(),103+nextLetterIndex*66,300,66,66,textSize );
		
		
		ap.fill(255);
		roundLetterButtons[nextLetterIndex].show();
		roundLetterButtons[nextLetterIndex].drawButton();
	
		if(nextLetterIndex==8){
			
			Countdown.btnVowel.hide();
			Countdown.btnConsonant.hide();			
			Countdown.btnDel.show();
			Countdown.btnClear.show();
			Countdown.btnConfirmWord.show();
			
			addCP5Controls();
			
			
			showLetters();
		}
	}
	public void addCP5Controls(){
		
		Countdown.wordInput
		    .setPosition(370,480)
	        .setSize(330,70)
	        .setFont(Countdown.font)
	        .setFocus(true)
	        .setCaptionLabel("Type words here")
		    .setColor(ap.color(255,255,255))
		    .show()
		    .setVisible(true);
			
		    ;
		Countdown.wordEntered
			.setLabel("Enter Word")
		    .setPosition(700,480)
	        .setSize(80,70)
	        .show()
	        .setVisible(true);
			;
		
		Countdown.goodWordsList
			.setLabel("Good Words")
		    .setPosition(700,300)
	        .setSize(80,200)
	        .show()
	        .setVisible(true);
			;
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
		
		setCurrentWord();
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
		setCurrentWord();
	}
	
	public void clearLetters(){
		for(int i=0; i<9; i++){
			if(selectedLetterButtons[i].btnText!=""){
				selectedLetterButtons[i].btnText="";
				selectedLetterButtons[i].drawButton();
			}
		}
		nextSelectedLetterIndex=0;
		setCurrentWord();
	}
	
	private void setCurrentWord(){
		String p1SelectedWord="";
		
		for(int i=0; i<9; i++){
			p1SelectedWord+=selectedLetterButtons[i].btnText;
			
		}
		
		p1Word=new Word(p1SelectedWord);
		
	}
	
	public void confirmWord(){
		setCurrentWord();
		
		stopTimer();
		calculateScores();
	}
	
	
	
	public void showTimer(){
		ap.fill(255);
		ap.rect(0,300,100,132);
		ap.fill(0);
		ap.textSize(48);
		ap.text(getTimer(),50,376);
		if(getTimer()==0){
			
			stopTimer();
			setCurrentWord();
			calculateScores();
			Countdown.btnNextRound.active=true;
			
		}
		
	}
	
	public int getPlayerChoosing(){
		return playerChoosing;
	}

	public void showLetters() {
		for(int i=0; i<9; i++){
			roundLetterButtons[i].show();
			roundLetterButtons[i].drawButton();
			selectedLetterButtons[i].show();
			selectedLetterButtons[i].drawButton();
		}
	}

	
	void drawRoundBasic() {
		drawBoard();
		showLetters();
	}
	
	

	

	
	

	
	
	
	
}
