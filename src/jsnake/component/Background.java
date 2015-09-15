package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;
import jsnake.interfaces.Rendered;
import java.util.ArrayList;

public class Background implements Rendered {
	
	private ArrayList<Point> backgroundElements;

	public Background(Dimension rendererSize, int basicSize) {
		backgroundElements = new ArrayList<Point>();
		init(rendererSize.width, rendererSize.height, basicSize);
	}

	private void init(int rendererWidth, int rendererHeight, int basicSize) {
		for (int x = 0; x < rendererWidth; ++x) {
			for (int y = 0; y < rendererHeight; ++y) {
				if (y == 4 || y == 0 || y == rendererHeight - 1 || x == 0 || x == rendererWidth - 1) {
					backgroundElements.add(new Point(x, y));
				}
			}
		}
	}

	// Rendered methods

	@Override
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize) {
		gr.setColor(Color.white);
		gr.fillRect(0, 0, renderedWidth * basicSize, renderedHeight * basicSize);
		gr.setColor(Color.gray);
		for (Point backgroundElement : backgroundElements) {
			gr.fill3DRect(backgroundElement.x * basicSize, backgroundElement.y * basicSize, basicSize, basicSize, true);
		}
	}

}
