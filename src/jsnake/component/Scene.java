package jsnake.component;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import jsnake.SnakeTimer;
import jsnake.interfaces.Renderer;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Animated;

public class Scene extends JPanel implements Renderer, Animated {
	
	private Rendered[] renderedComponents;
	private SnakeTimer snakeTimer;
	
	private int width;
	private int height;
	private int basicSize;
	
	public Scene() {
		width = 50;
		height = 50;
		basicSize = 15;
		this.setPreferredSize(new Dimension(width * basicSize, height * basicSize));
		reset();
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

	public void addRenderedComponents(Rendered[] renderedComponents) {
		this.renderedComponents = renderedComponents;
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

	public void reset() {
		// not implemented yet
	}

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
