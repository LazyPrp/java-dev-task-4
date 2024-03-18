package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
	
	
	private double x;
	private int width,height,startwidth;
	private long widthTimer;
	public boolean altWidth;
	
	public final int Ypos=BBMain.height-100;
	
public Paddle(int thewidth,int theHeight){
	altWidth=false;
	width=thewidth;
	height=theHeight;
	startwidth=thewidth;
	
	x=BBMain.width/2 - width/2;
}
public void update() {
	if((System.nanoTime()-widthTimer)/1000>4000000 && altWidth==true) {
		width=startwidth;
		altWidth=false;
	}
}

public void draw(Graphics2D g) {
	g.setColor(Color.DARK_GRAY);
	g.fillRect((int)x, Ypos, width, height);
	
	if(altWidth==true) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New",Font.BOLD,18));
		g.drawString("Shrinking in " +(4-(System.nanoTime()-widthTimer)/1000000000), (int)x, Ypos+18);
	}
	
}

public void mouseMoved1(int mouseXpos) {
	x=mouseXpos;
	if(x>BBMain.width-width) {
		x=BBMain.width-width;
	}
} 
public Rectangle getRect() {
	return new Rectangle((int)x,Ypos,width,height);
	
}
public int getWidth() {return width;}

public void setWidth(int newWidth) {
	altWidth=true;
//	width=newWidth;
//	setWidthimer();
	
	int oldWidth = width;
    width=newWidth;
    // Adjust the x position to keep the paddle centered
    x += (oldWidth - width) / 2;
    setWidthimer();
}

public void setWidthimer() {
	widthTimer=System.nanoTime();}
}
