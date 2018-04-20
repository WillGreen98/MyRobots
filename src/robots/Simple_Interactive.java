package robots;

/**
 * @author Will
 * This is a simple interactive RoboCode Tank :) 
 */

import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyEvent;
import java.util.EnumSet;
import robocode.AdvancedRobot;

public class Simple_Interactive extends AdvancedRobot {
	direction moveDirection;
	int turnDirection;
	double moveAmount;
	
	enum direction {
		RIGHT,
		LEFT,
		FORWARD,
		BACKWARD
	}
	
	public void run() {
		while(true) {
			moveAmount = Math.max(0, moveAmount - 1);
		}
	}
	
	public void onKeyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case VK_UP:
		case VK_W:
			moveDirection = direction.FORWARD;
			moveAmount = Double.POSITIVE_INFINITY;
			break;
		case VK_DOWN:
		case VK_S:
			moveDirection = direction.BACKWARD;
			moveAmount = Double.POSITIVE_INFINITY;
			break;
		case VK_RIGHT:
		case VK_D:
			moveDirection = direction.RIGHT;
			turnDirection = 1;
			break;
		case VK_LEFT:
		case VK_A:
			moveDirection = direction.LEFT;
			turnDirection = -1;
			break;
		}
	}

	public void onKeyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case VK_UP:
		case VK_W:
		case VK_DOWN:
		case VK_S:
			moveAmount = 0;
			break;
		case VK_RIGHT:
		case VK_D:
		case VK_LEFT:
		case VK_A:
			turnDirection = 0;
			break;
		}
	}
}
