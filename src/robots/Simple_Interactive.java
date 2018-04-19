package robots;

/**
 * @author Will
 * This is a simple interactive RoboCode Tank :) 
 */

import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyEvent;
import java.util.EnumSet;

/**
 * A very simple interactive bot
 */
import robocode.AdvancedRobot;

public class Simple_Interactive extends AdvancedRobot {
	int moveDirection;
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
			setAhead(moveAmount * moveDirection);
			moveAmount = Math.max(0, moveAmount - 1);
		}
	}
	
	public void onKeyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case VK_UP:
		case VK_W:
			moveDirection = 1;
			moveAmount = Double.POSITIVE_INFINITY;
			break;
		case VK_DOWN:
		case VK_S:
			moveDirection = -1;
			moveAmount = Double.POSITIVE_INFINITY;
			break;

		case VK_RIGHT:
		case VK_D:
			turnDirection = 1;
			break;

		case VK_LEFT:
		case VK_A:
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
			moveDirection = 0;
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
