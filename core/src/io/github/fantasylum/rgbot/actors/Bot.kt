package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import io.github.fantasylum.rgbot.Color

import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.Color.*
import io.github.fantasylum.rgbot.resid.*

class Bot(velocity: Float = DEFAULT_HORIZONTAL_VELOCITY,
          val minHeight: Float = 0f,
          val maxHeight: Float = Obstacle.HEIGHT,
          private val nextColor: (Color) -> Color = { Color.values()[(it.ordinal + 1) % Color.values().size] }): Actor() {
    private val textures = mapOf(RED   to RGBot.getAnimation(Animations.BOT_RED),
                                 GREEN to RGBot.getAnimation(Animations.BOT_GREEN),
                                 BLUE  to RGBot.getAnimation(Animations.BOT_BLUE))
    var color = GREEN
        private set

    private val velocity  = Vector2(velocity, 0f)
    private var timeAlive = 0f
    private var alive     = true

    init {
        width   = WIDTH
        height  = HEIGHT

        assert(textures.values
                       .flatMap { it.keyFrames.asIterable() }
                       .all { it.regionWidth == width.toInt() && it.regionHeight == height.toInt() } )
    }

    override fun act(delta: Float) {
        timeAlive += delta

        if (alive) {
            x += velocity.x * delta
            y += velocity.y * delta
        } else {
            // TODO: add more realistic death handling
            y -= velocity.x * delta
        }

        velocity.y = MathUtils.clamp(velocity.y + VERTICAL_ACCELERATION * delta, MIN_VERTICAL_VELOCITY, MAX_VERTICAL_VELOCITY)
        y = MathUtils.clamp(y, minHeight, maxHeight)
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        val texture = textures[color]!!.getKeyFrame(timeAlive, true)
        batch.draw(texture, x, y, width, height)
    }

    fun changeColor() {
        color = nextColor(color)
    }

    fun destroy() {
        alive = false
    }

    fun moveUp() {
        velocity.y = MOVE_UP_VELOCITY
    }

    companion object {
        val WIDTH = 30f
        val HEIGHT = 30f

        private const val DEFAULT_HORIZONTAL_VELOCITY = 60f
        private const val VERTICAL_ACCELERATION       = -250f
        private const val MOVE_UP_VELOCITY            = 100f
        private val MIN_VERTICAL_VELOCITY             = -100f
        private val MAX_VERTICAL_VELOCITY             = 400f
    }
}
