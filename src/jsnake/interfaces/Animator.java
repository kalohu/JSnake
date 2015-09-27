package jsnake.interfaces;

import java.util.List;

public interface Animator {
	
	void step();
	void addColliderComponents(List<Collided> colliderComponent);
	void addAnimatedComponents(List<Animated> animatedComponents);
	void addCollidedComponents(List<Collided> collidedComponent);

}
