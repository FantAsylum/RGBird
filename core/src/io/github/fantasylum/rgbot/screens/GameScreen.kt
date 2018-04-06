package io.github.fantasylum.rgbot.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Stage
import io.github.fantasylum.rgbot.Color

import io.github.fantasylum.rgbot.actors.Bot
import io.github.fantasylum.rgbot.actors.Obstacle

class GameScreen: ScreenAdapter() {
    private val mainStage       = Stage()
    private val bot             = Bot(BOT_VELOCITY)

    // TODO: stub logic, implement automatic generation
    private val obstacle        = Obstacle(listOf(
            Obstacle.Part(Color.RED, 0.33f),
            Obstacle.Part(Color.GREEN, 0.33f),
            Obstacle.Part(Color.BLUE, 0.34f)))

    init {
        mainStage.addActor(bot)
        mainStage.addActor(obstacle)
        bot.x = mainStage.width  / 2f
        bot.y = mainStage.height / 2f

        obstacle.x = mainStage.width / 1.5f
        obstacle.y = mainStage.height / 1.5f
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
