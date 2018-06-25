package io.github.fantasylum.rgbot

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.Screen
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.ParticleEffect
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.ui.Skin

import io.github.fantasylum.rgbot.resid.ATLAS
import io.github.fantasylum.rgbot.screens.LoadingScreen
import io.github.fantasylum.rgbot.screens.MenuScreen
import java.util.*

object RGBot : ApplicationAdapter() {

    private val screenStack = LinkedList<Screen>()
    val assetManager = AssetManager()
    private val scanLineWidth = 1f
    private val rand = Random()

    override fun create() {
        pushScreen(LoadingScreen())
        Gdx.gl.glLineWidth(scanLineWidth)
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)
        screenStack.peek().render(Gdx.graphics.deltaTime)
        Gdx.gl.glEnable(GL20.GL_BLEND)
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA)
        drawTvScanLines()
        Gdx.gl.glDisable(GL20.GL_BLEND)
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

    private fun drawTvScanLines() {
        val shapeRenderer = ShapeRenderer()
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line)
        for (i in 0..Gdx.graphics.height) {
            if (i % (scanLineWidth.toInt() + 1) == 0) {
                val alphaAddition = rand.nextInt(5..10).toFloat() / 100
                shapeRenderer.color = Color(0f, 0f, 0f, 0.1f + alphaAddition)
                shapeRenderer.line(Vector2(0f, i.toFloat()), Vector2(Gdx.graphics.width.toFloat(), i.toFloat()))
            }
        }
        shapeRenderer.end()
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

}
