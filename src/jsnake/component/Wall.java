package jsnake.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import jsnake.interfaces.Collided;
import jsnake.interfaces.Rendered;

public class Wall implements Rendered, Collided {

	private ArrayList<Point> wallElements;
	
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
	public ArrayList<ArrayList<Point>> checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent) {
		ArrayList<ArrayList<Point>> collidedComponents = new ArrayList<ArrayList<Point>>();
		collidedComponents.add(callerComponentCoords);
		collidedComponents.add(wallElements);
		return collidedComponents;
	}

	@Override
	public ArrayList<ArrayList<Point>> checkCollision(Collided collidedComponent) {
		return collidedComponent.checkCollision(wallElements, this);
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
