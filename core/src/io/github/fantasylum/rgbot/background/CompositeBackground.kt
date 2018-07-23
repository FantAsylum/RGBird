package io.github.fantasylum.rgbot.background

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.utils.Array as GdxArray

class CompositeBackground(vararg parts: Part) {
    val parts = GdxArray(parts)
    
    interface Part {
	fun draw(xPos: Float)
    }

    fun draw(xPos: Float) {
	for (part in parts) {
	    part.draw(xPos)
	}
    }
}
