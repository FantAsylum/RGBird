package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.Interpolation
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.TimeUtils
import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.resid.ATLAS
import com.badlogic.gdx.utils.TimeUtils.millis


class LoadingScreen : Screen {

    private var percent = 0f
    private val batch = SpriteBatch()
    private var logo : Texture? = null
    private val logoSize = Gdx.graphics.height / 4f
    private val logoPosition = Vector2(Gdx.graphics.width / 2f - logoSize / 2,Gdx.graphics.height / 2f - logoSize / 2)
    private var start = TimeUtils.millis()
    private var deltaTime : Long = 0

    init {
        logo = Texture(Gdx.files.internal("graphics/logo_white.png"))
        setResourcesToLoad()
    }

    override fun show() {
    }

    override fun hide() {
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0 / 255f, 0 / 255f, 0 / 255f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        percent = Interpolation.linear.apply(percent, RGBot.assetManager.progress, 0.1f)
        //println("Loaded: " + percent)
        batch.begin()
        batch.draw(logo,logoPosition.x, logoPosition.y, logoSize, logoSize)
        batch.end()
        deltaTime = TimeUtils.timeSinceMillis(start)
        if (RGBot.assetManager.update() && deltaTime > 1000) {
            RGBot.pushScreen(MenuScreen())
        }
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun dispose() {
        logo!!.dispose()
    }

    private fun setResourcesToLoad() {
        RGBot.assetManager.apply {
            load(ATLAS, TextureAtlas::class.java)
            load("effects/fire.p", ParticleEffect::class.java)
            load("effects/explosion.p", ParticleEffect::class.java)
        }
    }

}