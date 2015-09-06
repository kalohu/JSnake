package jsnake.component;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Point;
import jsnake.SnakeTimer;
import jsnake.CollisionDetector;
import jsnake.interfaces.Renderer;
import jsnake.interfaces.Controlled;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Collided;

public class Snake implements Controlled, Rendered, Animated, Collided {
	
	private ArrayList<SnakePiece> snake;
	private int horizontalDirection;
	private int verticalDirection;
	private SnakeTimer snakeTimer;
	private CollisionDetector collisionDetector;
	
	public Snake(Renderer rendererComponent) {
		snake = new ArrayList<SnakePiece>();
		
		init(rendererComponent);
	}

	public void init(Renderer rendererComponent) {
		int width = (int) rendererComponent.getRendererSize().getWidth();
		int height = (int) rendererComponent.getRendererSize().getHeight();
		int basicSize = rendererComponent.getBasicSize();

		snake.clear();
		for (int i = 0; i < 3; i++) {
			snake.add(new SnakePiece(width / 2 + i, height / 2, basicSize));
		}
	}

	private boolean isPossibleNewDirection(int horizontalDirection, int verticalDirection) {
		int snakeX = snake.get(1).getX() - snake.get(2).getX();
		int snakeY = snake.get(1).getY() - snake.get(2).getY();

		return ((snakeX == 0 && horizontalDirection != 0) || (snakeX != 0 && horizontalDirection == 0)) &&
			   ((snakeY == 0 && verticalDirection != 0) || (snakeY != 0 && verticalDirection == 0)); 
	}
		
	public void feed(int basicSize) {
		snake.add(new SnakePiece(-1, -1, basicSize));
	}
		
	public void addSnakeTimerReference(SnakeTimer snakeTimer) {
		this.snakeTimer = snakeTimer;
	}
	
	// Collided methods
	
	public void addCollisionDetector(CollisionDetector collisionDetector) {
		this.collisionDetector = collisionDetector;
	}
	
	public void checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent) {
		ArrayList<Point> snakeCoords = new ArrayList<Point>();
		for (SnakePiece snakePiece : snake) {
			snakeCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		}
		if (callerComponent instanceof Snake) {
			snakeCoords.remove(0);
		}
		if (collisionDetector.checkCollision(callerComponentCoords, snakeCoords)) {
			snakeTimer.stopStep();
		}
	}
	
	public void checkCollision(Collided collidedComponent) {
		ArrayList<Point> snakeCoords = new ArrayList<Point>();
		snakeCoords.add(new Point(snake.get(0).getX(), snake.get(0).getY()));
		collidedComponent.checkCollision(snakeCoords, this);
	}
	
	// Controlled methods
	
	public int getX() {
		return snake.get(0).getX();
	}
	
	public int getY() {
		return snake.get(0).getY();
	}

	public void setDirection(int horizontalDirection, int verticalDirection) {
		this.horizontalDirection = horizontalDirection;
		this.verticalDirection = verticalDirection;
	}

	// Rendered methods
	
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		boolean sneakHead = true;
		for (SnakePiece snakePiece : snake) {
			if (sneakHead) {
				snakePiece.drawHead(gr);
				sneakHead = false;
			}
			else {
				snakePiece.drawBody(gr);
			}
		}
	}

	// Animated methods

	public void step() {
		for (int i = snake.size() - 1; i > 0; i--) {
			snake.get(i).setX(snake.get(i - 1).getX());
			snake.get(i).setY(snake.get(i - 1).getY());
		}
		if (isPossibleNewDirection(horizontalDirection, verticalDirection)) {
			snake.get(0).setX(snake.get(1).getX() + horizontalDirection);
			snake.get(0).setY(snake.get(1).getY() + verticalDirection);
		}
		else {
			snake.get(0).setX(snake.get(1).getX() + (snake.get(1).getX() - snake.get(2).getX()));
			snake.get(0).setY(snake.get(1).getY() + (snake.get(1).getY() - snake.get(2).getY()));
		}
	}
	
}
