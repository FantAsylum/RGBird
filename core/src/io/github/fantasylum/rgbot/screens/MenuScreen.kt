package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.utils.viewport.FitViewport

import io.github.fantasylum.rgbot.RGBot
import io.github.fantasylum.rgbot.resid.*
import io.github.fantasylum.rgbot.screens.GameScreen
import io.github.fantasylum.rgbot.util.setOnClickListener

class MenuScreen : ScreenAdapter() {
    private val skin          = RGBot.getSkin(UI_SKIN)
    private val stage         = Stage(FitViewport(GameScreen.WORLD_WIDTH, GameScreen.WORLD_HEIGHT))
    private val table         = Table()
    private val title         = Label("RGBot", skin)
    private val newGameButton = TextButton("New Game", skin)
    private val exitButton    = TextButton("Exit", skin)

    init {
        table.setFillParent(true)

        title.setFontScale(2f)
        table.add(title).padBottom(50f).row();
        table.add(newGameButton).row();
        table.add(exitButton).fillX().padBottom(50f).row();
        table.add()
        stage.addActor(table)

        newGameButton.setOnClickListener { RGBot.pushScreen(GameScreen()) }
        exitButton.setOnClickListener { Gdx.app.exit() }
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        stage.act(delta)
        stage.draw()
    }
}
