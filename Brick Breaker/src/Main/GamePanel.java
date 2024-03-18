package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMOtionLitener theMouseListener;
	
	private int mouseX; 
	
	private Ball theball;
	private Paddle thePaddle;
	private Map theMap;
	private HUD theHud;
	private ArrayList<PowerUp> powerUps;
	
public GamePanel() {
		init();
	}

public void init() {
	mouseX=0;
	theball=new Ball();
	thePaddle=new Paddle(100,20);
	theMap=new Map(5,8);
	theHud=new HUD();
	theMouseListener=new MyMouseMOtionLitener();
	powerUps=new ArrayList <PowerUp>();
	addMouseMotionListener(theMouseListener);

	
	running=true;
	image=new BufferedImage(BBMain.width, BBMain.height, BufferedImage.TYPE_INT_RGB);
	g=(Graphics2D) image.getGraphics();
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	
}
	@Override
	public void run() {
		
		while(running) {
			update();
			draw();
			repaint();
			try {
				Thread.sleep(10);
			}catch(Exception e) {e.printStackTrace();}
		}
	}
	
	public void checkCollisions() {
		Rectangle ballRect=theball.getRect();
		Rectangle padelRect=thePaddle.getRect();
		
		for(int i=0;i<powerUps.size();i++) {
			Rectangle puRect=powerUps.get(i).getRect();
			
			if(padelRect.intersects(puRect)) {
				if(powerUps.get(i).getType()==PowerUp.widePaddle && !powerUps.get(i).getWasUsed()) {
					
					thePaddle.setWidth(thePaddle.getWidth()*2);
//					thePaddle.setWidthimer();
					powerUps.get(i).setWasUsed(true);
				}
				
			}
		}
		
		if(ballRect.intersects(padelRect)) {
			theball.setDy((-theball.getDy()));
			if(theball.getX() < mouseX+thePaddle.getWidth() / 4 ) {
				theball.setDx(theball.getDx() - .5);
			}
			if(theball.getX() < mouseX + thePaddle.getWidth() && theball.getX()> mouseX + thePaddle.getWidth()/4 ) {
				theball.setDx(theball.getDx() + .5);
			}
		}
//		int [][]theMapArray=theMap.getMapArray();
	A:	for (int row=0;row<theMap.getMapArray().length;row++ ) {
			for (int col=0;col<theMap.getMapArray()[0].length;col++ ) {
				if(theMap.getMapArray()[row][col]>0) {
					int brickx=col * theMap.getBrickWidth() + theMap.hor_Pad;
					int bricky=row * theMap.getBrickHeight() + theMap.Ver_Pad;
					int brickWidth=theMap.getBrickWidth();
					int brickHeight=theMap.getBrickHeight();
					
					Rectangle brickRect=new Rectangle(brickx,bricky,brickWidth,brickHeight);
					if(ballRect.intersects(brickRect)) {
						if(theMap.getMapArray()[row][col]>3) {
							powerUps.add(new PowerUp(brickx, bricky,theMap.getMapArray()[row][col] ,brickWidth, brickHeight));
							theMap.setBrick(row, col, 3);
						}else {
							theMap.hitBrick(row, col);
						}
						
						theMap.hitBrick(row, col);
						theball.setDy( - theball.getDy());
						theHud.addScore(50);
						break A;
					}
				}
			}
		}
	}

public void update() {
	checkCollisions();
	theball.update();
	thePaddle.update();
	
	for(PowerUp pu :powerUps) {
		pu.update();
	}
}
public void draw() {
	//background
g.setColor(Color.WHITE);
g.fillRect(0, 0, BBMain.width, BBMain.height);
	theMap.draw(g);
	theball.draw(g);
	thePaddle.draw(g);
	

	theHud.draw(g);
	drawPowerUps();
	if(theMap.isWinner()==true) {
		drawWin();
		running=false;
	}
	if(theball.youLoose()) {
		drawLooser();
		running=false;

	}
}

public void drawWin() {
	g.setColor(Color.RED);
	g.setFont(new Font("Courier New",Font.BOLD,50));
	g.drawString("Winner !! ", 200, 200);
	
}
public void drawPowerUps() {
	for(PowerUp pu :powerUps) {
		pu.draw(g);
	}
}
public void drawLooser() {
	g.setColor(Color.RED);
	g.setFont(new Font("Courier New",Font.BOLD,50));
	g.drawString("Looser !! ", 200, 200);
}

public void paintComponent(Graphics g) {
   Graphics2D g2= (Graphics2D)g;


   

   
    g2.drawImage(image, 0, 0, BBMain.width,BBMain.height,null);
}
private class MyMouseMOtionLitener implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX=e.getX();
		thePaddle.mouseMoved1(e.getX());
	}}
}