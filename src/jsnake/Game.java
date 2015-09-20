package jsnake;

import jsnake.gui.MainWindow;
import jsnake.gui.MainMenuBar;
import jsnake.component.Background;
import jsnake.component.Wall;
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
		// This is the object that implements the Renderer interface
		Scene scene = new Scene();

		// Graphic components
		Score score = new Score();
		Background background = new Background();
		Wall wall = new Wall(scene.getRendererSize(), scene.getBasicSize());
		SnakeTail snakeTail = new SnakeTail(scene);
		SnakeHead snakeHead = new SnakeHead(scene, snakeTail); // this is the object that implements the Controlled interface
		Food food = new Food(scene);
		
		// This object get and forward the direction to the object that implements the Controlled interface
		// This is the object that implements the Controller interface
		KeyInterpreter keyInterpreter = new KeyInterpreter(snakeHead);

		// Add components that implement the Rendered interface
		scene.addRenderedComponent(background);
		scene.addRenderedComponent(wall);
		scene.addRenderedComponent(score);
		scene.addRenderedComponent(snakeHead);
		scene.addRenderedComponent(snakeTail);
		scene.addRenderedComponent(food);
		
		// This is the object that implements the Animator interface
		Engine engine = new Engine();
		// This is the object that implements the Iterator interface
		SnakeTimer snakeTimer = new SnakeTimer(100, engine);

		CollisionDetector collisionDetector = new CollisionDetector();
		CollisionResolver collisionResolver = new CollisionResolver(collisionDetector, snakeTimer, scene, snakeTail, score);

		// Add components that implement the Animated interface
		engine.addAnimatedComponent(keyInterpreter);
		engine.addAnimatedComponent(snakeHead);
		engine.addAnimatedComponent(scene);
		
		engine.addControlledComponent(snakeHead);
		
		engine.addCollidedComponent(food);
		engine.addCollidedComponent(snakeTail);
		engine.addCollidedComponent(wall);

		snakeHead.setCollisionResolver(collisionResolver);
		snakeTail.setCollisionResolver(collisionResolver);
		wall.setCollisionResolver(collisionResolver);
		food.setCollisionResolver(collisionResolver);
		
		mainWindow.addController(keyInterpreter);
		mainWindow.addRenderer(scene);
		mainMenuBar.addAnimator(engine);
		mainMenuBar.addIterator(snakeTimer);
		mainMenuBar.addGame(this);
	}

}
