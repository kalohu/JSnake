package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import jsnake.interfaces.Rendered;

public class Score implements Rendered {
	
	private int score;
	
	public Score() {
		setScore(3);
	}
			
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score) {
		this.score += score;
	}
	
	// Rendered methods
	
	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		String scoreText = "Score: " + getScore();
		gr.setColor(Color.blue);
		gr.drawString(scoreText, (renderedWidth / 2 - scoreText.length() / 2) * basicSize, 3 * basicSize);
	}

}
