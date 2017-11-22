package com.fantasylum.rgbird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.fantasylum.screens.GameScreen
import com.fantasylum.utils.MyStack

class RGBirdGame : ApplicationAdapter() {

	private val screenStack = MyStack<Screen>()

	override fun create () {
		screenStack.push(GameScreen())
	}

	override fun render () {
		screenStack.peek()?.render(Gdx.graphics.deltaTime)
	}

	fun removeScreen() {
		screenStack.pop()?.dispose()
	}

	fun replaceScreen(screen : Screen) {
		screenStack.pop()?.dispose()
		screenStack.push(screen)
	}

	fun pushScreen(screen : Screen) {
		screenStack.push(screen)
	}

}
