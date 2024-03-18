package Main;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Ball {
	
		
	private double x,y,dx,dy;
	private int ballSize=30;
	
public Ball() {
	x=200;
	y=200;
	dx=1; 
	dy=3;
}
public void update() {
	setPosition();
}

public void setPosition() {
    x += dx;
    y += dy;
    if (x < 0 || x > BBMain.width - ballSize) {
        dx = -dx;
    }
    if (y < 0 || y > BBMain.height - ballSize) {
        dy = -dy;
    }


//	if(x<0) {
//		x= -dx;
//	}
//	if(y<0) {
//		y= -dy;
//	}
//	if(x>BBMain.width-ballSize) {
//		dx= -dx;
//	}
//	if(y>BBMain.width-ballSize) {
//		dy= -dy;
//	}
	
}

public void draw(Graphics2D g) {
	
//	Graphics2D g2d = (Graphics2D) g;
    g.setColor(Color.DARK_GRAY);
    g.setStroke(new BasicStroke(4));
    g.drawOval((int)x, (int)y, ballSize, ballSize);


}

public Rectangle getRect() {
	return new Rectangle((int)x,(int)y,ballSize,ballSize);
	
}
public void setDy(double theDy) {
	dy=theDy;
}
public double getDy() {
	return dy;
}

public void setDx(double theDx) {
	dx=theDx;
}
public double getDx() {
	return dx;
}
public double getX() {
	return x;
}
public boolean youLoose() {
	boolean looser=false;
	if(y>BBMain.height-ballSize*2) {
		looser=true;
	}
	return looser;
}
}
