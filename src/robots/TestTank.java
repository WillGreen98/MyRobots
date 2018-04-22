package robots;

/**
 * @author Will
 * This is one of the first RoboCode Tanks I have created.
 */

import static robocode.util.Utils.normalRelativeAngle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import com.sun.org.apache.bcel.internal.generic.NEW;

import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.Bullet;
import robocode.DeathEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.RobotDeathEvent;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

public class TestTank extends AdvancedRobot {
	Bullet bullet;
	boolean g_Running = true;
	
	public Color randColor() {
		Random rand = new Random();
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randColor = new Color(r, g, b);
		
		return randColor;
	} 
	
	public void run() {
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);
		
		Color[] colour_Init = new Color[3];
		ArrayList<Color> colors = new ArrayList<>();
		
		for(int i=0; i<=colour_Init.length; i++) {
			colour_Init[i] = randColor();
			colors.add(colour_Init[i]);
		}
	
		setBodyColor(Color.BLACK);
		setGunColor(colors.get(0));
		setBulletColor(colors.get(1));
		setRadarColor(colors.get(2));
		setScanColor(colors.get(3));
		
		while(g_Running) {
			turnGunLeft(40);
		}
	}
	
	int scannedX = Integer.MIN_VALUE;
	int scannedY = Integer.MIN_VALUE;

	public void onScannedRobot(ScannedRobotEvent e) {
	    double angle = Math.toRadians((getHeading() + e.getBearing()) % 360);
	 
	    scannedX = (int)(getX() + Math.sin(angle) * e.getDistance());
	    scannedY = (int)(getY() + Math.cos(angle) * e.getDistance());
	    
	    double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngle(absoluteBearing - getGunHeading());
		if(getGunHeat() == 0 && bullet == null) {
			final Bullet lbullet = fireBullet(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - 1));
			bullet = lbullet;
		}
	}
	
	public void onHitRobot(HitRobotEvent e) {
		if(e.isMyFault()) {
			System.out.println(e.getName() + " My fault, sorry.");
		} else {
			double tank_Bearing = e.getBearing();
			if(tank_Bearing <= 180) {
				turnGunLeft(tank_Bearing);
				fire(1);
			} else {
				turnGunRight(tank_Bearing);
				fire(1);
			}
		}
	}
	
	public void onHitWall(HitWallEvent e) {

	}
	
	public void onPaint(Graphics2D g) {
	    g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	    g.drawLine(scannedX, scannedY, (int)getX(), (int)getY());
	    g.fillRect(scannedX - 20, scannedY - 20, 40, 40);
	}
	
	@Override
	public void onWin(WinEvent event) {
		out.println("Win!");
		
		Color _randColor = randColor();
		turnRadarLeft(Double.POSITIVE_INFINITY);
		for(int i=0; i <= 8; i++) {
			setScanColor(_randColor);
			turnLeft(45.0);
			turnRight(90.0);
			for(int j=0; j <= 7; j++) {
				turnGunRight(45.0);
				turnGunLeft(90.0);
			}
		}
	}
	
	public void onDeath(RobotDeathEvent RBE) {
		System.out.println("Dead! You were killed by: ");
	}

	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		System.out.println("Round Ended!");
		g_Running = false;
		
	}

	@Override
	public void onBattleEnded(BattleEndedEvent event) {
		System.out.println("Battle Ended!");
	}
}