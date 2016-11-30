package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Launcher{
	private Point position, justTheTip;
	private int angle, score;
	private double initialVelocity;
	private ArrayList<Projectile> missles;
	private static final int LENGTH_ARM = 15, BARREL_X_ADJ = 30, BARREL_Y_ADJ = -8;
	public static final double GRAVITY = 9.8;
	
	public Launcher(int x, int y){
		angle = 45;
		position = new Point(x, y);
		initialVelocity = 50;
		missles = new ArrayList<Projectile>();
		score = 0;
	}

	public void showTrajectory(Graphics g, int percent){
		g.setColor(Color.RED);
		double vX = initialVelocity*Math.cos(Math.toRadians(angle));
		double vY = initialVelocity*Math.sin(Math.toRadians(angle));
		double tFinal = vY/GRAVITY + Math.sqrt(Math.pow(vY/GRAVITY,2) + 2*(position.y-justTheTip.y)/GRAVITY);
		double x = justTheTip.x + vX * tFinal;
		double y = justTheTip.y -  vY * tFinal + 0.5*GRAVITY*Math.pow(tFinal,2);
		g.fillOval((int)x,(int)y, 15, 15);
		for(int i=1; i<=percent; i++){
			x = justTheTip.x + vX * i*tFinal/(double)100;
			y = justTheTip.y -  vY * i*tFinal/(double)100 + 0.5*GRAVITY*Math.pow(i*tFinal/(double)100,2);
			g.fillOval((int)x,(int)y, 2, 2);
		}
	}

	public void draw(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(position.x, position.y, 50, 20);
		g.setColor(Color.darkGray);
		g.fillRect(position.x + 15, position.y - 10, 20, 10);
		g.setColor(Color.GRAY);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		justTheTip = new Point((int)(position.x + BARREL_X_ADJ + LENGTH_ARM*Math.cos(Math.toRadians(angle))), (int)(position.y + BARREL_Y_ADJ - LENGTH_ARM*Math.sin(Math.toRadians(angle))));
		g2.draw(new Line2D.Float(position.x + BARREL_X_ADJ, position.y + BARREL_Y_ADJ, justTheTip.x, justTheTip.y));
		showTrajectory(g, 40);
		for(Projectile proj : missles){
			if(!proj.isFinished())		// SHOULD CHANGE THIS
				proj.draw(g);
			//if(proj.isFinished())
				//missles.remove(proj);
		}
	}
	
	public void addProjectile(){
		missles.add(new Projectile(justTheTip, position.y, initialVelocity, angle));
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
	
	public void changeVelocity(double v){
		initialVelocity = v;
	}
	
	public double getInitialVelocity(){
		return initialVelocity;
	}
	
	public void moveTank(int x, int y){
		move(new Point(position.x + x, position.y + y));
	}
	public void moveAngle(int a){
		changeAngle(angle + a);
	}

	public boolean collisionDetection(Point target) {
		for(Projectile proj : missles)
			if(proj.collisionDetection(target)){
				//missles.remove(proj);
				score += 100;
				return true;
			}
		return false;
	}
	
	public boolean tankCollisionDetection(Point self) {
		for(Projectile proj : missles)
			if(proj.tankCollisionDetection(self)){
				//missles.remove(proj);
				score -= 100;
				return true;
			}
		return false;
	}

	public String getScore() {
		return new Integer(score).toString();
	}
		
}
