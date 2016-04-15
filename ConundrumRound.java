package kevOOP;

import processing.core.PApplet;

public class ConundrumRound extends GameRound{
	private String jumbledLetters;
	private String solution;
	PApplet ap;
	public ConundrumRound(PApplet applet) {
		ap=applet;
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
	
	
}
