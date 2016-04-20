package kevOOP;

import java.util.Random;

public class AIPlayer extends Player {
	int letterSkill;
	int numberSkill;
	static Random rand;
	
	public AIPlayer(){
		rand=new Random();
	}
	public AIPlayer(String pName, int pNum, int _letterSkill, int _numberSkill ) {
		super(pName, pNum);
		letterSkill=_letterSkill;
		numberSkill=_numberSkill;
		rand=new Random();
	}
	
	public int pickRandomWord(){
		
		
		int x=rand.nextInt(20*letterSkill);
		
		return x;
		
	}
	

	
}
