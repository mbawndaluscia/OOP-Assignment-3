package kevOOP;


abstract class GameRound {
	
	private String roundTitle;
	
	private int roundNumber;
	
	private int playerOneScore;
	
	private int playerTwoScore;
	
	protected int timer;
	
	protected boolean timerStarted;

	protected boolean timeUp;

	
	
	protected void setRoundNumber(int x){
		roundNumber=x;
	}
	
	public int getRoundNumber(){
		return roundNumber;
	}
	
	public void setPlayerScores(int player, int score) {
		
		//player one wins
		if(player==1){
			playerOneScore=score;
			playerTwoScore=0;
		
		//player two wins
		}else if (player==2){
			playerTwoScore=score;
			playerOneScore=0;
			
		//draw
		}else{
			playerOneScore=playerTwoScore=score;
			
		}
		Countdown.humanPlayer.updateScore(playerOneScore);
		Countdown.aiPlayer.updateScore(playerTwoScore);

	}

	public int getPlayerOneScore(){
		return playerOneScore;
	}
	
	public  int getPlayerTwoScore(){
		return playerTwoScore;
	}
	
	public void startTimer(){
		timer=30;
		timerStarted=true;
	}
	
	public void stopTimer(){
		timer=30;
		timerStarted=false;
	}
	
	public void incrementTimer(){
		if(timer>0){
			timer-=1;
		}else{
			timeUp=true;	
			calculateScores();
		}
	}
	
	public int getTimer(){
		return timer;
	}
	
	public abstract void showTimer();
	
	public boolean timerStarted(){
		return timerStarted;
	}
	
	public void setRoundTitle(String title){
		roundTitle=title;
	}
	
	public String getRoundTitle(){
		return roundTitle;
	}
	
	abstract void calculateScores();
	
	abstract void drawRoundLayout();
	
	abstract void drawRoundBasic();
	
	abstract int getPlayerChoosing();
}
