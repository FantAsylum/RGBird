package com.fantasylum.rgbird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fantasylum.rgbird.RGBirdGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "RGBird";
		config.width = 272;
		config.height = 408;
		new LwjglApplication(new RGBirdGame(), config);
	}
}
