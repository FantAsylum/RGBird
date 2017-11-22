package com.fantasylum.screens

import com.badlogic.gdx.Screen
import com.fantasylum.gameworld.GameRenderer
import com.fantasylum.gameworld.GameWorld

class GameScreen : Screen {

    private val world = GameWorld()
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
