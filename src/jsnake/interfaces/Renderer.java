package jsnake.interfaces;

import java.awt.Dimension;
import java.util.List;

public interface Renderer {
	
	void addRenderedComponents(List<Rendered> renderedComponents);
	Dimension getRendererSize();
	void setRendererSize(int width, int height);
	int getBasicSize();
	void setBasicSize(int size);

}
