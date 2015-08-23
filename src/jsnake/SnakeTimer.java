package jsnake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import jsnake.interfaces.Iterator;

public class SnakeTimer extends Timer implements Iterator {
	
	public SnakeTimer(int delay, Engine engine) {
		super(delay, new SnakeTimerActionListener(engine));
	}
	
	// Iterator methods

	public void startStep() {
		start();
	}
	
	public void stopStep() {
		stop();
	}

}

class SnakeTimerActionListener implements ActionListener {
	
	private Engine engine;
	
	public SnakeTimerActionListener(Engine engine) {
		this.engine = engine;
	}
	
	public void actionPerformed(ActionEvent ae) {
		engine.step();
	}
	
}
