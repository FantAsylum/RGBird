package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.math.MathUtils
import io.github.fantasylum.rgbot.Color


/**
 * Bot implementation with more simple controls. It can be only in three positions: up, middle and down.
 * @param upHeight fixed height for up bot position
 * @param downHeight fixed height for down bot position
 */
class SimpleBot(upHeight: Float,
                middleHeight: Float,
                downHeight: Float,
                velocity: Float = Bot.DEFAULT_HORIZONTAL_VELOCITY,
                nextColor: (Color) -> Color = Bot.defaultNextColor) : Bot(velocity, nextColor) {

    val upHeight     = upHeight + HEIGHT / 2
    val middleHeight = middleHeight - HEIGHT / 2
    val downHeight   = downHeight - HEIGHT / 2 * 3

    init {
        assert(upHeight > middleHeight)
        assert(middleHeight > downHeight)
    }

    var isMovingUp: Boolean   = false
    var isMovingDown: Boolean = false

    override fun act(delta: Float) {
        val bound: Float
        when {
            isMovingUp && !isMovingDown -> {
                velocity.y = VERTICAL_VELOCITY
                bound = upHeight
            }
            isMovingDown && !isMovingUp -> {
                velocity.y = -VERTICAL_VELOCITY
                bound = downHeight
            }
            y < middleHeight            -> {
                velocity.y = VERTICAL_VELOCITY
                bound = middleHeight
            }
            y > middleHeight            -> {
                velocity.y = -VERTICAL_VELOCITY
                bound = middleHeight
            }
            else                        -> {
                velocity.y = 0f
                bound = middleHeight
            }
        }
        super.act(delta)

        if (velocity.y > 0 && y > bound || velocity.y < 0 && y < bound) {
            y = bound
            velocity.y = 0f
        }
    }

    companion object {
        val VERTICAL_VELOCITY = 200f
    }
}