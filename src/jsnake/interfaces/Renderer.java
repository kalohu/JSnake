package jsnake.interfaces;

import java.awt.Dimension;

public interface Renderer {
	
	public void addRenderedComponents(Rendered[] renderedComponents);
	public Dimension getRendererSize();
	public void setRendererSize(int width, int height);
	public int getBasicSize();
	public void setBasicSize(int size);

}
