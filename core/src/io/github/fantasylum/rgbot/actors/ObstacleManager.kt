package io.github.fantasylum.rgbot.actors

import com.badlogic.gdx.scenes.scene2d.Stage
import io.github.fantasylum.rgbot.screens.GameScreen
import java.util.LinkedList
import java.util.Deque

class ObstacleManager(val startX: Float,
                      val interval: Float,
                      val bot: Bot,
                      val stage: Stage,
                      val height: Float = Obstacle.DEFAULT_HEIGHT,
                      val width: Float  = Obstacle.DEFAULT_WIDTH,
                      val generationCallback: () -> Obstacle = Obstacle.generateEven) {

    val obstacles: Deque<Obstacle> = LinkedList<Obstacle>()
    private var currentX = startX

    fun act() {
        if ((obstacles.peekLast()?.x ?: 0f) < stage.camera.position.x + stage.camera.viewportWidth)
            generateNewObstacle()

        obstacles.forEach { obstacle -> obstacle.checkCollision(bot) }
    }

    private fun generateNewObstacle() {
        // TODO: consider adding recycling references for minimize runtime allocations
        if (obstacles.size >= OBSTACLE_MAX_BUFFER_SIZE) {
            stage.actors.removeValue(obstacles.pollFirst(), true)
        }
        val obstacle = Obstacle.generateEven()
        obstacle.x = currentX + interval
        currentX = obstacle.x
        obstacles.offerLast(obstacle)
        stage.addActor(obstacle)
    }

    companion object {
        val OBSTACLE_MAX_BUFFER_SIZE = 5
    }
}