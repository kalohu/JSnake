package jsnake;

import jsnake.gui.MainWindow;
import jsnake.gui.MainMenuBar;
import jsnake.component.Background;
import jsnake.component.Food;
import jsnake.component.Scene;
import jsnake.component.Score;
import jsnake.component.SnakeHead;
import jsnake.component.SnakeTail;

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
		Background background = new Background(scene.getRendererSize(), scene.getBasicSize());
		SnakeTail snakeTail = new SnakeTail(scene);
		SnakeHead snakeHead = new SnakeHead(scene, snakeTail); // this is the object that implements the Controlled interface
		Food food = new Food(scene, snakeHead, score);
		
		// This object get and forward the direction to the object that implements the Controlled interface
		// This is the object that implements the Controller interface
		KeyInterpreter keyInterpreter = new KeyInterpreter(snakeHead);

		// Add components that implement the Rendered interface
		scene.addRenderedComponent(background);
		scene.addRenderedComponent(score);
		scene.addRenderedComponent(snakeHead);
		scene.addRenderedComponent(snakeTail);
		scene.addRenderedComponent(food);
		
		// Add components that implement the Animated interface
		engine.addAnimatedComponent(keyInterpreter);
		engine.addAnimatedComponent(snakeHead);
		engine.addAnimatedComponent(scene);
		
		engine.addControlledComponent(snakeHead);
		
		engine.addCollidedComponent(food);
		engine.addCollidedComponent(snakeTail);

		CollisionDetector collisionDetector = new CollisionDetector();
		snakeHead.addCollisionDetector(collisionDetector);
		food.addCollisionDetector(collisionDetector);
		snakeTail.addCollisionDetector(collisionDetector);

		// Add references here when it is not possible at the creating period
		snakeHead.addSnakeTimerReference(snakeTimer);
		snakeTail.addSnakeTimerReference(snakeTimer);
		
		mainWindow.addController(keyInterpreter);
		mainWindow.addRenderer(scene);
		mainMenuBar.addAnimator(engine);
		mainMenuBar.addIterator(snakeTimer);
		mainMenuBar.addGame(this);
	}

}
