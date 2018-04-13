package io.github.fantasylum.rgbot.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.screens.GameScreen

fun main(args: Array<String>) {
		val config = LwjglApplicationConfiguration();
		config.title = "RGBot";
		config.width = GameScreen.WORLD_WIDTH.toInt()
		config.height = GameScreen.WORLD_HEIGHT.toInt()
		LwjglApplication(RGBot, config);
}
