package com.fantasylum.gameobjects

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.fantasylum.utils.MyStack
import com.fantasylum.utils.TexturesLoader
import java.util.*

class Bird(
        val position: Vector2,
        val width: Float,
        val height: Float) {


    private var velocity = Vector2(0f,0f)
    private var acceleration = Vector2(0f,460f)
    private var rotation: Float = 0.toFloat()

    private val maxVelocity = 200f
    private val velocityOnClick = -140f
    val textures = MyStack<TextureRegion>()

    init {
        textures.push(TexturesLoader.instance.birdBlueTexture)
        textures.push(TexturesLoader.instance.birdGreenTexture)
        textures.push(TexturesLoader.instance.birdRedTexture)
    }

    fun nextTexture() {
        if (textures.count() > 0) {
            textures.pop()
        } else {
            textures.push(TexturesLoader.instance.birdBlueTexture)
            textures.push(TexturesLoader.instance.birdGreenTexture)
            textures.push(TexturesLoader.instance.birdRedTexture)
        }
    }

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
