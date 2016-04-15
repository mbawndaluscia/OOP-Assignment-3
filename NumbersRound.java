package kevOOP;

import java.util.Random;

import processing.core.PApplet;

public class NumbersRound extends GameRound{
	PApplet ap;
	
	//array of 6 numbers chosen for round
	private int[] roundNumbers=new int[6];
	
	//target number
	private int targetNumber;
	
	//counter for numbers chosen
	private int countPicked=0;
	
	//number distribution object for this round
	NumbersDistribution numDist;
	
	//number achieved by each player
	private int playerOneAnswer, playerTwoAnswer;
	
	//text of solution for each player
	private String[] playerOneSolution,playerTwoSolution;

	//constructor
	public NumbersRound(PApplet applet){
		ap=applet;
		numDist=new NumbersDistribution();
	}
	
	//randomly set number from 101-999
	public void setTargetNumber(){
		
		Random rand=new Random();
		
		int target=101;
		
		target+=rand.nextInt(899);
		
		targetNumber=target;
	}
	
	//add a large number to selection
	public void addLargeNumber(){
		roundNumbers[countPicked]=numDist.randomLargeNumber().getNumber();
		
	}
	
	//add a small number to selection
	public void addSmallNumber(){
		roundNumbers[countPicked]=numDist.randomSmallNumber().getNumber();
		
	}
	
	//return list of numbers chosen
	public int[] getRoundNumbers(){
		return roundNumbers;
	}
	
	//calculate points to be awarded to each player
	public void calculateScores(){
		
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
		
		
	}

	@Override
	void drawRoundLayout() {
		// TODO Auto-generated method stub
		
	}
	
	public void showTimer(){
		ap.text(getTimer(),55,55);
	}
	

	

	
	

	
	
	
	
	
}
