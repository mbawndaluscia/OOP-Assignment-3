package kevOOP;

public class Player {
	String playerName;
	int playerNumber;
	int score;
	
	public Player(){
		
	}
	
	public Player(String pName, int pNum){
		playerName=pName;
		playerNumber=pNum;
	}
	
	public String getName(){
		return playerName;
		
	}
	public int getNumber(){
		return playerNumber;
		
	}
	
	public int getScore(){
		return score;
	}
	
	public void updateScore(int i){
		score+=i;
	}
}
