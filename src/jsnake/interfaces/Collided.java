package jsnake.interfaces;

import java.util.ArrayList;
import java.awt.Point;

import jsnake.CollisionDetector;
import jsnake.component.SnakeHead;
import jsnake.component.SnakeTail;
import jsnake.component.Food;
import jsnake.component.Wall;

public interface Collided {
	
	public void setCollisionDetector(CollisionDetector collisionDetector);
	void checkCollision(Collided collidedComponent);

	void checkCollision(SnakeHead callerComponent, ArrayList<Point> callerComponentCoords);
	void checkCollision(SnakeTail callerComponent, ArrayList<Point> callerComponentCoords);
	void checkCollision(Food callerComponent, ArrayList<Point> callerComponentCoords);
	void checkCollision(Wall callerComponent, ArrayList<Point> callerComponentCoords);

}
