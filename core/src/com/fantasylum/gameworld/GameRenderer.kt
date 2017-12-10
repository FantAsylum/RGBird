package com.fantasylum.gameworld

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

class GameRenderer(private val world : GameWorld,
                   private val gameHeight : Float) {

    private val cam = OrthographicCamera()
    private val shapeRenderer = ShapeRenderer()
    private val batcher = SpriteBatch()
    private val frontGrass = world.scroller.frontGrass
    private val backGrass = world.scroller.backGrass

    init {
        cam.setToOrtho(true, 136f, gameHeight)
        shapeRenderer.projectionMatrix = cam.combined
        batcher.projectionMatrix = cam.combined
    }

    fun render(runTime : Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)

        shapeRenderer.setColor(88 / 255.0f, 193 / 255.0f, 214 / 255.0f, 1f)
        shapeRenderer.rect(0f, 0f, 136f, gameHeight / 2 + 66f)

        shapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1f)
        shapeRenderer.rect(0f, gameHeight / 2 + 66, 136f, 11f)

        shapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1f)
        shapeRenderer.rect(0f, gameHeight / 2 + 77, 136f, 52f)

        shapeRenderer.end()

        batcher.begin()
        batcher.draw(world.bird.animations.peek()!!.getKeyFrame(runTime), world.bird.position.x, world.bird.position.y, world.bird.width / 2f,
                world.bird.height / 2f, world.bird.width, world.bird.height, 1f, 1f, world.bird.rotation )

        batcher.end()
    }

}