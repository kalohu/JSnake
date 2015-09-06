package jsnake.interfaces;

public interface Animator {
	
	void step();
	void addControlledComponent(Controlled controlledComponent);
	void addAnimatedComponent(Animated animatedComponent);
	void addCollidedComponent(Collided collidedComponent);

}
