package jsnake;

import java.awt.event.KeyEvent;
import jsnake.interfaces.Animated;
import jsnake.interfaces.Controller;
import jsnake.interfaces.Controlled;

public class KeyInterpreter implements Controller, Animated {
	
	private Controlled controlledComponent;
	int horizontalDirection, verticalDirection;
	
	public KeyInterpreter(Controlled controlledComponent) {
		this.controlledComponent = controlledComponent;

		setLeftDirection();
	}
	
	// Controller methods
	
	public void keyEvaluate(int key) {
		if (key == KeyEvent.VK_LEFT) {
			setLeftDirection();
		}
		else if (key == KeyEvent.VK_RIGHT) {
			setRightDirection();
		}
		else if (key == KeyEvent.VK_UP) {
			setUpDirection();
		}
		else if (key == KeyEvent.VK_DOWN) {
			setDownDirection();
		}
	}
	
	private void setLeftDirection() {
		horizontalDirection = -1;
		verticalDirection = 0;		
	}

	private void setRightDirection() {
		horizontalDirection = 1;
		verticalDirection = 0;		
	}

	private void setUpDirection() {
		horizontalDirection = 0;
		verticalDirection = -1;		
	}

	private void setDownDirection() {
		horizontalDirection = 0;
		verticalDirection = 1;		
	}
	
	// Animated methods

	public void step() {
		controlledComponent.setDirection(horizontalDirection, verticalDirection);
	}
	
}
