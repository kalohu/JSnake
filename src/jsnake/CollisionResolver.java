package jsnake;

import jsnake.component.Wall;
import jsnake.component.SnakeTail;
import jsnake.component.Food;
import jsnake.component.Score;
import jsnake.interfaces.Collided;
import jsnake.interfaces.Renderer;

public class CollisionResolver {

	private Renderer rendererComponent;
	private SnakeTimer snakeTimer;
	private Score score;
	private SnakeTail snakeTail;
	private Food food;
	
	public void setRendererComponent(Renderer rendererComponent) {
		this.rendererComponent = rendererComponent;
	}
	
	public void setSnakeTimer(SnakeTimer snakeTimer) {
		this.snakeTimer = snakeTimer;
	}
	
	public void setScore(Score score) {
		this.score = score;
	}
	
	public void setSnakeTail(SnakeTail snakeTail) {
		this.snakeTail = snakeTail;
	}
	
	public void setFood(Food food) {
		this.food = food;
	}
	
	public void resolveCollision(Collided callerComponent, Collided calledComponent) {
		if (calledComponent instanceof Wall) {
			snakeTimer.stopStep();
		}
		else if (calledComponent instanceof SnakeTail) {
			snakeTimer.stopStep();
		}
		else if (calledComponent instanceof Food) {
			score.addScore();
			int basicSize = rendererComponent.getBasicSize();
			snakeTail.addTailPiece(basicSize);
			food.init(rendererComponent);
		}
	}

}
