package com.fantasylum.screens

import com.badlogic.gdx.Screen
import com.fantasylum.gameworld.GameRenderer
import com.fantasylum.gameworld.GameWorld
import com.badlogic.gdx.Gdx



class GameScreen : Screen {

    private val screenWidth = Gdx.graphics.width.toFloat()
    private val screenHeight = Gdx.graphics.height.toFloat()
    private val gameWidth = 136f
    private val gameHeight = screenHeight / (screenWidth / gameWidth)

    private val world = GameWorld(gameHeight / 2)
    private val renderer = GameRenderer(world)

    override fun show() {

    }

    override fun render(delta : Float) {
        world.update(delta)
        renderer.render()
    }

    override fun resize(width : Int, height : Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {

    }
}
