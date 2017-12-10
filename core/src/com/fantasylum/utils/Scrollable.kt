package com.fantasylum.utils

import com.badlogic.gdx.math.Vector2


open class Scrollable(private val x : Float,
                 private val y: Float,
                 val width: Float,
                 val heigth: Float,
                 private val scrollSpeed: Float) {

    val position = Vector2(x,y)
    private val velocity = Vector2(scrollSpeed,0f)
    var isScrolledLeft = false

    fun update(delta: Float) {
        position.add(velocity.cpy().scl(delta))
        if (position.x + width < 0) {
            isScrolledLeft = true
        }
    }

}