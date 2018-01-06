package io.github.fantasylum.rgbot.android

import android.os.Bundle

import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

import io.github.fantasylum.rgbot.RGBot

class AndroidLauncher: AndroidApplication() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState);
		val config = AndroidApplicationConfiguration();
		initialize(RGBot, config);
	}
}
