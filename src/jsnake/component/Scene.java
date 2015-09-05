package jsnake.component;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import jsnake.SnakeTimer;
import jsnake.interfaces.Renderer;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Animated;

public class Scene extends JPanel implements Renderer, Animated {
	
	private ArrayList<Rendered> renderedComponents;
	private SnakeTimer snakeTimer;
	
	private int width;
	private int height;
	private int basicSize;
	
	public Scene() {
		renderedComponents = new ArrayList<Rendered>();
		width = 50;
		height = 50;
		basicSize = 15;
		this.setPreferredSize(new Dimension(width * basicSize, height * basicSize));
	}
		
	private void drawComponents(Graphics gr) {
		for (Rendered renderedComponent : renderedComponents) {
			renderedComponent.draw(gr, width, height, basicSize);
		}
	}

	protected void paintComponent(Graphics gr) {
		drawComponents(gr);
	}
		
	public void addSnakeTimerReference(SnakeTimer snakeTimer) {
		this.snakeTimer = snakeTimer;
	}
	
	// Renderer methods

	public void addRenderedComponent(Rendered renderedComponent) {
		renderedComponents.add(renderedComponent);
	}
		
	public Dimension getRendererSize() {
		return new Dimension(width, height);
	}
			
	public void setRendererSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width * basicSize, height * basicSize));
	}
	
	public int getBasicSize() {
		return basicSize;
	}

	public void setBasicSize(int size) {
		this.basicSize = size;
		this.setPreferredSize(new Dimension(width * basicSize, height * basicSize));
	}

	// Animated methods

	public void step() {
		this.repaint();
	}

	public void checkCollision(int controlledX, int controlledY) {
		if (controlledX < 1 || controlledX > width - 2 ||
			controlledY < 5 || controlledY > width - 2) {
			snakeTimer.stopStep();
		}
	}

}
