package com.fantasylum.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion

class TexturesLoader {
    private val textureAtlas = TextureAtlas(Gdx.files.internal("graphics/atlas.atlas"))

    val birdRedTexture : TextureRegion = textureAtlas.findRegion("bird_red")

    private object Holder { val INSTANCE = TexturesLoader() }

    init {
        birdRedTexture.flip(false,true)
    }

    companion object {
        val instance: TexturesLoader by lazy { Holder.INSTANCE }
    }
}