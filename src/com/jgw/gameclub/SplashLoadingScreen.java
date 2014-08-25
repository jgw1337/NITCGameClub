package com.jgw.gameclub;

import com.jgw.framework.Game;
import com.jgw.framework.Graphics;
import com.jgw.framework.Screen;
import com.jgw.framework.Graphics.ImageFormat;

public class SplashLoadingScreen extends Screen {
	public SplashLoadingScreen(Game game) {
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.splash = g.newImage("splash.jpg", ImageFormat.RGB565);
		
		game.setScreen(new LoadingScreen(game));
	}

	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
