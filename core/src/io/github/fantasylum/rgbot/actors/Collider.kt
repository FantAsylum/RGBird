package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.scenes.scene2d.Actor

infix fun Actor.collidesWith(other: Actor): Boolean {
    fun Actor.withinBounds(x: Float, y: Float) =
            this.x < x
         && this.x + width > x
         && this.y < y
         && this.y + height > y

    return other.withinBounds(x, y) || other.withinBounds(x + width, y + height)
}
