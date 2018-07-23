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
		       
    override fun draw(xPos: Float) {
        val x = ((beginX + xPos * velocityRatio) % bounds.width).toInt()
        texture.setRegion(x, texture.regionY, regionWidth - x, texture.regionHeight)
        batch.begin()
        batch.draw(texture, bounds.x, bounds.y, bounds.width, bounds.height)
        batch.end()
    }
}
