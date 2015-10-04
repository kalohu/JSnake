package jsnake.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jsnake.CollisionDetector;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Rendered;

public class Wall implements Rendered, Collided {

	private ArrayList<Point> wallElements;
	private CollisionDetector collisionDetector;
	
	public Wall(Dimension rendererSize, int basicSize) {
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

	// Collided methods

	@Override
	public void setCollisionDetector(CollisionDetector collisionDetector) {
		this.collisionDetector = collisionDetector;
	}

	@Override
	public void checkCollision(SnakeHead callerComponent, ArrayList<Point> callerComponentCoords) {
		ArrayList<ArrayList<Point>> collidedComponents = new ArrayList<ArrayList<Point>>();
		collidedComponents.add(callerComponentCoords);
		collidedComponents.add(wallElements);
		collisionDetector.detectCollision(callerComponent, this, collidedComponents);
	}

	@Override
	public void checkCollision(SnakeTail callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(Food callerComponent, ArrayList<Point> callerComponentCoords) {
		ArrayList<ArrayList<Point>> collidedComponents = new ArrayList<ArrayList<Point>>();
		collidedComponents.add(callerComponentCoords);
		collidedComponents.add(wallElements);
		collisionDetector.detectCollision(callerComponent, this, collidedComponents);
	}

	@Override
	public void checkCollision(Wall callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(Collided collidedComponent) {
		collidedComponent.checkCollision(this, wallElements);
	}

	// Rendered methods

	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.gray);
		for (Point wallElement : wallElements) {
			gr.fill3DRect(wallElement.x * basicSize, wallElement.y * basicSize, basicSize, basicSize, true);
		}
	}

}
