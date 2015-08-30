package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Dimension;
import jsnake.component.Score;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Renderer;

public class Food implements Animated, Rendered {
	
	private Renderer rendererComponent;
	private Snake snake;
	private Score score;
	
	private int x;
	private int y;
	
	public Food(Renderer rendererComponent, Snake snake, Score score) {
		this.rendererComponent = rendererComponent;
		this.snake = snake;
		this.score = score;

		init(rendererComponent);
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

	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.red);
		gr.fill3DRect(x * basicSize, y * basicSize, basicSize, basicSize, true);
	}
	
	// Animated methods

	public void step() {
		// not implemented yet
	}

	public void checkCollision(int controlledX, int controlledY) {
		if (controlledX == x && controlledY == y) {
//			Dimension rendererSize = rendererComponent.getRendererSize();
			int basicSize = rendererComponent.getBasicSize();
			score.addScore();
			init(rendererComponent);
			snake.feed(basicSize);
		}
	}

}
