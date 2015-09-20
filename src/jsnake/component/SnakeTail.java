package jsnake.component;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jsnake.CollisionResolver;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class SnakeTail implements Rendered, Collided {

	private ArrayList<SnakePiece> snakeTail;
	private CollisionResolver collisionResolver;

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

	public void addTailPiece(int basicSize) {
		snakeTail.add(new SnakePiece(-1, -1, basicSize));
	}

	// Collided methods

	@Override
	public void setCollisionResolver(CollisionResolver collisionResolver) {
		this.collisionResolver = collisionResolver;
	}

	@Override
	public void checkCollision(SnakeHead callerComponent, ArrayList<Point> callerComponentCoords) {
		ArrayList<Point> snakeTailCoords = new ArrayList<Point>();
		for (SnakePiece snakePiece : snakeTail) {
			snakeTailCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		}
		ArrayList<ArrayList<Point>> collidedComponents = new ArrayList<ArrayList<Point>>();
		collidedComponents.add(callerComponentCoords);
		collidedComponents.add(snakeTailCoords);
		collisionResolver.resolveCollision(callerComponent, this, collidedComponents);
	}

	@Override
	public void checkCollision(SnakeTail callerComponent, ArrayList<Point> callerComponentCoords) {}
	
	@Override
	public void checkCollision(Food callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(Wall callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(Collided collidedComponent) {
		ArrayList<Point> snakeTailCoords = new ArrayList<Point>();
		for (SnakePiece snakePiece : snakeTail) {
			snakeTailCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		}
		collidedComponent.checkCollision(this, snakeTailCoords);
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
