package kevOOP;

import processing.core.PApplet;

public class ConundrumRound extends GameRound{
	private String jumbledLetters;
	private String solution;
	
	public ConundrumRound(PApplet ap) {
		// TODO Auto-generated constructor stub
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
	
	
	
}
