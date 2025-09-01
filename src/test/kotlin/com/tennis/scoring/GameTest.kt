package com.tennis.scoring

import com.tennis.scoring.model.Game
import com.tennis.scoring.model.Player
import com.tennis.scoring.service.ScoreService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GameTest {

    private val scoreService = ScoreService()

    private fun newGame(): Game {
        return Game(Player("Player 1"), Player("Player 2"))
    }

    @Test
    fun `the initial score of the game is expected to be Love-Love`() {
        val game = newGame()
        assertEquals("Love-Love", scoreService.getScore(game))
    }

    @Test
    fun `the score updates correctly when a player scores`() {
        val game = newGame()
        scoreService.pointWonBy(game, "Player 1")
        assertEquals("15 - Love", scoreService.getScore(game))
    }

    @Test
    fun `score should be 40-30`() {
        val game = newGame()
        repeat(3) { scoreService.pointWonBy(game, "Player 1") }
        repeat(2) { scoreService.pointWonBy(game, "Player 2") }
        assertEquals("40 - 30", scoreService.getScore(game))
    }

    @Test
    fun `game should reach Deuce`() {
        val game = newGame()
        repeat(3) { scoreService.pointWonBy(game, "Player 1") }
        repeat(3) { scoreService.pointWonBy(game, "Player 2") }
        assertEquals("Deuce", scoreService.getScore(game))
    }
















}