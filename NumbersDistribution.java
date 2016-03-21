package kevOOP;

import java.util.ArrayList;
import java.util.Random;

public class NumbersDistribution{
	ArrayList<LargeNumber> largeNumbers =new ArrayList<LargeNumber>();
	ArrayList<SmallNumber> smallNumbers=new ArrayList<SmallNumber>();
	
	
	public NumbersDistribution(){
	
		//Add 25,50,75 and 100 to large numbers list
		for(int i=25; i<101; i+=25){
			LargeNumber n=new LargeNumber(i);
			largeNumbers.add(n);
			largeNumbers.add(n);
		}
		
		//Add 2 each of the numbers 1-10 to small numbers list
		for(int i=1; i<11; i++){
			SmallNumber n=new SmallNumber(i);
			smallNumbers.add(n);
			smallNumbers.add(n);
		}
		
	}
	
	//Select a random large number and remove it from list
	public LargeNumber randomLargeNumber(){
		LargeNumber picked=null;
		
		Random random=new Random();
		int randomLarge=random.nextInt(largeNumbers.size());
		
		picked= largeNumbers.get(randomLarge);
		largeNumbers.remove(picked);
		
		return picked;
		
	}
	
	//Select a random small number and remove it from list
	public SmallNumber randomSmallNumber(){
		SmallNumber picked=null;
		
		Random random=new Random();
		int randomSmall=random.nextInt(smallNumbers.size());
		
		picked= smallNumbers.get(randomSmall);
		smallNumbers.remove(picked);
		
		return picked;
		
	}
}
