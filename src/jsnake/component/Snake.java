package jsnake.component;

import java.util.ArrayList;
import java.awt.Graphics;
import jsnake.SnakeTimer;
import jsnake.interfaces.Renderer;
import jsnake.interfaces.Controlled;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Animated;

public class Snake implements Controlled, Rendered, Animated {
	
	private Renderer rendererComponent;
	
	private ArrayList<SnakePiece> snake;
	private int horizontalDirection;
	private int verticalDirection;
	private SnakeTimer snakeTimer;
	
	public Snake(Renderer rendererComponent) {
		this.rendererComponent = rendererComponent;
		snake = new ArrayList<SnakePiece>();
		reset();
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
	
	public void generate(int rendererWidth, int rendererHeight, int basicSize) {
		snake.clear();
		for (int i = 0; i < 3; i++) {
			snake.add(new SnakePiece(rendererWidth / 2 + i,rendererHeight / 2, basicSize));
		}
	}

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

	public void reset() {
		int width = (int) rendererComponent.getRendererSize().getWidth();
		int height = (int) rendererComponent.getRendererSize().getHeight();
		int basicSize = rendererComponent.getBasicSize();
		generate(width, height, basicSize);
	}

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
	
	public void checkCollision(int controlledX, int controlledY) {
		boolean sneakHead = true;
		for (SnakePiece snakePiece : snake) {
			if (sneakHead) {
				sneakHead = false;
			}
			else {
				if (controlledX == snakePiece.getX() && controlledY == snakePiece.getY()) {
					snakeTimer.stopStep();
				}
			}
		}
	}

}
