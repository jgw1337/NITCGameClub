package com.jgw.gameclub;

import android.graphics.Rect;

public class Enemy {
	private int power, speedX, speedY, centerX, centerY;
	private Background bg = GameScreen.getBg1();
	public Rect r = new Rect(0, 0, 0, 0);
	public int health = 5;
	
	private int movementSpeedX, movementSpeedY;

	public void update() {
		follow();
		centerX += speedX;
		centerY += speedY;

		speedX = bg.getSpeedX() * 5 + movementSpeedX;
		speedY = movementSpeedY;
		r.set(centerX - 25, centerY - 25, 50, 60);
		if (r.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}
	}

	private void follow() {
		if (centerX < -95 || centerX > 810) {
			movementSpeedX = 0;
		} else if (Math.abs(Robot.getCenterX() - centerX) < 5) {
			movementSpeedX = 0;
		} else {
			if (Robot.getCenterX() >= centerX) {
				movementSpeedX = 1;
			} else {
				movementSpeedX = -1;
			}

		}
		if (centerY < 0 || centerY > 480) {
			movementSpeedY = 0;
		} else if (Math.abs(Robot.getCenterY() - centerY) < 5) {
			movementSpeedY = 0;
		} else {
			if (Robot.getCenterY() >= centerY) {
				movementSpeedY = 1;
			} else {
				movementSpeedY = -1;
			}

		}
	}

	private void checkCollision() {
		if (r.intersects(r, Robot.bodyUpper) || r.intersects(r, Robot.bodyLower)
				|| r.intersects(r, Robot.armStageLeft)
				|| r.intersects(r, Robot.armStageRight)) {
			System.out.println("collision");
		}
	}

	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
}
