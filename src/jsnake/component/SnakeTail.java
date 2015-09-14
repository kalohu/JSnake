package jsnake.component;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jsnake.CollisionDetector;
import jsnake.SnakeTimer;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class SnakeTail implements Rendered, Collided {

	private ArrayList<SnakePiece> snakeTail;
	private CollisionDetector collisionDetector;
	private SnakeTimer snakeTimer;

	public SnakeTail(Renderer rendererComponent) {
		snakeTail = new ArrayList<SnakePiece>();
		
		init(rendererComponent);
		
	}
	
	public void init(Renderer rendererComponent) {
		int width = (int) rendererComponent.getRendererSize().getWidth();
		int height = (int) rendererComponent.getRendererSize().getHeight();
		int basicSize = rendererComponent.getBasicSize();

		snakeTail.clear();
		for (int i = 1; i < 3; i++) {
			snakeTail.add(new SnakePiece(width / 2 + i, height / 2, basicSize));
		}
	}

	public void addSnakeTimerReference(SnakeTimer snakeTimer) {
		this.snakeTimer = snakeTimer;
	}
	
	public void addTailPiece(int basicSize) {
		snakeTail.add(new SnakePiece(-1, -1, basicSize));
	}

	// Collided methods
	
	@Override
	public void addCollisionDetector(CollisionDetector collisionDetector) {
		this.collisionDetector = collisionDetector;
	}
	
	@Override
	public void checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent) {
		ArrayList<Point> snakeTailCoords = new ArrayList<Point>();
		for (SnakePiece snakePiece : snakeTail) {
			snakeTailCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		}
		if (collisionDetector.checkCollision(callerComponentCoords, snakeTailCoords)) {
			snakeTimer.stopStep();
		}
	}
	
	@Override
	public void checkCollision(Collided collidedComponent) {
		ArrayList<Point> snakeTailCoords = new ArrayList<Point>();
		for (SnakePiece snakePiece : snakeTail) {
			snakeTailCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		}
		collidedComponent.checkCollision(snakeTailCoords, this);
	}

	// Rendered methods
	
	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		for (SnakePiece snakePiece : snakeTail) {
			snakePiece.drawBody(gr);
		}
	}

	// Animated methods

	public void step(int oldHeadX, int oldHeadY) {
		for (int i = snakeTail.size() - 1; i > 0; i--) {
			snakeTail.get(i).setX(snakeTail.get(i - 1).getX());
			snakeTail.get(i).setY(snakeTail.get(i - 1).getY());
		}
		snakeTail.get(0).setX(oldHeadX);
		snakeTail.get(0).setY(oldHeadY);
	}

}
