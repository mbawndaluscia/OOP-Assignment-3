package kevOOP;


abstract class GameRound {
	
	private String roundTitle;
	
	private int roundNumber;
	
	private int playerOneScore;
	
	private int playerTwoScore;
	
	private int timer;
	
	
	
	
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
		
	}

	public int getPlayerOneScore(){
		return playerOneScore;
	}
	
	public  int getPlayerTwoScore(){
		return playerTwoScore;
	}
	
	public void startTimer(){
		timer=30;
	}
	
	public void incrementTimer(){
		timer-=1;
	}
	
	public int getTimer(){
		return timer;
	}
	
	public void setRoundTitle(String title){
		roundTitle=title;
	}
	
	public String getRoundTitle(){
		return roundTitle;
	}
	
	abstract void drawRoundLayout();
}
