package Main;

import javax.swing.JFrame;

public class BBMain {
 public static final int width=640, height=480;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame theframe = new JFrame("Brick Breaker"); 

GamePanel thePanel=new GamePanel();
 Thread theThread=new Thread(thePanel);

theframe.setResizable(false);
theframe.setSize(width, height);
theframe.setLocationRelativeTo(null);

theframe.add(thePanel);
theThread.start();
theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
theframe.setVisible(true);
}

}
