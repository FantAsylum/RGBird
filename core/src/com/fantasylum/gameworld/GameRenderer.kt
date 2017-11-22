package com.fantasylum.gameworld

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class GameRenderer {

    val cam = OrthographicCamera()
    val shapeRenderer = ShapeRenderer()

    constructor(world : GameWorld) {
        shapeRenderer.projectionMatrix = cam.combined
    }

    fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    }

}