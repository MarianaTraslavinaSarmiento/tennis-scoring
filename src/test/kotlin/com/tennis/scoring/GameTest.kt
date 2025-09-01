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
    fun `when the game starts, then the score should be Love-Love`() {
        val game = newGame()
        assertEquals("Love-Love", scoreService.getScore(game))
    }

    @Test
    fun `when a player scores a point, then the score updates accordingly`() {
        val game = newGame()
        scoreService.pointWonBy(game, "Player 1")
        assertEquals("15 - Love", scoreService.getScore(game))
    }

    @Test
    fun `when one player has 40 and the other has 30, then the score should reflect 40-30`() {
        val game = newGame()
        repeat(3) { scoreService.pointWonBy(game, "Player 1") }
        repeat(2) { scoreService.pointWonBy(game, "Player 2") }
        assertEquals("40 - 30", scoreService.getScore(game))
    }

    @Test
    fun `when both players have 40 points, then the game should be Deuce`() {
        val game = newGame()
        repeat(3) { scoreService.pointWonBy(game, "Player 1") }
        repeat(3) { scoreService.pointWonBy(game, "Player 2") }
        assertEquals("Deuce", scoreService.getScore(game))
    }

    @Test
    fun `when Player 1 scores after Deuce, then the game should go to Player 1 has advantage`() {
        val game = newGame()
        repeat(3) { scoreService.pointWonBy(game, "Player 1") }
        repeat(3) { scoreService.pointWonBy(game, "Player 2") }
        scoreService.pointWonBy(game, "Player 1")
        assertEquals("Player 1 has advantage", scoreService.getScore(game))
    }

    @Test
    fun `when Player 1 loses Advantage, then the game should return to Deuce`() {
        val game = newGame()
        repeat(3) { scoreService.pointWonBy(game, "Player 1") }
        repeat(3) { scoreService.pointWonBy(game, "Player 2") }
        scoreService.pointWonBy(game, "Player 1")
        scoreService.pointWonBy(game, "Player 2")
        assertEquals("Deuce", scoreService.getScore(game))
    }






























}