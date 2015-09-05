package jsnake.interfaces;

import java.awt.Dimension;

public interface Renderer {
	
	void addRenderedComponents(Rendered[] renderedComponents);
	Dimension getRendererSize();
	void setRendererSize(int width, int height);
	int getBasicSize();
	void setBasicSize(int size);

}
