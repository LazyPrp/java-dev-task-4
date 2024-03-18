package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Map {
	

private int [][] theMap;
private int brickHeight,brickWidth;
public final int hor_Pad=80,Ver_Pad=50;
	public Map(int row,int col) {
			initMap(row,col);
			brickHeight=(BBMain.height/2 - Ver_Pad*2)/row;
			brickWidth=(BBMain.width - 2 * hor_Pad)/col;
	}
	
	public void initMap(int row,int col) {
		theMap=new int[row][col];
	for(int i=0;i<theMap.length;i++) {
		for(int j=0;j<theMap[0].length;j++) {
			int r=(int)(Math.random() * 3 + 1);
			theMap[i][j]=r;
		}
	}
	theMap[3][2]=4;
	theMap[3][6]=5;

	}
	
	public void draw(Graphics2D g) {
		
		for(int row=0;row<theMap.length;row++) {
			for(int col=0;col<theMap[0].length;col++) {
				if(theMap[row][col]>0) {
					if(theMap[row][col]==1) {
						g.setColor(new Color(0,200,200));
					}if(theMap[row][col]==2) {
						g.setColor(new Color(0,150,150));
					}if(theMap[row][col]==3) {
						g.setColor(new Color(0,100,100));
					}if(theMap[row][col]==PowerUp.widePaddle) {
						g.setColor(PowerUp.WideColor);
					}if(theMap[row][col]==PowerUp.fastBall) {
						g.setColor(PowerUp.fastColor);
					}
				
//					g.setColor(Color.DARK_GRAY);
					g.fillRect(col*brickWidth + hor_Pad, row*brickHeight + Ver_Pad , brickWidth, brickHeight);
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.WHITE);
					g.drawRect(col*brickWidth + hor_Pad, row*brickHeight + Ver_Pad , brickWidth, brickHeight);
				}
			}	
		}
	}
	
	public boolean isWinner() {
		boolean thereIsAWin=false;
		int bricksRemaining=0;
		for(int row=0;row<theMap.length;row++) {
			for(int col=0;col<theMap[0].length;col++) {
				bricksRemaining+=theMap[row][col];
				
			}
		}
		if (bricksRemaining==0) {
			thereIsAWin=true;
		}
		return thereIsAWin;
	}
	public int[][] getMapArray(){
		return theMap;
	}
	
	public void setBrick(int row,int col,int value) {
		theMap[row][col]=value;
	}

	public int getBrickWidth(){
		return brickWidth;
	}
	
	public int getBrickHeight(){
		return brickHeight;
	}
	public void hitBrick(int row,int col) {
		theMap[row][col]-=1;
		if(theMap[row][col]<0) {theMap[row][col]=0;
	}
	}
}
