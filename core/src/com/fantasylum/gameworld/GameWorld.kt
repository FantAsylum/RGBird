package com.fantasylum.gameworld

import com.badlogic.gdx.math.Vector2
import com.fantasylum.gameobjects.Bird
import com.fantasylum.gameobjects.ScrollHandler

class GameWorld(private val midPointY : Float) {

    val bird = Bird(Vector2(33f,midPointY - 5f), 17f, 12f)
    val scroller = ScrollHandler(midPointY + 66)

    fun update(delta : Float) {
        bird.update(delta)
        scroller.update(delta)
    }

}