package io.github.fantasylum.rgbot.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.resid.ATLAS

object GameScreenAssets {

    private val atlas = RGBot.assetManager.get(ATLAS, TextureAtlas::class.java)
    val fireAnimation = RGBot.assetManager.get("effects/fire.p", ParticleEffect::class.java)
    val explosionAnimation = RGBot.assetManager.get("effects/explosion.p", ParticleEffect::class.java)

    private val animationScaleFactor = Gdx.graphics.width.toFloat() / 640f
    private val ANIMATION_FRAME_DURATION = 0.1f

    init {
        fireAnimation.scaleEffect(animationScaleFactor)
    }

    fun getAnimation(id: String) = Animation(ANIMATION_FRAME_DURATION, atlas.findRegions(id))

    fun getTexture(id: String) = TextureRegion(atlas.findRegion(id))
}