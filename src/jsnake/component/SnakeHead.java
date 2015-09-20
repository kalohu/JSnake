package jsnake.component;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jsnake.CollisionResolver;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Controlled;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class SnakeHead implements Controlled, Rendered, Animated, Collided {
	
	private SnakePiece snakePiece;
	private SnakeTail snakeTail;
	private CollisionResolver collisionResolver;

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
	public void setCollisionResolver(CollisionResolver collisionResolver) {
		this.collisionResolver = collisionResolver;
	}

	@Override
	public void checkCollision(SnakeHead callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(SnakeTail callerComponent, ArrayList<Point> callerComponentCoords) {}
	
	@Override
	public void checkCollision(Food callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(Wall callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(Collided collidedComponent) {
		ArrayList<Point> snakeHeadCoords = new ArrayList<Point>();
		snakeHeadCoords.add(new Point(snakePiece.getX(), snakePiece.getY()));
		collidedComponent.checkCollision(this, snakeHeadCoords);
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
