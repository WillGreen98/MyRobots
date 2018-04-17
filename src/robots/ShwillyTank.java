package robots;

import java.awt.Color;

import robocode.AdvancedRobot;

/**
 * @author Will
 *
 */
public class ShwillyTank extends AdvancedRobot {
	public void run() {
		while(true) {
			turnGunLeft(360);
			fire(1.0);
		}
	}
}
