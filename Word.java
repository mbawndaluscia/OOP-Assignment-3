package kevOOP;

public class Word {

	String word;
	int length;
	int value;
	boolean inDictionary;
	
	
	public Word(String s){
		word =s;
		length=word.length();
		setValue();
	}
	
	public int getLength(){
		return length;
	}
	
	private void setValue(){
		if(inDictionary){
			if(length==9){
				value=18;
			}else{
				value=length;
			}
		}else{
			value=0;
		}
		
	}
}
