package jsnake.component;

import java.awt.Color;
import java.awt.Graphics;

public class SnakePiece {
	
	private int x;
	private int y;
	private int basicSize;
	
	public SnakePiece(int x, int y, int basicSize) {
		this.x = x;
		this.y = y;
		this.basicSize = basicSize;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		 this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
		
	public void drawHead(Graphics gr) {
		gr.setColor(Color.black);
		gr.fill3DRect(x * basicSize, y * basicSize, basicSize, basicSize, true);
	}

	public void drawBody(Graphics gr) {
		gr.setColor(Color.lightGray);
		gr.fill3DRect(x * basicSize, y * basicSize, basicSize, basicSize, true);
	}

}
