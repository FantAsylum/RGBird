package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.Array as GdxArray
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.Actor
import io.github.fantasylum.rgbot.Color
import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.resid.Animations
import io.github.fantasylum.rgbot.screens.GameScreen
import io.github.fantasylum.rgbot.util.collides

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
        height = DEFAULT_HEIGHT
        width = DEFAULT_WIDTH
    }

    private val textures = mapOf(
            Color.RED to RGBot.getAnimation(Animations.OBSTACLE_RED),
            Color.GREEN to RGBot.getAnimation(Animations.OBSTACLE_GREEN),
            Color.BLUE to RGBot.getAnimation(Animations.OBSTACLE_BLUE))

    private val blockTextures = mapOf(
            Color.RED to RGBot.getAnimation(Animations.BLOCK_RED),
            Color.GREEN to RGBot.getAnimation(Animations.BLOCK_GREEN),
            Color.BLUE to RGBot.getAnimation(Animations.BLOCK_BLUE))

    private var timeAlive = 0f

    private var active = true

    override fun act(delta: Float) {
        timeAlive += delta
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        if (! active)
            return

        var startY = y

        // TODO: check proportion (last part is smaller than the others!)
        fun Part.drawPart() {
            val partHeight = height * proportion
            batch.draw(
                    textures[color]!!.getKeyFrame(timeAlive, true),
                    x,
                    startY,
                    width,
                    partHeight)
            // BOTTOM BLOCK
            batch.draw(
                    blockTextures[color]!!.getKeyFrame(timeAlive, true),
                    x - BLOCK_EXPAND_WIDTH / 2,
                    startY,
                    width + BLOCK_EXPAND_WIDTH,
                    BLOCK_HEIGHT
            )
            startY += partHeight
            // TOP BLOCK
            batch.draw(
                    blockTextures[color]!!.getKeyFrame(timeAlive, true),
                    x - BLOCK_EXPAND_WIDTH / 2,
                    startY - BLOCK_HEIGHT,
                    width + BLOCK_EXPAND_WIDTH,
                    BLOCK_HEIGHT
            )
        }

        parts.forEach { it.drawPart() }

    }


    fun checkCollision(bot: Bot) {
        if (!active)
            return

        if (this collides bot) {
            var startY = y

            fun Part.checkPart() {
                val partY = startY
                val partTop = partY + height * proportion
                if (bot.y < partTop && bot.top > partY) {
                    if (bot.color == color) {
                        bot.changeColor()
                        active = false
                    } else {
                        bot.destroy()
                    }
                }
                startY = partTop
            }

            parts.forEach { it.checkPart() }
        }
    }

    data class Part(val color: Color, val proportion: Float)


    companion object {
        val DEFAULT_HEIGHT = GameScreen.WORLD_HEIGHT
        val DEFAULT_WIDTH  = 10f
        val BLOCK_HEIGHT = 10f
        val BLOCK_EXPAND_WIDTH = 10f

        private val generationBuffer = GdxArray<Color>(Color.values().size)

        val generateEven: () -> Obstacle = {
            generationBuffer.addAll(*Color.values())
            fun next() = Part(generationBuffer.removeIndex(MathUtils.random(generationBuffer.size - 1)), 1f / 3)

            Obstacle(listOf(next(), next(), next()))
        }
    }
}