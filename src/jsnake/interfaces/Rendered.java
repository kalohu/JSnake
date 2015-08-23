package jsnake.interfaces;

import java.awt.Graphics;

public interface Rendered {
	
	public void generate(int rendererWidth, int rendererHeight, int basicSize);
	public void draw(Graphics gr, int renderedWidth, int renderedHeight, int basicSize);

}
