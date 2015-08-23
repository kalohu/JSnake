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
		// This is the object that implements the Animator interface
		Engine engine = new Engine();
		// This is the object that implements the Iterator interface
		SnakeTimer snakeTimer = new SnakeTimer(100, engine);

		// Graphic components
		Score score = new Score();
		Background background = new Background();
		Snake snake = new Snake(); // this is the object that implements the Controlled interface
		Food food = new Food(snake, score);
		
		// This object get and forward the direction to the object that implements the Controlled interface
		// This is the object that implements the Controller interface
		KeyInterpreter keyInterpreter = new KeyInterpreter(snake);

		// This is the object that implements the Renderer interface
		Scene scene = new Scene(score, keyInterpreter);

		// Add components that implement the Rendered interface
		Rendered[] renderedComponents = {background, score, snake, food};
		scene.addRenderedComponents(renderedComponents);
		
		// Add components that implement the Animated interface
		Animated[] animatedComponents = {keyInterpreter, snake, food, score, scene};
		engine.addAnimatedComponents(animatedComponents);

		// GUI components
		MainMenuBar mainMenuBar = new MainMenuBar(engine, snakeTimer);
		MainWindow mainWindow = new MainWindow(mainMenuBar, keyInterpreter, scene);

		// Add references here when it is not possible at the creating period
		snake.addRendererReference(scene);
		food.addRendererReference(scene);
		snake.addSnakeTimerReference(snakeTimer);
		scene.addSnakeTimerReference(snakeTimer);
	}

}
