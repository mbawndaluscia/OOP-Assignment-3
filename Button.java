package kevOOP;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button {
	PApplet ap;
	String btnText;
	float xLoc,yLoc;
	float btnWidth, btnHeight;
	int textSize;
	boolean active;
	
	public Button(){
		
		btnText="";
	}
	
	public Button(PApplet applet, String text,float x,float y, float w, float h, int _textSize ){
		ap=applet;
		btnText=text;
		xLoc=x;
		yLoc=y;
		btnWidth=w;
		btnHeight=h;
		active=false;
		textSize=_textSize;
		
	}
	
	public void drawButton(){
		if(ap!=null){
			ap.fill(0,0,225);
			ap.stroke(210);
			ap.rect(xLoc, yLoc, btnWidth, btnHeight);
			ap.textAlign(PConstants.CENTER);
			ap.fill(255);
			ap.textSize(textSize);
			if(btnText.contentEquals("0")){
				ap.text("", xLoc+btnWidth/2, yLoc+btnHeight*2/3);
			}else{
				ap.text(btnText, xLoc+btnWidth/2, yLoc+btnHeight*2/3);
			}
			
		}
	}
	
	public void toggleButton(){
		active=!active;
		
	}
	
	public void hide(){
		active=false;
		
	}
	
	public void show(){
		active=true;
		
	}
	
	public boolean active(){
		return active;
	}
	
	public boolean clicked(int x, int y){
		if(x>=xLoc && x<=xLoc+btnWidth &&
		   y>=yLoc && y<=yLoc+btnHeight &&
		   this.active()){
			return true;
		}
		return false;
	}
	
}
