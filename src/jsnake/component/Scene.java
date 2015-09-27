package jsnake.component;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

import jsnake.interfaces.Renderer;
import jsnake.interfaces.Rendered;
import jsnake.interfaces.Animated;

public class Scene extends JPanel implements Renderer, Animated {
	
	private List<Rendered> renderedComponents;
	
	private int width;
	private int height;
	private int basicSize;
	
	public Scene() {
		width = 50;
		height = 50;
		basicSize = 15;
		this.setPreferredSize(new Dimension(width * basicSize, height * basicSize));
	}
		
	private void drawComponents(Graphics gr) {
		for (Rendered renderedComponent : renderedComponents) {
			renderedComponent.draw(gr, width, height, basicSize);
		}
	}

	@Override
	protected void paintComponent(Graphics gr) {
		drawComponents(gr);
	}
	
	// Renderer methods

	@Override
	public void addRenderedComponents(List<Rendered> renderedComponents) {
		this.renderedComponents = renderedComponents;
	}
		
	@Override
	public Dimension getRendererSize() {
		return new Dimension(width, height);
	}
			
	@Override
	public void setRendererSize(int width, int height) {
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width * basicSize, height * basicSize));
	}
	
	@Override
	public int getBasicSize() {
		return basicSize;
	}

	@Override
	public void setBasicSize(int size) {
		this.basicSize = size;
		this.setPreferredSize(new Dimension(width * basicSize, height * basicSize));
	}

	// Animated methods

	@Override
	public void step() {
		this.repaint();
	}

}
