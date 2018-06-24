package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.utils.Array as GdxArray
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import io.github.fantasylum.rgbot.actors.*
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.math.MathUtils.random
import com.badlogic.gdx.math.Vector2


class GameScreen: ScreenAdapter() {
    private val viewport  = FitViewport(WORLD_WIDTH, WORLD_HEIGHT)
    private val mainStage = Stage(viewport)
    private val camera    = mainStage.camera
    //private val bot: Bot  = SimpleBot(Obstacle.DEFAULT_HEIGHT * 0.75f, Obstacle.DEFAULT_HEIGHT * 0.5f, Obstacle.DEFAULT_HEIGHT * 0.25f)

    private var elapsed = 0f
    private var duration = 0f
    private var radius = 0f
    private var randomAngle = random.nextFloat() % 360f

    private val bot: Bot  = FingerBot()

    private var destroyAnimationPlayed = false
    private var gameOver = false
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

                    bot.isMovingUp   = touches.any { it != null && it < Gdx.graphics.height / 2 }
                    bot.isMovingDown = touches.any { it != null && it > Gdx.graphics.height / 2 }
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
            is FingerBot -> mainStage.addListener(object : InputListener() {
                var input = Vector3(0f,0f,0f)

                fun updatePoints() {
                    if (Gdx.input.isTouched) {
                        input.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
                        camera.unproject(input)
                        bot.inputY = input.y
                    }
                }

                override fun touchDown(event: InputEvent, x: Float, y: Float, pointer: Int, button: Int): Boolean {
                    updatePoints()
                    return true
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
        camera.position.y = WORLD_HEIGHT / 2
        update(delta)
        // TODO: handle Game Over
        if (gameOver) {
            print("GAME OVER!")
        }
    }

    companion object {
        val WORLD_WIDTH = 408f
        val WORLD_HEIGHT = 272f
    }

    /**
     * Start the screen shaking with a given power and duration
     *
     * @param radius The starting radius for the shake. The larger the radius, the large the shaking effect.
     * @param duration Time in milliseconds the screen should shake.
     */
    fun shake(radius: Float, duration: Float) {
        this.elapsed = 0f
        this.duration = duration / 1000f
        this.radius = radius
        this.randomAngle = random.nextFloat() % 360f
    }

    /**
     * Updates the shake and the camera.
     * This must be called prior to camera.update()
     */
    private fun update(delta: Float) {
        if (!bot.isAlive() && !destroyAnimationPlayed) {
            shake(5f,2000f)
            destroyAnimationPlayed = true
        }
        if (elapsed < duration) {

            // Calculate the shake based on the remaining radius.
            radius *= 0.9f // diminish radius each frame
            randomAngle += 150 + random.nextFloat() % 60f
            val x = (Math.sin(randomAngle.toDouble()) * radius).toFloat()
            val y = (Math.cos(randomAngle.toDouble()) * radius).toFloat()
            camera.translate(-x, -y, 0f)

            // Increase the elapsed time by the delta provided.
            elapsed += delta
            // TODO: check why this code never fires
            if (elapsed >= duration)
                gameOver = true
        }
    }
}
