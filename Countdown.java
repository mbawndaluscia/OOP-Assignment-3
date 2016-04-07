package kevOOP;




import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;



public class Countdown extends PApplet{
	PFont font;
	RoundController roundController;
	ArrayList<Button> buttons=new ArrayList<Button>();
	Button btnStartGame, btnGameRules;
	
	boolean gameStarted;
	public void setup(){
		 background(155);
		 font = createFont ("Serif",48);
		 textFont (font);
		 textSize(20);
		
		
	}
	
	public void settings(){
	    size(800,600); 
	   
	}
	public Countdown(){
		roundController=new RoundController(this);
		
		loadButtons();
	}
	
	
	
	private void loadButtons(){
		
		
		btnStartGame=new Button(this,"Start Game",50,510,120,40);
		btnGameRules=new Button(this,"Game Rules",630,510,120,40);
		buttons.add(btnStartGame);
		buttons.add(btnGameRules);
		
		
	}
	
	
	
	
	
	public void draw(){
		if(!gameStarted){
			showOpeningScreen();
			gameStarted=true;
		}
		
		text(roundController.getCurrentRoundNumber(),55,55);
		text(roundController.currentRound().getPlayerOneScore(),85,55);
	}
	
	public void showOpeningScreen(){
		stroke(0,0,255);
		noFill();
		strokeWeight(6);
		ellipse(width/2,height/2,width/2,width/2);
		stroke(210);
		ellipse(width/2,height/2,width/2+12,width/2+12);
		btnStartGame.drawButton();
		btnGameRules.drawButton();
	}
	
	public void mousePressed(){
		for(Button button: buttons){
			if(button.clicked(mouseX, mouseY)){
				
				if(button==btnStartGame){
					roundController.nextRound();
					roundController.currentRound().drawRoundLayout();
				}
			}
		}
	}

	public static void main(String[] args) {
		  String[] a = {"COUNTDOWN"};
	      PApplet.runSketch( a, new Countdown());

	}

}
