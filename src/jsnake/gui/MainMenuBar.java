package jsnake.gui;

import jsnake.SnakeTimer;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import jsnake.interfaces.Animator;

public class MainMenuBar extends JMenuBar implements ActionListener {
	
	private Animator animator;
	private SnakeTimer snakeTimer;

	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	private JMenu gameMenu;
	private JMenuItem startMenuItem;
	private JMenuItem stopMenuItem;

	public MainMenuBar(Animator animator, SnakeTimer snakeTimer) {
		this.animator = animator;
		this.snakeTimer = snakeTimer;
		
		this.add(createFileMenu());
		this.add(createGameMenu());
	}
	
	private JMenu createFileMenu() {
		fileMenu = new JMenu("File");
		
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(this);
		
		fileMenu.add(exitMenuItem);
		
		return fileMenu;
	}
	
	private JMenu createGameMenu() {
		gameMenu = new JMenu("Game");
		
		startMenuItem = new JMenuItem("Start");
		startMenuItem.addActionListener(this);
		stopMenuItem = new JMenuItem("Stop");
		stopMenuItem.addActionListener(this);
		
		gameMenu.add(startMenuItem);
		gameMenu.add(stopMenuItem);
		
		return gameMenu;		
	}
	
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == startMenuItem) {
			animator.resetAnimatedComponents();
			snakeTimer.startStep();
		}
		else if (actionEvent.getSource() == stopMenuItem) {
			snakeTimer.stopStep();
		}
		else if (actionEvent.getSource() == exitMenuItem) {
			System.exit(0);
		}
	}

}
