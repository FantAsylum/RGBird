package com.fantasylum.gameobjects

import com.badlogic.gdx.math.Vector2

class Bird(
        val x: Float,
        val y: Float,
        val width: Float,
        val height: Float) {

    private var position = Vector2(x,y)
    private var velocity = Vector2(0f,0f)
    private var acceleration = Vector2(0f,460f)
    private var rotation: Float = 0.toFloat()

    private val maxVelocity = 200f
    private val velocityOnClick = -140f

    fun update(delta: Float) {
        velocity.add(acceleration.cpy().scl(delta))
        if (velocity.y > maxVelocity) {
            velocity.y = maxVelocity
        }
        position.add(velocity.cpy().scl(delta))
    }

    fun onClick() {
        velocity.y = velocityOnClick
    }
}
