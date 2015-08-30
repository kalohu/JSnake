package jsnake;

import jsnake.gui.MainWindow;
import jsnake.gui.MainMenuBar;
import jsnake.component.Background;
import jsnake.component.Food;
import jsnake.component.Scene;
import jsnake.component.Score;
import jsnake.component.Snake;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Rendered;

public class Game {
	
	private MainWindow mainWindow;
	private MainMenuBar mainMenuBar;
	
	public Game(MainWindow mainWindow, MainMenuBar mainMenuBar) {
		this.mainWindow = mainWindow;
		this.mainMenuBar = mainMenuBar;
	}
	
	public void newGame() {
		// This is the object that implements the Animator interface
		Engine engine = new Engine();
		// This is the object that implements the Iterator interface
		SnakeTimer snakeTimer = new SnakeTimer(100, engine);

		// This is the object that implements the Renderer interface
		Scene scene = new Scene();

		// Graphic components
		Score score = new Score();
		Background background = new Background();
		Snake snake = new Snake(scene); // this is the object that implements the Controlled interface
		Food food = new Food(scene, snake, score);
		
		// This object get and forward the direction to the object that implements the Controlled interface
		// This is the object that implements the Controller interface
		KeyInterpreter keyInterpreter = new KeyInterpreter(snake);

		// Add components that implement the Rendered interface
		Rendered[] renderedComponents = {background, score, snake, food};
		scene.addRenderedComponents(renderedComponents);
		
		// Add components that implement the Animated interface
		Animated[] animatedComponents = {keyInterpreter, snake, food, score, scene};
		engine.addAnimatedComponents(animatedComponents);

		// Add references here when it is not possible at the creating period
		snake.addSnakeTimerReference(snakeTimer);
		scene.addSnakeTimerReference(snakeTimer);
		
		mainWindow.addController(keyInterpreter);
		mainWindow.addRenderer(scene);
		mainMenuBar.addAnimator(engine);
		mainMenuBar.addIterator(snakeTimer);
		mainMenuBar.addGame(this);
	}

}
