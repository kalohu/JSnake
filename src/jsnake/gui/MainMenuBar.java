package jsnake.gui;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import jsnake.Game;
import jsnake.interfaces.Animator;
import jsnake.interfaces.Iterator;

public class MainMenuBar extends JMenuBar implements ActionListener {
	
	private Animator animator;
	private Iterator iterator;
	private Game game;

	private JMenu fileMenu;
	private JMenuItem exitMenuItem;
	private JMenu gameMenu;
	private JMenuItem startMenuItem;
	private JMenuItem stopMenuItem;

	public MainMenuBar() {
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
			game.newGame();
			iterator.startStep();
		}
		else if (actionEvent.getSource() == stopMenuItem) {
			iterator.stopStep();
		}
		else if (actionEvent.getSource() == exitMenuItem) {
			System.exit(0);
		}
	}
	
	public void addAnimator(Animator animator) {
		this.animator = animator;
	}

	public void addIterator(Iterator iterator) {
		this.iterator = iterator;
	}
	
	public void addGame(Game game) {
		this.game = game;
	}

}
