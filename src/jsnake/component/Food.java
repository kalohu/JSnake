package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jsnake.CollisionDetector;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class Food implements Rendered, Collided {
	
	private CollisionDetector collisionDetector;
	private int x;
	private int y;
	
	public Food(Renderer rendererComponent) {
		x = 1;
		y = 5;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Collided methods

	@Override
	public void setCollisionDetector(CollisionDetector collisionDetector) {
		this.collisionDetector = collisionDetector;
	}

	@Override
	public void checkCollision(SnakeHead callerComponent, ArrayList<Point> callerComponentCoords) {
		ArrayList<Point> foodCoords = new ArrayList<Point>();
		foodCoords.add(new Point(x, y));
		ArrayList<ArrayList<Point>> collidedComponents = new ArrayList<ArrayList<Point>>();
		collidedComponents.add(callerComponentCoords);
		collidedComponents.add(foodCoords);
		collisionDetector.detectCollision(callerComponent, this, collidedComponents);
	}

	@Override
	public void checkCollision(SnakeTail callerComponent, ArrayList<Point> callerComponentCoords) {}
	
	@Override
	public void checkCollision(Food callerComponent, ArrayList<Point> callerComponentCoords) {
		ArrayList<Point> foodCoords = new ArrayList<Point>();
		foodCoords.add(new Point(x, y));
		ArrayList<ArrayList<Point>> collidedComponents = new ArrayList<ArrayList<Point>>();
		collidedComponents.add(callerComponentCoords);
		collidedComponents.add(foodCoords);
		collisionDetector.detectCollision(callerComponent, this, collidedComponents);
	}

	@Override
	public void checkCollision(Wall callerComponent, ArrayList<Point> callerComponentCoords) {}

	@Override
	public void checkCollision(Collided collidedComponent) {
		ArrayList<Point> foodCoords = new ArrayList<Point>();
		foodCoords.add(new Point(x, y));
		collidedComponent.checkCollision(this, foodCoords);
	}

	// Rendered methods
	
	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.red);
		gr.fill3DRect(x * basicSize, y * basicSize, basicSize, basicSize, true);
	}
	
}
