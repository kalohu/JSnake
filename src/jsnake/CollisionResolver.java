package jsnake;

import java.awt.Point;
import java.util.ArrayList;

import jsnake.component.Wall;
import jsnake.component.SnakeHead;
import jsnake.component.SnakeTail;
import jsnake.component.Food;
import jsnake.component.Score;
import jsnake.interfaces.Renderer;

public class CollisionResolver {

	private CollisionDetector collisionDetector;
	private SnakeTimer snakeTimer;
	private Renderer rendererComponent;
	private SnakeTail snakeTail;
	private Score score;
	
	public CollisionResolver(CollisionDetector collisionDetector, SnakeTimer snakeTimer, Renderer rendererComponent, SnakeTail snakeTail, Score score) {
		this.collisionDetector = collisionDetector;
		this.snakeTimer = snakeTimer;
		this.rendererComponent = rendererComponent;
		this.snakeTail = snakeTail;
		this.score = score;
	}
	
	public void resolveCollision(SnakeHead callerComponent, Wall calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (collisionDetector.checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			snakeTimer.stopStep();
		}
	}

	public void resolveCollision(SnakeHead callerComponent, Food calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (collisionDetector.checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			snakeTail.addTailPiece(rendererComponent.getBasicSize());
			score.addScore();
			calledComponent.init(rendererComponent);
		}
	}

	public void resolveCollision(SnakeHead callerComponent, SnakeTail calledComponent, ArrayList<ArrayList<Point>> componentsCoords) {
		if (collisionDetector.checkCollision(componentsCoords.get(0), componentsCoords.get(1))) {
			snakeTimer.stopStep();
		}
	}

}
