package jsnake;

import java.util.List;
import java.util.ArrayList;

import jsnake.gui.MainWindow;
import jsnake.gui.MainMenuBar;
import jsnake.component.Background;
import jsnake.component.Wall;
import jsnake.component.Food;
import jsnake.component.Scene;
import jsnake.component.Score;
import jsnake.component.SnakeHead;
import jsnake.component.SnakeTail;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Collided;

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
		
		// This is the object that implements the Animator interface
		Engine engine = new Engine();
		// This is the object that implements the Iterator interface
		SnakeTimer snakeTimer = new SnakeTimer(100, engine);

		CollisionDetector collisionDetector = new CollisionDetector();
		CollisionResolver collisionResolver = new CollisionResolver(collisionDetector, snakeTimer, scene, snakeTail, score);

		// Make list of components that implement the Rendered interface
		List<Rendered> renderedComponents = new ArrayList<Rendered>();
		renderedComponents.add(background);
		renderedComponents.add(wall);
		renderedComponents.add(score);
		renderedComponents.add(snakeHead);
		renderedComponents.add(snakeTail);
		renderedComponents.add(food);

		// Make list of components that implement the Animated interface
		List<Animated> animatedComponents = new ArrayList<Animated>();
		animatedComponents.add(keyInterpreter);
		animatedComponents.add(scene);
		animatedComponents.add(snakeHead);

		// Make list of components that implement the Collided interface
		List<Collided> collidedComponents = new ArrayList<Collided>();
		collidedComponents.add(wall);
		collidedComponents.add(snakeHead);
		collidedComponents.add(snakeTail);
		collidedComponents.add(food);

		// Make list of components that implement the Collided interface
		// and you want to collide them with other components
		List<Collided> colliderComponents = new ArrayList<Collided>();
		colliderComponents.add(snakeHead);

		scene.addRenderedComponents(renderedComponents);
		engine.addAnimatedComponents(animatedComponents);
		engine.addCollidedComponents(collidedComponents);
		engine.addColliderComponents(colliderComponents);

		snakeHead.setCollisionResolver(collisionResolver);
		snakeTail.setCollisionResolver(collisionResolver);
		wall.setCollisionResolver(collisionResolver);
		food.setCollisionResolver(collisionResolver);
		
		mainWindow.addController(keyInterpreter);
		mainWindow.addRenderer(scene);
		mainMenuBar.addIterator(snakeTimer);
		mainMenuBar.addGame(this);
	}

}
