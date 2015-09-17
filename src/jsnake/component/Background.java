package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;

import jsnake.interfaces.Rendered;

public class Background implements Rendered {

	// Rendered methods

	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.white);
		gr.fillRect(0, 0, renderedWidth * basicSize, renderedHeight * basicSize);
	}

}
