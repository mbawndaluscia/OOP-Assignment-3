package kevOOP;

import processing.core.PApplet;
import processing.core.PConstants;

public class Button {
	PApplet ap;
	String btnText;
	float xLoc,yLoc;
	float btnWidth, btnHeight;
	
	boolean active;
	
	public Button(PApplet applet, String text,float x,float y, float w, float h ){
		ap=applet;
		btnText=text;
		xLoc=x;
		yLoc=y;
		btnWidth=w;
		btnHeight=h;
	}
	
	public void drawButton(){
		ap.rect(xLoc, yLoc, btnWidth, btnHeight);
		ap.textAlign(PConstants.CENTER);
		ap.text(btnText, xLoc+btnWidth/2, yLoc+btnHeight*2/3);
	}
	
	public void toggleButton(){
		active=!active;
	}
	
	public boolean clicked(int x, int y){
		if(x>=xLoc && x<=xLoc+btnWidth &&
		   y>=yLoc && y<=yLoc+btnHeight){
			return true;
		}
		return false;
	}
	
}
