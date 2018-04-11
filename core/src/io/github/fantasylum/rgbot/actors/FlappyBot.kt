package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.math.MathUtils
import io.github.fantasylum.rgbot.Color

class FlappyBot(velocity: Float = Bot.DEFAULT_HORIZONTAL_VELOCITY,
                val minHeight: Float = 0f,
                val maxHeight: Float = Obstacle.HEIGHT,
                private val nextColor: (Color) -> Color = Bot.defaultNextColor): Bot(velocity, nextColor) {

    override fun act(delta: Float) {
        super.act(delta)

        if (alive) {
            y += velocity.y * delta
        }

        velocity.y = MathUtils.clamp(velocity.y + VERTICAL_ACCELERATION * delta, MIN_VERTICAL_VELOCITY, MAX_VERTICAL_VELOCITY)
        y = MathUtils.clamp(y, minHeight, maxHeight)
    }

    fun moveUp() {
        velocity.y = MOVE_UP_VELOCITY
    }

    companion object {
        private const val VERTICAL_ACCELERATION        = -250f
        private const val MOVE_UP_VELOCITY             = 100f
        private val MIN_VERTICAL_VELOCITY              = -100f
        private val MAX_VERTICAL_VELOCITY              = 400f
    }
}