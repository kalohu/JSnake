package jsnake.interfaces;

public interface Animated {
	
	public void reset();
	public void step();
	public void checkCollision(int controlledX, int controlledY);

}
