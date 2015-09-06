package jsnake;

import java.awt.Point;
import java.util.ArrayList;

public class CollisionDetector {
	
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

}
