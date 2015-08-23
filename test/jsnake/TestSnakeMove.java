package jsnake;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Test;

import jsnake.KeyInterpreter;
import jsnake.interfaces.Controlled;
import java.awt.event.KeyEvent;

@RunWith(JMock.class)
public class TestSnakeMove {

	Mockery context = new JUnit4Mockery();

	@Test
	public void created_KeyInterpreter_Should_Make_Snake_Moving_Left() {
		final Controlled controlled = context.mock(Controlled.class);
		KeyInterpreter keyInterpreter = new KeyInterpreter(controlled);
        context.checking(new Expectations() {{
            oneOf (controlled).setDirection(-1, 0);
        }});

        keyInterpreter.step();
	}

	@Test
	public void keyInterpreter_Evaluate_Left_Direction_Should_Make_Snake_Moving_Left() {
		final Controlled controlled = context.mock(Controlled.class);
		KeyInterpreter keyInterpreter = new KeyInterpreter(controlled);
        context.checking(new Expectations() {{
            oneOf (controlled).setDirection(-1, 0);
        }});

        keyInterpreter.keyEvaluate(KeyEvent.VK_LEFT);
        keyInterpreter.step();
	}

	@Test
	public void keyInterpreter_Evaluate_Right_Direction_Should_Make_Snake_Moving_Right() {
		final Controlled controlled = context.mock(Controlled.class);
		KeyInterpreter keyInterpreter = new KeyInterpreter(controlled);
        context.checking(new Expectations() {{
            oneOf (controlled).setDirection(1, 0);
        }});

        keyInterpreter.keyEvaluate(KeyEvent.VK_RIGHT);
        keyInterpreter.step();
	}

	@Test
	public void keyInterpreter_Evaluate_Up_Direction_Should_Make_Snake_Moving_Up() {
		final Controlled controlled = context.mock(Controlled.class);
		KeyInterpreter keyInterpreter = new KeyInterpreter(controlled);
        context.checking(new Expectations() {{
            oneOf (controlled).setDirection(0, -1);
        }});

        keyInterpreter.keyEvaluate(KeyEvent.VK_UP);
        keyInterpreter.step();
	}

	@Test
	public void keyInterpreter_Evaluate_Down_Direction_Should_Make_Snake_Moving_Down() {
		final Controlled controlled = context.mock(Controlled.class);
		KeyInterpreter keyInterpreter = new KeyInterpreter(controlled);
        context.checking(new Expectations() {{
            oneOf (controlled).setDirection(0, 1);
        }});

        keyInterpreter.keyEvaluate(KeyEvent.VK_DOWN);
        keyInterpreter.step();
	}

	@Test
	public void keyInterpreter_Evaluate_Unknown_Direction_Should_Not_Change_Direction() {
		final Controlled controlled = context.mock(Controlled.class);
		KeyInterpreter keyInterpreter = new KeyInterpreter(controlled);
        context.checking(new Expectations() {{
            oneOf (controlled).setDirection(-1, 0);
        }});

        keyInterpreter.keyEvaluate(KeyEvent.VK_A);
        keyInterpreter.step();
	}

}
