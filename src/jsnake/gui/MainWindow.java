package jsnake.gui;

import java.awt.Container;
import jsnake.KeyInterpreter;
import jsnake.component.Scene;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.BorderLayout;
import jsnake.interfaces.Controller;
import jsnake.interfaces.Renderer;

public class MainWindow extends JFrame implements KeyListener {
	
	private Controller controller;
	private Container contentPane;
	
	public MainWindow(MainMenuBar mainMenuBar) {
		initMainWindow();
		createMainMenuBar(mainMenuBar);
		
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
		contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
	}
	
	private void createMainMenuBar(MainMenuBar mainMenuBar) {
		this.setJMenuBar(mainMenuBar);		
	}
	
	public void addController(Controller controller) {
		this.controller = controller;
	}
	
	public void addRenderer(Renderer renderer) {
		contentPane.removeAll();
		contentPane.add((JPanel)renderer, "Center");
		pack();
	}

	public void keyReleased(KeyEvent keyEvent) {
		// not implemented yet
	}
	
	public void keyPressed(KeyEvent keyEvent) {
		controller.keyEvaluate(keyEvent.getKeyCode());
	}

	public void keyTyped(KeyEvent keyEvent) {
		// not implemented yet
	}

}
