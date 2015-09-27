package jsnake;

import java.util.List;

import jsnake.interfaces.Animator;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Collided;

public class Engine implements Animator {
	
	private List<Animated> animatedComponents;
	private List<Collided> collidedComponents;
	private List<Collided> colliderComponents;

	// Animator methods

	@Override
	public void step() {
		for (Animated animatedComponent : animatedComponents) {
			animatedComponent.step();
		}
		
		for (Collided colliderComponent : colliderComponents) {
			for (Collided collidedComponent: collidedComponents) {
				colliderComponent.checkCollision(collidedComponent);
			}
		}
	}

	@Override
	public void addColliderComponents(List<Collided> colliderComponents) {
		this.colliderComponents = colliderComponents;
	}

	@Override
	public void addAnimatedComponents(List<Animated> animatedComponents) {
		this.animatedComponents = animatedComponents;
	}
	
	@Override
	public void addCollidedComponents(List<Collided> collidedComponents) {
		this.collidedComponents = collidedComponents;
	}

}
