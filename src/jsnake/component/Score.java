package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Animated;

public class Score implements Rendered, Animated {
	
	private int score;
	
	public Score() {
		reset();
	}
			
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore() {
		score++;
	}
	
	// Rendered methods
	
	public void generate(int rendererWidth, int rendererHeight, int basicSize) {
		// not implemented yet
	}

	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		String scoreText = "Score: " + getScore();
		gr.setColor(Color.blue);
		gr.drawString(scoreText, (renderedWidth / 2 - scoreText.length() / 2) * basicSize, 3 * basicSize);
	}

	// Animated methods
	
	public void reset() {
		setScore(3);
	}

	public void step() {
		// not implemented yet
	}

	public void checkCollision(int controlledX, int controlledY) {
		// not implemented yet
	}
}
