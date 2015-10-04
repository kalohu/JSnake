package jsnake;

import java.awt.Point;
import java.util.ArrayList;

import jsnake.component.Food;
import jsnake.component.SnakeHead;
import jsnake.component.SnakeTail;
import jsnake.component.Wall;

public class CollisionDetector {
	
	private CollisionResolver collisionResolver;

	public CollisionDetector(CollisionResolver collisionResolver) {
		this.collisionResolver = collisionResolver;
	}

	public boolean checkCollision(ArrayList<Point> firstComponent, ArrayList<Point> secondComponent) {
		for (Point firstComponentElement : firstComponent) {
			for (Point secondComponentElement : secondComponent) {
				if (firstComponentElement.x == secondComponentElement.x && firstComponentElement.y == secondComponentElement.y) {
					return true;
				}
			}
		}
		return false;
	}

	public void detectCollision(SnakeHead callerComponent, Wall calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			collisionResolver.resolveCollision(callerComponent, calledComponent);
		}
	}

	public void detectCollision(SnakeHead callerComponent, Food calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			collisionResolver.resolveCollision(callerComponent, calledComponent);
		}
	}

	public void detectCollision(SnakeHead callerComponent, SnakeTail calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			collisionResolver.resolveCollision(callerComponent, calledComponent);
		}
	}

	public void detectCollision(Food callerComponent, Wall calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			collisionResolver.resolveCollision(callerComponent, calledComponent);
		}
	}

	public void detectCollision(Food callerComponent, SnakeHead calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			collisionResolver.resolveCollision(callerComponent, calledComponent);
		}
	}

	public void detectCollision(Food callerComponent, SnakeTail calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			collisionResolver.resolveCollision(callerComponent, calledComponent);
		}
	}

	public void detectCollision(Food callerComponent, Food calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			collisionResolver.resolveCollision(callerComponent, calledComponent);
		}
	}

}
