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
    val fireAnimationRED = RGBot.assetManager.get("effects/fire_red.p", ParticleEffect::class.java)
    val fireAnimationGREEN = RGBot.assetManager.get("effects/fire_green.p", ParticleEffect::class.java)
    val fireAnimationBLUE = RGBot.assetManager.get("effects/fire_blue.p", ParticleEffect::class.java)
    val explosionAnimation = RGBot.assetManager.get("effects/explosion.p", ParticleEffect::class.java)

    private val animationScaleFactor = Gdx.graphics.width.toFloat() / 640f
    private val ANIMATION_FRAME_DURATION = 0.1f

    init {
        fireAnimationRED.scaleEffect(animationScaleFactor)
        fireAnimationGREEN.scaleEffect(animationScaleFactor)
        fireAnimationBLUE.scaleEffect(animationScaleFactor)
    }

    fun getAnimation(id: String) = Animation(ANIMATION_FRAME_DURATION, atlas.findRegions(id))

    fun getTexture(id: String) = TextureRegion(atlas.findRegion(id))
}