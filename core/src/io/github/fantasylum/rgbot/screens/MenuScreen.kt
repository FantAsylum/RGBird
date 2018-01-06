package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table

import io.github.fantasylum.rgbot.resid.*
import io.github.fantasylum.rgbot.RGBot

class MenuScreen : ScreenAdapter() {
    private val stage = Stage()
    private val table = Table()
    private val title = Label("Stub", RGBot.getSkin(UI_SKIN))

    init {
        table.setFillParent(true)
        table.add(title)
        stage.addActor(table)
    }

    override fun show() {
        Gdx.input.inputProcessor = stage
    }
}
