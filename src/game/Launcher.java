package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import javax.swing.JComponent;

public class Launcher{
	private Point position;
	private Projectile missile;
	private int angle;
	private double initialVelocity;
	private static final int LENGTH_ARM = 30;
	private static final int BARREL_X_ADJ = 30;
	private static final int BARREL_Y_ADJ = -8;
	
	public Launcher(int x, int y){
		angle = 45;
		position = new Point(x, y);
		initialVelocity = 0;
	}
	public void increaseAngle(int a){
		
	}
	public void decreaseAngle(int a){
		
	}
	public void showTrajectory(double percent){
		
	}
	public void draw(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(position.x, position.y, 50, 20);
		g.setColor(Color.darkGray);
		g.fillRect(position.x + 15, position.y - 10, 20, 10);
		g.setColor(Color.GRAY);
		/*
		int[] xPoints = {position.x + 35, (int) (position.x + 35 + LENGTH_ARM*Math.cos(Math.toRadians(angle))), (int) (position.x + 35 + LENGTH_ARM*Math.cos(Math.toRadians(angle + 5))), position.x + 35};
		int[] yPoints = {position.y - 10, (int) (position.y - 10 - LENGTH_ARM*Math.sin(Math.toRadians(angle))),  (int) (position.y - 5 - LENGTH_ARM*Math.sin(Math.toRadians(angle + 5))), position.y - 5};
		g.fillPolygon(xPoints, yPoints, 4);
		*/
		//g.fillRect(x, y, width, height);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		g2.draw(new Line2D.Float(position.x + BARREL_X_ADJ, position.y + BARREL_Y_ADJ, (int)(position.x + BARREL_X_ADJ + LENGTH_ARM*Math.cos(Math.toRadians(angle))), (int)(position.y + BARREL_Y_ADJ - LENGTH_ARM*Math.sin(Math.toRadians(angle)))));
		
	}
	public int getAngle(){
		return angle;
	}
	public void move(Point p){
		position = p;
	}
	
	public void changeAngle(int a){
		angle = a;
	}
	
	public Point getLocation(){
		return position;
	}
	
	public int getX(){
		return position.x;
	}
	
	public int getY(){
		return position.y;
	}
	
	public void increaseVelocity(double v){
		
	}
	public void decreaseVelocity(double v){
		
	}
	public double getInitialVelocity(){
		return initialVelocity;
	}
	
	
}
