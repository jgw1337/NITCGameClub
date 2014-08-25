package com.jgw.gameclub;

import com.jgw.framework.Game;
import com.jgw.framework.Graphics;
import com.jgw.framework.Graphics.ImageFormat;
import com.jgw.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);

		Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
		Assets.character2 = g.newImage("character2.png", ImageFormat.ARGB4444);
		Assets.character3 = g.newImage("character3.png", ImageFormat.ARGB4444);
		Assets.characterJump = g.newImage("characterjumped.png", ImageFormat.ARGB4444);
		Assets.characterDown = g.newImage("characterdown.png", ImageFormat.ARGB4444);

		Assets.heliboy1 = g.newImage("heliboy1.png", ImageFormat.ARGB4444);
		Assets.heliboy2 = g.newImage("heliboy2.png", ImageFormat.ARGB4444);
		Assets.heliboy3 = g.newImage("heliboy3.png", ImageFormat.ARGB4444);
		Assets.heliboy4 = g.newImage("heliboy4.png", ImageFormat.ARGB4444);
		Assets.heliboy5 = g.newImage("heliboy5.png", ImageFormat.ARGB4444);
		
		Assets.tileDirt = g.newImage("tiledirt.png", ImageFormat.RGB565);
		Assets.tileGrassTop = g.newImage("tilegrasstop.png", ImageFormat.RGB565);
		Assets.tileGrassBottom = g.newImage("tilegrassbottom.png", ImageFormat.RGB565);
		Assets.tileGrassLeft = g.newImage("tilegrassleft.png", ImageFormat.RGB565);
		Assets.tileGrassRight = g.newImage("tilegrassright.png", ImageFormat.RGB565);

		Assets.button = g.newImage("button.png", ImageFormat.RGB565);
		// This is how you would load a sound if you had one
		// Assets.click = game.getAudio().createSound("explode.ogg");
		
		game.setScreen(new MainMenuScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

	@Override
	public void backButton() {
		
	}
	
}
