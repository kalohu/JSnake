package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;

import jsnake.CollisionDetector;
import jsnake.SnakeTimer;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Collided;
import java.util.ArrayList;

public class Background implements Rendered, Collided {
	
	private ArrayList<Point> wallElements;
	private CollisionDetector collisionDetector;
	private SnakeTimer snakeTimer;

	public Background(Dimension rendererSize, int basicSize) {
		wallElements = new ArrayList<Point>();
		init(rendererSize.width, rendererSize.height, basicSize);
	}

	private void init(int rendererWidth, int rendererHeight, int basicSize) {
		for (int x = 0; x < rendererWidth; ++x) {
			for (int y = 0; y < rendererHeight; ++y) {
				if (y == 4 || y == 0 || y == rendererHeight - 1 || x == 0 || x == rendererWidth - 1) {
					wallElements.add(new Point(x, y));
				}
			}
		}
	}

	public void addSnakeTimerReference(SnakeTimer snakeTimer) {
		this.snakeTimer = snakeTimer;
	}

	// Collided methods

	@Override
	public void addCollisionDetector(CollisionDetector collisionDetector) {
		this.collisionDetector = collisionDetector;
	}

	@Override
	public void checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent) {
		if (collisionDetector.checkCollision(callerComponentCoords, wallElements)) {
			snakeTimer.stopStep();
		}
	}

	@Override
	public void checkCollision(Collided collidedComponent) {
		collidedComponent.checkCollision(wallElements, this);
	}

	// Rendered methods

	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.white);
		gr.fillRect(0, 0, renderedWidth * basicSize, renderedHeight * basicSize);
		gr.setColor(Color.gray);
		for (Point wallElement : wallElements) {
			gr.fill3DRect(wallElement.x * basicSize, wallElement.y * basicSize, basicSize, basicSize, true);
		}
	}

}
