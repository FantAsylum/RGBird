package io.github.fantasylum.rgbot

import java.util.LinkedList

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.scenes.scene2d.ui.Skin

import io.github.fantasylum.rgbot.screens.MenuScreen

object RGBot : ApplicationAdapter() {
    private val screenStack = LinkedList<Screen>()
    private val assetManager = AssetManager()

    override fun create() {
        pushScreen(MenuScreen())
    }

    override fun render() {
        screenStack.peek().render(Gdx.graphics.deltaTime)
    }

    override fun dispose() {
        assetManager.dispose()
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

    fun getSkin(id: String): Skin {
        assetManager.load(id, Skin::class.java)
        assetManager.finishLoading()
        return assetManager.get(id)
    }
}
