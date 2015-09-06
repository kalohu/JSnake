package jsnake.interfaces;

import jsnake.CollisionDetector;
import java.util.ArrayList;
import java.awt.Point;

public interface Collided {
	
	void addCollisionDetector(CollisionDetector collisionDetector);
	void checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent);
	void checkCollision(Collided collidedComponent);

}
