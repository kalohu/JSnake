package jsnake;

import java.util.List;

import jsnake.component.Wall;
import jsnake.component.SnakeHead;
import jsnake.component.SnakeTail;
import jsnake.component.Food;
import jsnake.component.Score;
import jsnake.component.Scene;
import jsnake.FoodPosition;
import jsnake.interfaces.Collided;

public class CollisionResolver {

	private List<Collided> collidedComponents;
	private SnakeTimer snakeTimer;
	private Scene scene;
	private FoodPosition foodCollisions;
	private SnakeTail snakeTail;
	private Score score;
	
	public CollisionResolver(List<Collided> collidedComponents, SnakeTimer snakeTimer, Scene scene, SnakeTail snakeTail, Score score) {
		this.collidedComponents = collidedComponents;
		this.snakeTimer = snakeTimer;
		this.scene = scene;
		this.snakeTail = snakeTail;
		this.score = score;
	}
	
	public void resolveCollision(SnakeHead callerComponent, Wall calledComponent) {
		snakeTimer.stopStep();
	}
	
	public void resolveCollision(SnakeHead callerComponent, SnakeTail calledComponent) {
		snakeTimer.stopStep();
	}

	public void resolveCollision(SnakeHead callerComponent, Food calledComponent) {
		foodCollisions = new FoodPosition(scene, calledComponent, collidedComponents);
		foodCollisions.generatePosition();
		int basicSize = scene.getBasicSize();
		snakeTail.addTailPiece(basicSize);
		score.addScore(1);
	}

	public void resolveCollision(Food callerComponent, Wall calledComponent) {
		foodCollisions.setCollided();
	}

	public void resolveCollision(Food callerComponent, SnakeHead calledComponent) {
		foodCollisions.setCollided();
	}

	public void resolveCollision(Food callerComponent, SnakeTail calledComponent) {
		foodCollisions.setCollided();
	}

	public void resolveCollision(Food callerComponent, Food calledComponent) {
		foodCollisions.setCollided();
	}

}
