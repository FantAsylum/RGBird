package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Actor

class Bot: Actor() {
    private var color = BotColor.RED

    override fun draw(batch: Batch, parentAlpha: Float) {

    }

    private enum class BotColor {
        RED, GREEN, BLUE;

        fun next() = when (this) {
                         RED   -> GREEN
                         GREEN -> BLUE
                         BLUE  -> RED
                     }
    }
}
