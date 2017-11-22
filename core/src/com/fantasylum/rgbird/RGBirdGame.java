package com.fantasylum.rgbird;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.fantasylum.screens.GameScreen;

public class RGBirdGame extends Game {
	
	@Override
	public void create () {
		setScreen(new GameScreen());
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
