package jsnake;

import jsnake.gui.MainWindow;
import jsnake.gui.MainMenuBar;
import jsnake.component.Score;
import jsnake.component.Background;
import jsnake.component.Snake;
import jsnake.component.Scene;
import jsnake.component.Food;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Rendered;

public class JSnake {

	public static void main(String[] args) {
		// GUI components
		MainMenuBar mainMenuBar = new MainMenuBar();
		MainWindow mainWindow = new MainWindow(mainMenuBar);
		
		Game game = new Game(mainWindow, mainMenuBar);
		game.newGame();
	}

}
