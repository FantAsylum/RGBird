package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.scenes.scene2d.Actor

interface Collider {
    val topBound:    Float
    val bottomBound: Float
    val rightBound:  Float
    val leftBound:   Float
    
    infix fun collidesWith(other: Collider): Boolean {
        fun Collider.withinBounds(x: Float, y: Float) =
            x > leftBound
         && x < rightBound
         && y < topBound
         && y > bottomBound

        return other.withinBounds(leftBound, topBound)
            || other.withinBounds(rightBound, bottomBound)
    }

}

