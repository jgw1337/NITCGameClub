package com.jgw.gameclub;

import android.graphics.Rect;

import com.jgw.framework.Image;

public class Tile {
	private int tileX, tileY, speedX;
	public int type;
	public Image tileImage;
	private int pixelSize = 40;
	private Rect r;

	private Robot robot = GameScreen.getRobot();
	private Background bg = GameScreen.getBg1();

	public Tile(int x, int y, int typeInt) {
		tileX = x * pixelSize;
		tileY = y * pixelSize;

		type = typeInt;

		r = new Rect();

		/*
		 * switch (type) { case 1: tileImage = StartingClass.tileOcean; break;
		 * case 2: tileImage = StartingClass.tileDirt; break; }
		 */
		switch (type) {
		case 5:
			tileImage = Assets.tileDirt;
			break;
		case 8:
			tileImage = Assets.tileGrassTop;
			break;
		case 4:
			tileImage = Assets.tileGrassLeft;
			break;
		case 6:
			tileImage = Assets.tileGrassRight;
			break;
		case 2:
			tileImage = Assets.tileGrassBottom;
			break;
		default:
			type = 0;
			break;
		}
	}

	public void update() {
		/*
		 * For parallax effect if (type == 1) { if (bg.getSpeedX() == 0) {
		 * speedX = -1; } else { speedX = -2; } } else { speedX = bg.getSpeedX()
		 * * 5; } speedX = -1;
		 */
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		r.set(tileX, tileY, tileX + 40, tileY + 40);

		if (Rect.intersects(r, Robot.yellowRed) && type != 0) {
			checkVerticalCollision(Robot.bodyUpper, Robot.bodyLower);
			checkSideCollision(Robot.armStageLeft, Robot.armStageRight,
					Robot.footStageLeft, Robot.footStageRight);
		}
	}

	public void checkVerticalCollision(Rect rTop, Rect rBottom) {
		if (Rect.intersects(rTop, r)) {

		}

		if (Rect.intersects(rBottom, r) && type == 8) {
			robot.setJumped(false);
			robot.setSpeedY(0);
			robot.setCenterY(tileY - 63);
		}
	}

	public void checkSideCollision(Rect rArmStageLeft,
			Rect rArmStageRight, Rect rFootStageLeft,
			Rect rFootStageRight) {
		if (type != 5 && type != 2 && type != 0) {
			if (Rect.intersects(rArmStageLeft, r)) {
				robot.setCenterX(tileX + 102);
				robot.setSpeedX(0);
			} else if (Rect.intersects(rFootStageLeft, r)) {
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}
			
			if (Rect.intersects(rArmStageRight, r)) {
				robot.setCenterX(tileX - 62);
				robot.setSpeedX(0);
			} else if(Rect.intersects(rFootStageRight, r)) {
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
		}
	}

	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}
}
