package robots;

/**
 * @author Will
 * This is a simple interactive RoboCode Tank :)
 */

import static java.awt.event.KeyEvent.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import robocode.AdvancedRobot;

public class Simple_Interactive extends AdvancedRobot {
	Direction moveDirection;
	private final Set<Direction> directions = new HashSet<Direction>();
	
	@Override
	public void setAdjustGunForRobotTurn(boolean independent) {
		independent = true;
		super.setAdjustGunForRobotTurn(independent);
	}
	
	@Override
	public void setAdjustRadarForRobotTurn(boolean independent) {
		independent = true;
		super.setAdjustRadarForRobotTurn(independent);
	}
	
	@Override
	public void setAdjustRadarForGunTurn(boolean independent) {
		independent = true;
		super.setAdjustRadarForGunTurn(independent);
	}
	
	enum Direction {
		RIGHT,
		LEFT,
		FORWARD,
		BACKWARD
	}
	
	// Main Body - Will get to this.
	public void run() {
		while(true) {
			execute();
		}
	}
	
	public void onKeyPressed(KeyEvent ke) {
		switch(ke.getKeyCode()) {
		case VK_UP:
		case VK_W:
			directions.add(Direction.FORWARD);
			break;
		case VK_DOWN:
		case VK_S:
			directions.add(Direction.BACKWARD);
			break;
		case VK_RIGHT:
		case VK_D:
			directions.add(Direction.RIGHT);
			break;
		case VK_LEFT:
		case VK_A:
			directions.add(Direction.LEFT);
			break;
		case VK_SPACE:
			fire(1);
		}
	}

	public void onKeyReleased(KeyEvent ke) {
		switch(ke.getKeyCode()) {
		case VK_UP:
		case VK_W:
			directions.remove(Direction.FORWARD);
			break;
		case VK_DOWN:
		case VK_S:
			directions.remove(Direction.BACKWARD);
			break;
		case VK_RIGHT:
		case VK_D:
			directions.remove(Direction.RIGHT);
			break;
		case VK_LEFT:
		case VK_A:
			directions.remove(Direction.LEFT);
			break;
		}
	}
}
