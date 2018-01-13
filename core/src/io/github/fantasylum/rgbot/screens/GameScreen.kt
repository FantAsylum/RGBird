package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage

class GameScreen: ScreenAdapter() {
    private val mainStage = Stage()

    init {
        Gdx.input.inputProcessor = mainStage
    }
}
