package robots;

/**
 * @author Will
 * This is my Tank => Shwillybum
 */

import java.awt.Color;
import robocode.AdvancedRobot;

public class ShwillyTank extends AdvancedRobot {
	public void run() {
		while(true) {
			turnGunLeft(360);
			fire(1.0);
		}
	}
}
