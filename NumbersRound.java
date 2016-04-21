package kevOOP;

import java.util.ArrayList;
import java.util.Random;

import processing.core.PApplet;

public class NumbersRound extends GameRound{
	PApplet ap;
	
	//array of 6 numbers chosen for round
	protected int[] roundNumbers=new int[6];
	private ArrayList<Integer> listNumbers=new ArrayList<Integer>();
	public Button[] numberButtons=new Button[6];
	
	//target number
	private int targetNumber;
	
	//counter for numbers chosen
	private int countPicked=0;
	
	//number distribution object for this round
	NumbersDistribution numberDistribution;
	
	//numbersolver object used to find solutions
	NumberSolver numberSolver;
	
	//number achieved by each player
	private int playerOneAnswer, playerTwoAnswer;
	
	//text of solution for each player
	private String[] playerOneSolution,playerTwoSolution;
	
	private int playerChoosing;
	
	private String[] numberSelectOptions;
	
	private String solution="";
	Random rand;

	//constructor
	public NumbersRound(PApplet applet, int roundNumber){
		ap=applet;
		numberDistribution=new NumbersDistribution();
		
		setRoundNumber(roundNumber);
		setRoundTitle("Numbers Round");
		
		for(int i=0; i<6; i++){
			numberButtons[i]=new Button();
			Countdown.buttons.add(numberButtons[i]);
		
		}
		numberSelectOptions=new String[]{"4 Large, 2 Small",
										 "3 Large, 3 Small",
										 "2 Large, 4 Small",
										 "1 Large, 5 Small",
										 "6 Small"};
		
		if(getRoundNumber()==3||
		   getRoundNumber()==9){
			playerChoosing=1;
			
		}else{
			playerChoosing=2;
		}
		rand=new Random();
	}
	
	//randomly set number from 101-999
	public void setTargetNumber(){
		
		
		
		int target=101;
		
		target+=rand.nextInt(899);
		
		targetNumber=target;
	}
	
	//add a large number to selection
	public void addLargeNumber(){
		roundNumbers[countPicked]=numberDistribution.randomLargeNumber().getNumber();
		countPicked++;
	}
	
	//add a small number to selection
	public void addSmallNumber(){
		roundNumbers[countPicked]=numberDistribution.randomSmallNumber().getNumber();
		countPicked++;

	}
	
	//return list of numbers chosen
	public int[] getRoundNumbers(){
		return roundNumbers;
	}
	
	//calculate points to be awarded to each player
	public void calculateScores(){
		playerOneAnswer=Integer.valueOf(Countdown.numberEntered.getText());
		if(Countdown.aiPlayer.numberSkill==1){
			playerTwoAnswer=numberSolver.finalSolution.result;
		}else if(Countdown.aiPlayer.numberSkill==2){
			playerTwoAnswer=numberSolver.aiSolution1.result;
		}else{
			playerTwoAnswer=numberSolver.aiSolution2.result;
		}
		//find how close each player is to target number
		int p1Distance=Math.abs(targetNumber-playerOneAnswer);
		
		int p2Distance=Math.abs(targetNumber-playerTwoAnswer);
		
		//check which player is closest(leave winner=0 if both players equally close)
		int winner=0;
		int winnerDistance=p1Distance;
		
		//p1 closer
		if(p1Distance<p2Distance){
			winner=1;
			winnerDistance=p1Distance;
		//p2 closer
		}else if(p2Distance<p1Distance){
			winner=2;
			winnerDistance=p2Distance;
		}
		
		//Award points to winner(s)
		
		//10 points for finding target
		if(winnerDistance==0){
			setPlayerScores(winner,10);
			
		//7 points for within 5
		}else if(winnerDistance<=5){
			setPlayerScores(winner,7);
			
		//5 points for within 10
		}else if(winnerDistance<=10){
			setPlayerScores(winner,5);
			
		//no points for more than 10 away
		}else{
			setPlayerScores(winner,0);
		}
		
		Countdown.lblP1Score
		.setText(PApplet.str(Countdown.humanPlayer.getScore()));
		Countdown.lblP2Score
		.setText(PApplet.str(Countdown.aiPlayer.getScore()));
	}
	
	public void setNumbers(){
		setTargetNumber();
		int numLarge=0;
		if(playerChoosing==1){
		   numLarge=4-(int)(Countdown.numberSelectList.getValue());
		}else{
		   if(Countdown.aiPlayer.numberSkill==1){
			   numLarge=4;
		   }else{
			   numLarge=4-randomNumberSelection();
		   }
		}
		for(int i=0; i<numLarge; i++){
			addLargeNumber();
		}
		
		for(int i=numLarge; i<6; i++){
			addSmallNumber();
		}
		
		for(int i:roundNumbers){
			listNumbers.add(i);
		}
		
		showNumbers();
		Countdown.lblTargetNumber
			.setText("Target : " + targetNumber)
			.setPosition(600,300)
			.setFont(Countdown.font)
		;
		Countdown.numberEntered
			.setText("Enter best answer")
			.setPosition(600,500)
			.setSize(100,70)
			.setFont(Countdown.font)
			.setVisible(true)
			.setText("0")
			;
		
		numberSolver=new NumberSolver(roundNumbers,targetNumber);
		Countdown.lblSolution
		.setText( numberSolver.getSolution())
		.setPosition(440,390)
		.setFont(Countdown.font)
		.setVisible(false)
		;
		
		showTimer();
		startTimer();
	    
	   
	}
	
	public void showNumbers(){
		for(int i=0; i<6; i++){
			int textSize=44;
			String num=PApplet.str(roundNumbers[i]);
			numberButtons[i]=new Button(
					ap,num,202+i*66,300,66,66,textSize );
			
			
			ap.fill(255);
			numberButtons[i].drawButton();
			
		}
		
	}
	
		
	

	void drawRoundLayout() {
		ap.background(255,140,0);

		if(playerChoosing==1){
		Countdown.numberSelectList=Countdown.cp5.addListBox("numberSelect")
				  .setLabel("Select Numbers")
				  .setPosition(300,400)
			      .setSize(100,120)
			      .addItems(numberSelectOptions)
			      .setDefaultValue(4)
				  ;
				  Countdown.btnPickNumbers.show();
		}
		
		for(int i=0; i<6; i++){
			int textSize=44;
			
			numberButtons[i]=new Button(
					ap,"",202+i*66,300,66,66,textSize );
			
			
			ap.fill(255);
			numberButtons[i].drawButton();
		}
		
		Countdown.btnVowel.hide();
		Countdown.btnConsonant.hide();
		Countdown.btnDel.hide();
		Countdown.btnClear.hide();
		Countdown.btnConfirmWord.hide();
		Countdown.lblP1Word.setText("");
		Countdown.lblP2Word.setText("");
	}
	
	public int randomNumberSelection(){
		int x=0;
		x=rand.nextInt(4);
		
		return x;
	}
	
	public void showTimer(){
		ap.fill(255);
		ap.rect(0,300,100,132);
		ap.fill(0);
		ap.textSize(48);
		ap.text(getTimer(),50,376);
		 
		
			
		
		if(getTimer()==0){
			Countdown.lblSolution.setVisible(true);
			stopTimer();
			calculateScores();
			Countdown.btnNextRound.active=true;
			
		}
		
	}

	
	
	int getPlayerChoosing() {
		return playerChoosing;
	}

	
	void drawRoundBasic() {
		showNumbers();
		
		
		
		
	}
	

	

	
	

	
	
	
	
	
}
