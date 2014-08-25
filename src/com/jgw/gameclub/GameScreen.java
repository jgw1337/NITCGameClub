package com.jgw.gameclub;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;

import com.jgw.framework.Image;
import com.jgw.framework.Input.TouchEvent;
import com.jgw.framework.Game;
import com.jgw.framework.Graphics;
import com.jgw.framework.Screen;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Ready;

	/**
	 * Start - Create game objects here
	 */
	private static Background bg1, bg2;
	private static Robot robot;
	public static Heliboy hb1, hb2;
	
	private Image currentSprite, character, character2, character3;
	private Image heliboy1, heliboy2, heliboy3, heliboy4, heliboy5;
	
	private Animation anim, hAnim;
	
	private ArrayList tileArray = new ArrayList();
	/**
	 * End - Create game objects here
	 */

	int livesLeft = 1;
	Paint paint;

	public GameScreen(Game game) {
		super(game);

		/**
		 * Start - Initialize game objects here
		 */
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		
		robot = new Robot();
		
		hb1 = new Heliboy(340, 360);
		hb1 = new Heliboy(700, 360);

		character = Assets.character;
		character2 = Assets.character2;
		character3 = Assets.character3;
		
		heliboy1 = Assets.heliboy1;
		heliboy2 = Assets.heliboy2;
		heliboy3 = Assets.heliboy3;
		heliboy4 = Assets.heliboy4;
		heliboy5 = Assets.heliboy5;
		
		anim = new Animation();
		anim.addFrame(character, 50);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character4, 50);

		hAnim = new Animation();
		hAnim.addFrame(heliboy, 100);
		hAnim.addFrame(heliboy2, 100);
		hAnim.addFrame(heliboy3, 100);
		hAnim.addFrame(heliboy4, 100);
		hAnim.addFrame(heliboy5, 100);
		hAnim.addFrame(heliboy4, 100);
		hAnim.addFrame(heliboy3, 100);
		hAnim.addFrame(heliboy2, 100);
		
		currentSprite = anim.getImage();
		
		loadMap();
		/**
		 * End - Initialize game objects here
		 */

		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);
	}

	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;
		
		Scanner scanner = new Scanner(Game.map);
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if (line == null) {
				break;
			}
			
			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length();)
			}
			
		}
		
		height = lines.size();
		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile(i, j, Character.getNumericValue(ch));
					tileArray.add(t);
				}
			}
		}
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		switch (state) {
		case Ready:
			updateReady(touchEvents);
			break;
		case Running:
			updateRunning(touchEvents, deltaTime);
			break;
		case Paused:
			updatePaused(touchEvents);
			break;
		case GameOver:
			updateGameOver(touchEvents);
			break;
		}
	}

	private void updateReady(List<TouchEvent> touchEvents) {
		if (touchEvents.size() > 0) {
			state = GameState.Ready;
		}
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {
		// 1) All touch input handled here
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case TouchEvent.TOUCH_DOWN:
				if (inBounds(event, 0, 320, 65, 65)) {
					robot.jump();
					currentSprite = anim.getImage();
					robot.setDuched(false);
				} else if (event.x > 640) {
					// Move right
				}
				break;
			case TouchEvent.TOUCH_UP:
				if (event.x < 640) {
					// Stop moving left
				} else if (event.x > 640) {
					// Stop moving right
				}
				break;
			}
		}

		// 2) Check misc events like death
		if (livesLeft == 0) {
			state = GameState.GameOver;
		}

		// 3) Call individual update methods like robot.update()

	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			switch (event.type) {
			case TouchEvent.TOUCH_UP:
				// TODO
				break;
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.x > 300 && event.x < 980 && event.y > 100 & event.y < 500) {
				nullify();
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
	}

	private void nullify() {
		paint = null;
		// Garbage collector
		System.gc();
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		// 1) Draw game elements

		// 2) Draw UI above the game elements
		switch (state) {
		case Ready:
			drawReadyUI();
			break;
		case Running:
			drawRunningUI();
			break;
		case Paused:
			drawPausedUI();
			break;
		case GameOver:
			drawGameOverUI();
			break;
		}
	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap each side of the screen to move in that direction.",
				640, 300, paint);
	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();

		// Darken screen so you can display paused message
		g.drawARGB(155, 0, 0, 0);
	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		
		g.drawRect(0, 0, 1281, 801, Color.BLACK);
		g.drawString("GAME OVER", 640, 300, paint);
	}

	@Override
	public void pause() {
		if (state == GameState.Running) {
			state = GameState.Paused;
		}
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub

	}

}
