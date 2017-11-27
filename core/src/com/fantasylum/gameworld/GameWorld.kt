package com.fantasylum.gameworld

import com.fantasylum.gameobjects.Bird

class GameWorld(private val midPointY : Float) {

    private val bird = Bird(33f, midPointY - 5f, 17f, 12f)

    fun update(delta : Float) {
        bird.update(delta)
    }

}