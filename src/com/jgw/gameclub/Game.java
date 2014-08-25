package com.jgw.gameclub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

import com.jgw.framework.Screen;
import com.jgw.framework.implementation.AndroidGame;

public class Game extends AndroidGame {
	public static String map;
	boolean firstTimeCreate = true;

	@Override
	public Screen getInitScreen() {
		if (firstTimeCreate) {
			Assets.load(this);
			firstTimeCreate = false;
		}
		
		InputStream is = getResources().openRawResource(R.raw.map1);
		map = convertStreamToString(is);
		
		return new SplashLoadingScreen(this);
	}
	
	private String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
				
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (Exception e2) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

	@Override
	public void onBackPressed() {
		getCurrentScreen().backButton();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Assets.theme.play();
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Assets.theme.pause();
	}
}
