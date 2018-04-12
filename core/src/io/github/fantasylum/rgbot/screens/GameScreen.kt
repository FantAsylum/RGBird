package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.utils.Array as GdxArray
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.PooledLinkedList
import io.github.fantasylum.rgbot.Color
import io.github.fantasylum.rgbot.actors.*

class GameScreen: ScreenAdapter() {
    private val mainStage       = Stage()
    private val camera          = mainStage.camera
    private val bot: Bot        = FlappyBot()
    // TODO: add score

    private val obstacleManager = ObstacleManager(mainStage.width, 150f, bot, mainStage)

    init {
        mainStage.addActor(bot)
        bot.x = mainStage.width  / 2f
        bot.y = mainStage.height / 2f

        when (bot) {
            is FlappyBot ->  mainStage.addListener(object : InputListener() {
                override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    bot.moveUp()
                    return true
                }
            })
            is SimpleBot -> mainStage.addListener(object : InputListener() {
                val OBSERVABLE_TOUCHES_COUNT = 2
                val touches = Array<Int?>(OBSERVABLE_TOUCHES_COUNT, { null })

                fun updatePoints() {
                    for (i in 0 until OBSERVABLE_TOUCHES_COUNT)
                        touches[i] = if (Gdx.input.isTouched(i))
                                         Gdx.input.getY(i)
                                     else
                                         null

                    bot.isMovingUp   = touches.any { it != null && it < camera.viewportHeight / 2 }
                    bot.isMovingDown = touches.any { it != null && it > camera.viewportHeight / 2 }
                }

                override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    if (pointer <= OBSERVABLE_TOUCHES_COUNT) {
                        updatePoints()
                        return true
                    }
                    return false
                }

                override fun touchDragged(event: InputEvent, x: Float, y: Float, pointer: Int) {
                    updatePoints()
                }

                override fun touchUp(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int) {
                    updatePoints()
                }
            })

        }
        Gdx.input.inputProcessor = mainStage
    }

    override fun render(delta: Float) {
        mainStage.act(delta)
        obstacleManager.act()
        mainStage.draw()

        camera.position.x = bot.x
        camera.position.y = bot.y
    }
}
