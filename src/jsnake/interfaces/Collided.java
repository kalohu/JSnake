package jsnake.interfaces;

import java.util.ArrayList;
import java.awt.Point;

public interface Collided {
	
	ArrayList<ArrayList<Point>> checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent);
	ArrayList<ArrayList<Point>> checkCollision(Collided collidedComponent);

}
