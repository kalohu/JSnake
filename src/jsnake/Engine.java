package jsnake;

import jsnake.interfaces.Animator;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Controlled;

public class Engine implements Animator {
	
	private Animated[] animatedComponents;
	private Controlled controlledComponent;

	// Animator methods
		
	public void step() {
		int controlledX = controlledComponent.getX();
		int controlledY = controlledComponent.getY();

		for (Animated animatedComponent : animatedComponents) {
			animatedComponent.step();
			if (animatedComponent instanceof Controlled) {
				controlledX = controlledComponent.getX();
				controlledY = controlledComponent.getY();
			}
			animatedComponent.checkCollision(controlledX, controlledY);
		}
	}
	
	public void resetAnimatedComponents() {
		for (Animated animatedComponent : animatedComponents) {
			animatedComponent.reset();
		}
	}

	public void addAnimatedComponents(Animated[] animatedComponents) {
		this.animatedComponents = animatedComponents;

		controlledComponent = null;

		for (Animated animatedComponent : animatedComponents) {
			if (animatedComponent instanceof Controlled) {
				controlledComponent = (Controlled)animatedComponent;
			}
		}

		if (controlledComponent == null) {
			System.out.println("Controlled object has to be implemented!");
			System.exit(0);
		}
	}

}
