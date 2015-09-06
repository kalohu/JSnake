package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import jsnake.CollisionDetector;
import jsnake.component.Score;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class Food implements Animated, Rendered, Collided {
	
	private Renderer rendererComponent;
	private Snake snake;
	private Score score;
	private CollisionDetector collisionDetector;
	
	private int x;
	private int y;
	
	public Food(Renderer rendererComponent, Snake snake, Score score) {
		this.rendererComponent = rendererComponent;
		this.snake = snake;
		this.score = score;

		init(rendererComponent);
	}

	// Collided methods
	
	public void addCollisionDetector(CollisionDetector collisionDetector) {
		this.collisionDetector = collisionDetector;
	}
	
	public void checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent) {
		ArrayList<Point> foodCoords = new ArrayList<Point>();
		foodCoords.add(new Point(x, y));
		if (collisionDetector.checkCollision(callerComponentCoords, foodCoords)) {
			int basicSize = rendererComponent.getBasicSize();
			score.addScore();
			init(rendererComponent);
			snake.feed(basicSize);			
		}
	}
	
	public void checkCollision(Collided collidedComponent) {
		ArrayList<Point> foodCoords = new ArrayList<Point>();
		foodCoords.add(new Point(x, y));
		collidedComponent.checkCollision(foodCoords, this);
	}

	// Rendered methods
	
	public void init(Renderer rendererComponent) {
		int width = (int) rendererComponent.getRendererSize().getWidth();
		int height = (int) rendererComponent.getRendererSize().getHeight();

		int x;
		int y;
		
		Random rand = new Random();

		x = rand.nextInt(width - 3) + 1;
		y = rand.nextInt(height - 7) + 5;
		
		while (x == this.x && y == this.y) {
			x = rand.nextInt(width - 3) + 1;
			y = rand.nextInt(height - 7) + 5;
		}
		
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.red);
		gr.fill3DRect(x * basicSize, y * basicSize, basicSize, basicSize, true);
	}
	
	// Animated methods

	public void step() {
		// not implemented yet
	}

}
