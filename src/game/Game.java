package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.JFrame;

public class Game extends JFrame{
	private Launcher tank;
	private Target target;
	private Background background;
	private Obstacle obstacle;
	private ControlPanel control;
	private boolean challenge;
	private Challenge challengeGui;
	
	public Game(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800,700));
		setLayout(new GridLayout(2,0));
		tank = new Launcher(0,0);
		background = new Background(800,350, tank);
		control = new ControlPanel(tank);
		add(background);
		add(control);
	}
	public boolean collisionDetection(Projectile p){
		return false;
	}
	
	public void setTarget(Target t){
		target = t;
	}
	public static void main(String[] args){		
		Game game = new Game();
		game.setVisible(true);
	}
}
