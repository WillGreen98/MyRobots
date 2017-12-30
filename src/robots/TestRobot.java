package robots;

import static robocode.util.Utils.normalRelativeAngle;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import robocode.AdvancedRobot;
import robocode.BattleEndedEvent;
import robocode.Bullet;
import robocode.DeathEvent;
import robocode.RoundEndedEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

public class TestRobot extends AdvancedRobot {
	public Color randColor() {
		Random rand = new Random();
		
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		Color randColor = new Color(r, g, b);
		
		return randColor;
	} 
	
	public void run() {
		Color _randColor = randColor();
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
			
		setBodyColor(_randColor);
		setGunColor(_randColor);
		setBulletColor(_randColor);
		setRadarColor(_randColor);
		setScanColor(_randColor);
	}
	
	int scannedX = Integer.MIN_VALUE;
	int scannedY = Integer.MIN_VALUE;
	
	Bullet bullet;
	 
	public void onScannedRobot(ScannedRobotEvent e) {
	    double angle = Math.toRadians((getHeading() + e.getBearing()) % 360);
	 
	    scannedX = (int)(getX() + Math.sin(angle) * e.getDistance());
	    scannedY = (int)(getY() + Math.cos(angle) * e.getDistance());
	    
	    double absoluteBearing = getHeading() + e.getBearing();
		double bearingFromGun = normalRelativeAngle(absoluteBearing - getGunHeading());
		if (getGunHeat() == 0 && bullet == null) {
			final Bullet lbullet = fireBullet(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - 1));
			
			bullet = lbullet;
		}
	}
	
	public void onPaint(Graphics2D g) {
	    g.setColor(new Color(0xff, 0x00, 0x00, 0x80));
	    g.drawLine(scannedX, scannedY, (int)getX(), (int)getY());
	    g.fillRect(scannedX - 20, scannedY - 20, 40, 40);
	}
	
	@Override
	public void onWin(WinEvent e) {
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

	@Override
	public void onDeath(DeathEvent e) {
		System.out.println("Dead! You were killed by: ");
	}

	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		System.out.println("RoundEnded!");
	}

	@Override
	public void onBattleEnded(BattleEndedEvent event) {
		System.out.println("BattleEnded!");
	}
}