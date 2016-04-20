package kevOOP;




import java.util.ArrayList;
import controlP5.*;
import processing.core.PApplet;
import processing.core.PFont;



public class Countdown extends PApplet{
	static ControlP5 cp5;
	static PFont font;
	RoundController roundController;
	static ArrayList<Button> buttons=new ArrayList<Button>();
	static Button btnStartGame, btnGameRules;
	static Button btnConsonant, btnVowel;
	static Button  btnDel, btnClear, btnConfirmWord;
	static Button btnNextRound;
	static Textfield nameInput;
	static ListBox opponentList;
	static ListBox numberSelectList;
	static Textfield wordInput;
	static Bang wordEntered;
	static Textlabel lblP1Name, lblP2Name; 
	static Textlabel lblP1Score, lblP2Score;
	static Textlabel lblRoundTitle;
	static Textlabel lblP1Word;
	static Textlabel lblP2Word;
	boolean gameStarted, playersChosen;
	static Player humanPlayer;
	static AIPlayer aiPlayer;
	String[] opponents=new String[]{"Dim Dave","Brainy Bryan","Numbers Norm"};
	int timer=0;
	int pChoosing;
	public void setup(){
		
		 background(255,140,0);
		 font = createFont ("Serif",32,true);
		 textFont (font);
		 cp5=new ControlP5(this);
		
	}
	
	public void settings(){
	    size(800,600); 
	   
	}
	public Countdown(){
		
		roundController=new RoundController(this);
		humanPlayer=new Player();
		aiPlayer=new AIPlayer();
		loadButtons();
	}
	
	
	
	private void loadButtons(){
		int textSize=22;
		
		btnStartGame=new Button(this,"New Game",50,510,120,40,textSize);
		btnGameRules=new Button(this,"Game Rules",50,550,120,40,textSize);
		btnConsonant=new Button(this,"Consonant",103, 432,132,40,textSize);
		btnVowel=new Button(this,"Vowel",235, 432,132,40,textSize);
		btnDel=new Button(this,"Del",367, 432,66,40,textSize);
		btnClear=new Button(this,"Clear",433, 432,132,40,textSize);
		btnConfirmWord=new Button(this,"Confirm Word",565, 432,132,40,textSize);
		btnNextRound=new Button(this,"Next Round",675,200,120,40,textSize);
		buttons.add(btnStartGame);
		btnStartGame.active=true;
		buttons.add(btnGameRules);
		btnGameRules.active=true;
		buttons.add(btnConsonant);
		buttons.add(btnVowel);
		buttons.add(btnDel);
		buttons.add(btnClear);
		buttons.add(btnConfirmWord);
		buttons.add(btnNextRound);
		
	}
	
	
	
	
	
	public void draw(){
		
		if(! playersChosen){
			
			showOpeningScreen();
			
		}else{
		fill(255,140,0);
		stroke(255,140,0);
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
		background(255,140,0);
		stroke(0,0,255);
		noFill();
		strokeWeight(6);
		ellipse(width/2,height/2,width/2,width/2);
		stroke(210);
		ellipse(width/2,height/2,width/2+12,width/2+12);
		Button []titleButtons = new Button[9];
		String[] cDownLetters=new String[]{"C","O","U","N","T","D","O","W","N"};
		for(int i=0; i<9; i++){
			int textSize=44;
			titleButtons[i]=new Button(
					this,cDownLetters[i],103+i*66,277,66,66,textSize );
			
			
			fill(255);
			titleButtons[i].drawButton();
		}
		
		btnStartGame.drawButton();
		btnGameRules.drawButton();
		
		gameStarted=true;
		
		
	}
	
	private void addNameEntry(){
	   nameInput=cp5.addTextfield("nameInput")
	    .setPosition(175,510)
        .setSize(200,60)
        .setFont(Countdown.font)
        .setFocus(true)
        .setCaptionLabel("Enter Your Name")
	    .setColor(color(255))
	    
	    ;
	   
	   cp5.getController("nameInput")
	     .getCaptionLabel()
	     .setFont(font)
	     .setColor(0)
	     .setSize(20)
	     ;
	
	   
	  
	 
	   opponentList=cp5.addListBox("chooseOpponent")
			  .setLabel("Choose Opponent")
			  .setPosition(450,510)
		      .setSize(100,120)
		      .addItems(opponents)
		      
			  ;
	   
	   nameInput.keepFocus(true);
	}
	
	
	
	
	public void mousePressed(){
		for(Button button: buttons){
			if(button.clicked(mouseX, mouseY)){
				
				if(button==btnStartGame){
					if(!playersChosen){
						if(gameStarted){
							playersChosen=true;
						}
						addNameEntry();
						btnStartGame.btnText="Start Game";
						btnStartGame.drawButton();
					}else{
						
						startGame();
					}
				}
				
				if(button==btnNextRound){
					//btnNextRound.hide();
					roundController.nextRound();
					nextRound();
				}
				
				if(button==btnConsonant){
					((LettersRound) roundController.currentRound()).addConsonant();
				}else if(button==btnVowel){
					((LettersRound) roundController.currentRound()).addVowel();
				}else if (button==btnDel){
					((LettersRound) roundController.currentRound()).deleteLetter();
				}else if (button==btnClear){
					((LettersRound) roundController.currentRound()).clearLetters();
				}else if (button==btnConfirmWord){
					((LettersRound) roundController.currentRound()).confirmWord();
				}
				
			}
		}
		if(roundController.currentRound() instanceof LettersRound){
			for(Button letterButton:  ((LettersRound)roundController.currentRound()).roundLetterButtons){
				if(letterButton.clicked(mouseX, mouseY)){
					((LettersRound)roundController.currentRound()).pickLetter(letterButton.btnText);
				}
			}
		}
		
	}
	public void wordEntered(){
		
		LettersRound lr=((LettersRound)roundController.currentRound());
		lr.clearLetters();
		String word=cp5.get(Textfield.class,"wordInput").getText();
		for(int i=0; i<word.length(); i++){
			lr.pickLetter(word.substring(i, i+1));
		}
		
	}
	
	public void startGame(){
		btnStartGame.hide();
		String p1Name,p2Name;
		p1Name=nameInput.getText();
		if(p1Name.length()==0){
			p1Name="Player 1";
		}
		    
		
		p2Name=opponents[(int) opponentList.getValue()];
		nameInput.hide();
		opponentList.hide();
		humanPlayer=new Player(p1Name,1);
		
		int letterSkill = 0,numberSkill=0;
		if((int) opponentList.getValue()==0){
			letterSkill=4;
			numberSkill=4;
		}else if((int) opponentList.getValue()==1){
			letterSkill=1;
			numberSkill=1;
		}else if((int) opponentList.getValue()==2){
			letterSkill=2;
			numberSkill=1;
		}
		aiPlayer=new AIPlayer(p2Name,2,letterSkill,numberSkill);
		addLabels();
		nextRound();
		
	}
		
	
	public void nextRound(){
		pChoosing=roundController.currentRound().getPlayerChoosing();
		System.out.println(pChoosing);
		fill(255,140,0);
		rect(103,300,594,132);
		roundController.currentRound().drawRoundLayout();
		
		if(roundController.currentRound() instanceof LettersRound){
			if(pChoosing==1){
				btnConsonant.show();
				btnVowel.show();
			}else{
				
				
			}
			if(numberSelectList !=null)
				numberSelectList.setVisible(false);
		}
		else if(roundController.currentRound() instanceof NumbersRound){
			if(pChoosing==1){
				numberSelectList.setVisible(true);
			}else{
				numberSelectList.setVisible(false);
			}
			
			btnConsonant.hide();
			btnVowel.hide();
		}
		drawScorePanel();
		
		
		
		if(roundController.currentRound() instanceof LettersRound){
			if(pChoosing==2){
				((LettersRound) roundController.currentRound()).pickAILetters();
			}
		}
	}
	
	
	
	
	
	public void keyPressed(){
		if(key=='\n'){
			if(! playersChosen){
				if(roundController.currentRound() instanceof LettersRound){
					wordEntered();
				}
			}
		}
	}

	public void addLabels(){
		lblRoundTitle=cp5.addLabel("roundTitle");
		lblP1Name=cp5.addLabel("p1Name");
		lblP2Name=cp5.addLabel("p2Name");
		lblP1Score=cp5.addLabel("p1Score");
		lblP2Score=cp5.addLabel("p2Score");
		lblP1Word=cp5.addLabel("p1Word");
		lblP2Word=cp5.addLabel("p2Word");		
		wordInput=cp5.addTextfield("wordInput");
		wordEntered=cp5.addBang("wordEntered")	;
	}
	
	public void drawScorePanel(){
		
		String playerChoosing;
		String round;
		if(roundController.currentRound() instanceof LettersRound){
			round="letters";
		}else{
			round="numbers";
		}
		if(pChoosing==1){
			playerChoosing=humanPlayer.getName();
		}else{
			playerChoosing=aiPlayer.getName();
		}
		fill(255,140,0);
		stroke(255,140,0);

		rect(0,0,800,300);
		lblRoundTitle
				.setText("Round "+roundController.getCurrentRoundNumber()+
						 " : " +roundController.currentRound().getRoundTitle()+
						 "\n" +playerChoosing+" to select "+ round) 
				.setPosition(103, 190)
				.setFont(font)
				;
		
		lblP1Name
				.setText(humanPlayer.getName())
				.setPosition(10, 10)
				.setFont(font)
				;
		lblP2Name
				.setText(aiPlayer.getName())
				.setPosition(10, 60)
				.setFont(font)
				
				;
		lblP1Score
				.setText(str(humanPlayer.getScore()))
				.setPosition(width/2-110,10)
				.setFont(font)
				;
		lblP2Score
				.setText(str(aiPlayer.getScore()))
				.setPosition(width/2-110,60)
				.setFont(font)
				;
				
	}
	

	public static void main(String[] args) {
		  String[] a = {"COUNTDOWN"};
	      PApplet.runSketch( a, new Countdown());

	}

}
