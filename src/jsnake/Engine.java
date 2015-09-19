package jsnake;

import java.awt.Point;
import java.util.ArrayList;

import jsnake.interfaces.Animator;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Controlled;
import jsnake.interfaces.Collided;

public class Engine implements Animator {
	
	private ArrayList<Animated> animatedComponents;
	private ArrayList<Collided> collidedComponents;
	private ArrayList<Controlled> controlledComponents;
	private CollisionDetector collisionDetector;
	private CollisionResolver collisionResolver;
	
	public Engine(CollisionDetector collisionDetector, CollisionResolver collisionResolver) {
		animatedComponents = new ArrayList<Animated>();
		collidedComponents = new ArrayList<Collided>();
		controlledComponents = new ArrayList<Controlled>();
		this.collisionDetector = collisionDetector;
		this.collisionResolver = collisionResolver;
	}

	// Animator methods

	@Override
	public void step() {
		for (Animated animatedComponent : animatedComponents) {
			animatedComponent.step();
		}
		
		for (Controlled controlledComponent : controlledComponents) {
			for (Collided collidedComponent: collidedComponents) {
				ArrayList<ArrayList<Point>> collidedComponentsCoords = ((Collided)controlledComponent).checkCollision(collidedComponent);
				if (collisionDetector.checkCollision(collidedComponentsCoords.get(0), collidedComponentsCoords.get(1))) {
					collisionResolver.resolveCollision((Collided)controlledComponent, collidedComponent);
				}
			}
		}
	}

	@Override
	public void addControlledComponent(Controlled controlledComponent) {
		controlledComponents.add(controlledComponent);
	}

	@Override
	public void addAnimatedComponent(Animated animatedComponent) {
		animatedComponents.add(animatedComponent);
	}
	
	@Override
	public void addCollidedComponent(Collided collidedComponent) {
		collidedComponents.add(collidedComponent);
	}

}
