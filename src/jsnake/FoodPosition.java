package jsnake;

import java.util.List;
import java.awt.Point;

import jsnake.component.Food;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Renderer;

public class FoodPosition {
	
	private Food foodComponent;
	private List<Collided> collidedComponents;
	private Renderer rendererComponent;
	private boolean isCollided;
	
	public FoodPosition(Renderer rendererComponent, Food foodComponent, List<Collided> collidedComponents) {
		this.foodComponent = foodComponent;
		this.collidedComponents = collidedComponents;
		this.rendererComponent = rendererComponent;
	}
	
	public void generatePosition() {
		do {
			isCollided = false;
			Point generatedPosition = rendererComponent.generatePosition();
			foodComponent.setPosition(generatedPosition.x, generatedPosition.y);
			for (Collided collidedComponent: collidedComponents) {
				if (!(collidedComponent instanceof Food)) {
					foodComponent.checkCollision(collidedComponent);
				}
			}
		} while (isCollided);
	}
	
	public void setCollided() {
		isCollided = true;
	}

}
