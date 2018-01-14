package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor

import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.Color.*
import io.github.fantasylum.rgbot.resid.*

class Bot(var velocity: Float): Actor(), Collider {
    private val textures = mapOf(RED   to RGBot.getAnimation(Animations.BOT_RED),
                                 GREEN to RGBot.getAnimation(Animations.BOT_GREEN),
                                 BLUE  to RGBot.getAnimation(Animations.BOT_BLUE))
    private var color     = RED
    private var timeAlive = 0f

    init {
        val texture = textures[color]!!.getKeyFrame(0f)
        val width   = texture.regionWidth
        val height  = texture.regionHeight

        assert(textures.values
                       .flatMap { it.keyFrames.asIterable() }
                       .all { it.regionWidth == width && it.regionHeight == height } )

        setBounds(x, y, width.toFloat(), height.toFloat())
    }

    override fun act(delta: Float) {
        timeAlive += delta
        x += velocity * delta
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        val texture = textures[color]!!.getKeyFrame(timeAlive, true)
        batch.draw(texture, x, y)
    }

    override val topBound: Float
        get() = top
    override val bottomBound: Float
        get() = y
    override val leftBound: Float
        get() = x
    override val rightBound: Float
        get() = right
}
