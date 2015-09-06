package jsnake;

import java.util.ArrayList;

import jsnake.interfaces.Animator;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Controlled;
import jsnake.interfaces.Collided;

public class Engine implements Animator {
	
	private ArrayList<Animated> animatedComponents;
	private ArrayList<Collided> collidedComponents;
	private ArrayList<Controlled> controlledComponents;
	
	public Engine() {
		animatedComponents = new ArrayList<Animated>();
		collidedComponents = new ArrayList<Collided>();
		controlledComponents = new ArrayList<Controlled>();
	}

	// Animator methods
		
	public void step() {
		for (Animated animatedComponent : animatedComponents) {
			animatedComponent.step();
		}
		
		for (Controlled controlledComponent : controlledComponents) {
			for (Collided collidedComponent: collidedComponents) {
				((Collided)controlledComponent).checkCollision(collidedComponent);
			}
		}
	}

	public void addControlledComponent(Controlled controlledComponent) {
		controlledComponents.add(controlledComponent);
	}

	public void addAnimatedComponent(Animated animatedComponent) {
		animatedComponents.add(animatedComponent);
	}
	
	public void addCollidedComponent(Collided collidedComponent) {
		collidedComponents.add(collidedComponent);
	}

}
