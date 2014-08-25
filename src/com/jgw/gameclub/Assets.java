package com.jgw.gameclub;

import com.jgw.framework.Image;
import com.jgw.framework.Music;
import com.jgw.framework.Sound;

public class Assets {
	public static Image menu, splash;
	public static Image background;
	public static Image character, character2, character3;
	public static Image characterJump, characterDown;
	public static Image heliboy1, heliboy2, heliboy3, heliboy4, heliboy5;
	public static Image tileDirt, tileGrassTop, tileGrassBottom, tileGrassLeft, tileGrassRight;
	public static Image button;

	public static Sound click;
	public static Music theme;
	
	public static void load(MyGame game) {
		theme  = game.getAudio().createMusic("menutheme.mp3");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
}
