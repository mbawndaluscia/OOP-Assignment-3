package kevOOP;

public class Word {
	
	//Fields
	String word;
	int length;
	int value;
	

	
	//Constructor
	public Word(String s){
		word =s;
		length=word.length();
		setValue();
	}
	
	public String getWord(){
		
		return word;
	}
	
	//Count length of word
	public int getLength(){
		return length;
	}
	
	//Set value of word 
	//(If word is valid, value=length except for 9 letter word=18)
	
	private void setValue(){
		if(length==9){
				value=18;
			}else{
				value=length;
			}
	}
	
	public int getValue(){
		return value;
	
	}
}
