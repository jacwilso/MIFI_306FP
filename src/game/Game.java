package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Game extends JFrame{
	private Launcher tank;
	private Background background;
	private ControlPanel control;
	private boolean challenge;
	private Challenge challengeGui; 
	
	public Game(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(800,450));
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		tank = new Launcher(0,0);
		control = new ControlPanel(tank);
		background = new Background(800,350, tank, control);
		menuBar.add(createFileMenu());
		add(background,BorderLayout.CENTER);
		add(control,BorderLayout.SOUTH);
		addKeyListener(new KeyListener(){
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				//System.out.println(e.getKeyCode());
				if(e.getKeyCode() == 40 || e.getKeyCode() == 83)
					tank.moveAngle(-5);
				if(e.getKeyCode() == 39 || e.getKeyCode() == 68)
					tank.moveTank(5,0);
				if(e.getKeyCode() == 38 || e.getKeyCode() == 87)
					tank.moveAngle(5);
				if(e.getKeyCode() ==37 || e.getKeyCode() == 65)
					tank.moveTank(-5,0);
				if(e.getKeyCode() == 10 || e.getKeyCode() == 32)
					tank.addProjectile();
				control.update();
			}
			public void keyReleased(KeyEvent e) {}
		});
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		setFocusable(true);
	}
	
	private JMenu createFileMenu(){
		JMenu menu = new JMenu("File");
		menu.add(createChallengeItem());
		menu.add(createFileExitItem());
		return menu;
	}
	private JMenuItem createChallengeItem(){
		JMenuItem notes = new JMenuItem("Challenge");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				Challenge challenge = new Challenge(tank, control);
				background.changeTargetPosition(Challenge.getTargetPosition());
				challenge.setVisible(true);
			}
		}
		notes.addActionListener(new MenuItemListener());
		return notes;
	} 
	private JMenuItem createFileExitItem(){
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		}
		item.addActionListener(new MenuItemListener());
		return item;
	}
	
	public static void main(String[] args){	
		Game game = new Game();
		game.setVisible(true);
		JOptionPane intro = new JOptionPane();
		intro.showMessageDialog(game, "You can change the angle of the tank manually or with the up/down arrow keys and change the initial velocity manually.  \nYou can move the tank using the left/right arrow keys and press fire, hit the space bar or press enter to shoot! \nHit the target but not birds or yourself!", "How to play", JOptionPane.INFORMATION_MESSAGE);

	}
}
