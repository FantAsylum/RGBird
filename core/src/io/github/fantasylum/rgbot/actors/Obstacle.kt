package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import io.github.fantasylum.rgbot.Color
import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.resid.Animations
import io.github.fantasylum.rgbot.resid.Animations.OBSTACLE_BLUE
import io.github.fantasylum.rgbot.resid.Animations.OBSTACLE_GREEN
import io.github.fantasylum.rgbot.resid.Animations.OBSTACLE_RED

/**
 * Represents obstacle containing three (red, greed, blue) part
 * When bot collides with one of it, there are two possible outcomes:
 * 1. Bot is the same color, part of obstacle is: obstacle is deactivated
 * 2. Otherwise bot is destroyed
 * @param parts proportions and sequence of obstacle parts.
 *                    Must include all three colors. And proportions
 *                    sum must be 1
 */
class Obstacle(private val parts: List<Part>): Actor() {
    init {
        assert(parts.distinctBy { it.color }.size == 3)
        assert(MathUtils.isZero(parts.fold(0f, { acc, part -> acc + part.proportion }) - 1f))
        height = HEIGHT
        width = WIDTH
    }

    private val textures = mapOf(
            Color.RED to RGBot.getAnimation(Animations.OBSTACLE_RED),
            Color.GREEN to RGBot.getAnimation(Animations.OBSTACLE_GREEN),
            Color.BLUE to RGBot.getAnimation(Animations.OBSTACLE_BLUE))

    private var timeAlive = 0f

    override fun act(delta: Float) {
        timeAlive += delta
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        var startY = y

        fun Part.drawPart() {
            val partHeight = height * proportion
            batch.draw(
                    textures[color]!!.getKeyFrame(timeAlive, true),
                    x,
                    startY,
                    width,
                    partHeight)
            startY -= partHeight
        }

        parts.forEach { it.drawPart() }

    }

    data class Part(val color: Color, val proportion: Float)


    companion object {
        private val HEIGHT = 300f
        private val WIDTH  = 10f
    }
}