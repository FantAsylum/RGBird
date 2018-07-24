package io.github.fantasylum.rgbot.background

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle

import io.github.fantasylum.rgbot.util.GameScreenAssets

class ParallaxPart(private val velocityRatio: Float,
                   private val bounds: Rectangle,
                   textureName: String) : CompositeBackground.Part {
    private val batch = SpriteBatch()
    private val texture = GameScreenAssets.getTexture(textureName)
    private val beginX = texture.regionX
    private val regionWidth = texture.regionWidth
    private val ratio = texture.regionWidth.toFloat() / texture.regionHeight.toFloat()
    private val visibleWidth = ((bounds.width * texture.regionHeight) / bounds.height).toInt()
    override fun draw(xPos: Float) {
        val x = ((beginX + xPos * velocityRatio) % bounds.width).toInt()

        batch.begin()
        if (x + visibleWidth < regionWidth) {
            texture.setRegion(x, texture.regionY, visibleWidth, texture.regionHeight)
            batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height)
        } else {
            val lastPortion = regionWidth - x
            val ratio = lastPortion.toFloat() / visibleWidth.toFloat()
            texture.setRegion(x, texture.regionY, lastPortion, texture.regionHeight)
            batch.draw(texture, bounds.x, bounds.y, bounds.width * ratio, bounds.height)
            texture.setRegion(beginX, texture.regionY, visibleWidth - lastPortion, texture.regionHeight)
            batch.draw(texture, bounds.x + (bounds.width * ratio), bounds.y, bounds.width * (1f - ratio), bounds.height)
        }
        batch.end()
    }
}
