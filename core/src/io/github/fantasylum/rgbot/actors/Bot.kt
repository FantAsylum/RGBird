package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import io.github.fantasylum.rgbot.Color

import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.Color.*
import io.github.fantasylum.rgbot.resid.*

abstract class Bot(velocity: Float = DEFAULT_HORIZONTAL_VELOCITY,
                   private val nextColor: (Color) -> Color = defaultNextColor): Actor() {
    private val textures = mapOf(RED   to RGBot.getAnimation(Animations.BOT_RED),
                                 GREEN to RGBot.getAnimation(Animations.BOT_GREEN),
                                 BLUE  to RGBot.getAnimation(Animations.BOT_BLUE))
    var color = GREEN
        private set

    private var timeAlive = 0f
    protected val velocity  = Vector2(velocity, 0f)
    protected var alive     = true

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
        }
    // TODO: add more realistic death handling

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

    companion object {
        val defaultNextColor: (Color) -> Color = { Color.values()[(it.ordinal + 1) % Color.values().size] }

        val WIDTH = 30f
        val HEIGHT = 30f

        internal const val DEFAULT_HORIZONTAL_VELOCITY = 60f

    }
}