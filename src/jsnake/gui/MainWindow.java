package jsnake.gui;

import jsnake.KeyInterpreter;
import jsnake.component.Scene;
import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;

public class MainWindow extends JFrame implements KeyListener {
	
	private KeyInterpreter keyInterpreter;
	
	public MainWindow(MainMenuBar mainMenuBar, KeyInterpreter keyInterpreter, Scene scene) {
		this.keyInterpreter = keyInterpreter;
		
		initMainWindow();
		createMainMenuBar(mainMenuBar);
		addScene(scene);
		
		this.addKeyListener(this);

		pack();
		setVisible(true);
	}
	
	private void initMainWindow() {
		setTitle("JSnake");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		setResizable(false);
	}
	
	private void createMainMenuBar(MainMenuBar mainMenuBar) {
		this.setJMenuBar(mainMenuBar);		
	}
	
	private void addScene(Scene scene) {
		this.add(scene, "Center");
	}

	public void keyReleased(KeyEvent keyEvent) {
		// not implemented yet
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		keyInterpreter.keyEvaluate(keyEvent.getKeyCode());
	}

	public void keyTyped(KeyEvent keyEvent) {
		// not implemented yet
	}

}
