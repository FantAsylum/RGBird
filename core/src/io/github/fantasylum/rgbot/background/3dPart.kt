package io.github.fantasylum.rgbot.background

import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.ModelBatch
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder
import com.badlogic.gdx.math.Rectangle

class BackgroundPart(private val bounds: Rectangle,
                     textureName: String): CompositeBackground.Part {

    private val batch = ModelBatch()
    private val camera = PerspectiveCamera(67f, bounds.width.toFloat(), bounds.height.toFloat());
    private val model = {
        val builder = ModelBuilder()
        builder.begin()

        // TODO: implement model creation
        builder.end()
    }()
                         
    override fun draw(xPos: Float) {
        // TODO: implement
    }
}
