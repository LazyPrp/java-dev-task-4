package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class PowerUp {
private int x,y,dy,type,height,width;
private boolean isOnScreen;
private boolean wasUsed;
private Color color;
public static final int widePaddle=4;
public static final int fastBall=5;
public static final Color WideColor=Color.GREEN;
public static final Color fastColor=Color.RED;


public PowerUp(int xStart,int yStart,int theType,int theWidth,int theHeight) {
	x=xStart;
	y=yStart;
	width=theWidth;
	height=theHeight;
	type=theType;
	
	if(type<4) {type=4;}
	if(type<5) {type=5;}
	
	if(type==widePaddle) {color=WideColor;
	}else if(type==fastBall) {color=fastColor;
	}else {
        // Default color
        color = Color.GREEN;
    }

	
	dy=(int)(Math.random()*6+1);
	wasUsed=false;
}

public void draw(Graphics2D g) {
	g.setColor(color);
	g.fillRect(x, y, width, height);
}
public void update() {
	y+=dy;
	if(y>BBMain.height) {
		isOnScreen=false;
	}
}

public int getX() {
	return x;
}

public void setX(int newX) {
	x = newX;
}

public int getY() {
	return y;
}

public void setY(int newY) {
	 y=newY;
}

public int getDy() {
	return dy;
}

public void setDy(int newDy) {
	dy=newDy;
}

public int getType() {
	return type;
}

public boolean getIsOnScreen() {
	return isOnScreen;
}

public void setIsOnScreen(boolean onScreen) {
	isOnScreen=onScreen;
}

public Rectangle getRect() {
	return new Rectangle(x,y,width,height);
}

public boolean getWasUsed() {
	return wasUsed;
}

public void setWasUsed(boolean used) {
	wasUsed = used;
}

}
