package com.tennis.scoring.service

import com.tennis.scoring.model.Game
import com.tennis.scoring.model.GameState
import com.tennis.scoring.model.Player
import kotlin.math.abs

class ScoreService {

    fun pointWonBy(game: Game, playerName: String) {
        if (game.state is GameState.Finished) return

        val player = when (playerName) {
            game.player1.name -> game.player1
            game.player2.name -> game.player2
            else -> null
        }

        if (player != null) {
            player.score++
            updateGameState(game)
        } else {
            println("It does not exist a player with that name: $playerName")
        }
    }

    fun getScore(game: Game): String {
        return when (val state = game.state) {
            is GameState.LoveLove -> "Love-Love"
            is GameState.Regular -> "${mapPoints(game.player1.score)} - ${mapPoints(game.player2.score)}"
            is GameState.Deuce -> "Deuce"
            is GameState.Advantage -> "${state.player.name} has advantage"
            is GameState.Finished -> "Results: The winner is ${state.winner.name}"
        }
    }

    fun resetGame(game: Game) {
        game.player1.score = 0
        game.player2.score = 0
        println("Resetting the game...")
        game.state = GameState.LoveLove
    }

    private fun updateGameState(game: Game) {
        val scorePlayer1 = game.player1.score
        val scorePlayer2 = game.player2.score

        if (scorePlayer1 == 0 && scorePlayer2 == 0) {
            game.state = GameState.LoveLove
            return
        }

        if (scorePlayer1 == 3 && scorePlayer2 == 3) {
            game.state = GameState.Deuce
            return
        }

        if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            val diff = scorePlayer1 - scorePlayer2
            game.state = when {
                diff == 0 -> GameState.Deuce
                diff == 1 -> GameState.Advantage(game.player1)
                diff == -1 -> GameState.Advantage(game.player2)
                abs(diff) >= 2 -> GameState.Finished(winner(game))
                else -> GameState.Regular
            }
        } else {
            game.state = GameState.Regular
        }
    }

    private fun winner(game: Game): Player {
        return if (game.player1.score > game.player2.score) game.player1 else game.player2
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
