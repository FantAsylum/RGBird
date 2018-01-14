package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage

import io.github.fantasylum.rgbot.actors.Bot

class GameScreen: ScreenAdapter() {
    private val mainStage       = Stage()
    private val bot             = Bot(BOT_VELOCITY)

    init {
        mainStage.addActor(bot)
        bot.x = mainStage.width  / 2f
        bot.y = mainStage.height / 2f
        Gdx.input.inputProcessor = mainStage
    }

    override fun render(delta: Float) {
        mainStage.act(delta)
        mainStage.draw()
    }

    companion object {
        val BOT_VELOCITY = 10f
    }
}
