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
		gr.setColor(Color.gray);
		for (int x = 0; x < renderedWidth; ++x) {
			for (int y = 0; y < renderedHeight; ++y) {
				if (y == 4 || y == 0 || y == renderedHeight - 1 || x == 0 || x == renderedWidth - 1) {
					gr.fill3DRect(x * basicSize, y * basicSize, basicSize, basicSize, true);					
				}
			}
		}
	}

}
