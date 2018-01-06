package io.github.fantasylum.rgbot

import java.util.LinkedList

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen

import io.github.fantasylum.rgbot.screens.MenuScreen

object RGBot : ApplicationAdapter() {
    private val screenStack = LinkedList<Screen>()

    override fun create() {
        pushScreen(MenuScreen())
    }

    override fun render() {
        screenStack.peek().render(Gdx.graphics.deltaTime)
    }

    fun pushScreen(screen: Screen) {
        screenStack.push(screen)
    }

    fun popScreen() {
        screenStack.pop()
        if (screenStack.isEmpty())
            Gdx.app.exit()
    }

    fun replaceScreen(screen: Screen) {
        screenStack.pop()
        screenStack.push(screen)
    }
}
