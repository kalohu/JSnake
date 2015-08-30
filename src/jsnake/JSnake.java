package jsnake;

import jsnake.gui.MainWindow;
import jsnake.gui.MainMenuBar;

public class JSnake {

	public static void main(String[] args) {
		// GUI components
		MainMenuBar mainMenuBar = new MainMenuBar();
		MainWindow mainWindow = new MainWindow(mainMenuBar);
		
		Game game = new Game(mainWindow, mainMenuBar);
		game.newGame();
	}

}
