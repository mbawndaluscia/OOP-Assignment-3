package kevOOP;




import java.util.ArrayList;

import controlP5.ControlP5;
import controlP5.Textfield;
import processing.core.PApplet;
import processing.core.PFont;



public class Countdown extends PApplet{
	static ControlP5 cp5;
	static PFont font;
	RoundController roundController;
	static ArrayList<Button> buttons=new ArrayList<Button>();
	static Button btnStartGame, btnGameRules;
	static Button btnConsonant, btnVowel;
	static Button  btnDel, btnClear, btnSelectWord;
	boolean gameStarted;
	
	int timer=0;
	public void setup(){
		 background(185);
		 font = createFont ("Serif",48);
		 textFont (font);
		 cp5=new ControlP5(this);
		
	}
	
	public void settings(){
	    size(800,600); 
	   
	}
	public Countdown(){
		
		roundController=new RoundController(this);
		
		loadButtons();
	}
	
	
	
	private void loadButtons(){
		int textSize=22;
		
		btnStartGame=new Button(this,"Start Game",50,510,120,40,textSize);
		btnGameRules=new Button(this,"Game Rules",180,510,120,40,textSize);
		btnConsonant=new Button(this,"Consonant",103, 432,132,40,textSize);
		btnVowel=new Button(this,"Vowel",235, 432,132,40,textSize);
		btnDel=new Button(this,"Del",367, 432,66,40,textSize);
		btnClear=new Button(this,"Clear",433, 432,132,40,textSize);
		btnSelectWord=new Button(this,"Select Word",565, 432,132,40,textSize);
		buttons.add(btnStartGame);
		btnStartGame.active=true;
		buttons.add(btnGameRules);
		btnGameRules.active=true;
		buttons.add(btnConsonant);
		buttons.add(btnVowel);
		buttons.add(btnDel);
		buttons.add(btnClear);
		buttons.add(btnSelectWord);
	}
	
	
	
	
	
	public void draw(){
		 
		if(!gameStarted){
			showOpeningScreen();
			
		}else{
		fill(185);
		rect(103, 432,594,40);
		
		for(Button button:buttons){
			if(button.active())
				button.drawButton();
			}
		}
		
		if(roundController.currentRound().timerStarted()){
			timer++;
			if(timer==60){
				roundController.currentRound().incrementTimer();
				timer=0;
			}
			roundController.currentRound().showTimer();
		}
		
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
					btnStartGame.toggleButton();
					btnConsonant.toggleButton();
					btnVowel.toggleButton();
					gameStarted=true;
				}
				
				if(button==btnConsonant){
					((LettersRound) roundController.currentRound()).addConsonant();
				}else if(button==btnVowel){
					((LettersRound) roundController.currentRound()).addVowel();
				}else if (button==btnDel){
					((LettersRound) roundController.currentRound()).deleteLetter();
				}else if (button==btnClear){
					((LettersRound) roundController.currentRound()).clearLetters();
				}else if (button==btnSelectWord){
					((LettersRound) roundController.currentRound()).selectWord();
				}
				
			}
		}
		
		for(Button letterButton:  ((LettersRound)roundController.currentRound()).roundLetterButtons){
			if(letterButton.clicked(mouseX, mouseY)){
				((LettersRound)roundController.currentRound()).pickLetter(letterButton.btnText);
			}
		}
		
	}
	public void wordEntered(){
		
		LettersRound lr=((LettersRound)roundController.currentRound());
		lr.clearLetters();
		String word=Countdown.cp5.get(Textfield.class,"wordInput").getText();
		for(int i=0; i<word.length(); i++){
			lr.pickLetter(word.substring(i, i+1));
		}
		
	}
	
	public void keyPressed(){
		//if(roundController.currentRound() instanceof LettersRound){
			if(key==13){
				wordEntered();
			}
		//}
	}
	
	public void drawScorePanel(){
		
	}
	

	public static void main(String[] args) {
		  String[] a = {"COUNTDOWN"};
	      PApplet.runSketch( a, new Countdown());

	}

}
