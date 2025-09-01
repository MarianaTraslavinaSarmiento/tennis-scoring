package com.tennis.scoring

import com.tennis.scoring.model.Game
import com.tennis.scoring.model.Player
import com.tennis.scoring.service.ScoreService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GameTest {

    private val scoreService = ScoreService()

    private fun createGame(): Game {
        return Game(Player("Player 1"), Player("Player 2"))
    }

    @Test
    fun `the initial score of the game is expected to be Love-Love`() {
        val game = createGame()
        assertEquals("Love-Love", scoreService.getScore(game))
    }

}