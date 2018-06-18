package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.Gdx
import io.github.fantasylum.rgbot.Color

/**
 * Bot implementation, where movement is set to follow finger on the screen
 *
 */
class FingerBot(velocity: Float = Bot.DEFAULT_HORIZONTAL_VELOCITY,
                nextColor: (Color) -> Color = Bot.defaultNextColor) : Bot(velocity, nextColor) {

    val middleHeight = Gdx.graphics.height / 2f

    var inputY = Gdx.graphics.height / 2f

    override fun act(delta: Float) {
        val bound: Float
        when {
            inputY > y -> {
                velocity.y = VERTICAL_VELOCITY
                bound = inputY
            }
            inputY < y -> {
                velocity.y = -VERTICAL_VELOCITY
                bound = inputY
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
        val VERTICAL_VELOCITY = 300f
    }
}