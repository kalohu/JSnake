package jsnake.component;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jsnake.CollisionDetector;
import jsnake.SnakeTimer;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Controlled;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class SnakeHead implements Controlled, Rendered, Animated, Collided {
	
	private SnakePiece snakePiece;
	private CollisionDetector collisionDetector;
	private SnakeTimer snakeTimer;
	private SnakeTail snakeTail;

	private int horizontalDirection;
	private int verticalDirection;
	private int horizontalDirectionIntention = -1;
	private int verticalDirectionIntention;

	public SnakeHead(Renderer rendererComponent, SnakeTail snakeTail) {
		this.snakeTail = snakeTail;
		init(rendererComponent);
	}

	public void init(Renderer rendererComponent) {
		int width = (int) rendererComponent.getRendererSize().getWidth();
		int height = (int) rendererComponent.getRendererSize().getHeight();
		int basicSize = rendererComponent.getBasicSize();

		snakePiece = new SnakePiece(width / 2, height / 2, basicSize);
	}

	public void addSnakeTimerReference(SnakeTimer snakeTimer) {
		this.snakeTimer = snakeTimer;
	}
	
	@Override
	public void feed(int basicSize) {
		snakeTail.addTailPiece(basicSize);
	}

	// Controlled methods
	
	@Override
	public int getX() {
		return snakePiece.getX();
	}
	
	@Override
	public int getY() {
		return snakePiece.getY();
	}

	@Override
	public void setDirection(int newHorizontalDirection, int newVerticalDirection) {
		if (((this.horizontalDirection == 0 && newHorizontalDirection != 0) || (this.horizontalDirection != 0 && newHorizontalDirection == 0)) &&
		    ((this.verticalDirection == 0 && newVerticalDirection != 0) || (this.verticalDirection != 0 && newVerticalDirection == 0))) {
			this.horizontalDirectionIntention = newHorizontalDirection;
			this.verticalDirectionIntention = newVerticalDirection;
		}
	}

	// Collided methods
	
	@Override
	public void addCollisionDetector(CollisionDetector collisionDetector) {
		this.collisionDetector = collisionDetector;
	}
	
	@Override
	public void checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent) {
		ArrayList<Point> snakeHeadCoords = new ArrayList<Point>();
		snakeHeadCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		if (collisionDetector.checkCollision(callerComponentCoords, snakeHeadCoords)) {
			snakeTimer.stopStep();
		}
	}
	
	@Override
	public void checkCollision(Collided collidedComponent) {
		ArrayList<Point> snakeHeadCoords = new ArrayList<Point>();
		snakeHeadCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		collidedComponent.checkCollision(snakeHeadCoords, this);
	}

	// Rendered methods
	
	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		snakePiece.drawHead(gr);
	}

	// Animated methods

	@Override
	public void step() {
		horizontalDirection = horizontalDirectionIntention;
		verticalDirection = verticalDirectionIntention;
		snakeTail.step(snakePiece.getX(), snakePiece.getY());
		snakePiece.setX(snakePiece.getX() + horizontalDirection);
		snakePiece.setY(snakePiece.getY() + verticalDirection);
	}

}
