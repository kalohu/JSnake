package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import jsnake.interfaces.Collided;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class Food implements Rendered, Collided {
	
	private int x;
	private int y;
	
	public Food(Renderer rendererComponent) {
		init(rendererComponent);
	}

	// Collided methods

	@Override
	public ArrayList<ArrayList<Point>> checkCollision(ArrayList<Point> callerComponentCoords, Collided callerComponent) {
		ArrayList<Point> foodCoords = new ArrayList<Point>();
		foodCoords.add(new Point(x, y));
		ArrayList<ArrayList<Point>> collidedComponents = new ArrayList<ArrayList<Point>>();
		collidedComponents.add(callerComponentCoords);
		collidedComponents.add(foodCoords);
		return collidedComponents;
	}
	
	@Override
	public ArrayList<ArrayList<Point>> checkCollision(Collided collidedComponent) {
		ArrayList<Point> foodCoords = new ArrayList<Point>();
		foodCoords.add(new Point(x, y));
		return collidedComponent.checkCollision(foodCoords, this);
	}

	// Rendered methods
	
	public void init(Renderer rendererComponent) {
		int width = (int) rendererComponent.getRendererSize().getWidth();
		int height = (int) rendererComponent.getRendererSize().getHeight();

		int x;
		int y;
		
		Random rand = new Random();

		x = rand.nextInt(width - 3) + 1;
		y = rand.nextInt(height - 7) + 5;
		
		while (x == this.x && y == this.y) {
			x = rand.nextInt(width - 3) + 1;
			y = rand.nextInt(height - 7) + 5;
		}
		
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.red);
		gr.fill3DRect(x * basicSize, y * basicSize, basicSize, basicSize, true);
	}
	
}
