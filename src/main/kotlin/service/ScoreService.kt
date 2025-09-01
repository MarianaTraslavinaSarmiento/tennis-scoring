package com.tennis.scoring.service

import com.tennis.scoring.model.Game
import com.tennis.scoring.model.GameState
import kotlin.math.abs

class ScoreService {

    fun pointWonBy(game: Game, playerName: String) {
        if (game.state == GameState.FINISHED) return

        val player = when (playerName) {
            game.player1.name -> game.player1
            game.player2.name -> game.player2
            else -> null
        }

        if (player != null) {
            player.score++
            updateGameState(game)
        } else {
            println("It does not existe a player with that name: $playerName")
        }

    }

    fun getScore(game: Game): String {
        return when (game.state) {
            GameState.LOVE_LOVE -> "Love-Love"
            GameState.REGULAR -> "${mapPoints(game.player1.score)} - ${mapPoints(game.player2.score)}"
            GameState.DEUCE -> "Deuce"
            GameState.ADVANTAGE_PLAYER1 -> "${game.player1.name} has advantage"
            GameState.ADVANTAGE_PLAYER2 -> "${game.player2.name} has advantage"
            GameState.FINISHED -> "Results: The winner is ${winner(game)}"
        }
    }

    fun resetGame(game: Game) {
        game.player1.score = 0
        game.player2.score = 0
        game.state = GameState.LOVE_LOVE
    }

    private fun updateGameState(game: Game) {
        val scorePlayer1 = game.player1.score
        val scorePlayer2 = game.player2.score

        if (scorePlayer1 == 0 && scorePlayer2 == 0) {
            game.state = GameState.LOVE_LOVE
            return
        }

        if (scorePlayer1 == 3 && scorePlayer2 == 3) {
            game.state = GameState.DEUCE
            return
        }

        if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            val diff = scorePlayer1 - scorePlayer2
            game.state = when {
                diff == 0 -> GameState.DEUCE
                diff == 1 -> GameState.ADVANTAGE_PLAYER1
                diff == -1 -> GameState.ADVANTAGE_PLAYER2
                abs(diff) >= 2 -> GameState.FINISHED
                else -> GameState.REGULAR
            }
        } else {
            game.state = GameState.REGULAR
        }
    }

    private fun winner(game: Game): String {
        return if (game.player1.score > game.player2.score) game.player1.name else game.player2.name
    }

    private fun mapPoints(points: Int): String {
        return when (points) {
            0 -> "Love"
            1 -> "15"
            2 -> "30"
            3 -> "40"
            else -> "40"
        }
    }

}