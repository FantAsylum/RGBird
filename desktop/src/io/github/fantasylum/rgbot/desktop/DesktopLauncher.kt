package io.github.fantasylum.rgbot.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.fantasylum.rgbot.RGBot

fun main(args: Array<String>) {
		val config = LwjglApplicationConfiguration();
		config.title = "RGBot";
		config.width = 272;
		config.height = 408;
		LwjglApplication(RGBot, config);
}
