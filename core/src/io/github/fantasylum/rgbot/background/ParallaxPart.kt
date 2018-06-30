package io.github.fantasylum.rgbot.background

import com.badlogic.gdx.math.Rectangle

import io.github.fantasylum.rgbot.util.GameScreenAssets

class ParallaxPart(private val velocityRatio: Double,
                   private val bounds: Rectangle,
                   textureName: String) : CompositeBackground.Part {
    private val texture = GameScreenAssets.getTexture(textureName)
		       
    override fun draw(xPos: Int) {
	// TODO: implement
    }
}
