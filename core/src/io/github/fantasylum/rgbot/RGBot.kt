package io.github.fantasylum.rgbot

import java.util.LinkedList

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.scenes.scene2d.ui.Skin

import io.github.fantasylum.rgbot.resid.ATLAS
import io.github.fantasylum.rgbot.screens.MenuScreen

object RGBot : ApplicationAdapter() {
    private val ANIMATION_FRAME_DURATION = 0.1f

    private val screenStack = LinkedList<Screen>()
    private val assetManager by lazy { AssetManager() }
    private val atlas by lazy {
        with (assetManager) {
            load(ATLAS, TextureAtlas::class.java)
            finishLoading()
            get<TextureAtlas>(ATLAS)
        }
    }
    val fireAnimation by lazy {
        with (assetManager) {
            load("effects/fire.p", ParticleEffect::class.java)
            finishLoading()
            get<ParticleEffect>("effects/fire.p")
        }
    }
    val explosionAnimation by lazy {
        with (assetManager) {
            load("effects/explosion.p", ParticleEffect::class.java)
            finishLoading()
            get<ParticleEffect>("effects/explosion.p")
        }
    }

    override fun create() {
        val animationScaleFactor = Gdx.graphics.width.toFloat() / 640f
        fireAnimation.scaleEffect(animationScaleFactor)
        pushScreen(MenuScreen())
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        screenStack.peek().render(Gdx.graphics.deltaTime)
    }

    override fun dispose() {
        assetManager.dispose()
    }

    fun pushScreen(screen: Screen) {
        screenStack.push(screen)
    }

    fun popScreen() {
        screenStack.pop()
        if (screenStack.isEmpty())
            Gdx.app.exit()
    }

    fun replaceScreen(screen: Screen) {
        screenStack.pop()
        screenStack.push(screen)
    }

    fun getSkin(id: String): Skin {
        assetManager.load(id, Skin::class.java)
        assetManager.finishLoading()
        return assetManager.get(id)
    }

    fun getAnimation(id: String) = Animation(ANIMATION_FRAME_DURATION, atlas.findRegions(id))

}
