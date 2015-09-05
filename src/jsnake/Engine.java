package jsnake;

import java.util.ArrayList;

import jsnake.interfaces.Animator;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Controlled;

public class Engine implements Animator {
	
	private ArrayList<Animated> animatedComponents;
	private Controlled controlledComponent;
	
	public Engine() {
		animatedComponents = new ArrayList<Animated>();
	}

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

	public void addAnimatedComponent(Animated animatedComponent) {
		animatedComponents.add(animatedComponent);
		selectControlledComponent();
	}
	
	private void selectControlledComponent() {
		controlledComponent = null;

		for (Animated animatedComponent : animatedComponents) {
			if (animatedComponent instanceof Controlled) {
				controlledComponent = (Controlled)animatedComponent;
				break;
			}
		}		
	}

}
