package kevOOP;

import processing.core.PApplet;

public class ConundrumRound extends GameRound{
	private String jumbledLetters;
	private String solution;
	PApplet ap;
	public ConundrumRound(PApplet applet, int roundNumber) {
		ap=applet;
		setRoundNumber(roundNumber);
		setRoundTitle("Conundrum");
	}



	public void setConundrum(String jumbled, String sol){
		jumbledLetters=jumbled;
		solution=sol;
		
	}
	
	

	public String getJumbledLetters(){
		return jumbledLetters;
	}
	
	public String getsolution(){
		return solution;
	}



	@Override
	void drawRoundLayout() {
		// TODO Auto-generated method stub
		
	}
	public void showTimer(){
		ap.text(getTimer(),55,55);
	}



	@Override
	int getPlayerChoosing() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	void calculateScores() {
		// TODO Auto-generated method stub
		
	}



	@Override
	void drawRoundBasic() {
		// TODO Auto-generated method stub
		
	}
	
	
}
