package io.github.fantasylum.rgbot.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.screens.GameScreen

fun main(args: Array<String>) {
		val config = LwjglApplicationConfiguration()
		config.title = "RGBot"
		config.width = phoneSizes[1][1]
		config.height = phoneSizes[1][0]
		LwjglApplication(RGBot, config)
}

private val phoneSizes = arrayOf(
		intArrayOf(340, 480), // iPhone 3gs         // 1
		intArrayOf(270, 480), // FullHD equivalent  // 2
		intArrayOf(270, 860), // Ultra Height       // 3
		intArrayOf(860, 270)) // Ultra Width        // 4
