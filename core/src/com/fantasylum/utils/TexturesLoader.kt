package com.fantasylum.utils

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import javafx.animation.Animation

class TexturesLoader {
    private val textureAtlas = TextureAtlas(Gdx.files.internal("graphics/atlas.atlas"))

    val birdRedLowTexture : TextureRegion = textureAtlas.findRegion("bird_red_low")
    val birdRedMidTexture : TextureRegion = textureAtlas.findRegion("bird_red_mid")
    val birdRedHighTexture : TextureRegion = textureAtlas.findRegion("bird_red_high")

    val birdGreenLowTexture : TextureRegion = textureAtlas.findRegion("bird_green_low")
    val birdGreenMidTexture : TextureRegion = textureAtlas.findRegion("bird_green_mid")
    val birdGreenHighTexture : TextureRegion = textureAtlas.findRegion("bird_green_high")

    val birdBlueLowTexture : TextureRegion = textureAtlas.findRegion("bird_blue_low")
    val birdBlueMidTexture : TextureRegion = textureAtlas.findRegion("bird_blue_mid")
    val birdBlueHighTexture : TextureRegion = textureAtlas.findRegion("bird_blue_high")

    private object Holder { val INSTANCE = TexturesLoader() }

    init {
        birdRedLowTexture.flip(false,true)
        birdRedMidTexture.flip(false,true)
        birdRedHighTexture.flip(false,true)

        birdGreenLowTexture.flip(false,true)
        birdGreenMidTexture.flip(false,true)
        birdGreenHighTexture.flip(false,true)

        birdBlueLowTexture.flip(false,true)
        birdBlueMidTexture.flip(false,true)
        birdBlueHighTexture.flip(false,true)
    }

    companion object {
        val instance: TexturesLoader by lazy { Holder.INSTANCE }
    }
}