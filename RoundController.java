package kevOOP;
import processing.core.PApplet;
public class RoundController {
	PApplet ap;
	
	//array of 15 game rounds
	private GameRound[] rounds;
	
	//letters rounds
	private LettersRound round1,round2,round4,round5,round7,
						round8,round10,round11,round12,round13;
	
	//numbers rounds
	private NumbersRound round3,round6,round9,round14;
	
	
	//conundrum round
	private ConundrumRound round15;
	
	//counter for current round
	private int currentRound;

	//constructor
	public RoundController(PApplet applet){
		ap=applet;
		//initialise rounds
		round1=new LettersRound(ap);
		round2=new LettersRound(ap);
		round4=new LettersRound(ap);
		round5=new LettersRound(ap);
		round7=new LettersRound(ap);
		round8=new LettersRound(ap);
		round10=new LettersRound(ap);
		round11=new LettersRound(ap);
		round12=new LettersRound(ap);
		round13=new LettersRound(ap);
		
		round3=new NumbersRound(ap);
		round6=new NumbersRound(ap);
		round9=new NumbersRound(ap);
		round14=new NumbersRound(ap);
		
		round15=new ConundrumRound(ap);
		
		rounds=new GameRound[]{round1,round2,round3,round4,round5,
							   round6,round7,round8,round9,round10,
							   round11,round12,round13,round14,round15};
		
		currentRound=1;
	}
	
	//increment round counter
	public void nextRound(){
		currentRound+=1;
	}
	
	
	//get the current round
	public GameRound currentRound(){
		return(rounds[currentRound-1]);
	}
	
	//get current round counter
	public int getCurrentRoundNumber(){
		return currentRound;
	}
}