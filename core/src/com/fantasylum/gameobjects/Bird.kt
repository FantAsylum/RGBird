package com.fantasylum.gameobjects

import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.fantasylum.utils.MyStack
import com.fantasylum.utils.TexturesLoader


class Bird(
        val position: Vector2,
        val width: Float,
        val height: Float) {

    var rotation: Float = 0.toFloat()
    val animations = MyStack<Animation<TextureRegion>>()
    var collider = Circle()

    private var velocity = Vector2(0f,0f)
    private var acceleration = Vector2(0f,460f)
    private val maxVelocity = 200f
    private val velocityOnClick = -140f
    private val redBirdTextures = Array<TextureRegion>()
    private var greenBirdTextures = Array<TextureRegion>()
    private var blueBirdTextures = Array<TextureRegion>()

    init {
        pushAnimations()
    }

    fun nextColor() {
        if (animations.count() > 0) {
            animations.pop()
        } else {
            pushAnimations()
        }
    }

    private fun pushAnimations() {
        redBirdTextures.add(TexturesLoader.instance.birdRedLowTexture)
        redBirdTextures.add(TexturesLoader.instance.birdRedMidTexture)
        redBirdTextures.add(TexturesLoader.instance.birdRedHighTexture)

        greenBirdTextures.add(TexturesLoader.instance.birdGreenLowTexture)
        greenBirdTextures.add(TexturesLoader.instance.birdGreenMidTexture)
        greenBirdTextures.add(TexturesLoader.instance.birdGreenHighTexture)

        blueBirdTextures.add(TexturesLoader.instance.birdBlueLowTexture)
        blueBirdTextures.add(TexturesLoader.instance.birdBlueMidTexture)
        blueBirdTextures.add(TexturesLoader.instance.birdBlueHighTexture)

        val redBirdAnimation = Animation(0.06f,redBirdTextures)
        val greenBirdAnimation = Animation(0.06f,greenBirdTextures)
        val blueBirdAnimation = Animation(0.06f,blueBirdTextures)

        redBirdAnimation.playMode = Animation.PlayMode.LOOP_PINGPONG
        greenBirdAnimation.playMode = Animation.PlayMode.LOOP_PINGPONG
        blueBirdAnimation.playMode = Animation.PlayMode.LOOP_PINGPONG

        animations.push(greenBirdAnimation)
        animations.push(blueBirdAnimation)
        animations.push(redBirdAnimation)
    }

    fun update(delta: Float) {
        velocity.add(acceleration.cpy().scl(delta))
        if (velocity.y > maxVelocity) {
            velocity.y = maxVelocity
        }
        position.add(velocity.cpy().scl(delta))
        collider.set(position.x + 9, position.y + 6, 6.5f)
        if (velocity.y < 0) {
            rotation -= 600 * delta

            if (rotation < -20) {
                rotation = -20f
            }
        }
        if (isFalling()) {
            rotation += 480 * delta
            if (rotation > 90) {
                rotation = 90f
            }

        }
    }

    private fun isFalling(): Boolean {
        return velocity.y > 110
    }

    fun onClick() {
        velocity.y = velocityOnClick
    }
}
